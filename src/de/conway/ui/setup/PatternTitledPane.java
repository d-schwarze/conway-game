package de.conway.ui.setup;

import de.conway.pattern.Pattern;
import javafx.geometry.Pos;
import javafx.scene.control.TitledPane;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;

public class PatternTitledPane extends TitledPane {
	
	private HBox content;
	
	private Pattern pattern;
	
	private PatternNode patternNode;
	
	public PatternTitledPane(String header, Pattern pattern) {
		this.setText(header);
		this.pattern = pattern;
		
		init();
	}
	
	public void init() {
		
		content = new HBox();
		content.setAlignment(Pos.CENTER);
		
		patternNode = new PatternNode(pattern);
		
		content.getChildren().add(patternNode);
		this.setContent(content);
		
		
		this.setOnDragDetected(ev -> {
			
			Dragboard db = this.startDragAndDrop(TransferMode.MOVE);
			
			ClipboardContent cc = new ClipboardContent();
			cc.put(PatternFormat.FORMAT, pattern);
			db.setContent(cc);
			
			if(this.isExpanded())
				db.setDragView(content.snapshot(null, null));
			
			ev.consume();
			
		});
		
		this.setOnDragDone(eve -> System.out.println("finished"));
		
	}
	
}
