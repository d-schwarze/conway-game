package de.conway.ui.simulation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.conway.Cell;
import de.conway.ConwayGame;
import de.conway.ConwayPrinter;
import de.conway.Mode;
import de.conway.pattern.io.CsvIO;
import de.conway.ui.Controller;
import de.conway.ui.MsgType;
import de.conway.ui.TransferObject;
import de.conway.ui.setup.SetupController;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class SimulationController extends Controller<SimulationView> implements ConwayPrinter {
	
	private ConwayGame conwayGame;

	private StringProperty currentGenProp;
	
	private StringProperty cellsProp;
	
	private StringProperty generationTimeProp;
	
	private double generationTime = 0.0;
	
	private int cellsOld = 0;
	
	private int maxGen;
	
	private double genPeriod;
	
	@Override
	public void init() {
		
		this.view = new SimulationView();
		
		currentGenProp = new SimpleStringProperty();
		
		cellsProp = new SimpleStringProperty();
		
		generationTimeProp = new SimpleStringProperty();
		
		this.view.getSimulationSidebar().cellsTile.getContent().textProperty().bind(cellsProp);
		
		this.view.getSimulationSidebar().genDurationTile.getContent().textProperty().bind(generationTimeProp);
		
		this.view.getSimulationSidebar().currentGenTile.getContent().textProperty().bind(currentGenProp);
		
		this.view.getSimulationSidebar().startStopButton.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				
				if(newValue == true) {
					
					if(conwayGame == null) {
						
						throw new NullPointerException("Error!");
						
					} else {
						
						if(conwayGame.hasStarted())
							conwayGame.resume();
						else
							conwayGame.start(maxGen, (int) genPeriod * 1000, Mode.RUN);
						
					}
					
				} else {
					
					if(conwayGame == null) {
						
						throw new NullPointerException("Error!");
						
					} else {
						
						conwayGame.stop();
						
					}
				}
			}
		});
		
		view.getSimulationSidebar().zoomInButton.setOnAction(ev -> {
			
			view.getSimulationCanvas().zoomIn();
			
		
		
		});
		
		
		view.getSimulationSidebar().zoomOutButton.setOnAction(ev -> {
			
			view.getSimulationCanvas().zoomOut();
			
		});
		
		view.getSimulationSidebar().focusButton.setOnAction(ev -> {
			
			view.getSimulationCanvas().zoomToCells();
			
		});
		
		view.getSimulationSidebar().editButton.setOnAction(ev -> {
			
			this.conwayGame.stop();
			
			superController.showView(SetupController.class);
			
		});
		
		view.getSimulationSidebar().finishButton.setOnAction(ev -> {
			
			if(conwayGame == null) {
				
				throw new NullPointerException("Error!");
				
			} else {
				
				conwayGame.stop();
				
				try {
					CsvIO.writeFile(conwayGame.getPreviousGenerations(), "test.csv");
					
					superController.showMessage(MsgType.INFO, "Wuhu", "nice");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		
		
	}

	@Override
	public void onReceivedData(TransferObject obj) {
			
		if(obj instanceof SimulationController.TransObj) {
			
			SimulationController.TransObj to = (SimulationController.TransObj) obj;
			
			if(to.game != null) {
				
				setConwayGame(to.game);
				maxGen = to.maxGenerations;
				genPeriod = to.generationPeriod;
				
			}
		}
	}
	
	private void updateTiles() {
		
		Platform.runLater(() -> {
			
			currentGenProp.set(Integer.toString(this.conwayGame.getPreviousGenerations().size()));
			cellsProp.set(Integer.toString(this.conwayGame.getCurrentGeneration().getCells().size()));
			generationTimeProp.set(Double.toString(generationTime));
			
			
			int cellsNeu = this.conwayGame.getCurrentGeneration().getCells().size();
			
			if(cellsNeu > cellsOld) {
				
				view.getSimulationSidebar().setPositiveGrowth();
				
			} else if(cellsNeu < cellsOld) {
				
				view.getSimulationSidebar().setNegativeGrowth();
				
			} else {
				
				view.getSimulationSidebar().setNeutralGrowth();
			
			}
			
			cellsOld = cellsNeu;
			
		});
		
	}
	
	private void setConwayGame(ConwayGame game) {
		
		this.conwayGame = game;
		this.cellsProp.set(Integer.toString(this.conwayGame.getGeneration().getCells().size()));
		this.cellsOld = this.conwayGame.getCurrentGeneration().getCells().size();
		this.view.getSimulationCanvas().setRenderedCells(conwayGame.getGeneration().getCells());
		this.view.getSimulationCanvas().render();
		this.conwayGame.setPrinter(this);
		
		
	}

	@Override
	public void onPrint(List<Cell> cells, double generationTime) {
		
		this.view.getSimulationCanvas().setRenderedCells(cells);
		this.view.getSimulationCanvas().render();
		
		this.generationTime = generationTime;
		
		updateTiles();
		
	}
	
	public static class TransObj extends TransferObject {
		
		public TransObj() { }
		
		public ConwayGame game = null;
		
		public int maxGenerations = 0;
		
		public double generationPeriod = 1.0;
		
	}

}