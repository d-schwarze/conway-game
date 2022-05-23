package de.conway.ui.setup;

import de.conway.Cell;
import de.conway.Position;
import de.conway.Vector2D;
import de.conway.pattern.Pattern;
import de.conway.ui.ConwayCanvas;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class SetupCanvas extends ConwayCanvas {
	
	
	private boolean drawingMode = false;
	
	private boolean strgPressed = false;
	
	private EventHandler<MouseEvent> newMousePressedEvent;
	
	private EventHandler<MouseEvent> newMouseDraggedEvent;
			
	
	@Override
	public void init() {
		super.init();
		
		newMousePressedEvent = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				

				
				if(strgPressed) {
					
					lastCursorX = event.getX();
					lastCursorY = event.getY();
					
				}
				
			}
			
		};
		
		newMouseDraggedEvent = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				

				if(strgPressed) {
					
					globalX += (event.getX() - lastCursorX);
					globalY += (event.getY() - lastCursorY);
					
					lastCursorX = event.getX();
					lastCursorY = event.getY();
					
					render();
					
				}
			}
		};
		
		this.setOnDragDropped(ev -> {
			
			boolean success = false;
			
			if(ev.getDragboard().hasContent(PatternFormat.FORMAT)) {
				
				Pattern pattern = (Pattern) ev.getDragboard().getContent(PatternFormat.FORMAT);
				Vector2D pos = this.getPosition(ev.getX(), ev.getY());
				pattern.updatePosition(pos, false);
				
				for(Cell cell : pattern.getState().getCells()) {
					
					this.addCell(cell.clone());
					
				}
				
				render();
				
				success = true;
				
			}
			
			ev.setDropCompleted(success);
			ev.consume();
			
		});
		
	}

	public boolean isDrawingModeActivated() {
		
		return drawingMode;
		
	}
	
	public boolean isControlPressed() {
		
		return strgPressed;
		
	}
	
	public void activateDrawingMode() {
		
		if(!drawingMode) {
			drawingMode = true;
			
			this.setOnMousePressed(newMousePressedEvent);
			this.setOnMouseDragged(newMouseDraggedEvent);
			
		}
		
	}
	
	public void deactivateDrawingMode() {
		
		if(drawingMode) {
			drawingMode = false;
			
			this.setOnMousePressed(super.mousePressedEvent);
			this.setOnMouseDragged(super.mouseDraggedEvent);
		}
		
	}
	
	public Cell getCell(double canvasX, double canvasY) {
		
		return new Cell(getPosition(canvasX, canvasY));
		
	}
	
	public Vector2D getPosition(double canvasX, double canvasY) {
		
		int x = (new Double(canvasX).intValue() + this.globalX * -1) / this.gridWidth - 1;
		int y = (new Double(canvasY).intValue() + this.globalY * -1) / this.gridWidth - 1;
		
		return new Vector2D(x, y);
		
	}
	
	public void setStrgPressed(boolean pressed) {
		
		strgPressed = pressed;
		
	}
}
