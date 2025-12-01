package com.gestaoentregas.dados.repositorios;

import com.gestaoentregas.dados.beans.cliente.Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class RepositorioUsuario implements IRepositorioUsuario {

    private ArrayList<Usuario> usuarios;

    public RepositorioUsuario() {
        this.usuarios = new ArrayList<>();
    }

    @Override
    public void cadastrarUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
    }

    @Override
    public void atualizarUsuario(Usuario usuario) {
        int i = procurarIndice(usuario.getId());
        if (i != -1) {
            this.usuarios.set(i, usuario);
        }
    }

    @Override
    public void removerUsuario(String id) {
        int i = procurarIndice(id);
        if (i != -1) {
            this.usuarios.remove(i);
        }
    }

    @Override
    public Usuario buscarUsuario(String id) {
        Usuario usuario = null;
        int i = procurarIndice(id);
        if (i != -1) {
            usuario = this.usuarios.get(i);
        }
        return usuario;
    }


    private int procurarIndice(String id) {
        int indice = -1;
        for (int i = 0; i < this.usuarios.size(); i++) {
            if (this.usuarios.get(i).getId().equals(id)) {
                indice = i;
            }
        }
        return indice;
    }

    @Override
    public ArrayList<Usuario> listarUsuario() {
        return new ArrayList<>(this.usuarios);
    }
}