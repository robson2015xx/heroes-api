package br.com.gubee.interview.application.business.outbound;

import br.com.gubee.interview.application.business.ResponseMessageConstants;

public class BattleResponse {

	private String winnerMessage;
	
	private String loserMessage;

	public BattleResponse(String heroLoseName, String heroWinName, int totalLose, int totalWin) {
		super();
		this.winnerMessage = ResponseMessageConstants.battleWinnerMessage(heroLoseName, heroWinName, totalWin);;
		this.loserMessage = ResponseMessageConstants.battleLoserMessage(heroLoseName, heroWinName, totalLose);
	}

	public String getWinnerMessage() {
		return winnerMessage;
	}

	public String getLoserMessage() {
		return loserMessage;
	}
}
