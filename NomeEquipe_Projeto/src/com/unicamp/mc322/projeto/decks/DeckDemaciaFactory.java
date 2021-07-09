package com.unicamp.mc322.projeto.decks;

public class DeckDemaciaFactory implements DeckFactory {

	public Deck gerarDeck() {
		return new DeckDemacia();
	}

}
