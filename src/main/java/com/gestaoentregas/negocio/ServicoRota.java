package com.gestaoentregas.negocio;

import com.gestaoentregas.dados.beans.entrega.Rota;
import com.gestaoentregas.dados.repositorios.IRepositorioRota;
import com.gestaoentregas.dados.repositorios.RepositorioRota;
import com.gestaoentregas.excecoes.ENCException;
import com.gestaoentregas.excecoes.RCException;
import com.gestaoentregas.excecoes.RIException;
import org.springframework.stereotype.Service;

@Service
public class ServicoRota {
    private final IRepositorioRota repositorioRota;

    public ServicoRota(){
        this.repositorioRota = RepositorioRota.getInstance();
    }

    public void cadastrarRota(Rota rota) throws RCException{

        if(repositorioRota.buscarRota(rota.getIdRota()).getEntregasRota().isEmpty() || repositorioRota.buscarRota(rota.getIdRota()).getVeiculoMotoristaRota() == null){
            throw new ENCException();
        } else if(repositorioRota.buscarRota(rota.getIdRota()) != null){
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

    public void removerRota(String idRota) throws RIException{
        if(repositorioRota.buscarRota(idRota) != null){
            throw new  RIException();
        }
        repositorioRota.removerRota(idRota);
    }

    public Rota buscarRota(String idRota) throws RIException{
        Rota rota = repositorioRota.buscarRota(idRota);
        if(rota == null){
            throw new RIException();
        }
        return rota;
    }
}
