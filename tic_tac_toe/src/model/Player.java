package model;

import enumerations.PlayerType;

public abstract class Player {
	
	private String playerId;
	private String playerName;
	private Symbol playerSymbol;
	private PlayerType playerType;
	
	public Player(String id , String name , char symbol, PlayerType type) {
		playerId = id;
		playerName = name;
		playerSymbol = new Symbol(symbol);
		playerType = type;
	}
	
	

	public PlayerType getPlayerType() {
		return playerType;
	}



	public void setPlayerType(PlayerType playerType) {
		this.playerType = playerType;
	}



	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Symbol getPlayerSymbol() {
		return playerSymbol;
	}

	public void setPlayerSymbol(Symbol playerSymbol) {
		this.playerSymbol = playerSymbol;
	}


}
