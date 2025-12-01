package com.gestaoentregas.dados.beans.cliente;

import java.time.LocalDate;

public class Cliente extends Usuario {
    private String telefone;
    private LocalDate dataNascimento;

    public Cliente(String id, String nome, String cpf, String email, String senha, String telefone, LocalDate dataNascimento) {
        super(id, nome, cpf, email, senha, TipoUsuario.CLIENTE);
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Cliente: " + getNome() + " (ID: " + getId() + ")";
    }
}