package com.unicamp.mc322.projeto.cartas.tracos;

import com.unicamp.mc322.projeto.cartas.Evocavel;
import com.unicamp.mc322.projeto.cartas.Traco;
import com.unicamp.mc322.projeto.cartas.ativacoes.AtivacaoTraco;

public class AtaqueDuplo extends Traco {

    public AtaqueDuplo(AtivacaoTraco ativacao) {
        super(ativacao);
    }

    @Override
    protected void ativar(Evocavel evocavel) {
        evocavel.setAtaqueDuplo();
    }

}
