package com.gestaoentregas.negocio.controladores;

import com.gestaoentregas.dados.beans.entrega.Entrega;
import com.gestaoentregas.dados.repositorios.IRepositorioEntrega;
import com.gestaoentregas.excecoes.ECException;
import com.gestaoentregas.excecoes.EIException;
import com.gestaoentregas.excecoes.ENCException;

public class ControladorEntrega {
    private IRepositorioEntrega repositorioEntrega;

    public ControladorEntrega(IRepositorioEntrega repositorioEntrega) {
        this.repositorioEntrega = repositorioEntrega;
    }

    public void cadastrarEntrega(Entrega entrega) throws ECException, ENCException{
        if(repositorioEntrega.buscarEntrega(entrega.getCodEntrega()).getRotaEntrega() == null && repositorioEntrega.buscarEntrega(entrega.getCodEntrega()).getRotaEntrega().getVeiculoMotoristaRota() == null){
            throw new ENCException();
        } else if(repositorioEntrega.buscarEntrega(entrega.getCodEntrega()) == null){
            repositorioEntrega.cadastrarEntrega(entrega);
        } else{
            throw new ECException();
        }

    }

    public Entrega buscarEntrega(String codigo) throws EIException {
        Entrega entrega = repositorioEntrega.buscarEntrega(codigo);
        if(entrega == null){
            throw new EIException();
        }
        return entrega;
    }

    public void atualizarEntrega(Entrega entrega) throws EIException {
        if (repositorioEntrega.buscarEntrega(entrega.getCodEntrega()) != null) {
            repositorioEntrega.atualizarEntrega(entrega);
        } else {
            throw new EIException();
        }
    }

    public void removerEntrega(String codigo) throws EIException {
        if (repositorioEntrega.buscarEntrega(codigo) != null && repositorioEntrega.buscarEntrega(codigo).getStatusEntrega() != Entrega.StatusEntrega.EM_TRANSITO) {
            repositorioEntrega.removerEntrega(codigo);
        } else {
            throw new EIException();
        }
    }
}
