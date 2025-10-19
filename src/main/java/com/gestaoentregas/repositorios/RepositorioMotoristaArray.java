package com.gestaoentregas.repositorios;

import com.gestaoentregas.classes.motorista.Motorista;

public class RepositorioMotoristaArray implements IRepositorioMotorista {
    private Motorista[] motoristas;
    private int proximo = 0;

    public RepositorioMotoristaArray(int tamanho) {
        this.motoristas = new Motorista[tamanho];
        this.proximo = 0;
    }

    @Override
    public void cadastrarMotorista(Motorista motorista) {
        if (this.proximo == this.motoristas.length) {
            duplicaArrayMotorista();
        }
        this.motoristas[this.proximo] = motorista;
        this.proximo++;
        System.out.println("Motorista cadastrado com sucesso!");
    }

    @Override
    public Motorista buscarMotorista(int id) {
        int i = procurarIndice(id);
        if (i < this.proximo) {
            return this.motoristas[i];
        }
        return null; // não encontrado
    }

    @Override
    public void atualizarMotorista(Motorista motorista) {
        int i = procurarIndice(motorista.getIdMotorista());
        if (i < this.proximo) {
            this.motoristas[i] = motorista;
            System.out.println("Motorista atualizado com sucesso!");
        } else {
            System.out.println("Motorista não encontrado!");
        }
    }

    @Override
    public void removerMotorista(int id) {
        int i = procurarIndice(id);
        if (i < this.proximo) {
            // substitui o elemento removido pelo último
            this.motoristas[i] = this.motoristas[this.proximo - 1];
            this.motoristas[this.proximo - 1] = null;
            this.proximo--;
            System.out.println("Motorista removido com sucesso!");
        } else {
            System.out.println("Motorista não encontrado!");
        }
    }

    // busca o índice do motorista pelo ID
    private int procurarIndice(int id) {
        for (int i = 0; i < this.proximo; i++) {
            if (this.motoristas[i].getIdMotorista() == id) {
                return i;
            }
        }
        return this.proximo; // não encontrado
    }

    // duplica o array quando cheio
    private void duplicaArrayMotorista() {
        int novoTamanho = (this.motoristas.length > 0) ? this.motoristas.length * 2 : 1;
        Motorista[] arrayDuplicado = new Motorista[novoTamanho];
        for (int i = 0; i < this.proximo; i++) {
            arrayDuplicado[i] = this.motoristas[i];
        }
        this.motoristas = arrayDuplicado;
    }
}
