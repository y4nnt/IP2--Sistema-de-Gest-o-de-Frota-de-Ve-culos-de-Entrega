package com.gestaoentregas.dados.repositorios;

import com.gestaoentregas.dados.beans.motorista.Motorista;

import java.util.ArrayList;

public class RepositorioMotorista implements IRepositorioMotorista {
    private ArrayList<Motorista> motoristas;

    private static ArrayList<RepositorioMotorista> repositorioMotorista;

    private RepositorioMotorista() {
        this.motoristas = new ArrayList<>();
    }

    public static ArrayList<RepositorioMotorista> getInstance() {
        // Se a instância ainda não foi criada...
        if (repositorioMotorista == null) {
            // ...cria a única instância
            repositorioMotorista = new ArrayList<RepositorioMotorista>();
        }
        // Retorna a instância que já existe ou acabou de ser criada
        return repositorioMotorista;
    }

    @Override
    public void cadastrarMotorista(Motorista motorista) {
        motoristas.add(motorista);
    }

    @Override
    public Motorista buscarMotorista(int id) {
        Motorista motorista = null;
        int i = procurarIndice(id);
        if (i != 0) {
            motorista = this.motoristas.get(i);
        }
        return motorista; // não encontrado
    }

    @Override
    public void atualizarMotorista(Motorista motorista) {
        int i = procurarIndice(motorista.getIdMotorista());
        if (i != -1) {
            this.motoristas.set(i, motorista);
        }
    }

    @Override
    public void removerMotorista(int id) {
        int i = procurarIndice(id);
        // substitui o elemento removido pelo último
        if (i != 0) {
            this.motoristas.remove(i);
        }
    }

    // busca o índice do motorista pelo ID
    private int procurarIndice(int id) {
        int indice = -1;
        for (int i = 0; i < this.motoristas.size(); i++) {
            if (this.motoristas.get(i).getIdMotorista() == id) {
                indice = i;
            }
        }
        return indice; // não encontrado
    }

}
