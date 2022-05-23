package de.conway.ui.setup;

import de.conway.ui.Sidebar;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SettingsPane extends Sidebar {

	private Label numberOfGenerationsLabel;
	
	public TextField numberOfGenerationsField;
	
	private Label periodLabel;
	
	public TextField periodField;
	
	
	@Override
	public void init() {
		super.init();
		
		numberOfGenerationsLabel = new Label("Anzahl an Generationen");
		
		numberOfGenerationsField = new TextField();
		numberOfGenerationsField.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
				if(!newValue.matches("\\d*")) {
					
					numberOfGenerationsField.setText(newValue.replace("[^\\d]", ""));
					
				}
				
			}
		});
		
		periodLabel = new Label("Generierungsdauer");
		
		periodField = new TextField();
		periodField.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
				if(!newValue.matches("\\d*")) {
					
					periodField.setText(newValue.replace("[^\\d]", ""));
					
				}
				
			}
		});
		
		this.setPadding(new Insets(20));
		this.setSpacing(5);
		this.getChildren().addAll(numberOfGenerationsLabel, numberOfGenerationsField, periodLabel, periodField);
		
	}
	
}
