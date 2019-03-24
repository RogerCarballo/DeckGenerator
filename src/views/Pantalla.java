package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ControllerLogic;
import model.Card;

public class Pantalla extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textMazo;
	private JButton btnGuardarMazo, btnCargarCartas, buttonPasar, buttonDevolver, btnGenerarMazo, btnCargarMazo;
	private JList<Card> listCartas, list;
	private ControllerLogic cLogic;
	DefaultListModel<Card> modeloCarta, modeloMazo;
	public static boolean mazoCargado = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		Pantalla frame = new Pantalla();
		frame.setVisible(true);

	}

	/**
	 * Create the frame.
	 */
	public Pantalla() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 902, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		modeloCarta = new DefaultListModel<Card>();
		modeloMazo = new DefaultListModel<Card>();
		buttonPasar = new JButton(">");
		buttonPasar.setBounds(420, 221, 45, 23);

		buttonDevolver = new JButton("<");
		buttonDevolver.setBounds(420, 255, 45, 23);

		listCartas = new JList();
		listCartas.setBounds(15, 47, 347, 455);

		list = new JList();
		list.setBounds(527, 47, 349, 456);

		JLabel lblCartas = new JLabel("CARTAS");
		lblCartas.setBounds(138, 11, 71, 25);
		lblCartas.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblMazo = new JLabel("MAZO");
		lblMazo.setBounds(675, 11, 52, 25);
		lblMazo.setFont(new Font("Tahoma", Font.PLAIN, 20));

		btnGenerarMazo = new JButton("Generar Mazo");
		btnGenerarMazo.setBounds(372, 360, 145, 23);

		btnCargarCartas = new JButton("Cargar Cartas");
		btnCargarCartas.setBounds(372, 99, 145, 23);

		btnGuardarMazo = new JButton("Guardar Mazo");
		btnGuardarMazo.setBounds(372, 326, 145, 23);
		contentPane.setLayout(null);
		contentPane.add(listCartas);
		contentPane.add(buttonDevolver);
		contentPane.add(buttonPasar);
		contentPane.add(btnCargarCartas);
		contentPane.add(btnGuardarMazo);
		contentPane.add(list);
		contentPane.add(btnGenerarMazo);
		contentPane.add(lblCartas);
		contentPane.add(lblMazo);

		textMazo = new JTextField();
		textMazo.setBounds(372, 427, 145, 20);
		contentPane.add(textMazo);
		textMazo.setColumns(10);

		btnCargarMazo = new JButton("Cargar Mazo");
		btnCargarMazo.setBounds(372, 458, 145, 23);
		contentPane.add(btnCargarMazo);
		btnCargarCartas.addActionListener(this);
		buttonPasar.addActionListener(this);
		buttonDevolver.addActionListener(this);
		btnGenerarMazo.addActionListener(this);
		btnGuardarMazo.addActionListener(this);
		btnCargarMazo.addActionListener(this);
		cLogic = new ControllerLogic();

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCargarCartas) {
			cLogic.loadCards(listCartas, modeloCarta);
		} else if (e.getSource() == buttonDevolver) {
			cLogic.deleteCardDeck(listCartas, list, modeloMazo, modeloCarta);
		} else if (e.getSource() == buttonPasar) {
			cLogic.chooseCard(listCartas, list, modeloMazo, modeloCarta);
		} else if (e.getSource() == btnGenerarMazo) {
			cLogic.randomDeck(listCartas, list, modeloMazo, modeloCarta);
		} else if (e.getSource() == btnGuardarMazo) {
			cLogic.addDeck(listCartas, list, modeloMazo, modeloCarta, textMazo.getText());
			mazoCargado = false;
			if (!mazoCargado)
				textMazo.setText("");
		} else if (e.getSource() == btnCargarMazo) {
			cLogic.loadDeck(list, modeloMazo, textMazo.getText());
			mazoCargado = true;
		}

	}
}