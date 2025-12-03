package com.gestaoentregas.negocio;

import com.gestaoentregas.dados.beans.motorista.Motorista;
// Não precisa mais de RepositorioUsuario
import com.gestaoentregas.excecoes.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServicoMotorista {

    // MANTER APENAS O SERVICO USUARIO
    private final ServicoUsuario servicoUsuario;

    public ServicoMotorista(ServicoUsuario servicoUsuario) {
        this.servicoUsuario = servicoUsuario;
    }

    /**
     * 1. Executa as validações de Motorista (Regras de Negócio Específicas).
     * 2. Delega a persistência (e validações genéricas como ID duplicado) ao ServicoUsuario.
     */
    public void cadastrarMotorista(Motorista novoMotorista) throws IIException, UIException {

        // 1. VALIDAÇÕES ESPECÍFICAS DE MOTORISTA

        //Validação de Idade
        if (novoMotorista.getIdadeMotorista() < 18) {
            throw new IIException("Motorista menor de idade não permitido.");
        }

        //Validação de CNH
        if (novoMotorista.getCnhMotorista() == null || novoMotorista.getCnhMotorista().length() != 11) {
            throw new CNHException();
        }

        //Validação de CPF
        if (novoMotorista.getCpfMotorista().length() != 11) {
            throw new CPFException();
        }

        // ********************************************************
        // 2. DELEGAÇÃO: DEIXA O SERVICOUSUARIO FAZER A PERSISTÊNCIA
        // ********************************************************

        // Se o usuário já existir (ID ou Email), o ServicoUsuario se encarregará de
        // lançar a exceção apropriada (provavelmente uma UIException, não MCException).
        // Se você precisar da MCException aqui, você teria que fazer um 'catch' de UIException
        // e lançar MCException, mas o ideal é ter uma exceção genérica.

        servicoUsuario.cadastrarUsuario(novoMotorista);
    }
    public ArrayList<Motorista> listarMotoristas() {
        return servicoUsuario.listarMotoristas();
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