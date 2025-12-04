package com.gestaoentregas;

import com.gestaoentregas.dados.beans.admin.Admin;
import com.gestaoentregas.dados.beans.entrega.Entrega;
import com.gestaoentregas.dados.beans.entrega.Produto;
import com.gestaoentregas.dados.beans.entrega.StatusEntrega;
import com.gestaoentregas.dados.beans.motorista.Motorista;
import com.gestaoentregas.negocio.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InicializadorDados implements CommandLineRunner {
    private final ServicoEntrega servicoEntrega;
    private final ServicoProduto servicoProduto;
    private final ServicoMotorista servicoMotorista;
    private final ServicoUsuario servicoUsuario;
    private final ServicoAdmin servicoAdmin;

    public InicializadorDados(ServicoEntrega servicoEntrega,
                              ServicoProduto servicoProduto,
                              ServicoMotorista servicoMotorista,
                              ServicoAdmin servicoAdmin,
                              ServicoUsuario servicoUsuario) {
        this.servicoEntrega = servicoEntrega;
        this.servicoProduto = servicoProduto;
        this.servicoMotorista = servicoMotorista;
        this.servicoAdmin = servicoAdmin;
        this.servicoUsuario = servicoUsuario;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Criando dados teste...");

        // --- Produtos e Entregas ---
        Produto p1 = new Produto("Perfume", "001", 30.00);
        Produto p2 = new Produto("Camisa", "002", 30.00);
        Produto p3 = new Produto("Calça", "003", 30.00);

        Entrega e1 = new Entrega("101", "Rua do Poeta, 77", null, null, StatusEntrega.PENDENTE, "123@456");
        Entrega e2 = new Entrega("102", "Rua da Harmonia, 93", null, null, StatusEntrega.PENDENTE, "123@456");
        Entrega e3 = new Entrega("103", "Rua Santa Izabel, 42", null, null, StatusEntrega.PENDENTE, "123@456");

        e1.addProduto(p1); e1.addProduto(p2); e1.addProduto(p3);
        e2.addProduto(p1); e2.addProduto(p2); e2.addProduto(p3);
        e3.addProduto(p1); e3.addProduto(p2); e3.addProduto(p3);

        try {
            servicoEntrega.cadastrarEntrega(e1);
            servicoEntrega.cadastrarEntrega(e2);
            servicoEntrega.cadastrarEntrega(e3);
        } catch (Exception e) {
            System.out.println("Aviso: Entregas já existem.");
        }

        try {
            servicoProduto.cadastrarProduto(p1);
            servicoProduto.cadastrarProduto(p2);
            servicoProduto.cadastrarProduto(p3);
        } catch (Exception e) {
            System.out.println("Aviso: Produtos já existem.");
        }

        // --- Criação dos Motoristas (SEM VEÍCULOS) ---
        // O argumento 'null' indica que eles começam a pé.
        Motorista m1 = new Motorista("João", "81999999999", "12189312921", "11111111111", 29, null, 0, "teste1@gmail.com","123");
        Motorista m2 = new Motorista("Marcelo", "89218219213", "12312432661", "22222222222", 19, null, 0, "teste2@gmail.com","234");
        Motorista m3 = new Motorista("Lucas", "81940028922", "99320199430", "33333333333", 23, null, 0, "teste3@gmail.com", "345");

        // Salva os motoristas sem carro
        cadastrarMotoristaSeguro(m1);
        cadastrarMotoristaSeguro(m2);
        cadastrarMotoristaSeguro(m3);

        // --- Admin ---
        Admin a1 = new Admin("Yann", "admin1@gmail.com", "111", 1000);
        try {
            servicoAdmin.cadastrarAdmin(a1);
            servicoUsuario.cadastrarUsuario(a1);
            System.out.println("Admin " + a1.getNome() + " cadastrado.");
        } catch (Exception e) {
            System.out.println("Admin já existe.");
        }

        System.out.println("--- Dados inicializados com sucesso ---");
    }

    private void cadastrarMotoristaSeguro(Motorista m) {
        try {
            // APENAS chame o serviço do motorista.
            // Ele deve ser inteligente o suficiente para gerar o ID e salvar o usuário.
            servicoMotorista.cadastrarMotorista(m);

            System.out.println("Motorista " + m.getNomeMotorista() + " cadastrado. ID: " + m.getId());
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar " + m.getNomeMotorista() + ": " + e.getMessage());
        }
    }
}