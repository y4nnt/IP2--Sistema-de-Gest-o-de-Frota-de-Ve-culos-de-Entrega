package com.gestaoentregas.negocio;

import com.gestaoentregas.dados.beans.entrega.Entrega;
import com.gestaoentregas.dados.repositorios.IRepositorioEntrega;
import com.gestaoentregas.dados.repositorios.RepositorioEntrega;
import com.gestaoentregas.excecoes.ECException;
import com.gestaoentregas.excecoes.EIException;
import com.gestaoentregas.excecoes.ENCException;

public class ServicoEntrega {
    private final IRepositorioEntrega repositorioEntrega;

    public ServicoEntrega() {
        this.repositorioEntrega = RepositorioEntrega.getInstance();
    }

    public void cadastrarEntrega(Entrega entrega) throws ECException, ENCException{
        if(repositorioEntrega.buscarEntrega(entrega.getCodEntrega()) != null){
            throw new ECException();
        }
        repositorioEntrega.cadastrarEntrega(entrega);
    }

    public void atualizarEntrega(Entrega entrega) throws EIException {
        if (repositorioEntrega.buscarEntrega(entrega.getCodEntrega()) == null) {
            throw new EIException();
        }
        repositorioEntrega.atualizarEntrega(entrega);
    }

    public void removerEntrega(String codigo) throws EIException {
        if (repositorioEntrega.buscarEntrega(codigo) == null || repositorioEntrega.buscarEntrega(codigo).getStatusEntrega() == Entrega.StatusEntrega.EM_TRANSITO) {
            throw new EIException();
        }
        repositorioEntrega.removerEntrega(codigo);
    }

    public Entrega buscarEntrega(String codigo) throws EIException {
        Entrega entrega = repositorioEntrega.buscarEntrega(codigo);
        if(entrega == null){
            throw new EIException();
        }
        return entrega;
    }

    public void cancelarEntrega(Entrega entrega, Entrega.StatusEntrega novoStatus) throws EIException {
        Entrega entrega1 = buscarEntrega(entrega.getCodEntrega());
        if(entrega1.getStatusEntrega() == Entrega.StatusEntrega.EM_TRANSITO){
            throw new EIException ("A entrega não pode ser cancelada em trânsito");
        }
        entrega.atualizarStatus(novoStatus);
    }
}
