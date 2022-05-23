package de.conway;

import java.io.Serializable;
import java.util.ArrayList;

public class Cell implements Serializable {
	private static final long serialVersionUID = 1L;

	private Vector2D position;
	
	private Generation motherGeneration;

	public Cell() { }
	
	public Cell(Vector2D vec) {
		this.position = vec;
	}
	
	public Cell(int x, int y) {
		this.position = new Vector2D(x, y);
	}

	public int getX() {
		return position.getX();
	}

	public void setX(int x) {
		this.position.setX(x);
	}

	public int getY() {
		return this.position.getY();
	}

	public void setY(int y) {
		this.position.setY(y);
	}

	public Generation getMotherGeneration() {
		return motherGeneration;
	}

	public void setMotherGeneration(Generation motherGeneration) {
		this.motherGeneration = motherGeneration;
	}
	
	public void addVector(Vector2D vector) {
		
		this.position.add(vector);
		
	}
	
	private boolean isCellInArea(Cell cell, int yMax, int yMin, int xMax, int xMin) {
		int xDiff = this.position.getX() - cell.position.getX();
		int yDiff = this.position.getY() - cell.position.getY();
		
		if((xDiff <= xMax && xDiff >= xMin) && (yDiff <= yMax && yDiff >= yMin))
			return true;
		
		return false;
	}

	public boolean isCellInSmallArea(Cell cell) {
		return isCellInArea(cell, 1, -1, 1, -1);
	}
	
	public boolean isCellInBigArea(Cell cell) {
		return isCellInArea(cell, 2, -2, 2, -2);
	}
	
	public ArrayList<Cell> getBigArea() {
		ArrayList<Cell> cells = new ArrayList<>();
		
		for(Cell cell : motherGeneration.getCells()) {
			if(!cell.equals(this) && isCellInBigArea(cell)) {
				cells.add(cell);
			}
		}
		
		return cells;
	}
	
	public ArrayList<Cell> getSmallArea() {
		if(this.motherGeneration == null)
			throw new GenerationException("Cell has no mothergeneration!");
		
		return getSmallArea(this.motherGeneration);
	}
	
	
	public ArrayList<Cell> getSmallArea(Generation gen) {
		ArrayList<Cell> cells = new ArrayList<>();
		
		for(Cell cell : gen.getCells()) {
			if(!cell.equals(this) && isCellInSmallArea(cell)) {
				cells.add(cell);
			}
		}
		
		return cells;
	}
	
	public boolean isSurrounded() {
		if(getSmallArea().size() == 8)
			return true;
		
		return false;
	}
	
	@Override
	public Cell clone() {
		
		Cell c = new Cell(position);
		c.setMotherGeneration(motherGeneration);
		
		return c;

	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this)
			return true;
		
		if(!(o instanceof Cell))
			return false;
		
		Cell c = (Cell) o;
		
		if(c.position.equals(this.position))
			return true;
		
		return false;
	}
	
	@Override
	public String toString() {
		return "Cell: " + position.toString();
	}

	public Vector2D getPosition() {
		return position;
	}

	public void setPosition(Vector2D position) {
		this.position = position;
	}


}
