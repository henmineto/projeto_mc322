package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.cartas.Efeito;
import com.unicamp.mc322.projeto.cartas.ativacoes.AtivacaoEfeito;

public class DanificaNexus extends Efeito {

    private dano;

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

}