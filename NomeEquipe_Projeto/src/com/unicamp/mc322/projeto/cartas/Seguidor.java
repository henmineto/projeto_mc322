package com.unicamp.mc322.projeto.cartas;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.cartas.ativacoes.AtivacaoEfeito;
import com.unicamp.mc322.projeto.cartas.ativacoes.AtivacaoTraco;
import com.unicamp.mc322.projeto.jogadores.Jogador;
import com.unicamp.mc322.projeto.modulos.ModuloAtaque;
import com.unicamp.mc322.projeto.modulos.ModuloVida;

public class Seguidor extends Carta implements Evocavel {

	protected ModuloVida vida;
	protected ModuloAtaque ataque;
	private ArrayList<Traco> tracos;
	private int ataquesPorTurno;
	private boolean elusivo;
	private boolean furia;
	private int furiaBonusVida;
	private int furiaBonusAtaque;
	private boolean matouNesseTurno;
	
	public Seguidor(String nome, int vida, int ataque, int custo) {
		super(nome, custo);
		this.vida = new ModuloVida(vida);
		this.ataque = new ModuloAtaque(ataque);
		this.tracos = new ArrayList<Traco>();

		ataquesPorTurno = 1;

		matouNesseTurno = false;
	}
	
	public void addTraco(Traco traco) {
		if (traco.getAtivacao() == AtivacaoTraco.ADICAO_DO_TRACO) {
			traco.ativar(this);
		}
		else {
			tracos.add(traco);
		}
	}

	@Override
	public void ativar(Jogador jogador, AtivacaoEfeito ativacao) throws Exception {
		super.ativar(jogador, ativacao);

		if (ativacao == AtivacaoEfeito.FINAL_DO_TURNO) {
			matouNesseTurno = false;
			vida.desativarEscudo();
			ataque.liberarAtaque();
		}
	}
	
	public void setElusivo() {
		this.elusivo = true;
	}
	
	public boolean isElusivo() {
		return elusivo;
	}

	public void setAtaqueDuplo() { ataquesPorTurno = 2; }

	public int getAtaquesPorTurno() { return ataquesPorTurno; }

	public void setFuria(int bonusVida, int bonusAtaque) {
		this.furia = true;

		furiaBonusVida = bonusVida;
		furiaBonusAtaque = bonusAtaque;
	}

	public boolean isFuria() {
		return furia;
	}

	public void receberBonusFuria() {
		if (furia) {
			aumentarDano(furiaBonusAtaque);
			aumentarVida(furiaBonusVida);
		}
	}
	
	public void atacar(Atacavel atacavel) {
		atacavel.receberDano(ataque.getAtaque());

		if (!atacavel.estaVivo()) {
			matouNesseTurno = true;
		}

		receberDano(atacavel.getAtaque());
	}
	
	public boolean estaVivo() {
		return vida.getVida() > 0;
	}
	
	public int getVida() {
		return vida.getVida();
	}
	
	public void receberDano(int dano) {
		vida.receberDano(dano);
	}
	
	public int getAtaque() {
		return ataque.getAtaque();
	}
	
	public void aumentarDano(int bonus) {
		ataque.aumentarAtaque(bonus);
	}
	
	public void aumentarVida(int bonus) {
		vida.recuperarVida(bonus);
	}

	public void curaCompleta() {
		vida.curaCompleta();
	}

	public void garantirEscudo() {
		vida.ativarEscudo();
	}

	public void zerarAtaque() {
		ataque.zerarAtaque();
	}

	public boolean verificarMatouNesseTurno() {
		return matouNesseTurno;
	}

	public String getInfoTracos() {
		String txt;

		if (tracos.size() > 0) {
			txt = "Traços: (";
			boolean virgula = false;

			for (int i = 0; i < tracos.size(); i++) {
				if (virgula) {
					txt += ", ";
				}
				virgula = true;

				txt += tracos.get(i);
			}

			txt += ")";
		} else {
			txt = "Sem traços";
		}

		return txt;
	}

	protected String getInfoParcial() {
		return toString() + " - V: " + vida.getVida() + "/" + vida.getVidaMaxima() + " A: " + ataque.getAtaque() + " - " + getInfoEfeitos() + " - " + getInfoTracos();
	}

	@Override
	public String getInfo() {
		return "Unidade - " + getInfoParcial();
	}
}
