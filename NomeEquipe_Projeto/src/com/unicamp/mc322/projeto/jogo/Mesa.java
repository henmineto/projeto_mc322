package com.unicamp.mc322.projeto.jogo;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.cartas.Compravel;
import com.unicamp.mc322.projeto.cartas.Evocavel;
import com.unicamp.mc322.projeto.cartas.ativacoes.AtivacaoEfeito;
import com.unicamp.mc322.projeto.jogadores.Jogador;

public class Mesa {
	private final int QTD_UNIDADES_EVOCADAS = 6;
	private final int QTD_CARTAS_INICIAIS = 4;

	private Jogador jogador1;
	private Jogador jogador2;

	private String nomeAtacante;
	private String nomeDefensor;
	private Jogador atacante;
	private Jogador defensor;
	private int quantidadeRodadas;
	
	private ArrayList<Evocavel> unidadesEvocadasJogador1;
	private ArrayList<Evocavel> unidadesEvocadasJogador2;
	
	private Evocavel[] unidadesEmCampoAtacante;
	private Evocavel[] unidadesEmCampoDefensor;
	private int quantidadeCartasAtaque;
	
	public Mesa(Jogador jogador1, Jogador jogador2) {
		this.jogador1 = jogador1;
		this.jogador2 = jogador2;
		this.unidadesEvocadasJogador1 = new ArrayList<>(QTD_UNIDADES_EVOCADAS);
		this.unidadesEvocadasJogador2 = new ArrayList<>(QTD_UNIDADES_EVOCADAS);
		this.unidadesEmCampoAtacante = new Evocavel[QTD_UNIDADES_EVOCADAS];
		this.unidadesEmCampoDefensor = new Evocavel[QTD_UNIDADES_EVOCADAS];
		MediadorEfeitos.getInstance().setMesa(this);
		
		for (int i = 0; i < 4; i++) {
			this.jogador1.pegarCarta();
			this.jogador2.pegarCarta();
		}
	}
	
	public Jogador getAtacante() {
		return this.atacante;
	}
	
	public Jogador getDefensor() {
		return this.defensor;
	}
	
	public void iniciarRodada() {
		quantidadeRodadas++;
		
		if (atacante == null || atacante == jogador2) {
			nomeAtacante = "J1";
			atacante = jogador1;
			nomeDefensor = "J2";
			defensor = jogador2;
		}
		else {
			nomeAtacante = "J2";
			atacante = jogador2;
			nomeDefensor = "J1";
			defensor = jogador1;
		}
		
		if (quantidadeRodadas == 1) {
			for (int i = 0; i < QTD_CARTAS_INICIAIS - 1; i++) { // jogadores pegam QTD_CARTAS_INICIAIS cartas na 1ª rodada
				jogador1.pegarCarta();
				jogador2.pegarCarta();
			}
		}
		
		jogador1.pegarCarta();
		jogador2.pegarCarta();
		
		jogador1.resetarMana(quantidadeRodadas);
		jogador2.resetarMana(quantidadeRodadas);
	}
	
	public void realizarTurnoAtacante() {
		// substitui cartas na 1ª rodada
		if (quantidadeRodadas == 1)
			realizarSubstituicaoCartas(atacante);
	}
	
	public void realizarTurnoDefensor() {
		// substitui cartas na 1ª rodada
		if (quantidadeRodadas == 1)
			realizarSubstituicaoCartas(defensor);
	}
	
	public void addUnidadeCampo(Jogador j) {
		ArrayList<Evocavel> unidadesEvocadas = getUnidadesEvocadas(j);
		try {
			int indexMesa = j.escolherUnidadeParaCampo(unidadesEvocadas.size());
			if (j.equals(atacante)) {
				colocarCartaEmCampo(j, indexMesa, quantidadeCartasAtaque);
				quantidadeCartasAtaque++;
				indexMesa = j.escolherUnidadeParaCampo(unidadesEvocadas.size());
			}
			else if (j.equals(defensor) && quantidadeCartasAtaque > 0) {
				int indexDefesa = j.escolherPosicaoDefesa(quantidadeCartasAtaque);
				if (indexDefesa >= quantidadeCartasAtaque)
					throw new Exception("N�o h� carta de ataque na posi��o escolhida para defesa. Posi��o: "+indexDefesa);
				colocarCartaEmCampo(defensor, indexMesa, indexDefesa);
			}
		}
		catch (Exception ex) {
			j.exibirMensagemErro(ex.getMessage());
		}
	}
	
