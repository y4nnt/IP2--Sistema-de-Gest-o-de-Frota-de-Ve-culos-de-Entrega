package com.gestaoentregas.classes;
import com.gestaoentregas.classes.motorista.*;
import com.gestaoentregas.classes.veiculo.*;

public class VeiculoMotorista {
    private Motorista motoristaEntrega;
    private Veiculo veiculoEntrega;


    public VeiculoMotorista(Motorista motoristaEntrega, Veiculo veiculoEntrega) {
        this.motoristaEntrega = motoristaEntrega;
        this.veiculoEntrega = veiculoEntrega;
    }    
}
