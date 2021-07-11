package com.unicamp.mc322.projeto.cartas;

import com.unicamp.mc322.projeto.cartas.ativacoes.AtivacaoEfeito;
import com.unicamp.mc322.projeto.jogadores.Jogador;
import com.unicamp.mc322.projeto.jogo.LORException;

public interface Ativavel {
	void ativar(Jogador jogador, AtivacaoEfeito ativacao) throws LORException;
	
	void addEfeito(Efeito efeito);
}
