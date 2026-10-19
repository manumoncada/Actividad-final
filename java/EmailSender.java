package com.example.contactoapp;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// Envia correos con JavaMail (SMTP de Gmail)
public class EmailSender {

    public static void enviar(String nombre, String correoContacto, String mensaje)
            throws MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ConfigCorreo.CUENTA_ENVIO, ConfigCorreo.CLAVE_APP);
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(ConfigCorreo.CUENTA_ENVIO));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(ConfigCorreo.CORREO_DESTINO));
        message.setReplyTo(InternetAddress.parse(correoContacto));
        message.setSubject("Comentario de contacto: " + nombre);
        message.setText("Nombre: " + nombre + "\n"
                + "Correo: " + correoContacto + "\n\n"
                + "Mensaje:\n" + mensaje);

        Transport.send(message);
    }
}
