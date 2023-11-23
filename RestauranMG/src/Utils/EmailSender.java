
package Utils;

import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
    private String senderEmail;
    private String senderPassword;

    public EmailSender() {
       
    }
    public static void main(String[] args) {
        EmailSender e = new EmailSender();
        e.sendEmailFile("vundps33862@fpt.edu.vn", "hello", "Email thử nghiệm", "");
    }
    public static void sendEmail(String emailNhan, String tieuDe, String noidung) {
        try {
	       
            Properties p = new Properties();
            p.setProperty("mail.transport.protocol", "smtp");  
            p.put("mail.smtp.auth","true");
            p.put("mail.smtp.starttls.enable", "true"); // phai co lenh nay
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.put("mail.smtp.port","587");
            p.put("mail.smtp.ssl.protocols","TLSv1.2");
            p.put("mail.smtp.ssl.trust", "smtp.gmail.com"); // va lenh nay !!!!
            
            String user = "mts.studio.2019@gmail.com";
            String pass = "tmzseupwvnrzjyuu"; // app password
            
            Session s = Session.getDefaultInstance(p, new javax.mail.Authenticator()
            {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, pass);
                }
                
            });
            
            MimeMessage msg = new MimeMessage(s);
            msg.setFrom(new InternetAddress("mts.studio.2019@gmail.com"));
            msg.setRecipient(Message.RecipientType.TO,new InternetAddress( emailNhan));
            msg.setText(noidung);
            msg.setSubject(tieuDe);
            msg.setSentDate(new Date());
            msg.saveChanges();
            
     
            Transport.send(msg);
            System.out.println("Send eamil succes!");
        } catch (MessagingException ex) {
            System.out.println("Loi: "+ ex.getMessage());
        }
    }
    
    
     public void sendEmailFile(String emailNhan, String tieuDe, String noidung, String filePath) {
        try {
            Properties p = new Properties();
            p.setProperty("mail.transport.protocol", "smtp");  
            p.put("mail.smtp.auth","true");
            p.put("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.put("mail.smtp.port","587");
            p.put("mail.smtp.ssl.protocols","TLSv1.2");
            p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            
            String user = "mts.studio.2019@gmail.com";
            String pass = "tmzseupwvnrzjyuu"; // app password
            
            Session s = Session.getDefaultInstance(p, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, pass);
                }     
            });
            
            MimeMessage msg = new MimeMessage(s);
            msg.setFrom(new InternetAddress("mts.studio.2019@gmail.com"));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(emailNhan));
            msg.setSubject(tieuDe);
            msg.setSentDate(new Date());

            // Tạo phần thân của email
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(noidung);

            // Tạo phần đính kèm
            if (filePath != null && !filePath.isEmpty()) {
                MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                attachmentBodyPart.attachFile(filePath);

                // Gộp phần thân và phần đính kèm thành một phần
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
                multipart.addBodyPart(attachmentBodyPart);

                // Thiết lập phần tử đa phần là nội dung của thư
                msg.setContent(multipart);
            } else {
                // Nếu không có tệp đính kèm, chỉ gửi nội dung thông thường
                msg.setText(noidung);
            }

            // Gửi thư
            Transport.send(msg);
        } catch (MessagingException ex) {
            System.out.println("Loi: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Loi khi dinh kem file: " + ex.getMessage());
        }
    }
}
