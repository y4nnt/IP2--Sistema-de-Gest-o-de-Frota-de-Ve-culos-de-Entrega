package com.gestaoentregas.negocio;

import com.gestaoentregas.dados.beans.entrega.Produto;
import com.gestaoentregas.dados.repositorios.IRepositorioProduto;
import com.gestaoentregas.dados.repositorios.RepositorioProduto;
import com.gestaoentregas.excecoes.PCException;
import com.gestaoentregas.excecoes.PIException;

public class ServicoProduto {
    private final IRepositorioProduto repositorioProduto;

    public ServicoProduto() {
        this.repositorioProduto = RepositorioProduto.getInstance();
    }

    public void cadastrarProduto(Produto produto) throws PCException {
        if(repositorioProduto.buscarProduto(produto.getCodigo()) == null){
            repositorioProduto.cadastrarProduto(produto);
        } else{
            throw new PCException();
        }
    }

    public void atualizarProduto(Produto produto) throws PIException{
        if(repositorioProduto.buscarProduto(produto.getCodigo()) != null) {
            repositorioProduto.atualizarProduto(produto);
        } else{
            throw new PIException();
        }
    }

    public void removerProduto(String codigo) throws PIException{
        if(repositorioProduto.buscarProduto(codigo) != null){
            repositorioProduto.removerProduto(codigo);
        } else{
            throw new PIException();
        }
    }

    public Produto buscarProduto(String codigo) throws PIException{
        Produto produto = repositorioProduto.buscarProduto(codigo);
        if(produto == null){
            throw new PIException();
        }
        return produto;
    }
}
