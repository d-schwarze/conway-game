package de.conway.ui.setup;

import de.conway.ui.SelectOneButton;
import de.conway.ui.Sidebar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class PatternPane extends Sidebar {

	public TextField searchField;
	
	public ToggleButton staticToggle;
	
	public ToggleButton dynamicToggle;
	
	public ToggleButton ownToggle;
	
	public ToggleButton allToggle;
	
	public SelectOneButton filter;
	
	public Accordion patternsAccordion;
	
	private GridPane header;
	
	private ImageView searchImage;
	
	private ImageView filterImage;
	
	private HBox footer;
	
	private ToggleButton gridToggle;
	
	private ToggleButton listToggle;
	
	public SelectOneButton viewSettingsButton;
	
	public FlowPane patternsGrid;
	
	private ScrollPane sp;
	
	@Override
	public void init() {
		super.init();
		
		header = new GridPane();
		
		footer = new HBox();
		footer.setPrefHeight(40);
		footer.setAlignment(Pos.CENTER_RIGHT);
		
		
		
		
		searchImage = new ImageView(new Image("assets/search.png"));
		searchImage.setFitHeight(18);
		searchImage.setFitWidth(18);
		
		
		filterImage = new ImageView(new Image("assets/filter.png"));
		filterImage.setFitHeight(18);
		filterImage.setFitWidth(18);

		viewSettingsButton = new SelectOneButton(false);
		viewSettingsButton.setAlignment(Pos.CENTER_RIGHT);
		
		gridToggle = new ToggleButton();
		gridToggle.getStyleClass().add("view-button");
		gridToggle.getStyleClass().add("grid-image");
		
		listToggle = new ToggleButton();
		listToggle.getStyleClass().add("view-button");
		listToggle.getStyleClass().add("list-image");
		
		viewSettingsButton.getChildren().addAll(gridToggle, listToggle);
		
		viewSettingsButton.getGroup().selectToggle(gridToggle);
		
		footer.getChildren().addAll(viewSettingsButton);
		
		searchField = new TextField();
		searchField.getStyleClass().add("search-field");
		searchField.setPromptText("SUCHE");
		
		HBox.setMargin(gridToggle, new Insets(2, 0.5, 5, 5));
		HBox.setMargin(listToggle, new Insets(2, 5, 5, 0.5));
		
		staticToggle = new ToggleButton("STATISCH");
		
		dynamicToggle = new ToggleButton("DYNAMISCH");
		
		ownToggle = new ToggleButton("EIGENE");
		
		allToggle = new ToggleButton("ALLE");
		allToggle.setSelected(true);
		
		filter = new SelectOneButton(true);
		filter.getChildren().addAll(allToggle, staticToggle, dynamicToggle, ownToggle);
		filter.getGroup().selectToggle(allToggle);
		
		Separator sep = new Separator();
		sep.getStyleClass().add("sep");
		GridPane.setColumnSpan(sep, 3);
		
		header.add(searchImage, 0, 0);
		header.add(searchField, 1, 0);
		
		header.add(sep, 0, 1);
		
		header.add(filterImage, 0, 2);
		header.add(filter, 1, 2);
		
		
		ColumnConstraints c0 = new ColumnConstraints();
		c0.setPercentWidth(10);
		ColumnConstraints c1 = new ColumnConstraints();
		c1.setPercentWidth(80);
		ColumnConstraints c2 = new ColumnConstraints();
		c2.setPercentWidth(10);
		header.getColumnConstraints().addAll(c0, c1, c2);
		
		
		GridPane.setMargin(searchImage, new Insets(15, 5, 15, 5));
		GridPane.setMargin(filterImage, new Insets(15, 5, 15, 5));
		GridPane.setMargin(searchField, new Insets(15, 0, 15, 0));
		GridPane.setMargin(filter, new Insets(15, 0, 15, 0));
		
		
		//filtersPane = new HBox(staticToggle, dynamicToggle, ownToggle, allToggle);
		
		patternsGrid = new FlowPane();
		patternsGrid.setHgap(10);
		patternsGrid.setVgap(10);
		patternsGrid.setPadding(new Insets(10, 20, 10, 20));
		patternsGrid.setStyle("-fx-background-color: light-black");
		
		patternsAccordion = new Accordion();
		sp = new ScrollPane(patternsGrid);
		sp.setHbarPolicy(ScrollBarPolicy.NEVER);
		patternsGrid.prefWidthProperty().bind(sp.widthProperty());
		patternsAccordion.prefWidthProperty().bind(sp.widthProperty());
		VBox.setVgrow(sp, Priority.ALWAYS);
		
		this.getChildren().addAll(header, sp, footer);
		
	}	
	
	public void switchPatternsView() {
		
		if(sp.getContent() == patternsGrid) {
			
			sp.setContent(patternsAccordion);
			
		} else {
			
			sp.setContent(patternsGrid);
			
		}
		
	}
}
