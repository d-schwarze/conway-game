package de.conway.ui.statistic;

import de.conway.ui.View;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

public class StatisticView extends View<FlowPane> {

	private LargeTile<Label> genTimeTile;
	
	private LargeTile<Label> cellsTile;
	
	private MediumTile<Label> test1;
	
	private SmallTile<Label> test2;
	
	@Override
	public void init() {
		this.root = new FlowPane();
		
		genTimeTile = new LargeTile<>("h1", new Label("test"));
		
		cellsTile = new LargeTile<>("h1", new Label("test"));
		
		test1 = new MediumTile<>("h1", new Label("test"));
		
		test2 = new SmallTile<>("h1", new Label("test"));
		
		this.getRoot().getChildren().addAll(genTimeTile, test1, test2, cellsTile);
		
	}

}
