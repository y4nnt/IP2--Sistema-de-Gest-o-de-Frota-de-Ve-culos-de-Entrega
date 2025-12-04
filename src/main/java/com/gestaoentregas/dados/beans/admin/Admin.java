package com.gestaoentregas.dados.beans.admin;

import com.gestaoentregas.dados.beans.TipoUsuario;
import com.gestaoentregas.dados.beans.Usuario;

public class Admin extends Usuario{
    private String nome;

    public Admin(String nome, String email, String senha, int idAdmin) {
        super(email, senha, TipoUsuario.ADMIN, idAdmin);
        this.nome = nome;
    }

    public String getNome() {return this.nome;}
    public void setNome(String nome) {this.nome = nome;}
}
