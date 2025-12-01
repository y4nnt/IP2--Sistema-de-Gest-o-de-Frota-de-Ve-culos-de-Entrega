package com.gestaoentregas.dados.repositorios;

import com.gestaoentregas.dados.beans.cliente.Usuario;
import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuario { // Renomeado de RepositorioConta

    // Lista polimórfica: aceita Cliente, Admin e Motorista
    private List<Usuario> usuarios;

    public RepositorioUsuario() {
        this.usuarios = new ArrayList<>();
    }

    public void cadastrarUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
    }

    public void removerUsuario(String id) {
        int index = procurarIndice(id);
        if (index != -1) {
            this.usuarios.remove(index);
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public Usuario buscarUsuario(String id) {
        int index = procurarIndice(id);
        if (index != -1) {
            return this.usuarios.get(index);
        }
        return null;
    }

    // Método auxiliar privado
    private int procurarIndice(String id) {
        for (int i = 0; i < this.usuarios.size(); i++) {
            // Agora funciona! O método getId() existe na classe pai Usuario
            if (this.usuarios.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}