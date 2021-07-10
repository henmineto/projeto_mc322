package com.unicamp.mc322.projeto.modulos;

public class ModuloVida {
	private int vida;
	private int ultimoDanoSofrido;
	
	public ModuloVida(int vida) {
		this.vida = vida;
		
		ultimoDanoSofrido = 0;
	}
	
	public void recuperarVida(int bonus) {
		this.vida += bonus;
	}
	
	public void receberDano(int dano) {
		ultimoDanoSofrido = Math.min(vida, dano);
		this.vida = Math.max(vida - dano, 0);
	}
	
	public int getVida() {
		return vida;
	}
	
	public int getUltimoDanoSofrido() {
		return ultimoDanoSofrido;
	}
}
