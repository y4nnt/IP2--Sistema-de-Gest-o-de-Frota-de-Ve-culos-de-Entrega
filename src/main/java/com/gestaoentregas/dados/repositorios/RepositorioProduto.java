package com.gestaoentregas.dados.repositorios;

import com.gestaoentregas.dados.beans.entrega.Produto;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Repository
public class RepositorioProduto implements IRepositorioProduto{
    private ArrayList<Produto> produtos;

    private RepositorioProduto() {
        this.produtos = new ArrayList<>();
    }


    @Override
    public void cadastrarProduto(Produto produto) {
        produtos.add(produto);
    }

    @Override
    public void atualizarProduto(Produto produto) {
        int i = procurarIndice(produto.getCodigo());
        if (i != -1) {
            this.produtos.set(i, produto);
        }
    }

    @Override
    public void removerProduto(String codProduto) {
        int i = procurarIndice(codProduto);

        if (i != -1) {
            this.produtos.remove(i);
        }
    }

    @Override
    public Produto buscarProduto(String codProduto) {
        Produto produto = null;
        int i = procurarIndice(codProduto);
        if (i != -1) {
            produto = this.produtos.get(i);
        }
        return produto;
    }

    private int procurarIndice(String codigo) {
        int indice = -1;
        for (int i = 0; i < this.produtos.size(); i++) {
            if (this.produtos.get(i).getCodigo().equals(codigo)) {
                indice = i;
            }
        }
        return indice;
    }
}
