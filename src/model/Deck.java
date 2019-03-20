package model;

import java.util.List;

public class Deck {

	private String nombre;
	private int valueDeck;
	private List<Card> deck;
	
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getValueDeck() {
		return valueDeck;
	}
	
	public void setValueDeck(int valueDeck) {
		this.valueDeck = valueDeck;
	}
	
	public List<Card> getDeck() {
		return deck;
	}
	
	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}
	
	
	
	
	
	
}
