package com.unicamp.mc322.projeto.cartas;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.cartas.ativacoes.AtivacaoTraco;
import com.unicamp.mc322.projeto.modulos.ModuloAtaque;
import com.unicamp.mc322.projeto.modulos.ModuloVida;

public class Seguidor extends Carta implements Evocavel {

	private ModuloVida vida;
	protected ModuloAtaque ataque;
	private ArrayList<Traco> tracos;
	private int ataquesPorTurno;
	private boolean elusivo;
	private boolean furia;
	private int furiaBonusVida;
	private int furiaBonusAtaque;
	
	public Seguidor(String nome, int vida, int ataque, int custo, ArrayList<Efeito> efeitos, ArrayList<Traco> tracos) {
		super(nome, custo, efeitos);
		this.vida = new ModuloVida(vida);
		this.ataque = new ModuloAtaque(ataque);
		this.tracos = new ArrayList<Traco>();
		for(Traco traco : tracos) {
			adicionarTraco(traco);
		}

		ataquesPorTurno = 1;
	}
	
	public void adicionarTraco(Traco traco) {
		if (traco.getAtivacao() == AtivacaoTraco.ADICAO_DO_TRACO) {
			traco.ativar(this);
		}
		else {
			tracos.add(traco);
		}
	}
	
	public void setElusivo() {
		this.elusivo = true;
	}
	
	public boolean isElusivo() {
		return elusivo;
	}

	public void setAtaqueDuplo() { ataquesPorTurno = 2; }

	public int getAtaquesPorTurno() { return ataquesPorTurno; }

	public void setFuria(int bonusVida, int bonusAtaque) {
		this.furia = true;

		furiaBonusVida = bonusVida;
		furiaBonusAtaque = bonusAtaque;
	}

	public boolean isFuria() {
		return furia;
	}

	public void receberBonusFuria() {
		if (furia) {
			aumentarDano(furiaBonusAtaque);
			aumentarVida(furiaBonusVida);
		}
	}
	
	public void atacar(Atacavel atacavel) {
		atacavel.receberDano(ataque.getAtaque());
	}
	
	public boolean estaVivo() {
		return vida.getVida() > 0;
	}
	
	public void receberDano(int dano) {
		vida.receberDano(dano);
	}
	
	public void aumentarDano(int bonus) {
		ataque.aumentarAtaque(bonus);
	}
	
	public void aumentarVida(int bonus) {
		vida.recuperarVida(bonus);
	}
}