	public void realizarCombate() {
		for (int i = 0; i < unidadesEmCampoAtacante.length; i++) {
			if (unidadesEmCampoAtacante[i] != null) {
				for (int j = 0; j < unidadesEmCampoAtacante[i].getAtaquesPorTurno(); j++) {
					if (realizarCombateUnidades(unidadesEmCampoAtacante[i], unidadesEmCampoDefensor[i])) {
						if (!unidadesEmCampoDefensor[i].estaVivo()) {
							unidadesEmCampoDefensor[i] = null;
						}
					}
					else {
						unidadesEmCampoAtacante[i].atacar(defensor);
					}

					if (!unidadesEmCampoAtacante[i].estaVivo()) {
						unidadesEmCampoAtacante[i] = null;
						break;
					}
				}
			}
		}
		
		removerCartasEmCampo(jogador1);
		removerCartasEmCampo(jogador2);
		quantidadeCartasAtaque = 0;
	}
	
	boolean realizarCombateUnidades(Evocavel atacante, Evocavel defensor) {
		if (validarDefesa(atacante, defensor)) {
			atacante.atacar(defensor);
			
			if (!defensor.estaVivo() && atacante.isFuria()) {
				atacante.receberBonusFuria();
			}
			
			return true;
		}
		return false;
	}
	
	void realizarEvocacaoCartas(Jogador jogador) {
		try {
			int indexMao = jogador.escolherCartaNaMao(true);
				
			if (indexMao >= 0)
				evocarCarta(jogador, indexMao);
		}
		catch (Exception ex) {
			jogador.exibirMensagemErro(ex.getMessage());
		}
	}

	String getInfoCarta(Jogador jogador) {
		try {
			int indexMao = jogador.escolherCartaNaMao(true);

			return jogador.getInfoCartaMao(indexMao);
		} catch (Exception ex) {
			jogador.exibirMensagemErro(ex.getMessage());
			return "";
		}
	}
	
	private void realizarSubstituicaoCartas(Jogador jogador) {
		boolean descartarCarta = true;
		
		int numDescartes = jogador.escolherDescartes(descartarCarta);
		int numDescartesEfetivos = 0;
		
		for (int i = 0; i < numDescartes; i++) {
			if (jogador.descartaCarta())
				numDescartesEfetivos++;
				Jogo.getInstance().imprimeMesa();
		}
		for (int j = 0; j < numDescartesEfetivos; j++) {
			jogador.pegarCarta();
		}
	}
	
	private void evocarCarta(Jogador jogador, int indexMao) throws Exception {
		ArrayList<Evocavel> unidadesEvocadas = getUnidadesEvocadas(jogador);
		
		Compravel carta = jogador.evocarCarta(indexMao, podeEvocarUnidade(unidadesEvocadas));
		
		carta.ativar(jogador, AtivacaoEfeito.EVOCACAO_DA_CARTA);
		
		if(carta instanceof Evocavel) {
			unidadesEvocadas.add((Evocavel)carta);
		}
	}
	
	private void substituirCarta(Jogador jogador, int indexMao, int indexMesa) throws Exception {
		ArrayList<Evocavel> unidadesEvocadas = getUnidadesEvocadas(jogador);
		
		if (indexMesa < 0 || indexMesa >= unidadesEvocadas.size())
			throw new Exception("Posi��o de carta na mesa inv�lida. Posi��o: "+indexMesa+". Quantidade de cartas: "+unidadesEvocadas.size());
		
		Evocavel substituida = unidadesEvocadas.get(indexMesa);
		Compravel substituta = jogador.substituirCarta(indexMao, substituida);
	
		substituta.ativar(jogador, AtivacaoEfeito.EVOCACAO_DA_CARTA);
		unidadesEvocadas.remove(indexMesa);
		
		if(substituta instanceof Evocavel) {
			unidadesEvocadas.add((Evocavel)substituta);
		}
	}
	
