package com.unicamp.mc322.projeto.cartas;

import java.util.ArrayList;

public class Seguidor extends Unidade {

	private ArrayList<Traco> tracos;
	private boolean elusivo;
	
	public Seguidor(int vida, int ataque, int custo, ArrayList<Efeito> efeitos, ArrayList<Traco> tracos) {
		super(vida, ataque, custo, efeitos);
		for(Traco traco : tracos) {
			adicionarTraco(traco);
		}
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
}
