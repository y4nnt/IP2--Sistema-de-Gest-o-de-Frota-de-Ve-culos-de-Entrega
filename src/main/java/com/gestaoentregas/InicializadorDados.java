package com.gestaoentregas;

import com.gestaoentregas.dados.beans.VeiculoMotorista;
import com.gestaoentregas.dados.beans.entrega.Entrega;
import com.gestaoentregas.dados.beans.entrega.Produto;
import com.gestaoentregas.dados.beans.entrega.StatusEntrega;
import com.gestaoentregas.dados.beans.motorista.Motorista;
import com.gestaoentregas.dados.beans.veiculo.Veiculo;
import com.gestaoentregas.negocio.ServicoEntrega;
import com.gestaoentregas.negocio.ServicoMotorista;
import com.gestaoentregas.negocio.ServicoProduto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InicializadorDados implements CommandLineRunner {
    private ServicoEntrega servicoEntrega;
    private ServicoProduto servicoProduto;
    private ServicoMotorista servicoMotorista;

    public InicializadorDados(ServicoEntrega servicoEntrega, ServicoProduto servicoProduto, ServicoMotorista servicoMotorista) {
        this.servicoEntrega = servicoEntrega;
        this.servicoProduto = servicoProduto;
        this.servicoMotorista = servicoMotorista;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Criando dados teste");

        Produto p1 = new Produto("Perfume", "001", 30.00);
        Produto p2 = new Produto("Camisa", "002", 30.00);
        Produto p3 = new Produto("Calça", "003", 30.00);
        Entrega e1 = new Entrega("101", "Rua do Poeta, 77", null, null, StatusEntrega.PENDENTE, "123@456");
        Entrega e2 = new Entrega("102", "Rua da Harmonia, 93", null, null, StatusEntrega.PENDENTE, "123@456");
        Entrega e3 = new Entrega("103", "Rua Santa Izabel, 42", null, null, StatusEntrega.PENDENTE, "123@456");
        e1.addProduto(p1);
        e1.addProduto(p2);
        e1.addProduto(p3);
        e2.addProduto(p1);
        e2.addProduto(p2);
        e2.addProduto(p3);
        e3.addProduto(p1);
        e3.addProduto(p2);
        e3.addProduto(p3);
        Motorista m1 = new Motorista("João", "81999999999", "1218931292", "1", 29, 0, "teste1@gmail.com", "123");
        Motorista m2 = new Motorista("Marcelo", "89218219", "1231243266", "2", 19, 1, "teste2@gmail.com", "234");
        Motorista m3 = new Motorista("Lucas", "81940028922", "99320199430", "3", 23, 2, "teste3@gmail.com", "345");
        m1.setId(1);
        m2.setId(2);
        m3.setId(3);
        VeiculoMotorista vm1 = new VeiculoMotorista(m1, null);
        VeiculoMotorista vm2 = new VeiculoMotorista(m2, null);
        VeiculoMotorista vm3 = new VeiculoMotorista(m3, null);
        vm1.setVeiculoEntrega(new Veiculo("1", "carro", 4));
        vm2.setVeiculoEntrega(new Veiculo("1", "carro", 4));
        vm3.setVeiculoEntrega(new Veiculo("1", "carro", 4));



        try {
            servicoEntrega.cadastrarEntrega(e1);
            servicoEntrega.cadastrarEntrega(e2);
            servicoEntrega.cadastrarEntrega(e3);
        } catch (Exception e) {
            System.out.println("Aviso: Algumas entregas já existiam. Pulinho...");
        }

        // 3. Tentando Cadastrar PRODUTOS (com proteção)
        try {
            servicoProduto.cadastrarProduto(p1);
            servicoProduto.cadastrarProduto(p2);
            servicoProduto.cadastrarProduto(p3);
        } catch (Exception e) {
            System.out.println("Aviso: Alguns produtos já existiam. Pulinho...");
        }

        cadastrarMotoristaSeguro(m1);
        cadastrarMotoristaSeguro(m2);
        cadastrarMotoristaSeguro(m3);

        System.out.println("--- Dados inicializados com sucesso ---");
    }

    // Método auxiliar para não ficar repetindo try-catch
    private void cadastrarMotoristaSeguro(Motorista m) {
        try {
            servicoMotorista.cadastrarMotorista(m);
            System.out.println("Motorista " + m.getNomeMotorista() + " cadastrado.");
        } catch (Exception e) {
            System.out.println("Motorista " + m.getNomeMotorista() + " já existe no sistema.");
        }
    }
}