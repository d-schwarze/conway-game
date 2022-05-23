package de.conway.ui.statistic;

import javafx.scene.Node;

public class LargeTile<N extends Node> extends Tile<N> {

	public LargeTile(String title, N node) {
		super(title, node);
		
	}
	
	@Override
	public void init() {
		super.init();
		
		this.getStyleClass().add("large");
		
	}
}
