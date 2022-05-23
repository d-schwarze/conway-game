package de.conway.ui.setup;

import de.conway.Cell;
import de.conway.ConwayGame;
import de.conway.ConwayRulebook;
import de.conway.Position;
import de.conway.Startgeneration;
import de.conway.Vector2D;
import de.conway.pattern.Pattern;
import de.conway.pattern.PatternManager;
import de.conway.pattern.StaticPattern;
import de.conway.ui.Controller;
import de.conway.ui.HistoryEvent;
import de.conway.ui.MsgType;
import de.conway.ui.TransferObject;
import de.conway.ui.simulation.SimulationController;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class SetupController extends Controller<SetupView> {
	
	private PatternManager patternManager;
	
	private Vector2D lastPosition;
	
	@Override
	public void init() {
		//Init models
		patternManager = PatternManager.getInstance();
		
		//Init view
		this.view = new SetupView();
		
		//Fill with data
		for(Pattern p : patternManager.getPatterns()) {
			
			view.getPatternPane().patternsAccordion.getPanes().add(new PatternTitledPane(p.getName(), p));
			view.getPatternPane().patternsGrid.getChildren().add(new PatternTile(p.getName(), p));
		}
		
		for(StaticPattern p : patternManager.getStaticPatterns()) {
			
			view.getPatternPane().patternsAccordion.getPanes().add(new PatternTitledPane(p.getName(), p));
			view.getPatternPane().patternsGrid.getChildren().add(new PatternTile(p.getName(), p));
			
		}
		
		//Init controllers
		view.getSetupTopBar().patternButton.setOnAction(ev -> {
			
			view.setSidebar(view.getPatternPane());
			
		});
		
		view.getPatternPane().searchField.setOnKeyReleased(ev -> {
			
			searchPattern(view.getPatternPane().searchField.getText());
			
		});
		
		view.getPatternPane().filter.getGroup().selectedToggleProperty().addListener(ev -> {
			
			searchPattern(view.getPatternPane().searchField.getText());
			
		});
		
		view.getPatternPane().viewSettingsButton.getGroup().selectedToggleProperty().addListener(ev -> {
			
			view.getPatternPane().switchPatternsView();
			
		});
		
		view.getSetupTopBar().zoomInButton.setOnAction(ev -> {
				
				view.getSetupCanvas().zoomIn();
				
			
			
		});
		
		
		view.getSetupTopBar().zoomOutButton.setOnAction(ev -> {
			
			view.getSetupCanvas().zoomOut();
			
		});
		
		view.getSetupTopBar().restoreButton.setOnAction(ev -> {
			
			history.restoreLast();
			
		});
		
		view.getSetupTopBar().undoRestoreButton.setOnAction(ev -> {
			
			history.undoRestored();
			
		});
		
		view.getSetupCanvas().addEventHandler(MouseEvent.MOUSE_PRESSED, ev -> {
			
			if(view.getSetupCanvas().isDrawingModeActivated() && !view.getSetupCanvas().isControlPressed()) {
				
				if(ev.getButton() == MouseButton.PRIMARY) {
					
					lastPosition = view.getSetupCanvas().getPosition(ev.getX(), ev.getY());
					
					view.getSetupCanvas().addCell(new Cell(lastPosition));
					view.getSetupCanvas().render();
					
				} else if(ev.getButton() == MouseButton.SECONDARY) {
					
					lastPosition = view.getSetupCanvas().getPosition(ev.getX(), ev.getY());
					
					view.getSetupCanvas().removeCell(new Cell(lastPosition));
					view.getSetupCanvas().render();
				
				}	
			}
		});
		
		view.getSetupCanvas().addEventHandler(MouseEvent.MOUSE_DRAGGED, ev -> {
			
			if(view.getSetupCanvas().isDrawingModeActivated() && !view.getSetupCanvas().isControlPressed()) {
				
				if(ev.getButton() == MouseButton.PRIMARY) {
					
					Vector2D pos = view.getSetupCanvas().getPosition(ev.getX(), ev.getY());
					
					if(!pos.equals(lastPosition)) {
						
						lastPosition = pos;
						view.getSetupCanvas().addCell(new Cell(lastPosition));
						view.getSetupCanvas().render();
						
					}
					
				} else if(ev.getButton() == MouseButton.SECONDARY) {
					
					Vector2D pos = view.getSetupCanvas().getPosition(ev.getX(), ev.getY());
					
					if(!pos.equals(lastPosition)) {
						
						lastPosition = pos;
						view.getSetupCanvas().removeCell(new Cell(lastPosition));
						view.getSetupCanvas().render();
						
					}
				
				}				
			}
			
		});
		
		view.getSetupCanvas().setOnMouseClicked(ev -> {
			
			if(view.getSetupCanvas().isDrawingModeActivated() && !view.getSetupCanvas().isControlPressed()) {
				
				if(ev.getButton() == MouseButton.PRIMARY) {
					
					Cell cell = view.getSetupCanvas().getCell(ev.getX(), ev.getY());

					
					history.addHistoryEvent(new HistoryEvent() {

						@Override
						public void restore() {
							
							view.getSetupCanvas().removeCell(cell);
							view.getSetupCanvas().render();
							
						}

						@Override
						public void action() {
							
							view.getSetupCanvas().addCell(cell);
							view.getSetupCanvas().render();
							
						}
					});
					
					view.getSetupCanvas().addCell(cell);
					view.getSetupCanvas().render();
				
					
				} else if(ev.getButton() == MouseButton.SECONDARY) {
										
					Cell cell = view.getSetupCanvas().getCell(ev.getX(), ev.getY());
					
					view.getSetupCanvas().removeCell(cell);
					view.getSetupCanvas().render();
					
				}

				
			}
			
		});
		
		view.getSetupTopBar().drawingModeButton.setOnAction(ev -> {
			
			if(view.getSetupTopBar().drawingModeButton.isSelected()) {
				
				view.getSetupCanvas().activateDrawingMode();
				
			} else {
				
				view.getSetupCanvas().deactivateDrawingMode();
				
			}
			
		});
		
		view.getSetupTopBar().nextButton.setOnAction(ev -> {
			
			if(this.validateSetup()) {
			
				int maxGen = 0;
				
				if(!view.getSettingsPane().numberOfGenerationsField.getText().trim().isEmpty())
					maxGen = Integer.valueOf(view.getSettingsPane().numberOfGenerationsField.getText());
				
				double genPeriod = 0.0;
				
				if(!view.getSettingsPane().periodField.getText().trim().isEmpty())
					genPeriod =  Double.valueOf(view.getSettingsPane().periodField.getText());
				
				Startgeneration gen = new Startgeneration(view.getSetupCanvas().getRenderedCells());
				
				SimulationController.TransObj data = new SimulationController.TransObj();
				data.game = new ConwayGame(gen, new ConwayRulebook());
				data.maxGenerations = maxGen;
				data.generationPeriod = genPeriod;
				
				this.superController.sendData(SimulationController.class, data);
				this.superController.showView(SimulationController.class);
			
			}
			
		});
		
		view.getSetupTopBar().focusButton.setOnAction(ev -> {
			
			view.getSetupCanvas().zoomToCells();
			
		});
		
		view.getSetupTopBar().saveButton.setOnAction(ev -> {
			
			patternManager.saveNewPattern("test", view.getSetupCanvas().getRenderedCells());
			superController.showMessage(MsgType.INFO, "Nice", "Test");
			
		});
		
		superController.getScene().addEventFilter(KeyEvent.KEY_PRESSED, ev -> {
			
			if(ev.getCode() == KeyCode.CONTROL) {
				
				view.getSetupCanvas().setStrgPressed(true);
				
			}
			
		});
		
		superController.getScene().addEventFilter(KeyEvent.KEY_RELEASED, ev -> {
			
			if(ev.getCode() == KeyCode.CONTROL) {
				
				view.getSetupCanvas().setStrgPressed(false);
				
			}
			
		});
		
	}
	
	private boolean validateSetup() {
		
		if(!view.getSettingsPane().numberOfGenerationsField.getText().isEmpty()) {
			
			try {
				
				Integer.parseInt(view.getSettingsPane().numberOfGenerationsField.getText());
				
			} catch(NumberFormatException ex) {
				
				superController.showMessage(MsgType.ERROR, "Fehler", "Geben sie eine korrekte Zahl für Anzahl an Generationen ein.");
				
				return false;
				
			}
			
		}
		
		if(!view.getSettingsPane().periodField.getText().isEmpty()) {
			
			try {
				
				Integer.parseInt(view.getSettingsPane().periodField.getText());
				
			} catch(NumberFormatException ex) {
				
				superController.showMessage(MsgType.ERROR, "Fehler", "Geben sie eine korrekte Zahl für die Generierungsdauer ein.");
				
				return false;
				
			}
			
		}
		
		if(view.getSetupCanvas().getRenderedCells().isEmpty()) {
			
			superController.showMessage(MsgType.ERROR, "Fehler", "Fügen sie mindestens eine Zelle hinzu!");
			
			return false;
			
		}
			
		
		return true;
		
	}
	
	public void searchPattern(String searchString) {
		
		view.getPatternPane().patternsAccordion.getPanes().clear();
		view.getPatternPane().patternsGrid.getChildren().clear();
		
		if(view.getPatternPane().allToggle.isSelected() || view.getPatternPane().ownToggle.isSelected()) {
			
			for(Pattern p : patternManager.getPatterns()) {
				
				if(searchString.trim().isEmpty() || p.getName().contains(searchString)) {
					
					view.getPatternPane().patternsAccordion.getPanes().add(new PatternTitledPane(p.getName(), p));
					view.getPatternPane().patternsGrid.getChildren().add(new PatternTile(p.getName(), p));
					
				}
				
			}
		}
		
		if(view.getPatternPane().allToggle.isSelected() || view.getPatternPane().staticToggle.isSelected()) {
		
			for(StaticPattern p : patternManager.getStaticPatterns()) {
				
				if(searchString.trim().isEmpty() || p.getName().contains(searchString)) {
					
					view.getPatternPane().patternsAccordion.getPanes().add(new PatternTitledPane(p.getName(), p));
					view.getPatternPane().patternsGrid.getChildren().add(new PatternTile(p.getName(), p));
					
				}
			}
		}
		
	}

	@Override
	public void onReceivedData(TransferObject obj) {
		
		if(obj instanceof SetupController.TransObjectImpl) {
			
			
			
		}
		
	}
	
	public static class TransObjectImpl extends TransferObject {
		
		
		
	}

}
