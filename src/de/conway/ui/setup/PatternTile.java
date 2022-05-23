package de.conway.ui.setup;

import de.conway.pattern.Pattern;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

public class PatternTile extends VBox {

	private Label title;
	
	private PatternNode node;
	
	private String titleStr;
	
	private Pattern pattern;
	
	public PatternTile(String title, Pattern pattern) {
		
		titleStr = title;
		this.pattern = pattern;
		
		init();
		
	}
	
	public void init() {
		this.setMinHeight(125);
		this.setMinWidth(125);
		this.getStyleClass().add("pattern-tile");
		
		title = new Label(titleStr);
		VBox.setMargin(title, new Insets(3, 0, 12, 0));
		
		node = new PatternNode(pattern, 80, 80);
		this.setAlignment(Pos.TOP_CENTER);
		
		this.getChildren().addAll(title, node);
		
		this.setOnDragDetected(ev -> {
			
			Dragboard db = this.startDragAndDrop(TransferMode.MOVE);
			
			ClipboardContent cc = new ClipboardContent();
			cc.put(PatternFormat.FORMAT, pattern);
			db.setContent(cc);
			
			db.setDragView(this.snapshot(null, null));
			
			ev.consume();
			
		});
		
	}
	
}
