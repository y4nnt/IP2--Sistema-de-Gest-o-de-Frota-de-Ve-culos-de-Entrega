package com.gestaoentregas.negocio;

import com.gestaoentregas.dados.beans.motorista.Motorista;
// Não precisa mais de RepositorioUsuario
import com.gestaoentregas.dados.repositorios.RepositorioMotorista;
import com.gestaoentregas.excecoes.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServicoMotorista {

    // MANTER APENAS O SERVICO USUARIO
    private final ServicoUsuario servicoUsuario;
    private final RepositorioMotorista repositorioMotorista;


    public ServicoMotorista(ServicoUsuario servicoUsuario, RepositorioMotorista repositorioMotorista) {
        this.servicoUsuario = servicoUsuario;
        this.repositorioMotorista = repositorioMotorista;
    }

    /**
     * 1. Executa as validações de Motorista (Regras de Negócio Específicas).
     * 2. Delega a persistência (e validações genéricas como ID duplicado) ao ServicoUsuario.
     */
    public void cadastrarMotorista(Motorista novoMotorista) throws IIException, CNHException, CPFException, UIException { // Adicionado UIException

        // 1. VALIDAÇÕES ESPECÍFICAS DE MOTORISTA

        // Validação de Idade
        if (novoMotorista.getIdadeMotorista() < 18) {
            throw new IIException(); // IIException para Idade Inválida
        }

        // Validação de CNH: 11 dígitos E apenas números
        String cnh = novoMotorista.getCnhMotorista();
        if (cnh == null || cnh.length() != 11 || !cnh.matches("\\d+")) {
            throw new CNHException("CNH deve conter 11 dígitos e ser composta apenas por números.");
        }

        // Validação de CPF: 11 dígitos E apenas números
        String cpf = novoMotorista.getCpfMotorista();
        if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d+")) {
            throw new CPFException("CPF deve conter 11 dígitos e ser composto apenas por números.");
        }

        //Validação de Telefone

        String telefone = novoMotorista.getTelefoneMotorista(); // Supondo que exista
        if (telefone == null || telefone.length() != 11 || !telefone.matches("\\d+")) {
            throw new UIException("O telefone do motorista deve conter 11 dígitos e ser composto apenas por números.");
        }


        // ********************************************************
        // 2. DELEGAÇÃO: DEIXA O SERVICOUSUARIO FAZER A PERSISTÊNCIA
        // ********************************************************

        servicoUsuario.cadastrarUsuario(novoMotorista);
    }
    public ArrayList<Motorista> listarMotoristas() {
        return servicoUsuario.listarMotoristas();
    }

    // Em ServicoMotorista.java
    public Motorista buscarPorId(int id) {
        // Chama o repositório específico de motoristas
        return repositorioMotorista.buscarMotorista(id);
    }


    // ************************************************************
    // OS MÉTODOS ABAIXO FORAM REMOVIDOS (Responsabilidade do ServicoUsuario)
    // ************************************************************

    /*
    public void atualizarMotorista(Motorista motorista) throws MIException { ... }
    public void removerMotorista(int id) throws MIException { ... }
    public Motorista buscarMotorista(int id) throws MIException { ... }
    public ArrayList<Motorista> listarMotoristas() { ... }
    */
}