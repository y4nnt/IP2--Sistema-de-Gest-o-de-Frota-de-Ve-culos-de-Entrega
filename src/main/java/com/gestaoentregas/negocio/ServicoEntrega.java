package com.gestaoentregas.negocio;

import com.gestaoentregas.dados.beans.entrega.Entrega;
import com.gestaoentregas.dados.beans.entrega.StatusEntrega;
import com.gestaoentregas.dados.repositorios.IRepositorioEntrega;
import com.gestaoentregas.dados.repositorios.RepositorioEntrega;
import com.gestaoentregas.excecoes.ECException;
import com.gestaoentregas.excecoes.EIException;
import com.gestaoentregas.excecoes.ENCException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServicoEntrega {
    private final Alerta alerta;
    private final RepositorioEntrega repositorioEntrega;

    public ServicoEntrega(RepositorioEntrega repositorioEntrega, Alerta alerta) {
        this.repositorioEntrega = repositorioEntrega;
        this.alerta = alerta;
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
        if (repositorioEntrega.buscarEntrega(codigo) == null || repositorioEntrega.buscarEntrega(codigo).getStatusEntrega() == StatusEntrega.EM_TRANSITO) {
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

    public void atualizarStatusEntrega(Entrega entrega, StatusEntrega novoStatus) throws EIException {
        if (entrega == null) {
            throw new EIException("Entrega inválida informada.");
        }

        entrega.atualizarStatus(novoStatus);
        repositorioEntrega.atualizarEntrega(entrega);

        String emailDestino = entrega.getEmailComprador();

        if (emailDestino == null || emailDestino.trim().isEmpty()) {
            System.out.println("Aviso: Entrega sem e-mail de comprador. Notificação não enviada.");
            return;
        }

        String assunto = null;
        String mensagem = null;

        switch (novoStatus) {
            case ENTREGUE:
                assunto = "Pedido Entregue";
                mensagem = "Oba! Seu pedido acaba de ser entregue, aproveite! \n\nAtt, \nEntregas POO.";
                break;

            case EM_TRANSITO:
                assunto = "Pedido em trânsito";
                mensagem = "Seu pedido acaba de sair em trânsito! Tenha paciência que logo mais ele chega! \n\nAtt, \nEntregas POO.";
                break;

            case CANCELADA:
                assunto = "Pedido cancelado";
                mensagem = "Seu pedido foi cancelado. Espero poder ter você como cliente novamente. \n\nAtt, \nEntregas POO.";
                break;

            default:
                // Se for outro status (ex: PENDENTE), não faz nada e sai
                return;
        }

        // 5. Envia o E-mail (Uma única chamada)
        if (assunto != null) {
            alerta.enviarEmailSimples(emailDestino, assunto, mensagem);
        }
    }


    public void cancelarEntrega(Entrega entrega, StatusEntrega novoStatus) throws EIException {
        Entrega entrega1 = buscarEntrega(entrega.getCodEntrega());
        if(entrega1.getStatusEntrega() == StatusEntrega.EM_TRANSITO){
            throw new EIException ("A entrega não pode ser cancelada em trânsito");
        }
        entrega.atualizarStatus(novoStatus);
    }

    public ArrayList<Entrega> listarEntregas() {
        return repositorioEntrega.listarEntregas();
    }
}
