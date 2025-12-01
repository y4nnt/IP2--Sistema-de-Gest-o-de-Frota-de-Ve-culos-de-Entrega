package com.gestaoentregas.dados.repositorios;


import com.gestaoentregas.dados.beans.cliente.Usuario;

import java.util.ArrayList;

public interface IRepositorioUsuario {
    void cadastrarUsuario(Usuario conta);
    void atualizarUsuario(Usuario conta);
    void removerUsuario(String id);
    Usuario buscarUsuario(String id);
    ArrayList<Usuario> listarUsuario();
}
