package model;

public class Card {

	private int id;
	private String name;
	private int summonCost;
	private int attack;
	private int defense;
	private int value;

	public int getId() {
		return id;
	}

	public String getNombre() {
		return name;
	}

	public int getCoste() {
		return summonCost;
	}

	public int getAtaque() {
		return attack;
	}

	public int getDefensa() {
		return defense;
	}

	public int getValor() {
		return value;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", nombre=" + name + ", coste=" + summonCost + ", ataque=" + attack + ", defensa="
				+ defense + ", valor=" + value + "]";
	}

}
