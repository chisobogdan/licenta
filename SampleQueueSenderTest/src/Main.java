import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		ArrayList<Localitate> locations = new ArrayList<Localitate>();
		ArrayList<ZoneObserver> observers = new ArrayList<ZoneObserver>();

		DBOperations db = new DBOperations();
		try {
			ArrayList<String> nume = db.getDistinctLocalitati();
			System.out.println("localitati...");
			for (String n : nume) {
				int zona = db.getZoneByLocation(n);
				ArrayList<String> sisteme = db.getDistinctSisteme(n);

				Localitate loc = new Localitate(n, zona, sisteme);
				locations.add(loc);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// pornesc viewPanelu
		final MessageViewPanel form = new MessageViewPanel();

		// threaduri - pt fiecare localitate
		for (Localitate loc : locations)
			for (String sistem : loc.getSisteme()) {
				String queue = generateQueueName(loc.getNume(), sistem);
				TibcoEMSQueueReceiver subject = new TibcoEMSQueueReceiver(
						"admin", "", queue, loc.getZona());
				(new Thread(subject)).start();
				ZoneObserver obs = new ZoneObserver(subject, form);
				observers.add(obs);
			}

		// Frame for our test
		JFrame f = new JFrame("MonitorMessages");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(form.main, BorderLayout.NORTH);
		f.setResizable(false);
		// Show the frame
		f.pack();
		f.setVisible(true);
	}

	private static String generateQueueName(String nume, String sistem) {
		return nume + "." + sistem;
	}

	
}
