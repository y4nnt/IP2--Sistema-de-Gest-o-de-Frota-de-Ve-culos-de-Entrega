package com.gestaoentregas.dados.repositorios;

import com.gestaoentregas.dados.beans.Usuario;
import com.gestaoentregas.dados.beans.motorista.Motorista;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class RepositorioUsuario implements IRepositorioUsuario {

    // Lista polimórfica: aceita Cliente, Admin e Motorista
    private List<Usuario> usuarios;

    public RepositorioUsuario() {
        this.usuarios = new ArrayList<>();
    }

    @Override
    public void cadastrarUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
    }

    @Override
    public void removerUsuario(int id) {
        int index = procurarIndice(id);
        if (index != -1) {
            this.usuarios.remove(index);
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }
    @Override
    public Usuario buscarUsuario(int id) {
        int index = procurarIndice(id);
        if (index != -1) {
            return this.usuarios.get(index);
        }
        return null;
    }
    @Override
    public void atualizarUsuario(Usuario Usuario) {
        int i = procurarIndice(Usuario.getId());
        if (i != -1) {
            this.usuarios.set(i, Usuario);
        }
    }
    @Override
    public Usuario buscarUsuarioPorEmail(String email) {
        for (Usuario u : this.usuarios) {
            // Usa o getter herdado getEmail()
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }

    // Método auxiliar privado
    private int procurarIndice(int id) {
        for (int i = 0; i < this.usuarios.size(); i++) {
            // Agora funciona! O método getId() existe na classe pai Usuario
            if (this.usuarios.get(i).equals(id)) {
                return i;
            }
        }
        return -1;
    }
    @Override
    public ArrayList<Usuario> listarUsuarios(){return new ArrayList<>(this.usuarios);}
}