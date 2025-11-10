package org.example.services;

import org.example.dto.EnderecoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CepService {

    private static final String VIA_CEP_URL = "https://viacep.com.br/ws/%s/json/";

    private final RestTemplate restTemplate;
    private final Map<String, EnderecoDTO> cache = new ConcurrentHashMap<>();

    public CepService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public EnderecoDTO buscarEnderecoPorCep(String cep) {
        String sanitized = sanitizeCep(cep);

        EnderecoDTO cached = cache.get(sanitized);
        if (cached != null) {
            return copiarEndereco(cached);
        }

        try {
            ResponseEntity<ViaCepResponse> response = restTemplate.getForEntity(String.format(VIA_CEP_URL, sanitized), ViaCepResponse.class);
            ViaCepResponse body = response.getBody();

            if (body == null || Boolean.TRUE.equals(body.getErro())) {
                throw new IllegalArgumentException("CEP não encontrado.");
            }

            EnderecoDTO dto = mapearEndereco(body);
            cache.put(sanitized, dto);
            return copiarEndereco(dto);
        } catch (RestClientException ex) {
            throw new IllegalArgumentException("Não foi possível consultar o CEP informado.", ex);
        }
    }

    private String sanitizeCep(String cep) {
        String sanitized = cep == null ? null : cep.replaceAll("\\D", "");
        if (sanitized == null || !sanitized.matches("\\d{8}")) {
            throw new IllegalArgumentException("CEP inválido. Informe 8 dígitos numéricos.");
        }
        return sanitized;
    }

    private EnderecoDTO mapearEndereco(ViaCepResponse body) {
        EnderecoDTO dto = new EnderecoDTO();
        dto.setCep(body.getCep());
        dto.setLogradouro(body.getLogradouro());
        dto.setComplemento(body.getComplemento());
        dto.setBairro(body.getBairro());
        dto.setCidade(body.getLocalidade());
        dto.setEstado(body.getUf());
        return dto;
    }

    private EnderecoDTO copiarEndereco(EnderecoDTO origem) {
        EnderecoDTO dto = new EnderecoDTO();
        dto.setCep(origem.getCep());
        dto.setLogradouro(origem.getLogradouro());
        dto.setComplemento(origem.getComplemento());
        dto.setBairro(origem.getBairro());
        dto.setCidade(origem.getCidade());
        dto.setEstado(origem.getEstado());
        return dto;
    }

    private static class ViaCepResponse {
        private String cep;
        private String logradouro;
        private String complemento;
        private String bairro;
        private String localidade;
        private String uf;
        private Boolean erro;

        public String getCep() {
            return cep;
        }

        public void setCep(String cep) {
            this.cep = cep;
        }

        public String getLogradouro() {
            return logradouro;
        }

        public void setLogradouro(String logradouro) {
            this.logradouro = logradouro;
        }

        public String getComplemento() {
            return complemento;
        }

        public void setComplemento(String complemento) {
            this.complemento = complemento;
        }

        public String getBairro() {
            return bairro;
        }

        public void setBairro(String bairro) {
            this.bairro = bairro;
        }

        public String getLocalidade() {
            return localidade;
        }

        public void setLocalidade(String localidade) {
            this.localidade = localidade;
        }

        public String getUf() {
            return uf;
        }

        public void setUf(String uf) {
            this.uf = uf;
        }

        public Boolean getErro() {
            return erro;
        }

        public void setErro(Boolean erro) {
            this.erro = erro;
        }
    }
}
