package com.unicamp.mc322.projeto.cartas;

public interface Evocavel extends Atacavel {
	void adicionarTraco(Traco traco);
	
	boolean isElusivo();
	
	void atacar(Atacavel atacavel);
	
	void aumentarDano(int bonus);
	
	void aumentarVida(int bonus);
}
