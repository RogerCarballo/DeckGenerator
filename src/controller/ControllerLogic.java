package controller;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import impl.CardExistImpl;
import model.Card;

public class ControllerLogic {
	CardExistImpl exist;

	int valorIni = 0;

	public void loadCards(JList<Card> listCartas, DefaultListModel<Card> modeloCarta) {
		exist = CardExistImpl.getInstance();
		if (modeloCarta.getSize() == 0) {
			for (Card carta : exist.getCards()) {
				modeloCarta.addElement(carta);
			}
			listCartas.setModel(modeloCarta);
		}
	}

	public void chooseCard(JList<Card> listaCartas, JList<Card> listaMazo, DefaultListModel<Card> modeloMazo,
			DefaultListModel<Card> modeloCarta) {
		if (modeloCarta.getSize() != 0) {
			if (valorIni + listaCartas.getSelectedValue().getValor() <= 20) {

				valorIni = valorIni + listaCartas.getSelectedValue().getValor();
				modeloMazo.addElement(listaCartas.getSelectedValue());
				modeloCarta.removeElementAt(listaCartas.getSelectedIndex());
				listaMazo.setModel(modeloMazo);
			}
		}
	}

	public void deleteCardDeck(JList<Card> listaCartas, JList<Card> listaMazo, DefaultListModel<Card> modeloMazo,
			DefaultListModel<Card> modeloCarta) {
		if (modeloMazo.getSize() != 0) {
			valorIni = valorIni - listaMazo.getSelectedValue().getValor();
			modeloCarta.addElement(listaMazo.getSelectedValue());
			modeloMazo.removeElementAt(listaMazo.getSelectedIndex());
			listaCartas.setModel(modeloCarta);
		}
	}

}
