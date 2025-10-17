package com.gestaoentregas.classes.veiculomotorista;
import com.gestaoentregas.classes.motorista.*;
import com.gestaoentregas.classes.veiculo.*;

public class VeiculoMotorista {
    private Motorista motoristaEntrega;
    private Veiculo veiculoEntrega;


    public VeiculoMotorista(Motorista motoristaEntrega, Veiculo veiculoEntrega) {
        this.motoristaEntrega = motoristaEntrega;
        this.veiculoEntrega = veiculoEntrega;
    }
    public Veiculo getVeiculoEntrega() {return veiculoEntrega;}

    public void setVeiculoEntrega(Veiculo veiculoEntrega) {this.veiculoEntrega = veiculoEntrega;}

    public Motorista getMotoristaEntrega() {return motoristaEntrega;}

    public void setMotoristaEntrega(Motorista motoristaEntrega) {this.motoristaEntrega = motoristaEntrega;}
}
