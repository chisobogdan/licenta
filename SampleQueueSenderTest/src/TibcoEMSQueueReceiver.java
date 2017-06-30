
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.swing.text.Document;
import javax.xml.bind.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.tibco.tibjms.TibjmsQueueConnectionFactory;

/**
 * Clasa observata pentru primirea mesajelor
 * 
 * @author viata mea
 *
 */
public class TibcoEMSQueueReceiver implements Runnable {

	private String serverUrl = "tcp://localhost:7222";
	private String userName = "";
	private String password = "";
	private String queueName = "";
	private int zona;

	//mesajul primit si observat 
	private String message;

	private List<Observer> observers = new ArrayList<Observer>();

	public TibcoEMSQueueReceiver(String username, String pass, String queue, int zona) {
		this.userName = username;
		this.password = pass;
		this.queueName = queue;
		this.zona = zona;
	}

	public void run() {

		try {

			System.out.println("Start listening for incoming JMS message on "
					+ serverUrl + "...queue:.. " + queueName);

			QueueConnectionFactory factory = new TibjmsQueueConnectionFactory(
					serverUrl);
			QueueConnection connection = factory.createQueueConnection(
					userName, password);
			QueueSession session = connection.createQueueSession(false,
					javax.jms.Session.AUTO_ACKNOWLEDGE);

			// Use createQueue() to enable receiving from dynamic queues.
			Queue receiverQueue = session.createQueue(queueName);
			QueueReceiver receiver = session.createReceiver(receiverQueue);

			connection.start();

			/* read queue messages */
			while (true) {
				TextMessage message = (TextMessage) receiver.receive();
				if (message == null)
					break;
				setMessage("Received message: " + message.getText());
				System.out.println(queueName + " " + message.getText());
			}

			connection.close();

		} catch (JMSException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setMessage(String message) {
		this.message = message;
		System.out.println("Setare mesaj");
		notifyAllObservers();
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public String getMessage() {
		return message;
	}

	public List<Observer> getObservers() {
		return observers;
	}

	public void setObservers(List<Observer> observers) {
		this.observers = observers;
	}
	
	/**
	 * notificare observatori ca s-a primit mesaj nou
	 */
	public void notifyAllObservers() {
		System.out.print("Notificare observer ");
		for (Observer observer : observers) {
			System.out.println(observer);
			observer.update();
		}
	}

	/**
	 * adaugare Observatori in lista link{observers}
	 * 
	 * @param observer
	 */
	public void attach(Observer observer) {
		System.out.println("Observer atasat:" + observer);
		observers.add(observer);
	}

	public int getZona() {
		return zona;
	}

	public void setZona(int zona) {
		this.zona = zona;
	}
	
	
//	public static String getElementFromXML(String tagName, String XMLString)
//			throws ParserConfigurationException, SAXException, IOException {
//		String element;
//
//		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//		DocumentBuilder builder = factory.newDocumentBuilder();
//
//		StringBuilder xmlStringBuilder = new StringBuilder();
//		xmlStringBuilder.append(XMLString);
//		ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder
//				.toString().getBytes("UTF-8"));
//		Document doc = (Document) builder.parse(input);
//		
//		Element e = (Element) ((org.w3c.dom.Document) doc).getElementsByTagName(tagName);
//		element =e.toString();
//		return element;
//	}

}