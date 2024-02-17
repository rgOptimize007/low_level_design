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



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((playerId == null) ? 0 : playerId.hashCode());
		result = prime * result + ((playerName == null) ? 0 : playerName.hashCode());
		result = prime * result + ((playerSymbol == null) ? 0 : playerSymbol.hashCode());
		result = prime * result + ((playerType == null) ? 0 : playerType.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (playerId == null) {
			if (other.playerId != null)
				return false;
		} else if (!playerId.equals(other.playerId))
			return false;
		if (playerName == null) {
			if (other.playerName != null)
				return false;
		} else if (!playerName.equals(other.playerName))
			return false;
		if (playerSymbol == null) {
			if (other.playerSymbol != null)
				return false;
		} else if (!playerSymbol.equals(other.playerSymbol))
			return false;
		if (playerType != other.playerType)
			return false;
		return true;
	}
	
	

}
