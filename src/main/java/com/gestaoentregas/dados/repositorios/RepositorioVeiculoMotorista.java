package com.gestaoentregas.dados.repositorios;

import com.gestaoentregas.dados.beans.VeiculoMotorista;
import com.gestaoentregas.excecoes.CIException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Repository
public class RepositorioVeiculoMotorista implements IRepositorioVeiculoMotorista {
    private ArrayList<VeiculoMotorista> veiculosmotoristas;

    private RepositorioVeiculoMotorista() {
        this.veiculosmotoristas = new ArrayList<>();
    }

    @Override
    public void cadastrar(VeiculoMotorista associacao) {
        veiculosmotoristas.add(associacao);
    }

    @Override
    public void removerPorVeiculo(int idVeiculo) {
        for (int i = 0; i < this.veiculosmotoristas.size(); i++) {
            if (veiculosmotoristas.get(i).getVeiculoEntrega().getIdVeiculo() == idVeiculo) {
                veiculosmotoristas.remove(i);
                i = veiculosmotoristas.size();
            }
        }
    }

    @Override
    public VeiculoMotorista buscarPorVeiculo(int idVeiculo) {
        VeiculoMotorista veiculomotorista = null;
        for (int i = 0; i < this.veiculosmotoristas.size(); i++) {
            if (veiculosmotoristas.get(i).getVeiculoEntrega().getIdVeiculo() == idVeiculo) {
                veiculomotorista = this.veiculosmotoristas.get(i);
            }
        }
        return veiculomotorista;
    }

    @Override
    public void removerPorMotorista(int idMotorista) {
        for (int i = 0; i < this.veiculosmotoristas.size(); i++) {
            if (veiculosmotoristas.get(i).getMotoristaEntrega().getIdMotorista() == idMotorista) {
                veiculosmotoristas.remove(i);
                i = veiculosmotoristas.size();
            }
        }
    }

    @Override
    public VeiculoMotorista buscarPorMotorista(int idMotorista) {
        VeiculoMotorista veiculomotorista = null;
        for (int i = 0; i < this.veiculosmotoristas.size(); i++) {
            if (veiculosmotoristas.get(i).getMotoristaEntrega().getIdMotorista() == idMotorista) {
                veiculomotorista = this.veiculosmotoristas.get(i);
            }
        }
        return veiculomotorista;
    }

    /* Aqui seria só um toString não?
    public VeiculoMotorista[] listar() {
        VeiculoMotorista[] copia = new VeiculoMotorista[indice];
        System.arraycopy(lista, 0, copia, 0, indice);
        return copia;
    }*/

}
