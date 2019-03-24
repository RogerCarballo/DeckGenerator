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

	public String getName() {
		return name;
	}

	public int getsummonCost() {
		return summonCost;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefense() {
		return defense;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "name=" + name + ", COST:" + summonCost + ", ATQ:" + attack + ", DEF:" + defense + ", VAL:" + value + "";
	}

}
