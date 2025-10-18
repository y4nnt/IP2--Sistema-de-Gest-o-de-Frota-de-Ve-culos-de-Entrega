package com.gestaoentregas.classes.entrega;

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
    private List<Produto> produtos; // Relacionamento com a nova classe Produto
    private LocalDateTime dataHoraEntrega;

    public Entrega(String codEntrega, Rota rota, String observacoes, String problemas, StatusEntrega status) {
        this.codEntrega = codEntrega;
        this.rotaEntrega = rota;
        this.observacoesEntrega = observacoes;
        this.produtos = new ArrayList<>();
        this.statusEntrega = status;
        this.dataHoraEntrega = null;

        if (problemas != null && !problemas.trim().isEmpty()) {
            this.statusEntrega = StatusEntrega.PROBLEMA;
            System.out.println("Entrega criada com PROBLEMA: " + problemas);
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Entrega [Cód: ").append(codEntrega).append("]\n");
        sb.append("Status: ").append(statusEntrega).append("\n");
        sb.append("Data/Hora Entrega: ").append(dataHoraEntrega != null ? dataHoraEntrega : "Pendente").append("\n");
        sb.append("Rota: ").append(rotaEntrega.getOrigemRota()).append(" -> ").append(rotaEntrega.getDestinoRota()).append("\n");
        sb.append("Observações: ").append(observacoesEntrega).append("\n");
        sb.append("Produtos:\n");

        for (Produto p : produtos) {
            sb.append("    - ").append(p.getNome()).append(" (R$ ").append(String.format("%.2f", p.getValor())).append(")\n");
        }

        return sb.toString();
    }
}
