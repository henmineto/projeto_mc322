package com.unicamp.mc322.projeto.cartas;

import com.unicamp.mc322.projeto.cartas.ativacoes.AtivacaoEfeito;

public interface Ativavel {
	void ativar(AtivacaoEfeito ativacao) throws Exception;
}
