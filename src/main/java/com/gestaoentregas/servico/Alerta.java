package com.gestaoentregas.servico;

// A importação da 'Entrega' não é mais necessária aqui
// import com.gestaoentregas.dados.beans.entrega.Entrega;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.File;

@Service
public class Alerta {

    // --- ERRO 1 CORRIGIDO ---
    // Removido o campo "Entrega entrega;".
    // O serviço de Alerta não deve guardar o estado de UMA entrega.

    // --- MELHORIA (Injeção de Construtor) ---
    private final JavaMailSender mailSender; // Tornamos final
    private final String remetente = "yanntavares123@gmail.com";

    // O Spring irá automaticamente injetar o JavaMailSender aqui
    @Autowired
    public Alerta(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    // --- ERRO 2 CORRIGIDO ---
    // Removido o construtor "public Alerta(Entrega entrega)"
    // que estava causando a falha.


    /**
     * Método 1: Envio de texto simples
     */
    public void enviarEmailSimples(String para, String assunto, String texto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(remetente);
        message.setTo(para);
        message.setSubject(assunto);
        message.setText(texto);

        mailSender.send(message);
        System.out.println("Email simples enviado para " + para);
    }

    /**
     * Método 2: Envio de email com HTML
     */
    public void enviarEmailComHtml(String para, String assunto, String conteudoHtml) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(remetente);
            helper.setTo(para);
            helper.setSubject(assunto);
            helper.setText(conteudoHtml, true);

            mailSender.send(message);
            System.out.println("Email HTML enviado para " + para);

        } catch (MessagingException e) {
            System.err.println("Erro ao enviar email HTML: " + e.getMessage());
        }
    }

    /**
     * Método 3: Envio de email com Anexo (e HTML)
     */
    public void enviarEmailComAnexo(String para, String assunto, String texto, String caminhoDoAnexo) {
        // ... (Seu código aqui está perfeito)
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(remetente);
            helper.setTo(para);
            helper.setSubject(assunto);
            helper.setText(texto, true);

            FileSystemResource file = new FileSystemResource(new File(caminhoDoAnexo));
            if (file.exists()) {
                String nomeAnexo = file.getFilename();
                helper.addAttachment(nomeAnexo, file);
            } else {
                System.err.println("Arquivo de anexo não encontrado em: " + caminhoDoAnexo);
            }

            mailSender.send(message);
            System.out.println("Email com anexo enviado para " + para);

        } catch (MessagingException e) {
            System.err.println("Erro ao enviar email com anexo: " + e.getMessage());
        }
    }
}