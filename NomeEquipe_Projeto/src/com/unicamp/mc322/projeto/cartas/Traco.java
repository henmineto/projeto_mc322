package com.unicamp.mc322.projeto.cartas;

import com.unicamp.mc322.projeto.cartas.ativacoes.AtivacaoTraco;

public abstract class Traco {
	private AtivacaoTraco ativacao;
	
	protected Traco(AtivacaoTraco ativacao) {
		this.ativacao = ativacao;
	}
	
	protected AtivacaoTraco getAtivacao() {
		return ativacao;
	}
	
	protected abstract void ativar(Seguidor seguidor);
}
