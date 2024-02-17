package com.decofcards;

public class Card {
	
	private Suit suit;
	private Rank rank;
	
	public String toString(){
		return "Suit =  " + suit.toString() + " Rank = " + rank.toString();
	}
	
	public Card(Suit suit, Rank rank) {
		super();
		this.suit = suit;
		this.rank = rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}
	

	
}
