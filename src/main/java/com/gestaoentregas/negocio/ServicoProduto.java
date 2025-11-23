package com.gestaoentregas.negocio;

import com.gestaoentregas.dados.beans.entrega.Produto;
import com.gestaoentregas.dados.repositorios.IRepositorioProduto;
import com.gestaoentregas.dados.repositorios.RepositorioProduto;
import com.gestaoentregas.excecoes.PCException;
import com.gestaoentregas.excecoes.PIException;
import com.gestaoentregas.excecoes.VInException;
import org.springframework.stereotype.Service;

@Service
public class ServicoProduto {
    private final RepositorioProduto repositorioProduto;

    public ServicoProduto(RepositorioProduto repositorioProduto) {
        this.repositorioProduto = repositorioProduto;
    }

    public void cadastrarProduto(Produto produto) throws PCException, VInException {
        if(repositorioProduto.buscarProduto(produto.getCodigo()) != null){
            throw new PCException();
        } else if (produto.getValor() <= 0){
            throw new VInException();
        }
        repositorioProduto.cadastrarProduto(produto);
    }

    public void atualizarProduto(Produto produto) throws PIException{
        if(repositorioProduto.buscarProduto(produto.getCodigo()) == null) {
            throw new PIException();
        }
        repositorioProduto.atualizarProduto(produto);
    }

    public void removerProduto(String codigo) throws PIException{
        if(repositorioProduto.buscarProduto(codigo) == null){
            throw new PIException();
        }
        repositorioProduto.removerProduto(codigo);
    }

    public Produto buscarProduto(String codigo) throws PIException{
        Produto produto = repositorioProduto.buscarProduto(codigo);
        if(produto == null){
            throw new PIException();
        }
        return produto;
    }
}
