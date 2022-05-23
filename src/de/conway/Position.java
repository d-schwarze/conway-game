package de.conway;

import java.io.Serializable;

public class Position implements Serializable {
	private static final long serialVersionUID = 1L;

	private int x;
	
	private int y;
	
	public Position() { }
	
	public Position(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	
	public void setStandard() {
		
		this.x = 0;
		this.y = 0;
		
	}
	
	public double getVectorLength() {
		
		double length = x * x + y * y;
		
		length = Math.sqrt(length);
		
		return length;
		
	}

	public void increaseX(int i) {
		x += i;
	}
	
	public void increaseY(int i) {
		y += i;
	}
	
	public void addVector(Vector2D vector) {
		
		this.increase(vector.getX(), vector.getY());
		
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
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if(!(o instanceof Position))
			return false;
		
		Position p = (Position) o;
		
		if(p.x == x && p.y == y)
			return true;
		
		return false;
	}
	
	@Override
	public String toString() {
		return "X: " + x + " Y: " + y;
	}

	public void increase(int x, int y) {
		this.increaseX(x);
		this.increaseY(y);
	}
	
}
