package com.gestaoentregas.negocio;

import com.gestaoentregas.dados.repositorios.IRepositorioMotorista;
import com.gestaoentregas.dados.beans.motorista.Motorista;
import com.gestaoentregas.dados.repositorios.RepositorioMotorista;
import com.gestaoentregas.excecoes.DIException;
import com.gestaoentregas.excecoes.IIException;
import com.gestaoentregas.excecoes.MCException;
import com.gestaoentregas.excecoes.MIException;
import org.springframework.stereotype.Service;

@Service
public class ServicoMotorista {
    private final RepositorioMotorista repositorioMotorista;

    public ServicoMotorista(RepositorioMotorista repositorioMotorista) {
        this.repositorioMotorista = repositorioMotorista;
    }

    public void cadastrarMotorista(Motorista motorista) throws MCException, IIException, DIException {
        //CONDIÇÃO DE ERRO VERIFICADA PRIMEIRO (GUARD CAUSE)
        if (repositorioMotorista.buscarMotorista(motorista.getIdMotorista()) != null) {
            throw new MCException();
        } else if (repositorioMotorista.buscarMotorista(motorista.getIdMotorista()).getIdadeMotorista() >= 18){
            throw new IIException();
        } else if (repositorioMotorista.buscarMotorista(motorista.getIdMotorista()).getCnhMotorista() == null) {
            throw new DIException("Cnh do motorista não pode ser nula.");
        }
        repositorioMotorista.cadastrarMotorista(motorista);
    }


    public void atualizarMotorista(Motorista motorista) throws MIException {
        if (repositorioMotorista.buscarMotorista(motorista.getIdMotorista()) == null) {
            throw new MIException();
        }
        repositorioMotorista.atualizarMotorista(motorista);
    }

    public void removerMotorista(int id) throws MIException {
        if (repositorioMotorista.buscarMotorista(id) == null) {
            throw new MIException();
        }
        repositorioMotorista.removerMotorista(id);
    }

    public Motorista buscarMotorista(int id) throws MIException {
        Motorista motorista = repositorioMotorista.buscarMotorista(id);
        if (motorista == null) {
            throw new MIException();
        }
        return motorista;
    }
}
