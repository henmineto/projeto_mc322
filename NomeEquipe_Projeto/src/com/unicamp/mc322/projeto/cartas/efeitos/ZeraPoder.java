package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.cartas.Efeito;
import com.unicamp.mc322.projeto.cartas.ativacoes.AtivacaoEfeito;

public class ZeraPoder extends Efeito {

    public ZeraPoder(AtivacaoEfeito momento) {
        super(momento);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void ativar(Jogador jogador, Ativavel carta) throws Exception {
        super.ativar(jogador, carta);

        MediadorEfeitos.getInstance().zerarAtaqueUnidade(jogador);
    }
}