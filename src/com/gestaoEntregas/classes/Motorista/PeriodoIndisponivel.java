package com.gestaoEntregas.classes.Motorista;

import java.time.LocalDate;

public class PeriodoIndisponivel {
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String motivo;

    public PeriodoIndisponivel(LocalDate inicio, LocalDate fim, String motivo){
        this.dataInicio = inicio;
        this.dataFim = fim;
        this.motivo = motivo;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public boolean contem(LocalDate data){
        if(data.isAfter(dataInicio) && data.isBefore(dataFim)){
            return true;
        }
        else{
            return false;
        }
    }
}
