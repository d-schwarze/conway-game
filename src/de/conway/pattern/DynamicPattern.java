package de.conway.pattern;

import java.util.ArrayList;

import de.conway.Position;
import de.conway.Vector2D;

public class DynamicPattern extends Pattern {
	private static final long serialVersionUID = 1L;
	
	protected ArrayList<State> states;
	
	public DynamicPattern(String name, Vector2D pos, ArrayList<State> states) {
		super(name, pos);
		
		this.states = states;
	}

	public ArrayList<State> getStates() {
		return states;
	}

	public void setStates(ArrayList<State> states) {
		this.states = states;
	}
	
}
