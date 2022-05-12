package ums.utility;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ums.bean.EmailMessageBean;

public class EmailUtility {

	private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	private static Properties props = new Properties();

	public void sendMail(EmailMessageBean emailMessageDTO) throws MessagingException {

		String sender_Email = "dhruvip.inexture@gmail.com";
		String sender_email_pass = "IamDhruvi@inexture1510";

		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.port", 587);
		props.put("mail.smtp.socketFactory.port", 587);
		props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.put("mail.smtp.socketFactory.fallback", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		// Connection to Mail Server
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender_Email, sender_email_pass);
			}
		});

		session.setDebug(true);

		// Create a message
		MimeMessage msg = new MimeMessage(session);
		InternetAddress addressFrom = new InternetAddress(sender_Email);
		msg.setFrom(addressFrom);

		// Set TO addresses

		String[] emailIds = new String[0];

		if (emailMessageDTO.getTo() != null) {
			emailIds = emailMessageDTO.getTo().split(",");
		}

		InternetAddress[] addressTo = new InternetAddress[emailIds.length];

		for (int i = 0; i < emailIds.length; i++) {
			addressTo[i] = new InternetAddress(emailIds[i]);
		}

		if (addressTo.length > 0) {
			msg.setRecipients(Message.RecipientType.TO, addressTo);
		}

		// Setting the Subject and Content Type
		msg.setSubject(emailMessageDTO.getSubject());

		// Set message MIME type
		switch (emailMessageDTO.getMessageType()) {
		case EmailMessageBean.HTML_MSG:
			msg.setContent(emailMessageDTO.getMessage(), "text/html");
			break;
		case EmailMessageBean.TEXT_MSG:
			msg.setContent(emailMessageDTO.getMessage(), "text/plain");
			break;
		}

		// Send the mail
		Transport.send(msg);
	}
}
