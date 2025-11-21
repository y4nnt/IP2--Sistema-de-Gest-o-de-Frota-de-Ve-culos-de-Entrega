package com.gestaoentregas.dados.beans.entrega;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Entrega {


    public enum StatusEntrega {
        PENDENTE, EM_TRANSITO, ENTREGUE, CANCELADA, PROBLEMA
    }

    private String codEntrega;
    private String localEntrega;
    private StatusEntrega statusEntrega;
    private String observacoesEntrega;
    private String problemasEntrega;
    private List<Produto> produtos;
    private LocalDateTime dataHoraEntrega;
    private String emailComprador;


    public Entrega(String codEntrega, String localEntrega, String observacoes, String problemas, StatusEntrega status, String emailComprador) {
        this.codEntrega = codEntrega;
        this.localEntrega = localEntrega;
        this.observacoesEntrega = observacoes;
        this.problemasEntrega = problemas;
        this.statusEntrega = status;
        this.dataHoraEntrega = null;
        this.emailComprador = emailComprador;
        this.produtos = new ArrayList<>();


        if (problemas != null && !problemas.trim().isEmpty()) {
            this.statusEntrega = StatusEntrega.PROBLEMA;
            //É dever da gui imprimir na tela
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

    public void setCodEntrega(String codEntrega) {
        this.codEntrega = codEntrega;
    }

    public StatusEntrega getStatusEntrega() {
        return statusEntrega;
    }

    public String getEmailComprador() {
        return emailComprador;
    }

    public void setEmailComprador(String emailComprador) {
        this.emailComprador = emailComprador;
    }

    public String getLocalEntrega() {
        return localEntrega;
    }

    public void setRotaEntrega(String localEntrega) {
        this.localEntrega = localEntrega;
    }

    public String getObservacoesEntrega() {
        return observacoesEntrega;
    }

    public void setObservacoesEntrega(String observacoesEntrega) {
        this.observacoesEntrega = observacoesEntrega;
    }

    public String getProblemasEntrega() {
        return problemasEntrega;
    }

    public void setProblemasEntrega(String problemasEntrega) {
        this.problemasEntrega = problemasEntrega;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public LocalDateTime getDataHoraEntrega() {
        return dataHoraEntrega;
    }

    public void setDataHoraEntrega(LocalDateTime dataHoraEntrega) {
        this.dataHoraEntrega = dataHoraEntrega;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}