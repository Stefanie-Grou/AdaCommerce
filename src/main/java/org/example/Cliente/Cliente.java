package org.example.Cliente;

public class Cliente {
    private String nome;
    private String numeroCPF;
    private int telefone;
    private String status;
    private int cep;
    private int numeroDaResidencia;
    private String complementoDoEndereco;

    public String getNumeroCPF() {
        return numeroCPF;
    }

    public void setNumeroCPF(String numeroCPF) {
        try {
            validarCPF(numeroCPF);
            this.numeroCPF = numeroCPF;
            this.status = "Válido";
        } catch (Exception e) {
            this.status = "Inválido";
        }
    }

    private void validarCPF(String numeroCPF) throws Exception {
        if (numeroCPF.length() != 11) {
            throw new Exception("O CPF deve ter 11 dígitos, sem pontos ou traços.");
        }
    }

    public String getStatus() {
        return status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public int getNumeroDaResidencia() {
        return numeroDaResidencia;
    }

    public void setNumeroDaResidencia(int numeroDaResidencia) {
        this.numeroDaResidencia = numeroDaResidencia;
    }

    public String getComplementoDoEndereco() {
        return complementoDoEndereco;
    }

    public void setComplementoDoEndereco(String complementoDoEndereco) {
        this.complementoDoEndereco = complementoDoEndereco;
    }

}
