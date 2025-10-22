package com.gestaoentregas.servico;
/*
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

    private final JavaMailSender mailSender; // Tornamos final
    private final String remetente = "yanntavares123@gmail.com";

    @Autowired
    public Alerta(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Método 1: Envio de texto simples
     */
    /*public void enviarEmailSimples(String para, String assunto, String texto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(remetente);
        message.setTo(para);
        message.setSubject(assunto);
        message.setText(texto);

        mailSender.send(message);
    }

    /**
     * Método 2: Envio de email com HTML
     */
    /*public void enviarEmailComHtml(String para, String assunto, String conteudoHtml) {
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
    /*public void enviarEmailComAnexo(String para, String assunto, String texto, String caminhoDoAnexo) {
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
}*/