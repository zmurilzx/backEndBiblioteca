package org.example.dto;

public class FornecedorIndicadorDTO {

    private Long fornecedorId;
    private String razaoSocial;
    private long totalTitulos;
    private long totalEstoque;

    public FornecedorIndicadorDTO() {
    }

    public FornecedorIndicadorDTO(Long fornecedorId, String razaoSocial, long totalTitulos, long totalEstoque) {
        this.fornecedorId = fornecedorId;
        this.razaoSocial = razaoSocial;
        this.totalTitulos = totalTitulos;
        this.totalEstoque = totalEstoque;
    }

    public Long getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Long fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public long getTotalTitulos() {
        return totalTitulos;
    }

    public void setTotalTitulos(long totalTitulos) {
        this.totalTitulos = totalTitulos;
    }

    public long getTotalEstoque() {
        return totalEstoque;
    }

    public void setTotalEstoque(long totalEstoque) {
        this.totalEstoque = totalEstoque;
    }
}
