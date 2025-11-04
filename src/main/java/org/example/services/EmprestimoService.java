package org.example.services;

import org.example.dto.EmprestimoDTO;
import org.example.dto.RegistrarDevolucaoRequest;
import org.example.entities.Cliente;
import org.example.entities.Emprestimo;
import org.example.entities.Livro;
import org.example.enums.StatusEmprestimo;
import org.example.exceptions.ClienteNotFoundException;
import org.example.exceptions.EmprestimoNotFoundException;
import org.example.exceptions.LivroIndisponivelException;
import org.example.exceptions.LivroNotFoundException;
import org.example.mappers.EmprestimoMapper;
import org.example.repositories.ClienteRepository;
import org.example.repositories.EmprestimoRepository;
import org.example.repositories.LivroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmprestimoService {

    private static final BigDecimal MULTA_DIARIA_PADRAO = new BigDecimal("2.50");

    private final EmprestimoRepository emprestimoRepository;
    private final LivroRepository livroRepository;
    private final ClienteRepository clienteRepository;
    private final EmprestimoMapper emprestimoMapper;

    public EmprestimoService(EmprestimoRepository emprestimoRepository,
                             LivroRepository livroRepository,
                             ClienteRepository clienteRepository,
                             EmprestimoMapper emprestimoMapper) {
        this.emprestimoRepository = emprestimoRepository;
        this.livroRepository = livroRepository;
        this.clienteRepository = clienteRepository;
        this.emprestimoMapper = emprestimoMapper;
    }

    @Transactional
    public EmprestimoDTO registrarEmprestimo(EmprestimoDTO dto) {
        Livro livro = livroRepository.findById(dto.getLivroId())
                .orElseThrow(() -> new LivroNotFoundException("Livro não encontrado para o empréstimo."));

        if (livro.getEstoque() == null || livro.getEstoque() <= 0) {
            throw new LivroIndisponivelException("Livro sem unidades disponíveis para empréstimo.");
        }

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado para o empréstimo."));

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLivro(livro);
        emprestimo.setCliente(cliente);
        emprestimo.setDataEmprestimo(dto.getDataEmprestimo() != null ? dto.getDataEmprestimo() : LocalDate.now());
        if (dto.getDataDevolucaoPrevista() == null) {
            throw new IllegalArgumentException("A data de devolução prevista deve ser informada.");
        }
        emprestimo.setDataDevolucaoPrevista(dto.getDataDevolucaoPrevista());
        emprestimo.setStatus(StatusEmprestimo.ATIVO);
        emprestimo.setMulta(BigDecimal.ZERO);
        emprestimo.setBibliotecarioResponsavel(dto.getBibliotecarioResponsavel());

        livro.setEstoque(livro.getEstoque() - 1);

        Emprestimo salvo = emprestimoRepository.save(emprestimo);
        livroRepository.save(livro);

        return emprestimoMapper.toDTO(salvo);
    }

    @Transactional
    public EmprestimoDTO registrarDevolucao(Long emprestimoId, RegistrarDevolucaoRequest request) {
        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId)
                .orElseThrow(() -> new EmprestimoNotFoundException("Empréstimo não encontrado."));

        if (StatusEmprestimo.DEVOLVIDO.equals(emprestimo.getStatus()) || StatusEmprestimo.CANCELADO.equals(emprestimo.getStatus())) {
            throw new IllegalStateException("O empréstimo já foi finalizado.");
        }

        LocalDate dataDevolucaoReal = request.getDataDevolucaoReal() != null ? request.getDataDevolucaoReal() : LocalDate.now();
        emprestimo.setDataDevolucaoReal(dataDevolucaoReal);

        BigDecimal multa = calcularMulta(emprestimo.getDataDevolucaoPrevista(), dataDevolucaoReal);
        emprestimo.setMulta(multa);
        emprestimo.setStatus(StatusEmprestimo.DEVOLVIDO);

        Livro livro = emprestimo.getLivro();
        int estoqueAtual = livro.getEstoque() == null ? 0 : livro.getEstoque();
        livro.setEstoque(estoqueAtual + 1);

        Emprestimo salvo = emprestimoRepository.save(emprestimo);
        livroRepository.save(livro);

        return emprestimoMapper.toDTO(salvo);
    }

    @Transactional
    public List<EmprestimoDTO> listarPorCliente(Long clienteId, boolean apenasAtivos) {
        List<Emprestimo> emprestimos = apenasAtivos
                ? emprestimoRepository.findByClienteIdAndStatusIn(clienteId, EnumSet.of(StatusEmprestimo.ATIVO, StatusEmprestimo.ATRASADO))
                : emprestimoRepository.findByClienteId(clienteId);

        return atualizarStatusEAgrupar(emprestimos);
    }

    @Transactional
    public List<EmprestimoDTO> listarPorBibliotecario(String bibliotecario) {
        List<Emprestimo> emprestimos = emprestimoRepository.findByBibliotecarioResponsavelIgnoreCase(bibliotecario);
        return atualizarStatusEAgrupar(emprestimos);
    }

    @Transactional
    public List<EmprestimoDTO> listarAtrasados() {
        List<Emprestimo> emprestimos = emprestimoRepository.findByStatusInAndDataDevolucaoPrevistaBefore(
                EnumSet.of(StatusEmprestimo.ATIVO, StatusEmprestimo.ATRASADO), LocalDate.now());
        return atualizarStatusEAgrupar(emprestimos);
    }

    @Transactional
    public List<EmprestimoDTO> listarPorStatus(StatusEmprestimo status) {
        List<Emprestimo> emprestimos = status == null
                ? emprestimoRepository.findAll()
                : emprestimoRepository.findByStatus(status);
        return atualizarStatusEAgrupar(emprestimos);
    }

    private List<EmprestimoDTO> atualizarStatusEAgrupar(List<Emprestimo> emprestimos) {
        return emprestimos.stream()
                .map(this::atualizarStatusSeAtrasado)
                .map(emprestimoMapper::toDTO)
                .collect(Collectors.toList());
    }

    private Emprestimo atualizarStatusSeAtrasado(Emprestimo emprestimo) {
        if (emprestimo.getStatus() == StatusEmprestimo.DEVOLVIDO || emprestimo.getStatus() == StatusEmprestimo.CANCELADO) {
            return emprestimo;
        }

        if (emprestimo.getDataDevolucaoReal() == null && emprestimo.getDataDevolucaoPrevista().isBefore(LocalDate.now())) {
            emprestimo.setStatus(StatusEmprestimo.ATRASADO);
            emprestimo.setMulta(calcularMulta(emprestimo.getDataDevolucaoPrevista(), LocalDate.now()));
            return emprestimoRepository.save(emprestimo);
        }
        return emprestimo;
    }

    private BigDecimal calcularMulta(LocalDate dataPrevista, LocalDate dataReal) {
        if (dataReal == null || !dataReal.isAfter(dataPrevista)) {
            return BigDecimal.ZERO;
        }
        long diasAtraso = ChronoUnit.DAYS.between(dataPrevista, dataReal);
        return MULTA_DIARIA_PADRAO.multiply(BigDecimal.valueOf(diasAtraso));
    }
}
