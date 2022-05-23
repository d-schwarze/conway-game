package de.conway.ui;

import java.util.ArrayList;
import java.util.List;

import de.conway.Cell;
import de.conway.Cells;
import de.conway.Position;
import de.conway.Vector2D;
import de.conway.ui.setup.PatternFormat;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ConwayCanvas extends Canvas {

	protected GraphicsContext gc;
	
	protected int globalX = 0;
	protected int globalY = 0;
	
	protected double lastCursorX = 0;
	protected double lastCursorY = 0;
	
	protected int gridWidth = 15;
	
	protected double oldDeltaX = 0;
	protected double oldDeltaY = 0;
	
	protected List<Cell> renderedCells;
	
	protected Color gridColor;
	
	protected Color cellColor;
	
	
	protected EventHandler<? super MouseEvent> mousePressedEvent;
	
	protected EventHandler<? super MouseEvent> mouseDraggedEvent;
	
	public ConwayCanvas() {
		super(0, 0);
		
		renderedCells = new ArrayList<>();
		
		init();
		
		
	}

	public void init() {
		
		gc = this.getGraphicsContext2D();
		
		gridColor = Color.DARKGRAY;
		
		cellColor = Color.DEEPSKYBLUE;
		
		mousePressedEvent = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				lastCursorX = event.getX();
				lastCursorY = event.getY();
				
			}
			
		};
		
		mouseDraggedEvent = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				globalX += (event.getX() - lastCursorX);
				globalY += (event.getY() - lastCursorY);
				
				lastCursorX = event.getX();
				lastCursorY = event.getY();
				
				render();
				
			}
		};
		
		this.setOnMousePressed(mousePressedEvent);
		
		this.setOnMouseDragged(mouseDraggedEvent);
		
		this.setOnDragOver(ev -> {
			
			if(ev.getGestureSource() != this && ev.getDragboard().hasContent(PatternFormat.FORMAT)) {
				
				ev.acceptTransferModes(TransferMode.MOVE);
				
			}
			
			ev.consume();
			
		});
		
	}
	
	
	public void render() {
		
		gc.clearRect(0, 0, this.getWidth(), this.getHeight());
		drawGrid();
		drawCells();
		
	}
	
	private void drawGrid() {
		
		gc.setStroke(gridColor);

		for(int i = (globalX % gridWidth); i < this.getWidth(); i+= gridWidth) {
			
			gc.strokeLine(i, 0, i, getHeight());
			
		}
		
		
		
		for(int i = (globalY % gridWidth); i < this.getHeight(); i+= gridWidth) {
			
			gc.strokeLine(0, i, this.getWidth(), i);
			
		}	
	}
	
	private void drawCells() {
		
		gc.setFill(cellColor);
		
		for(Cell c : renderedCells) {
			
			int x = c.getX() * gridWidth + gridWidth + globalX;
			int y = c.getY() * gridWidth + gridWidth + globalY;
			
			gc.fillOval(x, y, gridWidth, gridWidth);
			
		}
		
	}
	
	public boolean isCellOnScreen(Cell cell) {
		
		int x = cell.getX() * gridWidth;
		int y = cell.getY() * gridWidth;
			
		if(x < globalX + this.getWidth() && 
		   x >= globalX && 
		   y >= globalY && 
		   y < globalX + this.getHeight()) {
			
			return true;
			
		}
		
		return false;
		
	}
	
	public List<Cell> getRenderedCells() {
		
		return renderedCells;
		
	}
	
	public void setRenderedCells(List<Cell> cells) {
		
		this.renderedCells = cells;
		
	}
	
	public void addCell(Cell cell) {
		
		if(!this.renderedCells.contains(cell))
			this.renderedCells.add(cell);
		
	}
	
	public void removeCell(Cell cell) {
		
		this.renderedCells.remove(cell);
		
	}
	
	public Pane getBindedPane() {
		
		Pane pane = new Pane();
		pane.getChildren().add(this);
		
		pane.widthProperty().addListener(ev -> {
			
			this.resize(pane.getWidth(), pane.getHeight());
			
		});
		
		pane.heightProperty().addListener(ev -> {
			
			this.resize(pane.getWidth(), pane.getHeight());
			
		});	
		
		return pane;
		
	}
	
	public void zoomIn() {
		
		this.gridWidth++;
		
		render();
		
	}
	
	public void zoomOut() {
		
		if(this.gridWidth > 2) {
			
			this.gridWidth--;
		
			render();
			
		}
	}
	
	public void changeGridWidth(int width) {
		
		this.gridWidth = width;
		
		render();
	}
	
	public int getGridWidth() {
		
		return this.gridWidth;
		
	}
	
	public void zoomToCells() {
		
		if(getRenderedCells().size() >= 2) {
			
			Vector2D pos = Cells.getCenter(getRenderedCells());
			
			this.globalX = pos.getX();
			this.globalY = pos.getY();
			
			render();
		
		}
	}
	
	@Override
	public boolean isResizable() {
		return true;
		
	}
	
	@Override
	public void resize(double width, double height) {
		
		super.setWidth(width);
		super.setHeight(height);
		
		render();
		
	}
}


