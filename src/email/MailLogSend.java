
package email;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
/**
 *
 * @author NahumFrog
 */
public class MailLogSend {
    private static final String USR="opinion.lili.project@gmail.com";//correo desde el que se enviará el mail
    private static final String PASS="chin366!";//contraseña
    private final String DESTINO="excalibur.965@gmail.com";//correo del destinatario
    
    
        public void sendMail(String msj, String name, String mail){//recibe el nombre y el mensaje de quien lo envía
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(USR, PASS);
                        }
                    });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(USR));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(DESTINO));
                message.setSubject("Opiniones proyecto Lili");
                message.setText("Nombre: "+name+"\nMail: "+mail+"\nMensaje: "+msj);

                Transport.send(message);
                JOptionPane.showMessageDialog(null, "Su mensaje ha sido enviado");

            } catch (MessagingException e) {
                JOptionPane.showMessageDialog(null,"Mensaje no enviado\n"+e,"Error",JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(e);
            }
        }
}
