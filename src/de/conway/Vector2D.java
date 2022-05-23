package de.conway;

import java.io.Serializable;

public class Vector2D implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int x;
	private int y;
	
	public Vector2D() { }
	
	public Vector2D(int x, int y) {
		
		this.x = x;
		this.y = y;
		
	}
	
	/**
	 * 
	 * @param pos1 use for the old position of the state
	 * @param pos2 use for the new position of the state
	 */
	public Vector2D(Position pos1, Position pos2) {
		
		int x = pos2.getX() - pos1.getX();
		int y = pos2.getY() - pos1.getY();
		
		this.x = x;
		this.y = y;
		
	}
	
	public double length() {
		
		double length = x * x + y * y;
		
		length = Math.sqrt(length);
		
		return length;
		
	}
	
	public double length(Vector2D vec) {
		
		double length;
		
		if(vec == null)
			length = x * x + y * y;
		else
			length = (x - vec.getX()) * (x - vec.getX()) + (y - vec.getY()) * (y - vec.getY());
		
		length = Math.sqrt(length);
		
		return length;
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void add(Vector2D vector) {
		
		this.x += vector.x;
		this.y += vector.y;
		
	}
	
	public Vector2D getInverseVector() {
		
		Vector2D vec = new Vector2D(this.x, this.y);
		
		vec.setX(vec.getX() * -1);
		vec.setY(vec.getY() * -1);
		
		return vec;
		
	}
}
