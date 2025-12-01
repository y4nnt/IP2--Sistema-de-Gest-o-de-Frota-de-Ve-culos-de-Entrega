package com.gestaoentregas.negocio;

import com.gestaoentregas.dados.beans.cliente.Usuario;
import com.gestaoentregas.dados.repositorios.RepositorioUsuario;
import com.gestaoentregas.excecoes.*;
import org.springframework.stereotype.Service;

@Service
public class ServicoUsuario {
    private final RepositorioUsuario repositorioUsuario;

    public ServicoUsuario(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    public void cadastrarUsuario(Usuario usuario) throws UCException, INException {
        if(repositorioUsuario.buscarUsuario(usuario.getId()) != null){
            throw new UCException();
        } else if (usuario.getId() == null){
            throw new INException();
        }
        repositorioUsuario.cadastrarUsuario(usuario);
    }

    public void atualizarUsuario(Usuario usuario) throws UIException{
        if(repositorioUsuario.buscarUsuario(usuario.getId()) == null) {
            throw new UIException();
        }
        repositorioUsuario.atualizarUsuario(usuario);
    }

    public void removerUsuario(String id) throws UIException{
        if(repositorioUsuario.buscarUsuario(id) == null){
            throw new UIException();
        }
        repositorioUsuario.removerUsuario(id);
    }

    public Usuario buscarUsuario(String id) throws UIException{
        Usuario usuario = repositorioUsuario.buscarUsuario(id);
        if(usuario == null){
            throw new UIException();
        }
        return usuario;
    }
}
