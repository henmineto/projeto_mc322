package com.unicamp.mc322.projeto.cartas;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.jogadores.Jogador;
import com.unicamp.mc322.projeto.modulos.ModuloAtaque;
import com.unicamp.mc322.projeto.modulos.ModuloVida;

public class Unidade extends Carta {
	private ModuloVida vida;
	protected ModuloAtaque ataque;
	
	protected Unidade(int vida, int ataque, int custo, ArrayList<Efeito> efeitos) {
		super(custo, efeitos);
		this.vida = new ModuloVida(vida);
		this.ataque = new ModuloAtaque(ataque);
	}
	
	public void atacar(Unidade unidade) {
		unidade.receberDano(ataque.getAtaque());
	}
	
	public void atacar(Jogador jogador) {
		jogador.receberDano(ataque.getAtaque());
	}
	
	public boolean estaVivo() {
		return vida.getVida() > 0;
	}
	
	private void receberDano(int dano) {
		vida.receberDano(dano);
	}
	
	public void aumentarDano(int bonus) {
		ataque.aumentarAtaque(bonus);
	}
	
	public void aumentarVida(int bonus) {
		vida.recuperarVida(bonus);
	}
}
