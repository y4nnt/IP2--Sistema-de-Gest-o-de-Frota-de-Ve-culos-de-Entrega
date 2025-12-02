package com.gestaoentregas.negocio;
import com.gestaoentregas.dados.beans.Usuario;
import com.gestaoentregas.dados.beans.TipoUsuario;
import com.gestaoentregas.dados.beans.motorista.Motorista;
import com.gestaoentregas.dados.repositorios.RepositorioUsuario;
import com.gestaoentregas.excecoes.UIException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class ServicoUsuario {

    private static volatile int nextId = 0;
    private final RepositorioUsuario repositorioUsuario;

    public ServicoUsuario(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    public synchronized int pegarProximoId() {
        return nextId++;
    }

    public void cadastrarUsuario(Usuario novoUsuario) throws UIException {
        //Verifica se existe outro usuário com o mesmo e-mail.
        Usuario usuarioNoBanco = repositorioUsuario.buscarUsuarioPorEmail(novoUsuario.getEmail()); // Chamada ao método do repositório

        if (usuarioNoBanco != null){
            throw new UIException("Usuário já cadastrado! E-mail já em uso.");
        }

        Usuario usuarioPorId = repositorioUsuario.buscarUsuario(novoUsuario.getId());

        if (usuarioPorId != null){
            throw new UIException("Falha no cadastro: ID interno já em uso.");
        }

        repositorioUsuario.cadastrarUsuario(novoUsuario);
    }

    public void atualizarUsuario(Usuario Usuario) throws UIException {
        if (repositorioUsuario.buscarUsuario(Usuario.getId()) == null){
            throw new UIException();
        }
        repositorioUsuario.atualizarUsuario(Usuario);
    }

    public void removerUsuario(int id) throws UIException {
        if(repositorioUsuario.buscarUsuario(id) == null){
            throw new UIException();
        }
        repositorioUsuario.removerUsuario(id);
    }

    public Usuario buscarUsuario(int id) throws UIException {
        Usuario usuario = repositorioUsuario.buscarUsuario(id);
        if (usuario == null){
            throw new UIException();
        }
        return usuario;
    }


    public Usuario autenticar(String email, String senha, TipoUsuario tipoEsperado) throws UIException {

        // 1. Buscar o usuário pelo email
        Usuario usuario = repositorioUsuario.buscarUsuarioPorEmail(email);

        // 2. Validar existência
        if (usuario == null) {
            throw new UIException("Usuário ou senha inválidos.");
        }

        // 3. Validar a senha
        if (!usuario.getSenha().equals(senha)) {
            throw new UIException("Usuário ou senha inválidos.");
        }

        // 4. Validar o Tipo de Usuário (para logins específicos: Cliente, Admin, Motorista)
        if (usuario.getTipo() != tipoEsperado) {
            throw new UIException("Tipo de usuário incorreto para este login.");
        }

        return usuario;
    }

    public ArrayList<Usuario> listarUsuarios(){return repositorioUsuario.listarUsuarios();}

    public ArrayList<Motorista> listarMotoristas() {
        ArrayList<Usuario> todosUsuarios = repositorioUsuario.listarUsuarios(); // Obtém a lista completa do Repositório
        ArrayList<Motorista> motoristas = new ArrayList<>();

        for (Usuario usuario : todosUsuarios) {
            // Usa o operador 'instanceof' para verificar se o objeto é, de fato, um Motorista
            if (usuario instanceof Motorista) {
                // Faz o downcasting para adicionar à lista específica de Motorista
                motoristas.add((Motorista) usuario);
            }
        }
        return motoristas;
    }
}
