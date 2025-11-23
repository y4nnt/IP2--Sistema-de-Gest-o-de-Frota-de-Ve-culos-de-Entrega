package com.gestaoentregas.dados.beans.entrega;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.gestaoentregas.dados.beans.VeiculoMotorista;
import com.gestaoentregas.dados.beans.motorista.Motorista;

public class Rota {
    private String origemRota;
    private List<Entrega> entregasRota;
    private List<String> pontosParada;
    private String destinoRota;
    private double distanciaKm;
    private LocalDateTime tempoEstimado;
    private VeiculoMotorista veiculoMotoristaRota;
    private String idRota;

    public Rota(String origem, double distancia, LocalDateTime tempoEstimado, VeiculoMotorista veiculoMotorista, String idRota) {
        this.origemRota = origem;
        this.destinoRota = null;
        this.distanciaKm = distancia;
        this.tempoEstimado = tempoEstimado;
        this.veiculoMotoristaRota = veiculoMotorista;
        this.entregasRota = new ArrayList<>();
        this.pontosParada = new ArrayList<>();
        this.idRota = idRota;
    }

    private void addParada(String parada) {
        if (parada != null && !parada.trim().isEmpty()) {
            this.pontosParada.add(parada);
            setDestinoRota(this.pontosParada);
        }
    }

    private void removerParada(String parada) {
        this.pontosParada.remove(parada);
        if (this.getDestinoRota().equals(parada)) {
            setDestinoRota(this.pontosParada);
        }
    }

    public void removerEntrega(Entrega entrega) {
        removerParada(entrega.getLocalEntrega());
        this.entregasRota.remove(entrega);
    }

    public void addEntrega(Entrega entrega) {
        if (entrega != null) {
            this.entregasRota.add(entrega);
            addParada(entrega.getLocalEntrega());
        }
    }

    public Entrega buscarEntregaRota(String codEntrega) {
        Entrega entrega = null;
        int indice = -1;
        for (int i = 0; i < this.entregasRota.size(); i++) {
            if (this.entregasRota.get(i).getCodEntrega().equals(codEntrega)) {
                indice = i;
            }
        }
        if (indice != -1) {
            entrega = this.entregasRota.get(indice);
        }
        return entrega;
    }

    public List<Entrega> getEntregasRota() {
        return entregasRota;
    }

    public double calcularDistancia() {
        return this.distanciaKm;
    }

    public void atualizarTempoEstimado(double distancia, Motorista motorista) {
        this.distanciaKm = distancia;
        this.veiculoMotoristaRota.setMotoristaEntrega(motorista);
    }

    public VeiculoMotorista getVeiculoMotoristaRota() {
        return veiculoMotoristaRota;
    }

    public String getOrigemRota() {
        return origemRota;
    }

    public void setOrigemRota(String origemRota) {
        this.origemRota = origemRota;
    }

    public String getDestinoRota() {
        return destinoRota;
    }

    private void setDestinoRota(List<String> pontosParada) {
        this.destinoRota = pontosParada.getLast();
    }

    public List<String> getPontosParada() {
        return pontosParada;
    }

    public String getIdRota() {
        return idRota;
    }

    @Override
    public String toString() {
        return String.format("Rota: %s -> %s | Distância: %.2f km | Tempo Estimado: %s",
                origemRota, destinoRota, distanciaKm, tempoEstimado);
    }
}