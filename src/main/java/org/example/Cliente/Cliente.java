package org.example.Cliente;

public class Cliente {
    private String nome;
    private String numeroCPF;
    private String email;
    private String telefone;
    private String status;
    private String cep;
    private String numeroDaResidencia;
    private String complementoDoEndereco;

    public Cliente(String nome, String numeroCPF, String email, String telefone, String cep, String numeroDaResidencia, String complementoDoEndereco) {
        this.nome = nome;
        this.numeroCPF = numeroCPF;
        this.email = email;
        this.telefone = telefone;
        this.cep = cep;
        this.numeroDaResidencia = numeroDaResidencia;
        this.complementoDoEndereco = complementoDoEndereco;
    }

    public String getNumeroCPF() {
        return numeroCPF;
    }

    public void setNumeroCPF(String numeroCPF) {
            this.numeroCPF = numeroCPF;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getters e setters restantes...
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumeroDaResidencia() {
        return numeroDaResidencia;
    }

    public void setNumeroDaResidencia(String numeroDaResidencia) {
        this.numeroDaResidencia = numeroDaResidencia;
    }

    public String getComplementoDoEndereco() {
        return complementoDoEndereco;
    }

    public void setComplementoDoEndereco(String complementoDoEndereco) {
        this.complementoDoEndereco = complementoDoEndereco;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", numeroCPF='" + numeroCPF + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cep='" + cep + '\'' +
                ", numeroDaResidencia='" + numeroDaResidencia + '\'' +
                ", complementoDoEndereco='" + complementoDoEndereco + '\'' +
                '}';
    }
}
