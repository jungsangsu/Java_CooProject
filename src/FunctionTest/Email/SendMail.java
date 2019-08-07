package FunctionTest.Email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	public static int SendMail(String email) {

		int number = 0;
		String host = "smtp.naver.com";
		final String user = "rtr456";
		final String password = "tkdtn2030112!@#";

		String to = email;

		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// Compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Subject
			message.setSubject("[CooService] 인증번호 발송입니다");

			// Text
			number = (int)(Math.random()*9999)+1000;
			
			message.setText("인증번호 : " +number +"\n 인증번호를 입력해주세요!!");

			// send the message
			Transport.send(message);
			System.out.println("message sent successfully...");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return number;
	}
	
//	public static void main(String[] args) {
//		SendMail.SendMail("rtr456@naver.com");
//	}
}