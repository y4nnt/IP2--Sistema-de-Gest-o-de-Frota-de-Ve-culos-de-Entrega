package com.gestaoentregas.negocio;

import com.gestaoentregas.dados.beans.motorista.Motorista;
import com.gestaoentregas.dados.repositorios.RepositorioMotorista;
import com.gestaoentregas.excecoes.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServicoMotorista {

    private final ServicoUsuario servicoUsuario;
    private final RepositorioMotorista repositorioMotorista;

    public ServicoMotorista(ServicoUsuario servicoUsuario, RepositorioMotorista repositorioMotorista) {
        this.servicoUsuario = servicoUsuario;
        this.repositorioMotorista = repositorioMotorista;
    }

    public void cadastrarMotorista(Motorista novoMotorista) throws IIException, CNHException, CPFException, UIException {

        // 1. VALIDAÇÕES ESPECÍFICAS DE MOTORISTA
        if (novoMotorista.getIdadeMotorista() < 18) {
            throw new IIException();
        }

        String cnh = novoMotorista.getCnhMotorista();
        if (cnh == null || cnh.length() != 11 || !cnh.matches("\\d+")) {
            throw new CNHException("CNH deve conter 11 dígitos e ser composta apenas por números.");
        }

        String cpf = novoMotorista.getCpfMotorista();
        if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d+")) {
            throw new CPFException("CPF deve conter 11 dígitos e ser composto apenas por números.");
        }

        String telefone = novoMotorista.getTelefoneMotorista();
        if (telefone == null || telefone.length() != 11 || !telefone.matches("\\d+")) {
            throw new UIException("O telefone do motorista deve conter 11 dígitos e ser composto apenas por números.");
        }

        // ********************************************************
        // 2. ORQUESTRAÇÃO DO CADASTRO (A CORREÇÃO ESTÁ AQUI)
        // ********************************************************

        // PASSO A: Salva como Usuário primeiro.
        // O RepositorioUsuario vai verificar se o ID é 0 e gerar um ID (ex: 1).
        // Também valida se o e-mail já existe.
        servicoUsuario.cadastrarUsuario(novoMotorista);

        // PASSO B: CRUCIAL!!!
        // Agora que o objeto tem ID e foi validado, adicionamos ele na lista de Motoristas.
        // Se você não fizer isso, o 'buscarPorId' vai retornar null e a lista virá vazia.
        repositorioMotorista.cadastrarMotorista(novoMotorista);
    }

    // CORREÇÃO AQUI TAMBÉM: Use o repositório de MOTORISTA para listar motoristas
    public ArrayList<Motorista> listarMotoristas() {
        return repositorioMotorista.listarMotoristas();
    }

    // Método usado pelo CadastroVeiculoController
    public Motorista buscarPorId(int id) {
        return repositorioMotorista.buscarMotorista(id);
    }
}