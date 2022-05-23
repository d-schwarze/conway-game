package de.conway.ui;

import javafx.scene.layout.Pane;

public abstract class View<R extends Pane> {

	protected R root;
	
	public View() {
		
		init();
		
		if(root == null)
			throw new NullPointerException("Root was not created!");
		
	}
	
	public abstract void init();
	
	public R getRoot() {
		
		return root;
		
	}
}
