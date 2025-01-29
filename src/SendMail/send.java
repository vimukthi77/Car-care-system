package SendMail;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class send {

    public static void main(String[] args) {

        // Sender's email ID needs to be mentioned
        String from = "gtao6968@gmail.com"; // replace with your Gmail address

        // Recipient's email ID needs to be mentioned.
        String to = "oshandilruk317@gmail.com"; // replace with the recipient's email address

        // Assuming you are sending email from through gmail's smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");


        // Get the Session object and pass username and password
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("gtao6968@gmail.com", "twpivmxzngpgwkzg");
            }
        });

        try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Testing JavaMail API");

            // Now set the actual message
            message.setText("Hello, this is a test2 email from JavaMail API!");
            
            message.setContent(
                    "<html><body>"
                    + "<h1>Hello there!</h1>"
                    + "<p>We hope you're enjoying our content on Bit by Bit Official. To stay updated with our latest videos and tutorials, please consider subscribing to our YouTube channel.</p>"
                    + "<p><a href=\"https://www.youtube.com/channel/UC5lhJdTdBFTbXZ2Xkpo7YJg\" target=\"_blank\" style=\"background-color: #0073e6; color: #ffffff; padding: 10px 20px; text-decoration: none; border-radius: 5px; font-weight: bold;\">Subscribe Now</a></p>"
                    + "<p>Thank you for your support!</p>"
                    + "</body></html>",
                    "text/html");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully...");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
