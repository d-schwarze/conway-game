package de.conway.ui.simulation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class SimulationTile<T extends Node> extends VBox {

	private T node;
	
	private String title;
	
	private Label titleLabel;
	
	public SimulationTile(String title, T node) {
		super(5);
		
		this.node = node;
		this.title = title;
		
		init();
		
	}
	
	public void init() {
		
		titleLabel = new Label(title);
		
		applyStyle();
		
		this.getChildren().addAll(titleLabel, node);
		this.setAlignment(Pos.CENTER);
		
		VBox.setMargin(this, new Insets(0, 20, 0, 20));
		this.getStyleClass().add("simulation-tile");
	
	}
	
	private void applyStyle() {
		
		if(node instanceof Label) {
			
			node.getStyleClass().add("st-content-label");
			
		} else if(node instanceof ImageView) {
			
			node.getStyleClass().add("st-content-image");
			
			((ImageView) node).setFitHeight(28);
			((ImageView) node).setFitWidth(28);
			
		}
		
		
	}

	public T getContent() {
		
		return node;
		
	}
	
	public void setContent(T content) {
		
		this.getChildren().remove(node);
		
		this.node = content;
		
		applyStyle();
		
		this.getChildren().add(node);
		
	}
	
}
