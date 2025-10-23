package com.gestaoentregas.dados.beans.entrega;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.gestaoentregas.dados.beans.VeiculoMotorista;
import com.gestaoentregas.dados.beans.motorista.Motorista;

public class Rota {
    private String origemRota;
    private String destinoRota;
    private double distanciaKm;
    private LocalDateTime tempoEstimado;
    private List<String> pontosParada;
    private VeiculoMotorista veiculoMotoristaRota;
    private int idRota;

    public Rota(String origem, String destino, double distancia, LocalDateTime tempoEstimado, VeiculoMotorista veiculoMotorista, int idRota) {
        this.origemRota = origem;
        this.destinoRota = destino;
        this.distanciaKm = distancia;
        this.tempoEstimado = tempoEstimado;
        this.veiculoMotoristaRota = veiculoMotorista;
        this.pontosParada = new ArrayList<>();
        this.idRota = idRota;
    }

    public void addParada(String parada) {
        if (parada != null && !parada.trim().isEmpty()) {
            this.pontosParada.add(parada);
        }
    }

    public void removerParada(String parada) {
        this.pontosParada.remove(parada);
    }

    public double calcularDistancia() {
        return this.distanciaKm;
    }

    public void atualizarTempoEstimado(double distancia, Motorista motorista) {
        System.out.println("Tempo estimado atualizado com base na distância e nas qualidades do motorista.");
    }

    public VeiculoMotorista getVeiculoMotoristaRota() {
        return veiculoMotoristaRota;
    }

    public String getOrigemRota() {
        return origemRota;
    }

    public String getDestinoRota() {
        return destinoRota;
    }

    public List<String> getPontosParada() {
        return pontosParada;
    }

    public int getIdRota() {
        return idRota;
    }

    @Override
    public String toString() {
        return String.format("Rota: %s -> %s | Distância: %.2f km | Tempo Estimado: %s",
                origemRota, destinoRota, distanciaKm, tempoEstimado);
    }
}