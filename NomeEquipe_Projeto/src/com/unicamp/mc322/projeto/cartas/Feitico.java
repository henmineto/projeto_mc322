package com.unicamp.mc322.projeto.cartas;

public class Feitico extends Carta {
	
	public Feitico(String nome, int custo) {
		super(nome, custo);
	}

	@Override
	public String getInfo() {
		return "Feitiço - " + toString() + " - " + getInfoEfeitos();
	}
}
