import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

public class MessageViewPanel {
	JPanel main = new JPanel();

	private static final long serialVersionUID = 1L;
	private String queueName;
	private int zona;
	private String message;
	JTextArea textZona1 = new JTextArea();
	JTextArea textZona2 = new JTextArea();
	JTextArea textZona3 = new JTextArea();
	JTextArea textZona4 = new JTextArea();
	ArrayList<JLabel> nrMesaje = new ArrayList<JLabel>();

	// Create a form with the fields
	public MessageViewPanel() {

		main.setLayout(new GridLayout(2, 2, 5, 5));
		main.setBorder(new EmptyBorder(4, 4, 4, 4));
		main.setPreferredSize(new Dimension(1800, 800));
		main.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		main.setBackground(Color.GRAY);

		this.nrMesaje = createLabel();

		JPanel tracking1 = new JPanel(new BorderLayout(1, 4));
		for (JLabel l : nrMesaje) {
			if (l.getText() == "Mesaje Zona1")
				tracking1.add(l);
		}

		textZona1 = newJTextArea();
		JScrollPane scrollPane1 = new JScrollPane(textZona1);
		TitledBorder titluZona1 = new TitledBorder("Zona1");
		titluZona1.setTitleColor(Color.WHITE);
		titluZona1.setTitleFont(new Font("Dialog", Font.BOLD, 40));
		JPanel zona1 = new JPanel(new BorderLayout(2, 1));
		zona1.setBackground(Color.GRAY);
		zona1.setBorder(titluZona1);
		//zona1.add(tracking1);
		zona1.add(scrollPane1);
		// zona1.setPreferredSize(new Dimension(600,400));

		textZona2 = newJTextArea();
		JScrollPane scrollPane2 = new JScrollPane(textZona2);
		TitledBorder titluZona2 = new TitledBorder("Zona2");
		titluZona2.setTitleColor(Color.WHITE);
		titluZona2.setTitleFont(new Font("Dialog", Font.BOLD, 40));
		JPanel zona2 = new JPanel(new BorderLayout(2, 1));
		zona2.setBorder(titluZona2);
		zona2.setPreferredSize(new Dimension(600, 400));
		zona2.setBackground(Color.GRAY);
		zona2.add(scrollPane2);
		
		textZona3 = newJTextArea();
		JScrollPane scrollPane3 = new JScrollPane(textZona3);
		TitledBorder titluZona3 = new TitledBorder("Zona3");
		titluZona3.setTitleColor(Color.WHITE);
		titluZona3.setTitleFont(new Font("Dialog", Font.BOLD, 40));
		JPanel zona3 = new JPanel(new BorderLayout(2, 1));
		zona3.setBorder(titluZona3);
		zona3.setPreferredSize(new Dimension(600, 400));
		zona3.setBackground(Color.GRAY);
		zona3.add(scrollPane3);

		textZona4 = newJTextArea();
		JScrollPane scrollPane4 = new JScrollPane(textZona4);
		TitledBorder titluZona4 = new TitledBorder("Zona4");
		titluZona4.setTitleColor(Color.WHITE);
		titluZona4.setTitleFont(new Font("Dialog", Font.BOLD, 40));
		JPanel zona4 = new JPanel(new BorderLayout(2, 1));
		zona4.setBorder(titluZona4);
		zona4.setPreferredSize(new Dimension(600, 400));
		zona4.setBackground(Color.GRAY);
		zona4.add(scrollPane4);

		main.add(zona1);
		main.add(zona2);
		main.add(zona3);
		main.add(zona4);

	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public int getZona() {
		return zona;
	}

	public void setZona(int zona) {
		this.zona = zona;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
		// atualizari text areea
		System.out.println("zona trimisa in view" + this.zona);
		switch (this.zona) {
		case 1:
			textZona1.setText(textZona1.getText() + this.message);
			break;
		case 2:
			textZona2.setText(textZona2.getText() + this.message);
			break;
		case 3:
			textZona3.setText(textZona3.getText() + this.message);
			break;
		case 4:
			textZona4.setText(textZona4.getText() + this.message);
			break;
		default:
			break;
		}

	}
//	private JPanel newJPanel(){
//		JPanel panel = new JPanel();
//		TitledBorder titluZona1 = new TitledBorder("Zona1");
//		titluZona1.setTitleColor(Color.WHITE);
//		titluZona1.setTitleFont(new Font("Dialog", Font.BOLD, 40));
//		//JPanel zona1 = new JPanel(new BorderLayout(2, 1));
//		panel.setBackground(Color.GRAY);
//		panel.setBorder(titluZona1);
//		//panel.add(tracking1);
//		JScrollPane scrollPane1 = new JScrollPane(textZona1);
//		zona1.add(scrollPane1);
//		
//		return null;
//	}

	private JTextArea newJTextArea() {
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 16));
		textArea.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				if (e.getMark() == e.getDot()) {
					Highlighter hl = textArea.getHighlighter();
					hl.removeAllHighlights();

					String pattern1 = "VERDE";
					String pattern2 = "GALBEN";
					String pattern3 = "PORTOCALIU";
					String pattern4 = "ROSU";
					String text = textArea.getText();
					int index1 = text.indexOf(pattern1);
					int index2 = text.indexOf(pattern2);
					int index3 = text.indexOf(pattern3);
					int index4 = text.indexOf(pattern4);
					// verde
					while (index1 >= 0) {
						try {
							Object o1 = hl.addHighlight(index1, index1
									+ pattern1.length(),
									new DefaultHighlighter.DefaultHighlightPainter(Color.green));
							index1 = text.indexOf(pattern1,
									index1 + pattern1.length());
						} catch (BadLocationException ex) {
							ex.printStackTrace();
						}
					}
					// GALBEN
					while (index2 >= 0) {
						try {
							Object o2 = hl.addHighlight(index2, index2
									+ pattern2.length(),
									new DefaultHighlighter.DefaultHighlightPainter(Color.yellow));
							index2 = text.indexOf(pattern2,
									index2 + pattern2.length());
						} catch (BadLocationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
					// /PORTOCALIU
					while (index3 >= 0) {
						try {
							Object o3 = hl.addHighlight(index3, index3
									+ pattern3.length(),
									new DefaultHighlighter.DefaultHighlightPainter(Color.orange));
							index3 = text.indexOf(pattern3,
									index3 + pattern3.length());
						} catch (BadLocationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
					// ROSU
					while (index4 >= 0) {
						try {
							Object o4 = hl.addHighlight(index4, index4
									+ pattern4.length(),
									new DefaultHighlighter.DefaultHighlightPainter(Color.red));
							index4 = text.indexOf(pattern4,
									index4 + pattern4.length());
						} catch (BadLocationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				}
			}
		});
		return textArea;
	}

	public ArrayList<JLabel> createLabel() {
		ArrayList<JLabel> nrMesaje = new ArrayList<JLabel>();
		for (int j = 0; j < 4; j++)
			for (int i = 0; i < 4; i++) {
				JLabel labelMesaj = new JLabel("Mesaje Zona" + i);
				if (i == 0)
					labelMesaj.setBackground(Color.GREEN);
				else if (i == 1)
					labelMesaj.setBackground(Color.YELLOW);
				else if (i == 2)
					labelMesaj.setBackground(Color.ORANGE);
				else
					labelMesaj.setBackground(Color.RED);
				nrMesaje.add(labelMesaj);
			}

		return nrMesaje;
	}

}