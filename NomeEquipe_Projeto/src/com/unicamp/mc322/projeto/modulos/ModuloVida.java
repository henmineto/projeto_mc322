package com.unicamp.mc322.projeto.modulos;

public class ModuloVida {
	private int vida;
	private int ultimoDanoSofrido;
	private boolean temEscudo;
	
	public ModuloVida(int vida) {
		this.vida = vida;
		
		ultimoDanoSofrido = 0;
		temEscudo = false;
	}
	
	public void recuperarVida(int bonus) {
		this.vida += bonus;
	}
	
	public void receberDano(int dano) {
		if (temEscudo) {
			dano = 0;
			temEscudo = false;
		}

		ultimoDanoSofrido = Math.min(vida, dano);
		this.vida = Math.max(vida - dano, 0);
	}
	
	public int getVida() {
		return vida;
	}
	
	public int getUltimoDanoSofrido() {
		return ultimoDanoSofrido;
	}

	public void ativarEscudo() {
		temEscudo = true;
	}

	public void desativarEscudo() {
		temEscudo = false;
	}
}
