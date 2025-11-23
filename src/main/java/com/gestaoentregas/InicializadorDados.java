package com.gestaoentregas;

import com.gestaoentregas.dados.beans.entrega.Entrega;
import com.gestaoentregas.dados.beans.entrega.Produto;
import com.gestaoentregas.dados.beans.entrega.StatusEntrega;
import com.gestaoentregas.negocio.ServicoEntrega;
import com.gestaoentregas.negocio.ServicoProduto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InicializadorDados implements CommandLineRunner {
    private ServicoEntrega servicoEntrega;
    private ServicoProduto servicoProduto;

    public InicializadorDados(ServicoEntrega servicoEntrega,  ServicoProduto servicoProduto) {
        this.servicoEntrega = servicoEntrega;
        this.servicoProduto = servicoProduto;
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

        servicoEntrega.cadastrarEntrega(e1);
        servicoEntrega.cadastrarEntrega(e2);
        servicoEntrega.cadastrarEntrega(e3);

        servicoProduto.cadastrarProduto(p1);
        servicoProduto.cadastrarProduto(p2);
        servicoProduto.cadastrarProduto(p3);
    }
}
