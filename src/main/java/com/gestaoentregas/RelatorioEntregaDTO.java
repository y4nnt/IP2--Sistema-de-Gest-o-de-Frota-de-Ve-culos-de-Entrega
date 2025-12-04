package com.gestaoentregas;

import com.gestaoentregas.dados.beans.entrega.Entrega;
import com.gestaoentregas.dados.beans.entrega.StatusEntrega;
import com.gestaoentregas.dados.beans.entrega.Rota;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RelatorioEntregaDTO {
    private String codEntrega; // Útil se precisar clicar na linha para ver detalhes
    private LocalDateTime data;
    private String nomeMotorista;
    private String placaVeiculo;
    private String descricaoRota;
    private StatusEntrega status;

    // Construtor Vazio (Necessário para algumas bibliotecas)
    public RelatorioEntregaDTO() {
    }

    // Construtor Completo
    public RelatorioEntregaDTO(String codEntrega, LocalDateTime data, StatusEntrega status, Rota rota) {
        this.codEntrega = codEntrega;
        this.data = data;
        this.status = status;

        if (rota.getMotoristaRota() != null) {
            this.nomeMotorista = rota.getMotoristaRota().getNomeMotorista();
        } else {
            this.nomeMotorista = "Sem Motorista";
        }

        if (rota.getMotoristaRota().getVeiculoMotorista() != null) {
            this.placaVeiculo = rota.getMotoristaRota().getVeiculoMotorista().getPlacaVeiculo();
        } else {
            this.placaVeiculo = "Sem Placa";
        }
    }

    // --- A "Sacada Mestre": Construtor que converte Entidade -> DTO ---
    // Isso limpa muito o seu código no Service!
    public RelatorioEntregaDTO(Entrega entrega, Rota rota) {
        this.codEntrega = entrega.getCodEntrega();
        this.data = entrega.getDataHoraEntrega();

        /*// Null checks (evita NullPointerException se algo estiver faltando)
        this.nomeMotorista = (entrega.g() != null) ? entrega.getMotorista().getNome() : "N/A";
        this.placaVeiculo = (entrega.getVeiculo() != null) ? entrega.getVeiculo().getPlaca() : "N/A";*/

        // Supondo que Rota tenha origem e destino
        if (rota != null) {
            this.descricaoRota = rota.getOrigemRota() + " -> " + rota.getDestinoRota();
        } else {
            this.descricaoRota = "Rota não definida";
        }

        this.status = entrega.getStatusEntrega(); // Ex: "PENDENTE", "CONCLUIDO"
    }

    // --- Getters e Setters (Essenciais para o JavaFX ler os dados) ---

    public String getCODEntrega() { return codEntrega; }
    public void setCodEntrega(String codEntrega) { this.codEntrega = codEntrega; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }

    public String getNomeMotorista() { return nomeMotorista; }
    public void setNomeMotorista(String nomeMotorista) { this.nomeMotorista = nomeMotorista; }

    public String getPlacaVeiculo() { return placaVeiculo; }
    public void setPlacaVeiculo(String placaVeiculo) { this.placaVeiculo = placaVeiculo; }

    public String getDescricaoRota() { return descricaoRota; }
    public void setDescricaoRota(String descricaoRota) { this.descricaoRota = descricaoRota; }

    public StatusEntrega getStatus() { return status; }
    public void setStatus(StatusEntrega status) { this.status = status; }

    // --- Método auxiliar para exibir data formatada (Opcional) ---
    public String getDataFormatada() {
        return data != null ? data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
    }
}
