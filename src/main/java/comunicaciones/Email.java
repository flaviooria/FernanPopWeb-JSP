package comunicaciones;

import utilidades.FicherosConverter;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;


public class Email {
    public static boolean seEnviaElEmail(String destino, String mensaje, String asunto) {
        boolean envio;
        String emisor = FicherosConverter.obtenerPropertieEmail("emisor");
        String username = FicherosConverter.obtenerPropertieEmail("username");
        String password = FicherosConverter.obtenerPropertieEmail("password");
        String host = FicherosConverter.obtenerPropertieEmail("host");
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", 587);
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            //Creamos el mensaje de correo por defecto
            Message message = new MimeMessage(session);
            //En el mensaje, establecemos el emisor con los datos pasados a la función
            message.setFrom(new InternetAddress(emisor));
            //En el mensaje establecemos el receptor.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destino));
            //Establecemos el asunto
            message.setSubject(asunto);
            //Añadimos el contenido al mensaje
            message.setContent(mensaje, "text/html; charset=utf-8");
            //Envio el mensaje
            Transport.send(message);
            envio = true;
        } catch (Exception e) {// si entra aqui es porque ha fallado
            throw new RuntimeException(e);
        }
        return envio;
    }
    public static boolean seEnviaElEmailAdjunto(String destino, String asunto,String ruta) {
        boolean envio;
        String emisor = FicherosConverter.obtenerPropertieEmail("emisor");
        String username = FicherosConverter.obtenerPropertieEmail("username");
        String password = FicherosConverter.obtenerPropertieEmail("password");
        String host = FicherosConverter.obtenerPropertieEmail("host");
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", 587);
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            //creamos el metodo para adjuntar el fichero
            BodyPart attached = new MimeBodyPart();
            //obtenemos elfichero que vamos adjuntar
            attached.setDataHandler(new DataHandler(new FileDataSource(ruta)));
            // le damos un nombre
            attached.setFileName("listaUsuarios.xlsx");
            //creamo el multipart para adjuntar archivos
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(attached);
            //Creamos el mensaje de correo por defecto
            Message message = new MimeMessage(session);
            //En el mensaje, establecemos el emisor con los datos pasados a la función
            message.setFrom(new InternetAddress(emisor));
            //En el mensaje establecemos el receptor.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destino));
            //Establecemos el asunto
            message.setSubject(asunto);
            //Añadimos el contenido al mensaje
            message.setContent(multipart);
            //Envio el mensaje
            Transport.send(message);
            envio = true;
        } catch (Exception e) {// si entra aqui es porque ha fallado
            throw new RuntimeException(e);
        }
        return envio;
    }
}

