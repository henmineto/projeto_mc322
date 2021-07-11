package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.cartas.Ativavel;
import com.unicamp.mc322.projeto.cartas.Efeito;
import com.unicamp.mc322.projeto.cartas.ativacoes.AtivacaoEfeito;
import com.unicamp.mc322.projeto.jogadores.Jogador;
import com.unicamp.mc322.projeto.jogo.MediadorEfeitos;

public class DanificaNexus extends Efeito {

    private int dano;

    public DanificaNexus(AtivacaoEfeito momento, int dano) {
        super(momento);
        this.dano = dano;
        // TODO Auto-generated constructor stub
    }

    @Override
    public void ativar(Jogador jogador, Ativavel carta) throws Exception {
        super.ativar(jogador, carta);

        MediadorEfeitos.getInstance().danificarNexusInimigo(jogador, dano);
    }

    @Override
    public String toString() {
        return "Causa " + dano + " de dano ao nexus inimigo em " + momento;
    }

}