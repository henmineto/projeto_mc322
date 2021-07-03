package com.unicamp.mc322.projeto.cartas.condicoes;

import com.unicamp.mc322.projeto.cartas.Atacavel;
import com.unicamp.mc322.projeto.cartas.Campeao;
import com.unicamp.mc322.projeto.cartas.ModuloCondicao;
import com.unicamp.mc322.projeto.cartas.Evocavel;

public class QuantidadeAtaque extends ModuloCondicao {

    public QuantidadeAtaque(int objetivo) {
        super(objetivo);
    }

    @Override
    public void contabilizarAtaque(Atacavel atacavel, int dano) {
        if (atacavel != null) {
            quantidadeAtingida++;
        }

        super.contabilizarAtaque(atacavel, dano);
    }

    @Override
    protected void contabilizarDanoSofrido(int danoSofrido) {

    }

}