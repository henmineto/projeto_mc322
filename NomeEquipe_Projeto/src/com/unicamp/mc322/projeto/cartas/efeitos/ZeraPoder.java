package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.cartas.Ativavel;
import com.unicamp.mc322.projeto.cartas.Efeito;
import com.unicamp.mc322.projeto.cartas.ativacoes.AtivacaoEfeito;
import com.unicamp.mc322.projeto.jogadores.Jogador;
import com.unicamp.mc322.projeto.jogo.LORException;
import com.unicamp.mc322.projeto.jogo.MediadorEfeitos;

public class ZeraPoder extends Efeito {

    public ZeraPoder(AtivacaoEfeito momento) {
        super(momento);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void ativar(Jogador jogador, Ativavel carta) throws LORException {
        super.ativar(jogador, carta);

        MediadorEfeitos.getInstance().zerarAtaqueUnidade(jogador);
    }

    @Override
    public String toString() {
        return "Zera o ataque de uma unidade por um turno em " + momento;
    }
}