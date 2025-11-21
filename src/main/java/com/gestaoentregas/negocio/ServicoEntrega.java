package com.gestaoentregas.negocio;

import com.gestaoentregas.dados.beans.entrega.Entrega;
import com.gestaoentregas.dados.repositorios.IRepositorioEntrega;
import com.gestaoentregas.dados.repositorios.RepositorioEntrega;
import com.gestaoentregas.excecoes.ECException;
import com.gestaoentregas.excecoes.EIException;
import com.gestaoentregas.excecoes.ENCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoEntrega {
    private final RepositorioEntrega repositorioEntrega;

    @Autowired
    private Alerta alertaService;

    public ServicoEntrega(RepositorioEntrega repositorioEntrega) {
        this.repositorioEntrega = repositorioEntrega;
    }

    public void cadastrarEntrega(Entrega entrega) throws ECException, ENCException{
        if(repositorioEntrega.buscarEntrega(entrega.getCodEntrega()).getRotaEntrega() == null || repositorioEntrega.buscarEntrega(entrega.getCodEntrega()).getRotaEntrega().getVeiculoMotoristaRota() == null){
            throw new ENCException();
        } else if(repositorioEntrega.buscarEntrega(entrega.getCodEntrega()) != null){
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

    public void atualizarStatusDaEntrega(Entrega entrega, Entrega.StatusEntrega novoStatus) {

        // 1. Atualiza o objeto de dados
        entrega.atualizarStatus(novoStatus);

        // (Aqui você também salvaria no banco de dados, ex: entregaRepository.save(entrega))

        // 2. Coloca a lógica de notificação AQUI
        String email = entrega.getEmailComprador();

        if (novoStatus == Entrega.StatusEntrega.EM_TRANSITO) {
            alertaService.enviarEmailSimples(email,
                    "Pedido em rota de entrega",
                    "Boa notícia! Seu pedido já está em rota de entrega, fique atento(a) que já, já ele chega em seu endereço! \n\nAtt, EntregasPOO");

        } else if (novoStatus == Entrega.StatusEntrega.ENTREGUE) {
            alertaService.enviarEmailSimples(email,
                    "Pedido entregue",
                    "Eba! Seu pedido foi entregue, aproveite! \n\nAtt, EntregasPOO");

        } else if (novoStatus == Entrega.StatusEntrega.CANCELADA) {
            alertaService.enviarEmailSimples(email,
                    "Pedido cancelado",
                    "Que pena! Seu pedido foi cancelado, sinta-se a vontade para sempre tornar a utilizar nossos serviços. \n\nAtt, EntregasPOO");
        }
    }
}
