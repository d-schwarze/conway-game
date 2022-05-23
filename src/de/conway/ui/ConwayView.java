package de.conway.ui;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public abstract class ConwayView extends View<BorderPane> {
	
	@Override
	public void init() {
		
		this.root = new BorderPane();
		
	}
	
	public void setSidebar(Sidebar sidebar) {
		
		this.root.setLeft(sidebar);
		
	}
	
	public void setContent(Pane pane) {
		
		this.root.setCenter(pane);
		
	}
	
	public void setTop(Pane pane) {
		
		this.root.setTop(pane);
		
	}
	
}
