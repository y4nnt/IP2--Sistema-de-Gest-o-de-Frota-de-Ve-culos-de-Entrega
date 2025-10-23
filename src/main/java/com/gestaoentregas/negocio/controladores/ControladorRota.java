package com.gestaoentregas.negocio.controladores;

import com.gestaoentregas.dados.beans.entrega.Rota;
import com.gestaoentregas.dados.repositorios.IRepositorioRota;
import com.gestaoentregas.excecoes.RCException;
import com.gestaoentregas.excecoes.RIException;

public class ControladorRota {
    private IRepositorioRota repositorioRota;

    public ControladorRota(IRepositorioRota repositorioRota){
        this.repositorioRota = repositorioRota;
    }

    public void cadastrarRota(Rota rota) throws RCException{
        if(repositorioRota.buscarRota(rota.getIdRota()) == null){
            repositorioRota.cadastrarRota(rota);
        } else{
            throw new RCException();
        }
    }

    public Rota buscarRota(int idRota) throws RIException{
        Rota rota = repositorioRota.buscarRota(idRota);
        if(rota == null){
            throw new RIException();
        }
        return rota;
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
}
