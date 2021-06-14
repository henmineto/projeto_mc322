package com.unicamp.mc322.projeto.cartas;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.cartas.efeitos.AtivacaoEfeito;
import com.unicamp.mc322.projeto.cartas.efeitos.Efeito;

public abstract class Carta {
	private ArrayList<Efeito> efeitos;
	private int custo;
	
	protected Carta(int custo, ArrayList<Efeito> efeitos) {
		this.custo = custo;
		this.efeitos = efeitos;
	}
	
	public void ativar(AtivacaoEfeito ativacao) {
		for (Efeito efeito : efeitos) {
			if (efeito.getAtivacao() == ativacao && !efeito.getUsado()) {
				efeito.ativar();
			}
		}
	}
	
	public int getCusto() {
		return custo;
	}
}
