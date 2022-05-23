package de.conway.ui.setup;

import de.conway.ui.Sidebar;
import javafx.geometry.Insets;
import javafx.scene.control.Label;

public class RulebookPane extends Sidebar {

	private Label headerLabel;
	
	private Label diesIfLabel;
	
	private Label option1;
	
	private Label option2;
	
	private Label livesIfLabel;
	
	private Label option3;
	
	@Override
	public void init() {
		super.init();
		
		headerLabel = new Label("STANDARD REGELWERK");
		
		diesIfLabel = new Label("Stirb, wenn");
		
		option1 = new Label("- sie von weniger als 2 Zellen umgeben ist.");
		
		option2 = new Label("- sie von mehr als 3 Zellen umgeben ist.");
		
		livesIfLabel = new Label("Neue Zelle wird generiert, wenn");
		
		option3 = new Label("- wenn sie von 3 lebenden Zellen umgeben ist.");
		
		this.getChildren().addAll(headerLabel, diesIfLabel, option1, option2, livesIfLabel, option3);
		this.setPadding(new Insets(20));
		
	}
}