	private void colocarCartaEmCampo(Jogador jogador, int indexEvocadas, int indexCampo) throws Exception {
		ArrayList<Evocavel> unidadesEvocadas = getUnidadesEvocadas(jogador);
		Evocavel[] unidadesEmCampo = getUnidadesEmCampo(jogador);
		
		if (indexEvocadas < 0 || indexEvocadas >= unidadesEvocadas.size())
			throw new Exception("Posi��o de carta evocada inv�lida. Posi��o: "+indexEvocadas+". Quantidade de cartas: "+unidadesEvocadas.size());
		
		if (indexCampo < 0 || indexCampo >= unidadesEmCampo.length)
			throw new Exception("Posi��o do campo inv�lida. Posi��o: "+indexCampo+". Quantidade de posi��es: "+unidadesEmCampo.length);
		
		if (unidadesEmCampo[indexCampo] != null) {
			unidadesEvocadas.add(unidadesEmCampo[indexCampo]);
		}
	
		unidadesEmCampo[indexCampo] = unidadesEvocadas.remove(indexEvocadas);

		if (jogador.deveFazerImpressaoContinua()) {
			Jogo.getInstance().imprimeMesa();
		}
	}
	
	void finalizarTurno(Jogador jogador) {
		Jogador outroJogador = getOutroJogador(jogador);
		ArrayList<Evocavel> unidadesEvocadasOutroJogador = getUnidadesEvocadas(outroJogador);
		for (Evocavel unidade : unidadesEvocadasOutroJogador) {
			try {
				unidade.ativar(outroJogador, AtivacaoEfeito.FINAL_DO_TURNO);
			}
			catch (Exception ex) {
				outroJogador.exibirMensagemErro(ex.getMessage());
			}
		}
	}

	private void removerCartasEmCampo(Jogador jogador) {
		ArrayList<Evocavel> unidadesEvocadas = getUnidadesEvocadas(jogador);
		Evocavel[] unidadesEmCampo = getUnidadesEmCampo(jogador);
		
		for (int i = 0; i < unidadesEmCampo.length; i++) {
			if (unidadesEmCampo[i] != null) {
				if (unidadesEmCampo[i].estaVivo()) {
					unidadesEvocadas.add(unidadesEmCampo[i]);
				}
				unidadesEmCampo[i] = null;
			}
		}
	}
	
	private boolean validarDefesa(Evocavel atacante, Evocavel defensor) {
		if (defensor == null) //nao existe defensor nessa posicao
			return false;
		if (!atacante.isElusivo()) //existe defensor e atacante � um seguidor n�o elusivo
			return true;
		
		return defensor.isElusivo(); //atacante � seguidor elusivo, s� defende se defensor for elusivo tamb�m.
	}
	
	private boolean podeEvocarUnidade(ArrayList<Evocavel> unidadesEvocadas) {
		return unidadesEvocadas.size() < QTD_UNIDADES_EVOCADAS;
	}
	
	ArrayList<Evocavel> getUnidadesEvocadas(Jogador jogador) {
		return jogador == jogador1 ? unidadesEvocadasJogador1 : unidadesEvocadasJogador2;
	}
	
	Jogador getOutroJogador(Jogador jogador) {
		return jogador == jogador1 ? jogador2 : jogador1;
	}
	
	private Evocavel[] getUnidadesEmCampo(Jogador jogador) {
		return jogador == atacante ? unidadesEmCampoAtacante : unidadesEmCampoDefensor;
	}
	
	public String toString() {
		String txt = "Jogador 2:\n";
		txt += this.jogador2;
		txt += "\n" + formataUnidadesEvocadas(jogador2);
		txt += formataUnidadesEmCampo();
		txt += "\nJogador 1:\n";
		txt += formataUnidadesEvocadas(jogador1) + "\n";
		txt += this.jogador1;
		return txt;
	}

	private String formataUnidadesEvocadas(Jogador jogador) {
		ArrayList<Evocavel> evocadas = getUnidadesEvocadas(jogador);

		String txt = "";

		if (evocadas.size() > 0) {
			txt += "Unidades Evocadas: ";

			for (int i = 0; i < evocadas.size(); i++) {
				txt += "\n" + i + ": " + evocadas.get(i);
			}
		} else {
			txt += "Nenhuma unidade evocada";
		}

		return txt;
	}

	private String formataUnidadesEmCampo() {
		String txt = "\nAtacante (" + nomeAtacante + "):";
		for (int i = 0; i < this.unidadesEmCampoAtacante.length; i++) {
			txt += "\n" + i + ": ";
			if (this.unidadesEmCampoAtacante[i] != null) {
				txt += this.unidadesEmCampoAtacante[i];
			}
		}
		txt += "\nDefensor (" + nomeDefensor + "):";
		for (int i = 0; i < this.unidadesEmCampoDefensor.length; i++) {
			txt += "\n" + i + ": ";
			if (this.unidadesEmCampoDefensor[i] != null) {
				txt += this.unidadesEmCampoDefensor[i];
			}
		}
		return txt;
	}
}