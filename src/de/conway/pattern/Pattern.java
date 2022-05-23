package de.conway.pattern;

import java.io.Serializable;

import de.conway.Cell;
import de.conway.Position;
import de.conway.Vector2D;

public class Pattern implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String name;
	
	private State state;
	
	private Vector2D position;

	public Pattern() { }
	
	public Pattern(String name, Vector2D pos) {
		this.name = name;
		this.position = pos;
	}
	
	public Pattern(String name, Vector2D pos, State state) {
		this(name, pos);
		this.state = state;
		this.state.setPattern(this);
	}

	public Vector2D getPosition() {
		return position;
	}

	public void setPosition(Vector2D position) {
		
		this.position = position;
		
	}
	
	public void updatePosition(Vector2D position, boolean inverse) {
		
		this.position = position;
		
		for(Cell c : state.getCells()) {
			
			if(inverse)
				c.addVector(position.getInverseVector());
			else
				c.addVector(position);
			
		}
		
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
