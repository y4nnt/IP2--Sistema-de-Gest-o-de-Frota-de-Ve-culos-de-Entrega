package com.gestaoentregas.classes;

import com.gestaoentregas.classes.entrega.Entrega;
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
    Entrega entrega;

    public Alerta(Entrega entrega) {
        this.entrega = entrega;
    }

    @Autowired
    private JavaMailSender mailSender;

    private String remetente = "entregas.poo@gmail.com"; // Defina seu email remetente aqui

    /**
     * Método 1: Envio de texto simples
     */
    public void enviarEmailSimples(String para, String assunto, String texto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(remetente);
        message.setTo(para);
        message.setSubject("Pedido em rota de entrega");
        message.setText("Boa notícia! Seu pedido está em rota de entrega e deve chegar logo, logo! \nFique atento para recebê-lo e aproveite!");

        mailSender.send(message);
        System.out.println("Email simples enviado para " + para);
    }

    /**
     * Método 2: Envio de email com HTML
     * * @param para Destinatário
     * @param assunto Assunto do email
     * @param conteudoHtml O corpo do email formatado em HTML
     */
    public void enviarEmailComHtml(String para, String assunto, String conteudoHtml) {
        try {
            MimeMessage message = mailSender.createMimeMessage();

            // O 'true' indica que este é um email multipart (necessário para HTML)
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(remetente);
            helper.setTo(para);
            helper.setSubject(assunto);

            // O segundo parâmetro 'true' indica que o texto é HTML
            helper.setText(conteudoHtml, true);

            mailSender.send(message);
            System.out.println("Email HTML enviado para " + para);

        } catch (MessagingException e) {
            System.err.println("Erro ao enviar email HTML: " + e.getMessage());
            // Lide com a exceção (ex: logar, lançar uma exceção customizada)
        }
    }

    /**
     * Método 3: Envio de email com Anexo (e HTML)
     * * @param para Destinatário
     * @param assunto Assunto
     * @param texto O corpo do email (pode ser HTML ou texto)
     * @param caminhoDoAnexo O caminho completo para o arquivo (ex: "C:/docs/meu_arquivo.pdf")
     */
    public void enviarEmailComAnexo(String para, String assunto, String texto, String caminhoDoAnexo) {
        try {
            MimeMessage message = mailSender.createMimeMessage();

            // O 'true' indica que é multipart
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(remetente);
            helper.setTo(para);
            helper.setSubject(assunto);
            helper.setText(texto, true); // Supondo que o texto também é HTML

            // Adicionando o anexo
            FileSystemResource file = new FileSystemResource(new File(caminhoDoAnexo));
            if (file.exists()) {
                // Você pode dar um nome diferente para o anexo no email
                String nomeAnexo = file.getFilename();
                helper.addAttachment(nomeAnexo, file);
            } else {
                System.err.println("Arquivo de anexo não encontrado em: " + caminhoDoAnexo);
                // Decida se quer enviar o email mesmo sem o anexo ou falhar
            }

            mailSender.send(message);
            System.out.println("Email com anexo enviado para " + para);

        } catch (MessagingException e) {
            System.err.println("Erro ao enviar email com anexo: " + e.getMessage());
        }
    }
}


