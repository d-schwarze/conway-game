package de.conway.pattern;

import java.io.Serializable;

import de.conway.Position;
import de.conway.Vector2D;

public class StaticPattern extends Pattern {
	private static final long serialVersionUID = 1L;

	public StaticPattern() { }
	
	public StaticPattern(String name, Vector2D pos, State state) {
		
		super(name, pos, state);
		
	}

}
