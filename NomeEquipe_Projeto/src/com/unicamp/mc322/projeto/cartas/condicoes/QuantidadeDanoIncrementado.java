package com.unicamp.mc322.projeto.cartas.condicoes;

import com.unicamp.mc322.projeto.cartas.Atacavel;
import com.unicamp.mc322.projeto.cartas.Campeao;
import com.unicamp.mc322.projeto.cartas.ModuloCondicao;
import com.unicamp.mc322.projeto.cartas.Evocavel;

public class QuantidadeDanoIncrementado extends ModuloCondicao {

    public QuantidadeDanoIncrementado(int objetivo) {
        super(objetivo);
    }

    @Override
    public void contabilizarAtaque(Atacavel atacavel, int dano) {

    }

    @Override
    protected void contabilizarDanoSofrido(int danoSofrido) {
        quantidadeAtingida += danoSofrido;

        super.contabilizarDanoSofrido(quantidadeAtingida);
    }

    @Override
    public String toString() {
        return "Evolui ao sofrer " + objetivo + " dano";
    }

}