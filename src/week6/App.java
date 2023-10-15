package week6;

import java.util.*;

// card class. handles all the data and operations for a single card
class Card {
	private int value;
	private String name;
	
	// set the name and value of a card
	void setValue(int a, String type) {
		this.value = a;
		switch(a) {
			case 2: 
				this.name = "Two of " + type;
				break;
			case 3: 
				this.name = "Three of " + type;
				break;
			case 4: 
				this.name = "Four of " + type;
				break;
			case 5: 
				this.name = "Five of " + type;
				break;
			case 6: 
				this.name = "Six of " + type;
				break;
			case 7: 
				this.name = "Seven of " + type;
				break;
			case 8: 
				this.name = "Eight of " + type;
				break;
			case 9: 
				this.name = "Nine of " + type;
				break;
			case 10: 
				this.name = "Ten of " + type;
				break;
			case 11: 
				this.name = "Jack of " + type;
				break;
			case 12: 
				this.name = "Queen of " + type;
				break;
			case 13: 
				this.name = "King of " + type;
				break;
			case 14: 
				this.name = "Ace of " + type;
				break;			
		}
	}
	
	// return name
	String getString() {
		return this.name;
	}
	
	//return value
	int getValue() {
		return this.value;
	}
	
	// print out aspects of this card
	void describe() {
		System.out.println("Card power rating: " + (this.value - 1));
		System.out.println("Card name : " + this.name);
		System.out.println();
	}
}

// deck class. handles all operations relating to lists of cards
class Deck {
	
	private List<Card> cards = new ArrayList<Card>();
	
	//default constructor. adds all 4 types of suits to a list
	Deck() {
		for(int i = 2; i <= 14; i++) {
			Card cd = new Card();
			cd.setValue(i, "Hearts");
			cards.add(cd);
		}
		for(int i = 2; i <= 14; i++) {
			Card cd = new Card();
			cd.setValue(i, "Diamonds");
			cards.add(cd);
		}
		for(int i = 2; i <= 14; i++) {
			Card cd = new Card();
			cd.setValue(i, "Spades");
			cards.add(cd);
		}
		for(int i = 2; i <= 14; i++) {
			Card cd = new Card();
			cd.setValue(i, "Clovers");
			cards.add(cd);
		}
	}
	
	// this methods randomizes the order of the deck
	void shuffle() {
		List<Card> temp = new ArrayList<Card>();
		int remaining = 52;
		Random rand = new Random();
		while(remaining > 0) {
			int index = rand.nextInt(remaining - 0 ) + 0;
			temp.add(cards.get(index));
			this.cards.remove(index);
			remaining--;
		}
		this.cards = temp;
	}
	
	// this method return the top of the deck and deletes it from said deck
	Card draw() {
		int index = cards.size() - 1;
		Card temp = cards.get(index);
		cards.remove(index);
		return temp;
	}
}

// player method. handles all operations relating to players 
class Player {
	private List<Card> hand = new ArrayList<Card>();
	private int score;
	private String name;
	
	// default constructor. sets score
	Player() {
		this.score = 0;
	}
	
	// method to name the player
	void setName(String s) {
		this.name = s;
	}
	
	// this method prints out all relevant data with assistance via a for loop
	void describe() {
		System.out.println("Player name: " + this.name);
		System.out.println("Players current score: " + this.score);
		for(int i = 0; i < hand.size(); i++) {
			hand.get(i).describe();
		}
	}
	
	// this method returns the top of the hand list and also deletes it
	Card flip() {
		int index = hand.size() - 1;
		Card temp = hand.get(index);
		hand.remove(index);
		return temp;
	}
	
	// this method calls the draw method in the deck class
	void draw(Deck d) {
		hand.add(d.draw());
	}
	
	// this method increases score by one
	void incrementScore() {
		this.score++;
	}
	
	// the method returns the score
	int getScore() {
		return this.score;
	}
}

// main method
public class App {
	public static void main(String[] args) {
		Deck fDeck = new Deck();
		fDeck.shuffle();
		Player player1 = new Player();
		player1.setName("Player1");
		Player player2 = new Player();
		player2.setName("Player2");
		for(int i = 0; i < 52; i++) {
			if(i % 2 == 0) {
				player1.draw(fDeck);
			} else {
				player2.draw(fDeck);
			}
		}
		for(int i = 0; i < 26; i++) {
			player1.describe();
			player2.describe();
			int p1 = player1.flip().getValue();
			int p2 = player2.flip().getValue();
			if(p1 > p2) {
				player1.incrementScore();
				System.out.println("Player 1 scores a point!");
			} else if (p1 < p2) {
				player2.incrementScore();
				System.out.println("Player 2 scores a point!");
			} else {
				System.out.println("Tie! No points awarded.");
			}
		}
		int p1 = player1.getScore();
		int p2 = player2.getScore();
		System.out.println("!!!GAME END!!!");
		System.out.println("Player 1 score: " + p1);
		System.out.println("Player 2 score: " + p2);
		if(p1 > p2) {
			System.out.println("Winner: Player 1");
		} else if(p1 < p2) {
			System.out.println("Winner: Player 2");
		} else {
			System.out.println("Game ends in a draw.");
		}
	}
}
