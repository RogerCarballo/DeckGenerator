package model;

import java.util.List;

public class Deck {

	private String deckName;
	private int deckValue;
	private List<Card> cards;

	public String getDeckName() {
		return deckName;
	}

	public void setNombre(String deckName) {
		this.deckName = deckName;
	}

	public int getDeckValue() {
		return deckValue;
	}

	public void setValueDeck(int valueDeck) {
		this.deckValue = valueDeck;
	}

	public List<Card> getCards() {
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
