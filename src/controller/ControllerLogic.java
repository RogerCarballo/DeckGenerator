package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import impl.CardExistImpl;
import impl.DeckMongoImpl;
import model.Card;
import model.Deck;
import views.Pantalla;

public class ControllerLogic {
	CardExistImpl exist;
	DeckMongoImpl mongo;
	int valorIni = 0;
	boolean generateRandom = false;
	boolean existeMazo = false;

	public void loadCards(JList<Card> listCartas, DefaultListModel<Card> modeloCarta) {
		modeloCarta.clear();
		exist = CardExistImpl.getInstance();
		if (modeloCarta.getSize() == 0) {
			for (Card carta : exist.getCards()) {
				modeloCarta.addElement(carta);
			}
			generateRandom = true;
			listCartas.setModel(modeloCarta);
		}
	}

	public void chooseCard(JList<Card> listaCartas, JList<Card> listaMazo, DefaultListModel<Card> modeloMazo,
			DefaultListModel<Card> modeloCarta) {
		if (modeloCarta.getSize() != 0) {
			if (valorIni + listaCartas.getSelectedValue().getValue() <= 20) {

				valorIni = valorIni + listaCartas.getSelectedValue().getValue();
				modeloMazo.addElement(listaCartas.getSelectedValue());
				modeloCarta.removeElementAt(listaCartas.getSelectedIndex());
				listaMazo.setModel(modeloMazo);
			}
		}
	}

	public void deleteCardDeck(JList<Card> listaCartas, JList<Card> listaMazo, DefaultListModel<Card> modeloMazo,
			DefaultListModel<Card> modeloCarta) {
		if (modeloMazo.getSize() != 0) {
			valorIni = valorIni - listaMazo.getSelectedValue().getValue();
			modeloCarta.addElement(listaMazo.getSelectedValue());
			modeloMazo.removeElementAt(listaMazo.getSelectedIndex());
			listaCartas.setModel(modeloCarta);
		}
		System.out.println("carta eliminada " + valorIni);
	}

	public void randomDeck(JList<Card> listaCartas, JList<Card> listaMazo, DefaultListModel<Card> modeloMazo,
			DefaultListModel<Card> modeloCarta) {

		if (generateRandom) {
			valorIni = 0;
			modeloMazo.clear();
			loadCards(listaMazo, modeloCarta);
			int random = 0;
			while (valorIni <= 20) {
				random = (int) (Math.random() * modeloCarta.size() + 1) - 1;
				System.out.println(random);
				if (valorIni + listaCartas.getModel().getElementAt(random).getValue() <= 20) {
					modeloMazo.addElement(listaCartas.getModel().getElementAt(random));
					valorIni = valorIni + listaCartas.getModel().getElementAt(random).getValue();
					modeloCarta.removeElementAt(random);
				} else {
					valorIni = valorIni + listaCartas.getModel().getElementAt(random).getValue();
				}
			}
			valorIni = valorIni - listaCartas.getModel().getElementAt(random).getValue();
			listaMazo.setModel(modeloMazo);
		}
	}

	public void addDeck(JList<Card> listaCartas, JList<Card> listaMazo, DefaultListModel<Card> modeloMazo,
			DefaultListModel<Card> modeloCarta, String deckName) {
		if (!Pantalla.mazoCargado) {
			String nombreMazo = JOptionPane.showInputDialog("Introduce el nombre del mazo");
			if (!nombreMazo.equals("")) {
				if (!comprobarSiExiste(nombreMazo)) {
					List<Card> cartas = new ArrayList<Card>();
					Deck mazo = new Deck();
					mazo.setNombre(nombreMazo);
					for (int j = 0; j < listaMazo.getModel().getSize(); j++) {
						cartas.add(listaMazo.getModel().getElementAt(j));
					}
					mazo.setDeck(cartas);
					mazo.setValueDeck(valorIni);
					modeloMazo.clear();
					modeloCarta.clear();
					loadCards(listaCartas, modeloCarta);
					mongo = DeckMongoImpl.getInstance();
					mongo.insertDeck(mazo);
					JOptionPane.showMessageDialog(null, "Deck " + nombreMazo + " creado correctamente");
				} else {
					JOptionPane.showMessageDialog(null, "Ya existe el nombre del mazo", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "El nombre no puede estar en blanco", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			List<Card> cartas = new ArrayList<Card>();
			Deck mazo = new Deck();
			mazo.setNombre(deckName);
			for (int j = 0; j < listaMazo.getModel().getSize(); j++) {
				cartas.add(listaMazo.getModel().getElementAt(j));
			}
			mazo.setDeck(cartas);
			mazo.setValueDeck(valorIni);
			mongo.updateDeck(mazo);
			modeloMazo.clear();
			loadCards(listaCartas, modeloCarta);
			JOptionPane.showMessageDialog(null, "Deck " + deckName + " actualizado correctamente");

		}
	}

	private boolean comprobarSiExiste(String name) {
		mongo = DeckMongoImpl.getInstance();
		Deck deck = mongo.getDeckByName(name);
		if (deck == null) {
			return false;
		}

		return true;
	}

	public void loadDeck(JList<Card> listaMazo, DefaultListModel<Card> modeloMazo, String name) {
		modeloMazo.clear();
		if (comprobarSiExiste(name)) {
			mongo = DeckMongoImpl.getInstance();
			Deck deck = mongo.getDeckByName(name);
			for (int i = 0; i < deck.getCards().size(); i++) {
				modeloMazo.addElement(deck.getCards().get(i));
			}
			listaMazo.setModel(modeloMazo);
			valorIni = deck.getDeckValue();
			JOptionPane.showMessageDialog(null, "Deck " + name + " cargado");
		} else {
			JOptionPane.showMessageDialog(null, "El mazo no existe", "Error", JOptionPane.ERROR_MESSAGE);
		}
		;
	};
}
