package com.gestaoentregas.negocio;

import com.gestaoentregas.dados.beans.cliente.Cliente; // Importar a classe Cliente
import com.gestaoentregas.excecoes.CPFException; // Exceção de CPF (se precisar)
import com.gestaoentregas.excecoes.UIException; // Exceção genérica de usuário (se precisar)
import org.springframework.stereotype.Service;

@Service
public class ServicoCliente {

    // MANTER APENAS O SERVICO USUARIO
    private final ServicoUsuario servicoUsuario;

    public ServicoCliente(ServicoUsuario servicoUsuario) {
        this.servicoUsuario = servicoUsuario;
    }

    /**
     * 1. Executa as validações de Cliente (Regras de Negócio Específicas).
     * 2. Delega a persistência (e validações genéricas) ao ServicoUsuario.
     */
    public void cadastrarCliente(Cliente novoCliente) throws CPFException, UIException {

        // 1. VALIDAÇÕES ESPECÍFICAS DE CLIENTE

        // Exemplo de Validação de CPF para Cliente:
        String cpf = novoCliente.getCpfCliente();

        if (cpf == null || cpf.isEmpty()) {
            throw new CPFException("CPF não pode ser vazio.");
        }

        if (cpf.length() != 11) {
            throw new CPFException("O CPF deve conter exatamente 11 dígitos.");
        }

        // A VERIFICAÇÃO PRINCIPAL: Se ele NÃO corresponde ao padrão de apenas dígitos (\\d+)
        if (!cpf.matches("\\d+")) {
            throw new CPFException("O CPF deve conter apenas números, sem pontos ou traços.");
        }

        String telefone = novoCliente.getTelefoneCliente();

        // Validação de Telefone: Verifica se é nulo, vazio, tem tamanho diferente de 11 OU se NÃO contém apenas dígitos.
        if (telefone == null || telefone.isEmpty() ||
                telefone.length() != 11 ||
                !telefone.matches("\\d+")) { // <-- Adiciona a verificação de APENAS dígitos e inverte com '!'

            throw new UIException("O telefone do cliente é obrigatório, deve conter 11 dígitos e ser composto apenas por números.");
        }



        // ********************************************************
        // 2. DELEGAÇÃO: DEIXA O SERVICOUSUARIO FAZER A PERSISTÊNCIA
        // ********************************************************

        // O ServicoUsuario lida com:
        // - Verificação de E-mail duplicado
        // - Verificação de ID duplicado
        // - Persistência no Repositório

        servicoUsuario.cadastrarUsuario(novoCliente);
    }

}