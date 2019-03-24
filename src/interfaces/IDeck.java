package interfaces;

import model.Deck;

public interface IDeck {

	public Deck getDeckByName(String name);

	public void loadDeck(Deck deck);

	public void insertDeck(Deck deck);

	public void updateDeck(Deck deck);
}
