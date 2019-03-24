package interfaces;

import java.util.List;

import model.Card;

public interface ICard {

	public List<Card> getCards();

	public Card getCard(int id);

}
