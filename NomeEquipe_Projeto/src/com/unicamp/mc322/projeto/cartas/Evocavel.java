package com.unicamp.mc322.projeto.cartas;

public interface Evocavel extends Atacavel, Compravel {
	void addTraco(Traco traco);
	
	boolean isElusivo();
	
	void atacar(Atacavel atacavel);
	
	void aumentarDano(int bonus);
	
	void aumentarVida(int bonus);

	void curaCompleta();
	
	void setAtaqueDuplo();
	
	int getAtaquesPorTurno();
	
	void setFuria(int bonusVida, int bonusAtaque);
	
	boolean isFuria();
	
	void receberBonusFuria();
	
	void setElusivo();

	void garantirEscudo();

	void zerarAtaque();

	boolean verificarMatouNesseTurno();
}
