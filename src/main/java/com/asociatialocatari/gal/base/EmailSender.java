package com.asociatialocatari.gal.base;
/*
import com.sun.mail.smtp.SMTPTransport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
*/
//@Component
public class EmailSender {
/*
    private static final Log log = LogFactory.getLog(EmailSender.class);

    @Value("${mail.port}")
    private Integer port;

    @Value("${mail.host}")
    private String host;

    // auth false , no ssl
    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;

    @Value("${mail.from}")
    private String mailFrom;

    public void sendMap(Map<Long,Email> emailMap){
        if(emailMap != null && !emailMap.isEmpty())
            emailMap.forEach((k, v) -> {
                if(v.getMailTo() != null)
                    send(v);
            });
    }
    public void send(Email email){
        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", host); //optional, defined in SMTPTransport
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", port); // default port 25

        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);

        try {
            msg.setFrom(new InternetAddress(mailFrom));
            if(email.getMailTo() != null && !email.getMailTo().isEmpty())
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getMailTo(), false));
            if (email.getMailCc() != null && !email.getMailCc().isEmpty())
                msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(email.getMailCc(), false));
            if (email.getMailBcc() != null && !email.getMailBcc().isEmpty())
                msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(email.getMailBcc(), false));

            msg.setSubject(email.getMailSubject());
            msg.setText(email.getMailBody());
            msg.setSentDate(new Date());
            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
            t.connect(host, username, password);
            t.sendMessage(msg, msg.getAllRecipients());
            t.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

 */
}