package impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.bson.Document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import interfaces.IDeck;
import model.Deck;

public class DeckMongoImpl implements IDeck {

	private static DeckMongoImpl deckMongo;
	final String db = "Mazos";
	final String col = "Mazo";
	private static String URI = "mongodb://localhost:27017";
	private MongoClientURI connector;
	private MongoClient mongoClient;
	private MongoDatabase database;
	private MongoCollection<Document> coleccion;

	private DeckMongoImpl() {
		// TODO Auto-generated constructor stub
		connect();
	}

	private void connect() {
		try {
			connector = new MongoClientURI(URI);
			mongoClient = new MongoClient(connector);
			database = mongoClient.getDatabase(db);
			coleccion = database.getCollection(col);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DeckMongoImpl getInstance() {

		if (deckMongo == null) {
			deckMongo = new DeckMongoImpl();
		}
		return deckMongo;
	}

	public Deck getDeckByName(String name) {
		Deck deck;

		try {
			MongoCursor<Document> cursor = coleccion.find(Filters.eq("deckName", name)).iterator();

			Document document = cursor.next();
			deck = new Gson().fromJson(document.toJson(), Deck.class);
			return deck;
		} catch (NoSuchElementException e) {
			deck = null;
		}
		return deck;
	}

	public void loadDeck(Deck deck) {
		// TODO Auto-generated method stub

	}

	public void insertDeck(Deck deck) {
		deck.toString();
		List<Object> cardDeck = new BasicDBList();
		Document doc = new Document();
		doc.put("deckName", deck.getNombre());
		for (int i = 0; i < deck.getDeck().size(); i++) {
			DBObject object = new BasicDBObject();
			object.put("id", deck.getDeck().get(i).getId());
			object.put("name", deck.getDeck().get(i).getNombre());
			object.put("summonCost", deck.getDeck().get(i).getCoste());
			object.put("attack", deck.getDeck().get(i).getAtaque());
			object.put("defense", deck.getDeck().get(i).getDefensa());
			object.put("value", deck.getDeck().get(i).getValor());
			cardDeck.add(object);
		}

		doc.put("deckValue", deck.getValueDeck());
		doc.put("cards", cardDeck);
		coleccion.insertOne(doc);

	}

	public void updateDeck(Deck deck) {
		Document document = coleccion.find(Filters.eq("deckName", deck.getNombre())).first();
		ObjectMapper mapper = new ObjectMapper();
		
		boolean saved=false;
		if(document!=null) {
			String userJson = null;
			try {
				userJson = mapper.writeValueAsString(deck);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
            Document userDoc = Document.parse(userJson);
			coleccion.findOneAndReplace(Filters.eq("deckName", deck.getNombre()), userDoc);
			saved=true;
		}
	}

}
