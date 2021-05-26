package ar.unrn.parcial.observadores;

import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ar.unrn.parcial.modelo.ObservadorVentas;

public class MailTrap implements ObservadorVentas {

	// usuario y clave que se obtiene desde Mailtrap
	private final String USERNAME = "6227bfb49c2649";
	private final String PASSWORD = "dc44856a549026";

	@Override
	public void actualizar(Map<String, String> datos) {
		// remitente
		String to = "test@example.com";
		// destinatario
		String from = datos.get("mailDestinatario");

		String host = "smtp.mailtrap.io";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");// it’s optional in Mailtrap
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "2525");
		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USERNAME, PASSWORD);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(datos.get("titulo"));
			message.setText(datos.get("cuerpo"));
			// Send message
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException("envio de email fallido", e);
		}
	}

}
