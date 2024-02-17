package com.decofcards;

public class Main {

	public static void main(String[] args) {

		DeckOfCards deck = new DeckOfCards();
		
		Card first = deck.getCards().get(2);
		Card second = deck.getCards().get(12);
		System.out.println("First Card: " + first.toString() + "\n" + "Second Card: " + second.toString() + "\n"
				            + "Bigger card: " + deck.compareRank(first,second)); 

		/*Card one = new Card(Suit.DIMOND,Rank.ACE);
		Card two = new Card(Suit.SPADE,Rank.FOUR);
		System.out.println("First Card: " + one.toString() + "\n" + "Second Card: " + two.toString() + "\n"
	            + "Bigger card: " + deck.compareRank(one , two).toString()); 
*/
		deck.sort();
		System.out.println("Sorted deck: \n" + deck.getCards().toString() );
	}

}
