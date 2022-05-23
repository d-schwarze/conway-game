package de.conway.ui.statistic;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Tile<T extends Node> extends VBox {

	private T node;
	
	private String title;
	
	private Label titleLabel;
	
	public Tile(String title, T node) {
		super(5);
		
		this.node = node;
		this.title = title;
		
		init();
		
	}
	
	public void init() {
		
		titleLabel = new Label(title);
		
		applyStyle();
		
		this.getChildren().addAll(titleLabel, node);
		this.setAlignment(Pos.TOP_CENTER);
		
		VBox.setMargin(this, new Insets(0, 20, 0, 20));
		this.getStyleClass().add("tile");
	
	}
	
	private void applyStyle() {
		
		if(node instanceof Label) {
			
			node.getStyleClass().add("tile-label");
			
		} else if(node instanceof ImageView) {
			
			node.getStyleClass().add("tile-image-view");
			
			((ImageView) node).setFitHeight(28);
			((ImageView) node).setFitWidth(28);
			
		} else if(node instanceof AreaChart) {
			
			node.getStyleClass().add("tile-area-chart");
			
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
