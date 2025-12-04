package com.gestaoentregas.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${app.mail.remetente}")
    private String remetente;

    @Autowired
    public Alerta(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Método 1: Envio de texto simples
     */
    public void enviarEmailSimples(String para, String assunto, String texto) {
        try {
            // --- CORREÇÃO: Proteção contra Nulo ---
            if (para == null || para.trim().isEmpty()) {
                System.err.println("Erro: Tentativa de enviar e-mail sem destinatário.");
                return;
            }

            // Verifica se o remetente foi carregado corretamente do application.properties
            if (remetente == null) {
                System.err.println("Erro: Remetente não configurado no application.properties.");
                return;
            }

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(remetente);
            message.setTo(para);
            message.setSubject(assunto);
            message.setText(texto);

            mailSender.send(message);
            System.out.println("Email enviado para: " + para);

        } catch (Exception e) {
            // O catch impede que o programa feche se der erro no email
            System.err.println("Falha ao enviar email (O sistema continuará rodando): " + e.getMessage());
        }
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
    }*/

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
    }*/
}