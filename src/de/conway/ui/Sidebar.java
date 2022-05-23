package de.conway.ui;

import javafx.scene.layout.VBox;

public class Sidebar extends VBox {

	public Sidebar() {
		
		init();
		
	}
	
	public void init() {
		
		this.getStyleClass().add("sidebar");
		this.setPrefWidth(300);
		this.setMinWidth(300);
		
	}
	
}
