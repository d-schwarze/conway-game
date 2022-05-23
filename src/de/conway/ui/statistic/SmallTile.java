package de.conway.ui.statistic;

import javafx.scene.Node;

public class SmallTile<N extends Node> extends Tile<N> {

	public SmallTile(String title, N node) {
		super(title, node);
		
	}
	
	@Override
	public void init() {
		super.init();
		
		this.getStyleClass().add("small");
		
	}
}
