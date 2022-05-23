package de.conway.ui.statistic;

import javafx.scene.Node;

public class MediumTile<N extends Node> extends Tile<N> {

	public MediumTile(String title, N node) {
		super(title, node);
		
	}
	
	@Override
	public void init() {
		super.init();
		
		this.getStyleClass().add("medium");
		
	}
}
