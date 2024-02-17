package com.decofcards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckOfCards {

	private List<Card> cards;

	public DeckOfCards(){
		    Suit suits[] = Suit.values();
		    Rank ranks[] = Rank.values();
       	 	cards = new ArrayList<>();
       	 	for(int i=0;i<4;i++){
       	 		for(int j=0;j<13;j++){
       	 			cards.add(new Card(suits[i],ranks[j]));
       	 		}
       	 	}
       	 	Collections.shuffle(cards);
	}

	public List<Card> getCards() {
		return cards;
	}
	
	public Card compareRank(Card first,Card second){
       return first.getRank().ordinal()>second.getRank().ordinal()?first:second;
	}
	
	public void sort(){
		Collections.sort(cards, (card1,card2) -> {
			if(card1.getSuit() == card2.getSuit()){
				return (card1.getRank().ordinal() - card2.getRank().ordinal());
			}
			else{
				return (card1.getSuit().compareTo(card2.getSuit()));
			}
		});
	}
}
