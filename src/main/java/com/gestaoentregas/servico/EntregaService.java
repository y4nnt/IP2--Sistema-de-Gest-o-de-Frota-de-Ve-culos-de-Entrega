package com.gestaoentregas.servico; // Ou um pacote de "serviços"

import com.gestaoentregas.dados.beans.entrega.Entrega;
import com.gestaoentregas.dados.beans.entrega.Entrega.StatusEntrega;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/*
@Service
public class EntregaService {

    @Autowired
        private Alerta alertaService;

    /**
     * Este método agora contém a LÓGICA DE NEGÓCIO.
     * Ele recebe a entrega, atualiza o status E envia o alerta.
     */
    /*public void atualizarStatusDaEntrega(Entrega entrega, StatusEntrega novoStatus) {

        // 1. Atualiza o objeto de dados
        entrega.atualizarStatus(novoStatus);

        // (Aqui você também salvaria no banco de dados, ex: entregaRepository.save(entrega))

        // 2. Coloca a lógica de notificação AQUI
        String email = entrega.getEmailComprador();

        if (novoStatus == StatusEntrega.EM_TRANSITO) {
            alertaService.enviarEmailSimples(email,
                    "Pedido em rota de entrega",
                    "Boa notícia! Seu pedido já está em rota de entrega, fique atento(a) que já, já ele chega em seu endereço! \n\nAtt, EntregasPOO");

        } else if (novoStatus == StatusEntrega.ENTREGUE) {
            alertaService.enviarEmailSimples(email,
                    "Pedido entregue",
                    "Eba! Seu pedido foi entregue, aproveite! \n\nAtt, EntregasPOO");

        } else if (novoStatus == StatusEntrega.CANCELADA) {
            alertaService.enviarEmailSimples(email,
                    "Pedido cancelado",
                    "Que pena! Seu pedido foi cancelado, sinta-se a vontade para sempre tornar a utilizar nossos serviços. \n\nAtt, EntregasPOO");
        }
    }

    public Entrega criarNovaEntrega() {
        // ...
        // Entrega novaEntrega = new Entrega(...);
        // (Aqui você salvaria a novaEntrega no banco)
        // return novaEntrega;
        return null; // Apenas exemplo
    }
}*/