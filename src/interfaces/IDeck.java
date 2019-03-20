package interfaces;

import model.Deck;

public interface IDeck {

	public Deck getDeckByName(String name);
	public void loadDeck(Deck deck);
	
	
}
