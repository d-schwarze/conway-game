package de.conway.ui.setup;

import de.conway.Cell;
import de.conway.Cells;
import de.conway.Position;
import de.conway.Vector2D;
import de.conway.pattern.Pattern;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class PatternNode extends Canvas {
	
	private GraphicsContext gc;
	
	private static int canvasWidth = 100;
	
	private static int canvasHeight = 100;
	
	public PatternNode(Pattern pattern) {
		super(canvasWidth, canvasHeight);
		
		gc = this.getGraphicsContext2D();
		
		draw(pattern);
		
	}
	
	public PatternNode(Pattern pattern, int canvasW, int canvasH) {
		super(canvasW, canvasH);
		
		canvasHeight = canvasH;
		canvasWidth = canvasW;
		
		gc = this.getGraphicsContext2D();
		
		draw(pattern);
		
	}
	
	public void draw(Pattern pattern) {
		
		int cellWidth = canvasWidth / (pattern.getState().getWidth() + 1);
		int cellHeight = canvasHeight / (pattern.getState().getHeight() + 1);
		
		if(cellWidth < cellHeight)
			cellHeight = cellWidth;
		else if(cellHeight < cellWidth)
			cellWidth = cellHeight;
		
		Vector2D pos = Cells.getEdges(pattern.getState().getCells())[0];
		pattern.updatePosition(pos, true);
		
		gc.clearRect(0, 0, canvasWidth, canvasHeight);
		
		for(Cell cell : pattern.getState().getCells()) {
			
			gc.fillOval(cell.getX() * cellWidth, 
						cell.getY() * cellHeight, 
						cellWidth, 
						cellHeight);
			
		}
	}
}
