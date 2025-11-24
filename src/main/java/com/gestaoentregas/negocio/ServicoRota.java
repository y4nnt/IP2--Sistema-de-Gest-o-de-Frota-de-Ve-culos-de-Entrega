package com.gestaoentregas.negocio;

import com.gestaoentregas.dados.beans.entrega.Rota;
import com.gestaoentregas.dados.repositorios.IRepositorioRota;
import com.gestaoentregas.dados.repositorios.RepositorioRota;
import com.gestaoentregas.excecoes.ENCException;
import com.gestaoentregas.excecoes.RCException;
import com.gestaoentregas.excecoes.RIException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServicoRota {
    private final RepositorioRota repositorioRota;

    public ServicoRota(RepositorioRota repositorioRota) {
        this.repositorioRota = repositorioRota;
    }

    public void cadastrarRota(Rota rota) throws RCException{

        // Tirei uma parte da exceção pq tava dando erro, lembrar de mostrar
        if(repositorioRota.buscarRota(rota.getIdRota()) != null){
            throw new  RCException();
        }
        repositorioRota.cadastrarRota(rota);
    }

    public void atualizarRota(Rota rota) throws RIException{
        if(repositorioRota.buscarRota(rota.getIdRota()) == null){
            throw new  RIException();
        }
        repositorioRota.atualizarRota(rota);
    }

    public void removerRota(int idRota) throws RIException{
        if(repositorioRota.buscarRota(idRota) != null){
            throw new  RIException();
        }
        repositorioRota.removerRota(idRota);
    }

    public Rota buscarRota(int idRota) throws RIException{
        Rota rota = repositorioRota.buscarRota(idRota);
        if(rota == null){
            throw new RIException();
        }
        return rota;
    }

    public ArrayList<Rota> listarRotas() {
        return repositorioRota.listarRotas();
    }
}
