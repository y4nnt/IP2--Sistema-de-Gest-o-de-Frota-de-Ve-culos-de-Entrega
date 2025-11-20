package com.gestaoentregas; // Pacote raiz do seu projeto

// IMPORTS NECESSÁRIOS
import com.gestaoentregas.dados.beans.entrega.Entrega;
import com.gestaoentregas.dados.beans.entrega.Entrega.StatusEntrega;
import com.gestaoentregas.dados.beans.entrega.Rota; // Precisei supor que essa classe existe
import com.gestaoentregas.negocio.EntregaService; // O SERVIÇO QUE VOCÊ CRIOU
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner; // 1. IMPORTE O COMMANDLINERUNNER
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner { // 2. IMPLEMENTE A INTERFACE

    @Autowired
    private final EntregaService entregaService;

    public Main(EntregaService entregaService) {
        this.entregaService = entregaService;
    }


    // 4. O MÉTODO MAIN TRADICIONAL
    // A única responsabilidade dele é iniciar o Spring. NÃO COLOQUE LÓGICA AQUI.
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


    // 5. O MÉTODO "RUN"
    // Este método será executado automaticamente DEPOIS que o Spring
    // já estiver pronto e todas as injeções (@Autowired) tiverem funcionado.
    // É AQUI QUE VOCÊ COLOCA SUA LÓGICA DE TESTE!
    @Override
    public void run(String... args) throws Exception {

        System.out.println("==================================================");
        System.out.println(">>> APLICAÇÃO INICIADA. EXECUTANDO TESTE DE EMAIL...");
        System.out.println("==================================================");

        // 6. Vamos criar os objetos de dados (POJOs) para o teste
        // (Estou supondo que sua classe Rota tem um construtor simples)
        Rota rotaTeste = null;

        Entrega minhaEntregaTeste = new Entrega(
                "ID-987654",
                rotaTeste,
                "Deixar com o porteiro.",
                null, // Sem problemas
                StatusEntrega.PENDENTE,
                "nicolasg22132@gmail.com" // <<< COLOQUE SEU EMAIL AQUI PARA TESTAR
        );

        System.out.println(">>> Entrega de teste criada. Status atual: " + minhaEntregaTeste.getStatusEntrega());

        // 7. AGORA VAMOS USAR O SERVIÇO!
        //    Esta chamada irá:
        //    1. Mudar o status da entrega para EM_TRANSITO
        //    2. Chamar o 'alertaService'
        //    3. Enviar o email para o destinatário que você definiu acima.
        System.out.println(">>> Atualizando status para EM_TRANSITO e enviando email...");

        entregaService.atualizarStatusDaEntrega(minhaEntregaTeste, StatusEntrega.EM_TRANSITO);

        System.out.println(">>> Status atualizado para: " + minhaEntregaTeste.getStatusEntrega());
        System.out.println(">>> Teste concluído. Verifique sua caixa de entrada!");
        System.out.println("==================================================");
    }
}