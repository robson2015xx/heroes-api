package br.com.gubee.interview.application.business;

import java.text.MessageFormat;

public class ResponseMessageConstants {

	public static final String HERO_HAS_BEEN_DELETED = "Herói apagado com sucesso, essa operação não afeta o total de vitórias de outros heróis!";
	
	public static String battleLoserMessage(String heroLoseName, String heroWinName, int totalLose) {
		return MessageFormat.format("O herói {0} precisa treinar mais um pouco para atualizar sua força assim talvez ganhe do {1}, "
				+ "você até o momento ganhou {2, number, integer}, então continue sua esforçando!" , heroLoseName, heroWinName, totalLose);
	}
	
	public static String battleWinnerMessage(String heroLoseName, String heroWinName, int totalWin) {
		return MessageFormat.format("Parabéns pela vitória contra o {0}, caro herói {1} lembre de continuar sempre treinando, "
				+ "atualmente seu total de vitórias é {2}!" , heroLoseName, heroWinName, totalWin);
	}
}
