package com.gestaoentregas.negocio;

import com.gestaoentregas.dados.repositorios.IRepositorioMotorista;
import com.gestaoentregas.dados.beans.motorista.Motorista;

public class ControladorMotorista {
    private IRepositorioMotorista repositorioMotorista;

    public ControladorMotorista(IRepositorioMotorista repositorioMotorista) {
        this.repositorioMotorista = repositorioMotorista;
    }

    public void cadastrarMotorista(Motorista motorista) {
        Motorista existente = repositorioMotorista.buscarMotorista(motorista.getIdMotorista());
        if (existente == null) {
            repositorioMotorista.cadastrarMotorista(motorista);
        } else {
            System.out.println("Já existe um motorista com este ID!");
        }
    }

    public Motorista buscarMotorista(int id) {
        Motorista motorista = repositorioMotorista.buscarMotorista(id);
        if (motorista == null) {
            System.out.println("Motorista não encontrado!");
        }
        return motorista;
    }

    public void atualizarMotorista(Motorista motorista) {
        Motorista existente = repositorioMotorista.buscarMotorista(motorista.getIdMotorista());
        if (existente != null) {
            repositorioMotorista.atualizarMotorista(motorista);
        } else {
            System.out.println("Motorista não encontrado para atualização!");
        }
    }

    public void removerMotorista(int id) {
        Motorista existente = repositorioMotorista.buscarMotorista(id);
        if (existente != null) {
            repositorioMotorista.removerMotorista(id);
        } else {
            System.out.println("Motorista não encontrado para remoção!");
        }
    }
}
