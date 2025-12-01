package com.gestaoentregas.dados.beans.cliente;

import java.time.LocalDate;

public class Cliente {
    private String nomeCliente;
    private String telefoneCliente;
    private String cpfCliente;
    private String emailCliente;
    private String idCliente;
    private LocalDate dataNascimentoCliente;

    public Cliente(String nomeCliente, String telefoneCliente, String cpfCliente, String emailCliente, String idCliente, LocalDate dataNascimentoCliente) {
        this.nomeCliente = nomeCliente;
        this.telefoneCliente = telefoneCliente;
        this.cpfCliente = cpfCliente;
        this.emailCliente = emailCliente;
        this.idCliente = idCliente;
        this.dataNascimentoCliente = dataNascimentoCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDate getDataNascimentoCliente() {
        return dataNascimentoCliente;
    }

    public void setDataNascimentoCliente(LocalDate dataNascimentoCliente) {
        this.dataNascimentoCliente = dataNascimentoCliente;
    }

    @Override
    public String toString(){
        return String.format("Cliente [Nome: %s, Data de Nascimento: %d/%d/%d, Telefone: %s, CPF: %s, Email: %s, Id: %s]",
                this.nomeCliente, this.dataNascimentoCliente.getDayOfMonth(), this.dataNascimentoCliente.getMonthValue(),
                this.dataNascimentoCliente.getYear(), this.telefoneCliente, this.cpfCliente, this.emailCliente, this.idCliente);
    }
}
