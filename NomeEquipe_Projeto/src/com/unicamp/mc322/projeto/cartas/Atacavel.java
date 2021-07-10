package com.unicamp.mc322.projeto.cartas;

public interface Atacavel {
	void receberDano(int dano);
	
	int getAtaque();
	
	int getVida();
	
	boolean estaVivo();
}
