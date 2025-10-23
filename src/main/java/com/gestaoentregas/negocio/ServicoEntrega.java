package com.gestaoentregas.negocio;

import com.gestaoentregas.dados.beans.entrega.Entrega;
import com.gestaoentregas.dados.repositorios.IRepositorioEntrega;
import com.gestaoentregas.dados.repositorios.RepositorioEntrega;
import com.gestaoentregas.excecoes.ECException;
import com.gestaoentregas.excecoes.EIException;

public class ServicoEntrega {
    private final IRepositorioEntrega repositorioEntrega;

    public ServicoEntrega() {
        this.repositorioEntrega = RepositorioEntrega.getInstance();
    }

    public void cadastrarEntrega(Entrega entrega) throws ECException{
        if(repositorioEntrega.buscarEntrega(entrega.getCodEntrega()) == null){
            repositorioEntrega.cadastrarEntrega(entrega);
        } else{
            throw new ECException();
        }
    }

    public void atualizarEntrega(Entrega entrega) throws EIException {
        if (repositorioEntrega.buscarEntrega(entrega.getCodEntrega()) != null) {
            repositorioEntrega.atualizarEntrega(entrega);
        } else {
            throw new EIException();
        }
    }

    public void removerEntrega(String codigo) throws EIException {
        if (repositorioEntrega.buscarEntrega(codigo) != null) {
            repositorioEntrega.removerEntrega(codigo);
        } else {
            throw new EIException();
        }
    }

    public Entrega buscarEntrega(String codigo) throws EIException {
        Entrega entrega = repositorioEntrega.buscarEntrega(codigo);
        if(entrega == null){
            throw new EIException();
        }
        return entrega;
    }
}
