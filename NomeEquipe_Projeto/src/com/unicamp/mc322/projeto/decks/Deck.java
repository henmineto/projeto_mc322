package com.unicamp.mc322.projeto.decks;

import java.util.ArrayList;
import java.util.Random;

import com.unicamp.mc322.projeto.cartas.Carta;

public abstract class Deck {
	protected ArrayList<Carta> cartas;
	
	protected Deck() {
		cartas = new ArrayList<>();
	}
	
	public Carta pegarCarta() {
		if ((cartas.size()) > 0) {
			int indexAleatorio = new Random().nextInt(cartas.size());
			return cartas.remove(indexAleatorio);
		}
		return null;
	}
}
