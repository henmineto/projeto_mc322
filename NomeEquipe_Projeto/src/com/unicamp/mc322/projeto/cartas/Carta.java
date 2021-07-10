package com.unicamp.mc322.projeto.cartas;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.cartas.ativacoes.AtivacaoEfeito;
import com.unicamp.mc322.projeto.jogadores.Jogador;

public abstract class Carta implements Compravel {
	private String nome;
	private int custo;
	private ArrayList<Efeito> efeitos;
	
	protected Carta(String nome, int custo) {
		this.nome = nome;
		this.custo = custo;
		this.efeitos = new ArrayList<Efeito>();
	}
	
	public void ativar(Jogador jogador, AtivacaoEfeito ativacao) throws Exception {
		for (Efeito efeito : efeitos) {
			if (efeito.getAtivacao() == ativacao && !efeito.getUsado()) {
				efeito.ativar(jogador, this);
			}
		}
	}
	
	public void addEfeito(Efeito efeito) {
		efeitos.add(efeito);
	}
	
	public int getCusto() {
		return custo;
	}
	
	public String toString() {
		return nome + " (" + custo + ")";
	}
}
