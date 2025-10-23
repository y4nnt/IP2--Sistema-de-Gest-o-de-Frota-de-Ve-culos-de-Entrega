package com.gestaoentregas.dados.beans.entrega;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Entrega {

    public enum StatusEntrega {
        PENDENTE, EM_TRANSITO, ENTREGUE, CANCELADA, PROBLEMA
    }

    private String codEntrega;
    private Rota rotaEntrega;
    private StatusEntrega statusEntrega;
    private String observacoesEntrega;
    private String problemasEntrega;
    private List<Produto> produtos;
    private LocalDateTime dataHoraEntrega;
    private String emailComprador;


    public Entrega(String codEntrega, Rota rota, String observacoes, String problemas, StatusEntrega status, String emailComprador, int  idEntrega) {
        this.codEntrega = codEntrega;
        this.rotaEntrega = rota;
        this.observacoesEntrega = observacoes;
        this.problemasEntrega = problemas;
        this.statusEntrega = status;
        this.dataHoraEntrega = null;
        this.emailComprador = emailComprador;
        this.produtos = new ArrayList<>();

        if (problemas != null && !problemas.trim().isEmpty()) {
            this.statusEntrega = StatusEntrega.PROBLEMA;
            //é dever da gui imprimir na tela
        }
    }

    public void addProduto(Produto produto) {
        if (produto != null) {
            this.produtos.add(produto);
        }
    }

    public void atualizarStatus(StatusEntrega novoStatus) {
        this.statusEntrega = novoStatus;

        if (novoStatus == StatusEntrega.ENTREGUE) {
            this.dataHoraEntrega = LocalDateTime.now();
        }

    }

    public String getCodEntrega() {
        return codEntrega;
    }

    public StatusEntrega getStatusEntrega() {
        return statusEntrega;
    }

    public String getEmailComprador() {
        return emailComprador;
    }

    @Override
    public String toString() {
        // ... seu toString() está ótimo ...
        return super.toString();
    }
}