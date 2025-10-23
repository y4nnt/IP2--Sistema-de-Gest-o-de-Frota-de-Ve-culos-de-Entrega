package com.gestaoentregas.negocio;

import com.gestaoentregas.dados.beans.entrega.Rota;
import com.gestaoentregas.dados.repositorios.IRepositorioRota;
import com.gestaoentregas.dados.repositorios.RepositorioRota;
import com.gestaoentregas.excecoes.RCException;
import com.gestaoentregas.excecoes.RIException;

public class ServicoRota {
    private final IRepositorioRota repositorioRota;

    public ServicoRota(){
        this.repositorioRota = RepositorioRota.getInstance();
    }

    public void cadastrarRota(Rota rota) throws RCException{
        if(repositorioRota.buscarRota(rota.getIdRota()) == null){
            repositorioRota.cadastrarRota(rota);
        } else{
            throw new RCException();
        }
    }

    public void atualizarRota(Rota rota) throws RIException{
        if(repositorioRota.buscarRota(rota.getIdRota()) != null){
            repositorioRota.atualizarRota(rota);
        } else{
            throw new RIException();
        }
    }

    public void removerRota(int idRota) throws RIException{
        if(repositorioRota.buscarRota(idRota) != null){
            repositorioRota.removerRota(idRota);
        } else{
            throw new RIException();
        }
    }

    public Rota buscarRota(int idRota) throws RIException{
        Rota rota = repositorioRota.buscarRota(idRota);
        if(rota == null){
            throw new RIException();
        }
        return rota;
    }
}
