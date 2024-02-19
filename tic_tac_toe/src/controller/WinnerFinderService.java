package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import interfaces.WinningStrategy;
import model.Cell;
import model.Move;
import model.Player;
import services.ColumnWinningStrategy;
import services.RowWinningStrategy;

public class WinnerFinderService {
	
	Map<String,Map<Player,Integer>> freqMap = new HashMap<>();

	public Player findWinner(Move move, List<List<Cell>> board, List<WinningStrategy> list) {
		
		//System.out.println("Checking Winner... ");
		
		for(WinningStrategy strategy : list){
			String freqMapKey = null;
			if(strategy instanceof RowWinningStrategy){
			//	System.out.println("Checking row Strategy...");
				freqMapKey = Integer.toString(move.getCell().getRow()) + "-row";
				}
			else if(strategy instanceof ColumnWinningStrategy){
			//	System.out.println("Checking Col Strategy...");
			    freqMapKey = Integer.toString(move.getCell().getRow()) + "-col";
			}
			
			Map<Player,Integer> symbolFreqMap = freqMap.get(freqMapKey);
			if(symbolFreqMap == null){
				symbolFreqMap = new HashMap<>();
				symbolFreqMap.put(move.getPlayer(),1);
				freqMap.put(freqMapKey, symbolFreqMap);
			}
			else {	
				Integer currentFreq = symbolFreqMap.get(move.getPlayer());
				if(currentFreq == null){
					symbolFreqMap.put(move.getPlayer(), 1);
				}
				else if(currentFreq.intValue() + 1 >= board.get(0).size()){
					return move.getPlayer();
				}
				else {
				symbolFreqMap.put(move.getPlayer(), currentFreq.intValue() + 1);
				freqMap.put(freqMapKey, symbolFreqMap);
				}
			}
		}
		return null;
	}
	
	

}
