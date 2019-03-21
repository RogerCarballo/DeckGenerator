package impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.google.gson.Gson;

import interfaces.ICard;
import model.Card;

public class CardExistImpl implements ICard {

	private static CardExistImpl cardExist;
	final String driver = "org.exist.xmldb.DatabaseImpl";
	private static String URI = "xmldb:exist://localhost:8888/exist/xmlrpc/db/Card";
	private Database database;
	Collection col;
	XMLResource res;
	Class cl;
	private List<Card> cards = new ArrayList<Card>();

	private void connect() {
		try {
			cl = Class.forName(driver);
			database = (Database) cl.newInstance();
			DatabaseManager.registerDatabase(database);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// constructor privado para no poder instanciar directamente y tengamos que
	// llamar al getInstance.
	private CardExistImpl() {
		// TODO Auto-generated constructor stub
		connect();
		try {
			Collection col = DatabaseManager.getCollection(URI);
			res = (XMLResource) col.getResource("cards.xml");
			JSONObject xmlJSONObj = XML.toJSONObject((String) res.getContent());
			
			JSONArray allCards = xmlJSONObj.getJSONObject("cards").getJSONArray("card");
//			System.out.println(allCards.toString());
			for (Object object : allCards) {
				Card carta = new Gson().fromJson(object.toString(), Card.class);
				cards.add(carta);
			}

		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Patron signleton para instanciar una vez el objeto la primera vez que lo
	// llamemos y nunca mas.
	public static CardExistImpl getInstance() {

		if (cardExist == null) {
			cardExist = new CardExistImpl();
		}
		return cardExist;
	}

	public List<Card> getCards() {
		// TODO Auto-generated method stub
		return cards;
	}

	public Card getCard(int id) {

		return null;
	}
	
	//TEST
	public static void main(String[] args) {
		CardExistImpl cardImpl = CardExistImpl.getInstance();
		System.out.println(cardImpl.getCards());
	}

}
