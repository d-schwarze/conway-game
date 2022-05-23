package de.conway.ui.simulation;

import de.conway.ui.Sidebar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class SimulationSidebar extends Sidebar {

	private HBox header;
	
	public ToggleButton startStopButton;
	
	public Button nextGenButton;
	
	public Button lastGenButton;
	
	private HBox subHeader;
	
	public Button editButton;
	
	public Button zoomInButton;
	
	public Button zoomOutButton;
	
	public Button focusButton;
	
	public SimulationTile<Label> currentGenTile;
	
	public SimulationTile<Label> cellsTile;
	
	public SimulationTile<Label> genDurationTile;
	
	public SimulationTile<ImageView> growthTile;
	
	public Button finishButton;
	
	private HBox footer;
	
	private ImageView posGrowthImage;
	
	private ImageView negGrowthImage;
	
	private ImageView neutralGrowthImage;
	
	private VBox tiles;
	
	@Override
	public void init() {
		super.init();
		
		
		startStopButton = new ToggleButton();
		startStopButton.getStyleClass().add("ss-button");
		
		nextGenButton = new Button();
		nextGenButton.getStyleClass().add("next-image");
		
		lastGenButton = new Button();
		lastGenButton.getStyleClass().add("last-image");
		
		header = new HBox(lastGenButton, startStopButton, nextGenButton);
		header.setAlignment(Pos.CENTER);
		header.setStyle("-fx-background-color: dark-black");
		header.setPrefHeight(50);
		
		editButton = new Button();
		editButton.setTooltip(new Tooltip("Konfiguration ändern"));
		editButton.getStyleClass().addAll("edit", "image-button");
		
		zoomInButton = new Button();
		zoomInButton.setTooltip(new Tooltip("Einzoomen"));
		zoomInButton.getStyleClass().addAll("zoom-in", "image-button");
		
		zoomOutButton = new Button();
		zoomOutButton.setTooltip(new Tooltip("Auszoomen"));
		zoomOutButton.getStyleClass().addAll("zoom-out", "image-button");
		
		focusButton = new Button();
		focusButton.setTooltip(new Tooltip("Zoome zum Geschehen"));
		focusButton.getStyleClass().addAll("focus", "image-button");
		
		subHeader = new HBox(editButton, zoomInButton, zoomOutButton, focusButton);
		subHeader.setAlignment(Pos.CENTER);
		subHeader.setStyle("-fx-background-color: transparent; -fx-border-width: 0 0 0 0; -fx-border-color:blue");
		subHeader.setPrefHeight(40);
		
		currentGenTile = new SimulationTile<>("Aktuelle Generation", new Label("0"));
		
		cellsTile = new SimulationTile<>("Zellen", new Label("0"));
		
		genDurationTile = new SimulationTile<>("Generierungsdauer", new Label("-"));
		
		posGrowthImage = new ImageView(new Image("assets/trending-up_w.png"));
		negGrowthImage = new ImageView(new Image("assets/trending-down_w.png"));
		neutralGrowthImage = new ImageView(new Image("assets/minus_w.png"));
		
		growthTile = new SimulationTile<>("Wachstum", neutralGrowthImage);
		
		tiles = new VBox(currentGenTile, cellsTile, genDurationTile, growthTile);
		tiles.setSpacing(10);
		
		VBox.setMargin(tiles, new Insets(10, 0, 10, 0));
		
		ImageView saveImage = new ImageView(new Image("assets/arrow-right_w.png"));
		saveImage.setFitHeight(18);
		saveImage.setFitWidth(18);
		
		
		finishButton = new Button("FERTIG");
		finishButton.getStyleClass().add("vip-button");
		finishButton.setGraphic(saveImage);
		finishButton.setContentDisplay(ContentDisplay.RIGHT);
		
		footer = new HBox(finishButton);
		footer.setAlignment(Pos.CENTER);
		footer.setStyle("-fx-background-color: dark-black");
		footer.setPrefHeight(40);
		
		VBox.setVgrow(tiles, Priority.ALWAYS);
		
		this.getChildren().addAll(header, subHeader, tiles, footer);
		
	}
	
	public void setNegativeGrowth() {
		
		if(growthTile.getContent() != negGrowthImage)
			growthTile.setContent(negGrowthImage);
		
	}
	
	public void setPositiveGrowth() {
		
		if(growthTile.getContent() != posGrowthImage) 
			growthTile.setContent(posGrowthImage);
		
	}
	
	public void setNeutralGrowth() {
		
		if(growthTile.getContent() != neutralGrowthImage) 
			growthTile.setContent(neutralGrowthImage);
		
	}
}
