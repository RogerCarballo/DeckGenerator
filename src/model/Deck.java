package model;

import java.util.List;

public class Deck {

	private String deckName;
	private int deckValue;
	private List<Card> cards;

	public String getNombre() {
		return deckName;
	}

	public void setNombre(String deckName) {
		this.deckName = deckName;
	}

	public int getValueDeck() {
		return deckValue;
	}

	public void setValueDeck(int valueDeck) {
		this.deckValue = valueDeck;
	}

	public List<Card> getDeck() {
		return cards;
	}

	public void setDeck(List<Card> cards) {
		this.cards = cards;
	}

	@Override
	public String toString() {
		return "Deck [nombre=" + deckName + ", valueDeck=" + deckValue + ", cards=" + cards + "]";
	}

}
