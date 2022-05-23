package de.conway.ui.setup;

import de.conway.ui.SelectOneButton;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class SetupTopBar extends BorderPane {

	public ToggleButton rulebookButton;
	
	public ToggleButton patternButton;
	
	public ToggleButton settingsButton;
	
	private SelectOneButton sidebarSwitcher;
	
	public ToggleButton drawingModeButton;
	
	public Button saveButton;
	
	public Button nextButton;
	
	public Button restoreButton;
	
	public Button undoRestoreButton;
	
	public Button zoomOutButton;
	
	public Button zoomInButton;
	
	public Button focusButton;
	
	
	public SetupTopBar() {
		
		init();
	}
	
	public void init() {
		
		this.setStyle("-fx-background-color: light-black");
		this.setPrefHeight(50);
		
		//LEFT
		rulebookButton = new ToggleButton("REGELWERK");
		rulebookButton.getStyleClass().add("sidebar-button");
		
		patternButton = new ToggleButton("MUSTER");
		patternButton.getStyleClass().add("sidebar-button");
		
		settingsButton = new ToggleButton("EINSTELLUNGEN");
		settingsButton.getStyleClass().add("right");
		settingsButton.getStyleClass().add("sidebar-button");
		
		sidebarSwitcher = new SelectOneButton(false);
		sidebarSwitcher.setMinWidth(300);
		sidebarSwitcher.getChildren().addAll(rulebookButton, patternButton, settingsButton);
		
		
		drawingModeButton = new ToggleButton("ZEICHENMODUS");
		drawingModeButton.setSelected(false);
		HBox.setMargin(drawingModeButton, new Insets(0, 20, 0, 20));
		drawingModeButton.getStyleClass().add("select-button");
		
		restoreButton = new Button();
		restoreButton.getStyleClass().addAll("chevron-left", "image-button");
		restoreButton.setTooltip(new Tooltip("Letzte Aktion rückgänig machen."));
		
		undoRestoreButton = new Button();
		undoRestoreButton.getStyleClass().addAll("chevron-right", "image-button");
		undoRestoreButton.setTooltip(new Tooltip("Letzte rückgänig gemachte Aktion wiederherstellen."));
		
		zoomInButton = new Button();
		zoomInButton.setTooltip(new Tooltip("Einzoomen"));
		zoomInButton.getStyleClass().addAll("zoom-in", "image-button");
		
		zoomOutButton = new Button();
		zoomOutButton.setTooltip(new Tooltip("Auszoomen"));
		zoomOutButton.getStyleClass().addAll("zoom-out", "image-button");
		
		focusButton = new Button();
		focusButton.setTooltip(new Tooltip("Zoome zum Geschehen"));
		focusButton.getStyleClass().addAll("focus", "image-button");
		
		HBox left = new HBox(sidebarSwitcher,
					  	     drawingModeButton,
					  	     restoreButton,
					  	     undoRestoreButton,
					  	     zoomInButton,
					  	     zoomOutButton,
					  	     focusButton);
		
		left.setAlignment(Pos.CENTER_LEFT);
		
		this.setLeft(left);
		
		//RIGHT
		saveButton = new Button();
		saveButton.getStyleClass().addAll("save", "image-button");
		
		ImageView saveImage = new ImageView(new Image("assets/arrow-right_w.png"));
		saveImage.setFitHeight(18);
		saveImage.setFitWidth(18);
		
		nextButton = new Button("WEITER");
		nextButton.getStyleClass().add("vip-button");
		HBox.setMargin(nextButton, new Insets(0, 10, 0, 10));
		nextButton.setGraphic(saveImage);
		nextButton.setContentDisplay(ContentDisplay.RIGHT);
		
		FadeTransition ft = new FadeTransition(Duration.millis(1000), nextButton);
		ft.setFromValue(1);
		ft.setToValue(0.7);
		ft.setCycleCount(Timeline.INDEFINITE);
		ft.setAutoReverse(true);
		
		
		nextButton.setOnMouseEntered(ev -> {
			
			ft.play();
			
		});
		
		nextButton.setOnMouseExited(ev -> {
			
			ft.stop();
			
		});
		
		
		HBox right = new HBox(saveButton, nextButton);
		right.setAlignment(Pos.CENTER_RIGHT);
		
		this.setRight(right);
	}
	
}
