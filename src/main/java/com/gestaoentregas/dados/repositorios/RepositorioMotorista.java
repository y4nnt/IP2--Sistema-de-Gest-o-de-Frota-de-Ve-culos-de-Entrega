package com.gestaoentregas.dados.repositorios;

import com.gestaoentregas.dados.beans.entrega.Entrega;
import com.gestaoentregas.dados.beans.motorista.Motorista;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Repository
public class RepositorioMotorista implements IRepositorioMotorista {
    private ArrayList<Motorista> motoristas;
    private int contadorId = 101;

    private RepositorioMotorista() {
        this.motoristas = new ArrayList<>();
    }

    @Override
    public void cadastrarMotorista(Motorista motorista) {
        if (motorista.getId() == 0) {
            motorista.setId(contadorId);
            contadorId++;
        }
        motoristas.add(motorista);
    }

    @Override
    public void atualizarMotorista(Motorista motorista) {
        int i = procurarIndice(motorista.getId());
        if (i != -1) {
            this.motoristas.set(i, motorista);
        }
    }

    @Override
    public void removerMotorista(int id) {
        int i = procurarIndice(id);
        if (i != -1) {
            this.motoristas.remove(i);
        }
    }

    @Override
    public Motorista buscarMotorista(int id) {
        Motorista motorista = null;
        int i = procurarIndice(id);
        if (i != -1) {
            motorista = this.motoristas.get(i);
        }
        return motorista;
    }

    // busca o índice do motorista pelo ID
    private int procurarIndice(int id) {
        int indice = -1;
        for (int i = 0; i < this.motoristas.size(); i++) {
            if (this.motoristas.get(i).getId() == id) {
                indice = i;
            }
        }
        return indice;
    }

    @Override
    public ArrayList<Motorista> listarMotoristas() {
        return new ArrayList<>(this.motoristas);
    }
}
