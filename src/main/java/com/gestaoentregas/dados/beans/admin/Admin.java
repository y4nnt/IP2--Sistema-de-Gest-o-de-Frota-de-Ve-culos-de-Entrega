package com.gestaoentregas.dados.beans.admin;

import com.gestaoentregas.dados.beans.cliente.TipoUsuario;
import com.gestaoentregas.dados.beans.cliente.Usuario;

public class Admin extends Usuario {

    public Admin(String id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha, TipoUsuario.ADMIN);
    }

    @Override
    public String toString() {
        return String.format("Administrador: %s (CPF: %s, ID: %s)",
                getNome(), getCpf(), getId());
    }
}