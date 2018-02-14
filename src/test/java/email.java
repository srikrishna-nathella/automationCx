import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.annotations.AfterTest;

public class email  {

    public static void execute(String reportFileName) throws Exception {
        //final String username = "msf.qa.automationreports@gmail.com";
        //final String password = "qaautomation";
        final String username = "srikrishna.n@inxsasia.com";
        final String password = "sree7733";
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("srikrishna.n@inxsasia.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("sree_krish2005@yahoo.co.in"));
            message.setSubject("Test Report");
            /*message.setText("Hi Team," + "\n\n Performed Testing on Application, Please find the Report Attached herewith." +
                    "Request you to download the attachment and view");*/
            message.setContent("Hi Team," + "\n\n Performed Testing on Application, Please find the Report Attached herewith." +
            "Request you to download the attachment and view","text/html; charset=utf-8");
            System.out.println("Mail set");
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            messageBodyPart = new MimeBodyPart();
            String file = "E:\\Automation\\qa2.automation\\Reports\\";
            String fileName = reportFileName;
            DataSource source = new FileDataSource(file + fileName);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            System.out.println("Sending");
            Transport.send(message);
            System.out.println("Done");
        } catch (MessagingException e) {
        }
    }
}
