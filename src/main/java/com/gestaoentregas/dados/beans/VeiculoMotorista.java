package com.gestaoentregas.dados.beans;
import com.gestaoentregas.dados.beans.motorista.Motorista;
import com.gestaoentregas.dados.beans.veiculo.Veiculo;

public class VeiculoMotorista {
    private Motorista motoristaEntrega;
    private Veiculo veiculoEntrega;


    public VeiculoMotorista(Motorista motoristaEntrega, Veiculo veiculoEntrega) {
        this.motoristaEntrega = motoristaEntrega;
        this.veiculoEntrega = veiculoEntrega;
    }

    public VeiculoMotorista() {
        this.motoristaEntrega = null;
        this.veiculoEntrega = null;
    }

    public Veiculo getVeiculoEntrega() {return veiculoEntrega;}

    public void setVeiculoEntrega(Veiculo veiculoEntrega) {this.veiculoEntrega = veiculoEntrega;}

    public Motorista getMotoristaEntrega() {return motoristaEntrega;}

    public void setMotoristaEntrega(Motorista motoristaEntrega) {this.motoristaEntrega = motoristaEntrega;}
}
