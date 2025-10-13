package com.gestaoentregas.main;

import com.gestaoentregas.classes.motorista.*;
import com.gestaoentregas.classes.veiculo.*;


import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        PeriodoIndisponivel ferias = new PeriodoIndisponivel(LocalDate.of(2025, 8, 1),
                LocalDate.of(2025, 9, 1),
                null);
        Motorista motorista = new Motorista("José",
                "81 99126-2475",
                "709.106.064-47",
                "123.456.789");

        System.out.println(motorista.toString());

        motorista.addFerias(ferias.getDataInicio(), ferias.getDataFim());

        System.out.println(motorista.toString());

        ferias.contem(LocalDate.of(2025, 8, 10));

        if(!motorista.estaDisponivel(LocalDate.of(2025, 8, 3))){
            System.out.println("Não está disponível!");
        }
        else{
            System.out.println("Está disponível!");
        }
    }
}
