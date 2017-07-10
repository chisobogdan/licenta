import java.util.Vector;
 
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
 
import com.tibco.tibjms.TibjmsQueueConnectionFactory;
/**
 * Clasa de trimitere a mesajelor spre middleware
 * @author chiso
 *
 */
public class TibcoEMSQueueSender {
 
	public static void sendMessage(String message) {
 
		String serverUrl = "tcp://localhost:7222";
		String userName = "admin";
		String password = "";
 
		String queueName = "meteo.notifySituationSystem";
 
		try {
 
			Vector<Object> data = new Vector<Object>();
			data.add("<root><Cod>ROSU</Cod><Destinatie>ZONA1</Destinatie><Mesaj>STARE DE ALERTA: GRINDINA</Mesaj></root> ");
 
			System.out.println("Sending JMS message to server " + serverUrl + "...");
 
			QueueConnectionFactory factory = new TibjmsQueueConnectionFactory(serverUrl);
			QueueConnection connection = factory.createQueueConnection(userName, password);
			QueueSession session = connection.createQueueSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
 
			// Use createQueue() to enable sending into dynamic queues.
			Queue senderQueue = session.createQueue(queueName);
			QueueSender sender = session.createSender(senderQueue);
 
			/* publish messages */
			for (int i = 0; i < data.size(); i++) {
				TextMessage jmsMessage = session.createTextMessage();
				// String text = (String) data.elementAt(i);
				jmsMessage.setText(message);
				sender.send(jmsMessage);
				System.out.println("Sent message: " + message);
			}
 
			connection.close();
 
		} catch (JMSException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
 
}