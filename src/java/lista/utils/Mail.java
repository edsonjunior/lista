package lista.utils;

import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@ManagedBean(name="mail")
@RequestScoped
public class Mail{

	public String header;
	public String name;
	public String mailAddress;
                
        public String getMailAddress(){
            return mailAddress;
        }
        
        public void setMailAddress(String mailAddress){
            this.mailAddress = mailAddress;
        }
	 
	public String getHeader() {
            return header; 
	} 

	public void setHeader(String header) {
            this.header = header; 
	} 

	public String getName() {
            return name; 
	} 

	public void setName(String name) {
            this.name = name; 
	}

	public void sendMail() {
            final String username = "pdsw2022@gmail.com";
            final String password = "qwe098qwe098";

            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            Session session = Session.getInstance(properties,
                    new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(username, password); 
                            }
                    }) ; 
            try { 
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("pdsw2022@gmail.com"));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("edsonjunior@live.com"));			
                    message.setSubject("Recupera????o de Senha"); 
                    message.setText("Seu c??digo ??: " + name + "'send you message from " + mailAddress + " which header = " + header); Transport.send(message); 
            } catch (MessagingException ex) {
                    throw new RuntimeException(ex); 
            }
        }
}