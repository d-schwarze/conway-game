package de.conway;

import java.util.ArrayList;
import java.util.List;

public class Cells {
	
	private Cells() { }
	
	public static Vector2D getCenter(List<Cell> cells) {
		
		Vector2D[] edges = getEdges(cells);
		
		int x = (edges[0].getX() + edges[1].getX()) / 2;
		int y = (edges[0].getY() + edges[1].getY()) / 2;
		
		return new Vector2D(x, y);
		
	}
	
	public static Vector2D[] getEdges(List<Cell> cells) {
		int smallestX = -1;
		int smallestY = -1;
		int biggestX = -1;
		int biggestY = -1;
		
		for(Cell c : cells) {
			if(smallestX == -1 || c.getX() < smallestX) {
				smallestX = c.getX();
			}
			
			if(smallestY == -1 || c.getY() < smallestY) {
				smallestY = c.getY();
			}
			
			if(biggestX == -1 || c.getX() > biggestX) {
				biggestX = c.getX();
			}
			
			if(biggestY == -1 || c.getY() > biggestY) {
				biggestY = c.getY();
			}
		}
		
		 Vector2D[] edges = { new Vector2D(smallestX, smallestY), 
							 new Vector2D(biggestX, biggestY) };
		 
		 return edges;
	}
	
	public static boolean inSameArea(List<Cell> cells) {
		Vector2D[] edges = getEdges(cells);
		
		if((edges[1].getX() - edges[0].getX()) <= 2 && (edges[1].getY() - edges[0].getY()) <= 2)
			return true;
		
		return false;
	}
	
	public static Vector2D getCenterOfArea(List<Cell> cells) {
		if(inSameArea(cells)) {
			Vector2D[] edges = getEdges(cells);
			
			Vector2D pos = null;
			
			if(formSpecialSquare(edges)) {
				pos = getCenterOfSpecialSquare(cells, edges);
			} else if(formSquare(edges)) {
				pos = getCenterOfSquare(cells, edges);
			} else if(formRectangle(edges)) {
				pos = getCenterOfRectangle(cells, edges);
			}
			
			return pos;
		}
		
		throw new CellsException("Cells are not in the same area!");
	}
	
	public static boolean formRectangle(Vector2D[] edges) {
		if(!formSquare(edges))
			return true;
		return false;
	}
	
	public static Vector2D getCenterOfRectangle(List<Cell> cells, Vector2D[] edges) {
		if(formRectangle(edges)) {
			int x = (edges[1].getX() - edges[0].getX()) / 2;
			int y = (edges[1].getY() - edges[0].getY()) / 2;
			
			Vector2D pos = new Vector2D(x, y);
			
			boolean isAvailable = true;
			
			for(Cell cell : cells) {
				if(cell.getPosition().equals(pos)) {
					isAvailable = false;
					break;
				}
			}
			
			if(isAvailable) {
				return pos;
			} else {
				int mX = (edges[1].getX() - edges[0].getX()) % 2;
				int mY = (edges[1].getY() - edges[0].getY()) % 2;
				
				if(mX > 0) {
					x += mX;
				} else if(mY > 0) {
					y += mY;
				}
				
				pos.setX(x);
				pos.setY(y);
				
				isAvailable = true;
				
				for(Cell cell : cells) {
					if(cell.getPosition().equals(pos)) {
						isAvailable = false;
						break;
					}
				}
				
				if(isAvailable) 
					return pos;
				else
					return null;
			}
		}
		
		throw new CellsException("Cells do not form a rectangle!");
	}
	
	public static boolean formSpecialSquare(Vector2D[] edges) {
		if(edges[1].getX() - 1 == edges[0].getX() && edges[1].getY() - 1 == edges[0].getY())
			return true;
		
		return false;
	}
	
	public static Vector2D getCenterOfSpecialSquare(List<Cell> cells, Vector2D[] edges) {
		if(formSpecialSquare(edges)) {
			boolean isAvailable = true;
			
			//Coordinates of smallest edge.
			Vector2D pos = new Vector2D(edges[0].getX(), edges[0].getY());
			
			for(Cell c : cells) {
				if(c.getPosition().equals(pos)) {
					isAvailable = false;
					break;
				}
			}
			
			if(!isAvailable) {
				//Increase x:
				pos.add(new Vector2D(1, 0));
				
				for(Cell c : cells) {
					if(c.getPosition().equals(pos)) {
						isAvailable = false;
						break;
					}
				}
			}
			
			
			if(!isAvailable) {
				//Increase y:
				pos.add(new Vector2D(0, 1));
				isAvailable = true;
				
				for(Cell c : cells) {
					if(c.getPosition().equals(pos)) {
						isAvailable = false;
						break;
					}
				}
			}
			
			if(!isAvailable) {
				//Default position:
				pos.add(new Vector2D(-1, -1));
				
				//Increase y:
				pos.add(new Vector2D(0, 1));
				isAvailable = true;
				
				for(Cell c : cells) {
					if(c.getPosition().equals(pos)) {
						isAvailable = false;
						break;
					}
				}
			}
			
			
			if(isAvailable)
				return pos;
			else
				return null;
			
		}
		
		throw new CellsException("Cells do not form a square!");
		
	}
	
	public static boolean formSquare(Vector2D[] edges) {
		if(!formSpecialSquare(edges) && (edges[1].getX() - edges[0].getX() == edges[1].getY() - edges[0].getY()))
			return true;
		
		return false;
	}
	
	public static Vector2D getCenterOfSquare(List<Cell> cells, Vector2D[] edges) {
		if(formSquare(edges)) {
			int addX = (edges[1].getX() - edges[0].getX()) / 2;
			int addY = (edges[1].getY() - edges[0].getY()) / 2;
			
			Vector2D pos = new Vector2D(edges[0].getX(), edges[0].getY());
			pos.add(new Vector2D(addX, addY));
			
			boolean isAvailable = true;
			
			for(Cell cell : cells) {
				if(cell.getPosition().equals(pos)) {
					isAvailable = false;
					break;
				}
			}
			
			if(isAvailable)
				return pos;
			else
				return null;
		}
		
		throw new CellsException("Cells do not form a square!");
	}

	public static List<Cell> getAvailableCells(List<Cell> area) {
		Vector2D[] edges = Cells.getEdges(area);
		
		//Adding a border of cells, because it could be also the case,
		//that a new cell has a position outside of the area.
		edges[0].add(new Vector2D(-1, -1));
		edges[1].add(new Vector2D(1, 1));
		
		
		Vector2D pos = new Vector2D(edges[0].getX(), edges[0].getY());
		
		boolean stop = false;
		
		List<Cell> availableCells = new ArrayList<>();
		
		while(!stop) {
			if(!Cells.containsPosition(area, pos)) {
				availableCells.add(new Cell(pos));
			}
			
			if(pos.getX() < edges[1].getX())
				pos.add(new Vector2D(1, 0));
			else if(pos.getY() < edges[1].getY()) {
				pos.add(new Vector2D(0, 1));
				pos.setX(edges[0].getX());
			} else {
				stop = true;
			}
				
		}
		
		return availableCells;
	}
	
	public static boolean containsPosition(List<Cell> cells, Vector2D pos) {
		for(Cell cell : cells) {
			if(cell.getPosition().equals(pos))
				return true;
		}
		
		return false;
	}
	
	public static Cell getNearestToOrigin(List<Cell> cells) {
		
		Cell cell = null;
		
		for(Cell c : cells) {
			
			if(cell == null || c.getPosition().length() < cell.getPosition().length()) {
				
				cell = c;
				
			}
			
		}
		
		return cell;
		
	}
}
