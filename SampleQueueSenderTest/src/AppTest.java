import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.TextMessage;

import com.tibco.tibjms.TibjmsQueueConnectionFactory;

public class AppTest {
	public static long start = 0;
	private static int NR_MSG = 150;
	
	public static void main(String[] args) {
		
		start = System.currentTimeMillis();
		for (int i = 0; i < NR_MSG; i++) {
			String mesaj = createMessage("ROSU", "ZONA3", "JALE", i + "");
			TibcoEMSQueueSender.sendMessage(mesaj);
		}
		AppTest app = new AppTest();
		TibcoEMSQueueReceiverAux subject = app.new TibcoEMSQueueReceiverAux(
				"admin", "", "Baciu.PRIMARIE");
		subject.run();
		//setQueues();
	}

//	private static void setQueues() {
//
//		AppTest app = new AppTest();
//		List<Thread> threads = new ArrayList<Thread>();
//		List<TibcoEMSQueueReceiverAux> threadObjects = new ArrayList<TibcoEMSQueueReceiverAux>();
//		// threaduri - pt fiecare localitate
//		for (Localitate loc : locations)
//			for (String sistem : loc.getSisteme()) {
//				System.out.println("in creare thread..");
//				String queue = generateQueueName(loc.getNume(), sistem);
//				TibcoEMSQueueReceiverAux subject = app.new TibcoEMSQueueReceiverAux(
//						"admin", "", queue, loc.getZona());
//				Thread t = new Thread(subject);
//				t.start();
//				threads.add(t);
//				threadObjects.add(subject);
//			}
//		
//		for (Thread thread : threads) {
//            try {
//                thread.join();
//            } catch (InterruptedException ex) {
//            }
//        }
//		int count = 0;
//        for (TibcoEMSQueueReceiverAux threadObject : threadObjects) {
//            System.out.println(threadObject.getValue());
//        }
//
//	}

	private static String createMessage(String selectedItem,
			String selectedItem2, String selectedItem3, String text) {
		// Do stuff with your data
		return "<root><Cod>" + selectedItem + "</Cod><Destinatie>"
				+ selectedItem2 + "</Destinatie><Tip>" + selectedItem3
				+ "</Tip><Descriere>" + text + "</Descriere></root>";
	}

	private static String generateQueueName(String nume, String sistem) {
		return nume + "." + sistem;
	}

	public class TibcoEMSQueueReceiverAux  {

		private String serverUrl = "tcp://localhost:7222";
		private String userName = "";
		private String password = "";
		private String queueName = "";
		private int zona;

		//mesajul primit si observat 
		private String message;

		public TibcoEMSQueueReceiverAux(String username, String pass, String queue) {
			this.userName = username;
			this.password = pass;
			this.queueName = queue;
		}

		public TibcoEMSQueueReceiverAux(){}
		

		public void run() {

			try {

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
				
				int count = 0;
				String aux = "";
				/* read queue messages */
				while (true) {

					TextMessage message = (TextMessage) receiver.receive();
					if(count++ == (NR_MSG - 1)) 
						System.out.println(System.currentTimeMillis() - start);
					if (message == null)
						break;
				}

				connection.close();

			} catch (JMSException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		
	}
}
