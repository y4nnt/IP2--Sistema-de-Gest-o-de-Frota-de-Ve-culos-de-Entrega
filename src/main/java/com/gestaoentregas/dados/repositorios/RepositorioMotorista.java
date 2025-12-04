package com.gestaoentregas.dados.repositorios;

import com.gestaoentregas.dados.beans.entrega.Entrega;
import com.gestaoentregas.dados.beans.motorista.Motorista;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Repository
public class RepositorioMotorista implements IRepositorioMotorista {
    private ArrayList<Motorista> motoristas;

    private RepositorioMotorista() {
        this.motoristas = new ArrayList<>();
    }

    @Override
    public void cadastrarMotorista(Motorista motorista) {
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
        System.out.println("=== RAIO-X DO REPOSITÓRIO ===");
        System.out.println("Procurando por Motorista ID: " + id);
        System.out.println("Tamanho da lista de motoristas: " + this.motoristas.size());

        for (Motorista m : this.motoristas) {
            System.out.println(" -> Encontrei na lista: [Nome: " + m.getNomeMotorista() + " | ID: " + m.getId() + "]");

            if (m.getId() == id) {
                System.out.println("=== ENCONTRADO! ===");
                return m;
            }
        }

        System.out.println("=== NÃO ENCONTRADO ===");
        return null;
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
