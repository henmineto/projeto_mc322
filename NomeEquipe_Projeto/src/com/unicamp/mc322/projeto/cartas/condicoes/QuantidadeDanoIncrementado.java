package com.unicamp.mc322.projeto.cartas.condicoes;

import com.unicamp.mc322.projeto.cartas.Atacavel;
import com.unicamp.mc322.projeto.cartas.ModuloCondicao;

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