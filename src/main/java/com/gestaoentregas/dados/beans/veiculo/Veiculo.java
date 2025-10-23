package com.gestaoentregas.dados.beans.veiculo;

import java.util.ArrayList;
import java.util.List;

public class Veiculo {
    private String placaVeiculo;
    private String modeloVeiculo;
    private double capacidadeVeiculo;
    private StatusVeiculo statusVeiculo;
    private List<Manutencao> historicoManutecaoVeiculo;
    private int idVeiculo;
    //private List<Entrega> historicoEntregaVeiculo; esperando criar a classe entrega


    public Veiculo(String placaVeiculo, String modeloVeiculo, double capacidadeVeiculo) {
        this.placaVeiculo = placaVeiculo;
        this.modeloVeiculo = modeloVeiculo;
        this.capacidadeVeiculo = capacidadeVeiculo;
        this.historicoManutecaoVeiculo = new ArrayList<>();
    }

    public String getPlacaVeiculo() {
        return this.placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public String getModeloVeiculo() {
        return this.modeloVeiculo;
    }

    public void setModeloVeiculo(String modeloVeiculo) {
        this.modeloVeiculo = modeloVeiculo;
    }

    public double getCapacidadeVeiculo() {
        return this.capacidadeVeiculo;
    }

    public void setCapacidadeVeiculo(double capacidadeVeiculo) {
        this.capacidadeVeiculo = capacidadeVeiculo;
    }

    public StatusVeiculo getStatusVeiculo() {
        return this.statusVeiculo;
    }

    public void setStatusVeiculo(StatusVeiculo statusVeiculo) {
        this.statusVeiculo = statusVeiculo;
    }

    public List<Manutencao> getHistoricoManutecaoVeiculo() {
        return this.historicoManutecaoVeiculo;
    }

    public void addManutencao(Manutencao manutencao) {
        this.historicoManutecaoVeiculo.add(manutencao);
    }

    public int getIdVeiculo() {return this.idVeiculo; }

    public void setIdVeiculo(int idVeiculo) {this.idVeiculo = idVeiculo;}

}
