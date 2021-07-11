package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.cartas.Ativavel;
import com.unicamp.mc322.projeto.cartas.Efeito;
import com.unicamp.mc322.projeto.cartas.ativacoes.AtivacaoEfeito;
import com.unicamp.mc322.projeto.jogadores.Jogador;
import com.unicamp.mc322.projeto.jogo.MediadorEfeitos;

public class GeraEscudo extends Efeito {

    public GeraEscudo(AtivacaoEfeito momento) {
        super(momento);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void ativar(Jogador jogador, Ativavel carta) throws Exception {
        super.ativar(jogador, carta);

        MediadorEfeitos.getInstance().garantirEscudoUnidade(jogador);
    }

    @Override
    public String toString() {
        return "Gera uma escudo em uma unidade em " + momento;
    }
}