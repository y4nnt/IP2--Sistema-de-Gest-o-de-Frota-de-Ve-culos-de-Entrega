package com.gestaoentregas.dados.repositorios;

import com.gestaoentregas.dados.beans.entrega.Produto;

public interface IRepositorioProduto {
    void cadastrarProduto(Produto produto);
    void atualizarProduto(Produto produto);
    void removerProduto(String codProduto);
    Produto buscarProduto(String codProduto);
}
