package com.unicamp.mc322.projeto.cartas.tracos;

import com.unicamp.mc322.projeto.cartas.Evocavel;
import com.unicamp.mc322.projeto.cartas.Traco;
import com.unicamp.mc322.projeto.cartas.ativacoes.AtivacaoTraco;

public class Furia extends Traco {
    private int bonusVida;
    private int bonusAtaque;

    public Furia(AtivacaoTraco ativacao, int bonusVida, int bonusAtaque) {
        super(ativacao);

        this.bonusVida = bonusVida;
        this.bonusAtaque = bonusAtaque;
    }

    public int getBonusVida() {
        return bonusVida;
    }

    public int getBonusAtaque() {
        return bonusAtaque;
    }

    @Override
    protected void ativar(Evocavel evocavel) {
        evocavel.setFuria(bonusVida, bonusAtaque);
    }

    @Override
    public String toString() {
        return "Fúria (+" + bonusVida + "/+" + bonusAtaque + ")";
    }
}
