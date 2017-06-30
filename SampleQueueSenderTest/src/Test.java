import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Test extends JPanel {

    private static JComboBox comboBoxCod;
    private static JComboBox comboBoxZona;
    private static JComboBox comboBoxTip;
    private static JTextArea textArea;
    

    // Create a form with the fields
    public Test() {
        super(new BorderLayout());
        this.setPreferredSize(new Dimension(400,200));
        // Panel for the labels
        JPanel labelPanel = new JPanel(new GridLayout(4, 1)); // 2 rows 1 column
        add(labelPanel, BorderLayout.WEST);

        // Panel for the fields
        JPanel fieldPanel = new JPanel(new GridLayout(4, 1)); // 2 rows 1 column
        add(fieldPanel, BorderLayout.CENTER);

        // ComboboxCOD
        JLabel labelComboCod = new JLabel("COD:");

        // Options in the combobox
        String[] options = { "ROSU", "PORTOCALIU", "GALBEN", "VERDE" };
        comboBoxCod = new JComboBox(options);
     
        // ComboBox ZONA
        
     // ComboboxCOD
        JLabel labelComboZona = new JLabel("Zona:");

        // Options in the combobox
        String[] optionsZona = { "ZONA1", "ZONA2", "ZONA3", "ZONA4" };
        comboBoxZona = new JComboBox(optionsZona);
        
        // ComboboxTIP
        JLabel labelComboTip = new JLabel("Tip:");

        // Options in the combobox
        String[] optionsTip = { "Inundatie", "Alunecari de teren", "Cutremur" };
        comboBoxTip = new JComboBox(optionsTip);
        
        
        // Textfield
        JLabel labelTextField = new JLabel("MESAJ");
        textArea = new JTextArea();

        // Add labels
        labelPanel.add(labelComboCod);
        labelPanel.add(labelComboZona);
        labelPanel.add(labelComboTip);
        labelPanel.add(labelTextField);

        // Add fields
        fieldPanel.add(comboBoxCod);
        fieldPanel.add(comboBoxZona);
        fieldPanel.add(comboBoxTip);
        JScrollPane scrollPane = new JScrollPane(textArea);
        fieldPanel.add(scrollPane);
    }

    public static void main(String[] args) {
        final Test form = new Test();

        // Button submit
        JButton submit = new JButton("SendMessage");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String mesaj = createMessage((String) comboBoxCod.getSelectedItem(),(String) comboBoxZona.getSelectedItem(),(String) comboBoxTip.getSelectedItem(), textArea.getText());
            	TibcoEMSQueueSender send = new TibcoEMSQueueSender();
            	send.sendMessage(mesaj);
            }
        });

        // Frame for our test
        JFrame f = new JFrame("Meteorologie");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(form, BorderLayout.NORTH);

        // Panel with the button
        JPanel p = new JPanel();
        p.add(submit);
        f.getContentPane().add(p, BorderLayout.SOUTH);

        // Show the frame
        f.pack();
        f.setVisible(true);
    }

    private static String createMessage(String selectedItem,String selectedItem2,String selectedItem3, String text) {
        // Do stuff with your data
    	System.out.println("<root>"+selectedItem+"<Cod>"+selectedItem2+"</Cod><Destinatie>"+selectedItem3+"</Destinatie><Tip>"+text+"</Tip><Descriere>"+text+"</Descriere></root>");
        return "<root><Cod>"+selectedItem+"</Cod><Destinatie>"+selectedItem2+"</Destinatie><Tip>"+selectedItem3+"</Tip><Descriere>"+text+"</Descriere></root>";
    }
}