package de.conway.ui;

import java.util.List;

import de.conway.Cell;
import de.conway.ConwayGame;
import de.conway.ConwayPrinter;
import de.conway.ConwayRulebook;
import de.conway.Mode;
import de.conway.Position;
import de.conway.Rulebook;
import de.conway.Startgeneration;
import de.conway.ui.setup.SetupController;
import de.conway.ui.simulation.SimulationController;
import de.conway.ui.statistic.StatisticController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ConwayGameFX extends Application implements ConwayPrinter {

	private ConwayGame game;
	
	private Canvas canvas;
	
	private GraphicsContext gc;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		/*canvas = new Canvas(500, 500);
		gc = canvas.getGraphicsContext2D();
		
		Rulebook rulebook = new ConwayRulebook();
		
		//Startgeneration gen = new Startgeneration(new Cell(10, 15), new Cell(10, 14), new Cell(11, 12), new Cell(11, 13), new Cell(10, 13));
		Startgeneration gen = new Startgeneration(new GliderPattern(new Position(10, 10)));
		
		gc.setFill(Color.BLUEVIOLET);
		gc.fillRect(10, 10, 10, 10);
		
		
		game = new ConwayGame(gen, rulebook);
		game.setPrinter(this);
		
		ComboBox<Mode> mode = new ComboBox<>();
		mode.setItems(FXCollections.observableArrayList(Mode.RUN, Mode.STEP));
		mode.getSelectionModel().select(Mode.RUN);
		mode.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Mode>() {

			@Override
			public void changed(ObservableValue<? extends Mode> observable, Mode oldValue, Mode newValue) {
				game.changeMode(newValue);
				
			}
			
		});
		
		Button start = new Button("Start");
		start.setOnAction(even -> {
			game.start(3000, 100, mode.getSelectionModel().getSelectedItem());
		});
		
		Button stop = new Button("Stop");
		stop.setOnAction(event -> {
			game.stop();
		});
		
		Button resume = new Button("Weiter");
		resume.setOnAction(event -> {
			game.resume();
		});
		
		Button quit = new Button("Beenden");
		quit.setOnAction(event -> {
			game.quit();
		});
		
		Button nextStep = new Button(">>");
		nextStep.setOnAction(event -> {
			game.nextStep();
		});
		
		BorderPane mainPane = new BorderPane();
		mainPane.setCenter(canvas);
		mainPane.setTop(new HBox(mode, start, stop, resume, quit, nextStep));
		*/
		
		Scene scene = new Scene(new Pane());
		scene.getStylesheets().add("styles.css");
		
		primaryStage.getIcons().add(new Image("assets/conway-game_icon@32.png"));
		
		CentralUI.getInstance().scene = scene;
		
		SetupController con = new SetupController();
		SimulationController conSim = new SimulationController();
		StatisticController conSta = new StatisticController();
		
		CentralUI.getInstance().showView(con.getClass());
		
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void onPrint(List<Cell> cells, double genT) {
		gc.clearRect(0, 0, 500, 500);
		for(Cell c : cells) {
			gc.fillOval(c.getX() * 10, c.getY() * 10, 10, 10);
		}
	}

}
