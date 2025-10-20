package com.gestaoentregas.negocio.controladores;

import com.gestaoentregas.dados.repositorios.IRepositorioMotorista;
import com.gestaoentregas.dados.beans.motorista.Motorista;
import com.gestaoentregas.excecoes.MCException;
import com.gestaoentregas.excecoes.MIException;

public class ControladorMotorista {
    private IRepositorioMotorista repositorioMotorista;

    public ControladorMotorista(IRepositorioMotorista repositorioMotorista) {
        this.repositorioMotorista = repositorioMotorista;
    }

    public void cadastrarMotorista(Motorista motorista) throws MCException {
        if (repositorioMotorista.buscarMotorista(motorista.getIdMotorista()) == null) {
            repositorioMotorista.cadastrarMotorista(motorista);
        } else {
            throw new MCException();
        }
    }

    public Motorista buscarMotorista(int id) throws MIException {
        Motorista motorista = repositorioMotorista.buscarMotorista(id);
        if (motorista == null) {
            throw new MIException();
        }
        return motorista;
    }

    public void atualizarMotorista(Motorista motorista) throws MIException {
        if (repositorioMotorista.buscarMotorista(motorista.getIdMotorista()) != null) {
            repositorioMotorista.atualizarMotorista(motorista);
        } else {
            throw new MIException();
        }
    }

    public void removerMotorista(int id) throws MIException {
        if (repositorioMotorista.buscarMotorista(id) != null) {
            repositorioMotorista.removerMotorista(id);
        } else {
            throw new MIException();
        }
    }
}
