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
    private int contadorId = 1;

    public RepositorioUsuario() {
        this.usuarios = new ArrayList<>();
    }

    @Override
    public void cadastrarUsuario(Usuario usuario) {
        if (usuario.getId() == 0){
            usuario.setId(contadorId);
            contadorId++;
        }
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
    public void atualizarUsuario(Usuario usuarioAtualizado) {
        boolean encontrou = false;

        // Varre a lista procurando pelo ID
        for (int i = 0; i < this.usuarios.size(); i++) {
            if (this.usuarios.get(i).getId() == usuarioAtualizado.getId()) {
                // ACHOU! Substitui o objeto antigo pelo novo (que tem o veículo vinculado)
                this.usuarios.set(i, usuarioAtualizado);
                encontrou = true;
                System.out.println("DEBUG: Usuário ID " + usuarioAtualizado.getId() + " atualizado com sucesso no RepoUsuario.");
                break;
            }
        }

        // Se varreu tudo e não achou, lançamos uma RuntimeException para o Serviço pegar
        if (!encontrou) {
            // Imprime o que tem na lista para ajudar a debugar
            System.out.println("DEBUG ERRO: Tentando atualizar ID " + usuarioAtualizado.getId());
            System.out.println("DEBUG ERRO: Lista atual de usuários tem " + this.usuarios.size() + " itens:");
            for(Usuario u : this.usuarios) {
                System.out.println(" - ID: " + u.getId() + " Nome: " + u.getId());
            }

            throw new RuntimeException("UsuarioNaoEncontrado");
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