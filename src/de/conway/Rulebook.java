package de.conway;

import java.util.ArrayList;

public interface Rulebook {
	ArrayList<Cell> getNewCells(Generation gen);
	
	ArrayList<Cell> getLostCells(Generation gen);
	
	default void applyRules(Generation gen) {
		ArrayList<Cell> newCells = getNewCells(gen);
		ArrayList<Cell> lostCells = getLostCells(gen);
		
		for(Cell c : lostCells) {
			gen.killCell(c);
		}
		
		for(Cell c : newCells) {
			gen.addCell(c);
		}
	}
}
