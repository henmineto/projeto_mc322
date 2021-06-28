package com.unicamp.mc322.projeto.cartas.tracos;

import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.cartas.Traco;
import com.unicamp.mc322.projeto.cartas.ativacoes.AtivacaoTraco;

public class AtaqueDuplo extends Traco {

    public AtaqueDuplo(AtivacaoTraco ativacao) {
        super(ativacao);
    }

    @Override
    protected void ativar(Seguidor seguidor) {
        seguidor.setAtaqueDuplo();
    }

}
