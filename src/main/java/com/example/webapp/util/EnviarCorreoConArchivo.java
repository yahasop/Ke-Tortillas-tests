package com.example.webapp.util;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class EnviarCorreoConArchivo {
    public static void enviarCorreoConArchivoAdjunto(String host, int port, final String username, final String password,
                                                     String destinatario, String asunto, String cuerpo, String rutaArchivoAdjunto) {

        // Configurar propiedades
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Crear una sesión de correo
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Crear un mensaje de correo con archivo adjunto
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);

            // Crear el cuerpo del correo
            BodyPart mensajeBodyPart = new MimeBodyPart();
            mensajeBodyPart.setText(cuerpo);

            // Crear el archivo adjunto
            BodyPart adjuntoBodyPart = new MimeBodyPart();
            DataSource fuente = new FileDataSource(rutaArchivoAdjunto);
            adjuntoBodyPart.setDataHandler(new DataHandler(fuente));
            adjuntoBodyPart.setFileName(fuente.getName());

            // Combinar el cuerpo del mensaje y el archivo adjunto
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mensajeBodyPart);
            multipart.addBodyPart(adjuntoBodyPart);

            // Agregar el multipart al mensaje
            message.setContent(multipart);

            // Enviar el mensaje
            Transport.send(message);

            System.out.println("El correo electrónico con el archivo adjunto se envió correctamente.");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error al enviar el correo electrónico: " + e.getMessage());
        }
    }
}
