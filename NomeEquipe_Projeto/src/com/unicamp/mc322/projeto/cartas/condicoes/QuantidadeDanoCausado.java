package com.unicamp.mc322.projeto.cartas.condicoes;

import com.unicamp.mc322.projeto.cartas.Atacavel;
import com.unicamp.mc322.projeto.cartas.Campeao;
import com.unicamp.mc322.projeto.cartas.ModuloCondicao;
import com.unicamp.mc322.projeto.cartas.Evocavel;

public class QuantidadeDanoCausado extends ModuloCondicao {

    public QuantidadeDanoCausado(int objetivo) {
        super(objetivo);
    }

    @Override
    public void contabilizarAtaque(Atacavel atacavel, int dano) {
        if (atacavel != null) {
            quantidadeAtingida += dano;
        }

        super.contabilizarAtaque(atacavel, dano);
    }

    @Override
    protected void contabilizarDanoSofrido(int danoSofrido) {

    }

    @Override
    public String toString() {
        return "Evolui ao causar " + objetivo + " dano";
    }
}