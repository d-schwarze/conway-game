package de.conway.pattern;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.conway.Cell;
import de.conway.Vector2D;

public class State implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Cell> cells;
	
	private Pattern pattern;
	
	public State() {
		this.cells = new ArrayList<>();
	}
	
	public State(Cell...cells) {
		this.cells = new ArrayList<>();
		for(Cell cell : cells) {
			this.cells.add(cell);
		}
	}
	
	public State(ArrayList<Cell> cells) {
		this.setCells(cells);
	}
	
	public State(ArrayList<Cell> cells, Pattern pattern) {
		this(cells);
		this.setPattern(pattern);
	}
	
	public int getWidth() {
		
		int smallestX = 0;
		int biggestX = 0;
		
		for(Cell cell : cells) {
			
			if(cell.getX() < smallestX)
				smallestX = cell.getX();
			
			if(cell.getX() > biggestX)
				biggestX = cell.getX();
			
		}
		
		return biggestX - smallestX;
		
	}
	
	public int getHeight() {
		
		int smallestY = 0;
		int biggestY = 0;
		
		for(Cell cell : cells) {
			
			if(cell.getY() < smallestY)
				smallestY = cell.getY();
			
			if(cell.getY() > biggestY)
				biggestY = cell.getY();
			
		}
		
		return biggestY - smallestY;
		
	}

	public List<Cell> getCells() {
		return cells;
	}

	public void setCells(List<Cell> cells) {
		this.cells = cells;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}
	
}
