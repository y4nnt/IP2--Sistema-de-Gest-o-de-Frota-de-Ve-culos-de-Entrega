package com.gestaoentregas.negocio;

import com.gestaoentregas.dados.beans.admin.Admin;
import com.gestaoentregas.dados.beans.cliente.Cliente;
import com.gestaoentregas.excecoes.CPFException;
import com.gestaoentregas.excecoes.UIException;
import org.springframework.stereotype.Service;

@Service
public class ServicoAdmin {
    private final ServicoUsuario servicoUsuario;

    public ServicoAdmin(ServicoUsuario servicoUsuario) {
        this.servicoUsuario = servicoUsuario;
    }

    /**
     * 1. Executa as validações de Cliente (Regras de Negócio Específicas).
     * 2. Delega a persistência (e validações genéricas) ao ServicoUsuario.
     */
    public void cadastrarAdmin(Admin novoAdmin) throws CPFException, UIException {



        // ********************************************************
        // 2. DELEGAÇÃO: DEIXA O SERVICOUSUARIO FAZER A PERSISTÊNCIA
        // ********************************************************

        // O ServicoUsuario lida com:
        // - Verificação de E-mail duplicado
        // - Verificação de ID duplicado
        // - Persistência no Repositório

        servicoUsuario.cadastrarUsuario(novoAdmin);
    }
}
