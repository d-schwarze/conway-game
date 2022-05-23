package de.conway;

import java.util.ArrayList;
import java.util.List;

import de.conway.pattern.Pattern;

public class Startgeneration extends Generation {
	
	public Startgeneration(Pattern... patterns) {
		super(new ArrayList<>(), null);
		for(Pattern p : patterns) {
			for(Cell c : p.getState().getCells()) {
				this.addCell(c);
			}
		}
		
	}
	
	public Startgeneration(Cell...cells) {
		super(new ArrayList<>(), null);
		for(Cell c : cells) {
			this.addCell(c);
		}
	}
	
	public Startgeneration(List<Cell> cells) {
		super(cells, null);
	}


	public void addPattern(Pattern p) {
		
		for(Cell c : p.getState().getCells()) {
			this.addCell(c);
		}
		
	}
	
	public void addCell(Cell cell) {
		this.getCells().add(cell);
	}

}
