package de.conway.ui;

import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class SelectOneButton extends HBox {
	
	private ToggleGroup group;
	
	private boolean builtInStyle = true;
	
	
	public SelectOneButton(boolean builtInStyle) {
		super();
		
		this.builtInStyle = builtInStyle;
		
		init();
		
	}
	
	public SelectOneButton(Node... el) { 
		super(el);
		init();
		
	}
	
	public void init() {
		
		if(builtInStyle)
			this.getStyleClass().add("select-one-button");
		
		group = new ToggleGroup();
		addAlwaysOneSelectedSupport(group);
		
		
		this.getChildren().addListener(new ListChangeListener<Node>() {

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Node> c) {
				
				while(c.next()) {
					
					if(c.wasAdded()) {
						
						for(Node n : c.getAddedSubList()) {
							
							if(n instanceof ToggleButton) {
								
								ToggleButton tb = (ToggleButton) n;
								
								tb.prefWidthProperty().bind(widthProperty().divide(getChildren().size()));
								tb.prefHeightProperty().bind(heightProperty());
								
								group.getToggles().add(tb);
								
							} else {
								
								getChildren().remove(n);
								
							}
						}
						
						
						
					}
					
					if(c.wasRemoved()) {
						
						group.getToggles().removeAll(c.getRemoved());
						
					}
					
					if(builtInStyle)
						updateStyles();
				}
			}
			
		});
		
	}
	
	private void updateStyles() {
		
		for(int i = 0; i < this.getChildren().size(); i++) {
			
			Node n = this.getChildren().get(i);
			
			n.getStyleClass().clear();
			n.getStyleClass().add("select-item");
			
			if(i == 0) {
				
				n.getStyleClass().add("left");
				
			}
			
			if(i == this.getChildren().size() - 1) {
				
				n.getStyleClass().add("right");
				
			}
			
		}
		
	}
	
	public ToggleGroup getGroup() {
		
		return group;
		
	}
	
	/*
	 * www.jensd.de/wordpress/?p=2381
	 */
	private EventHandler<MouseEvent> consumeMouseEventfilter = (MouseEvent mouseEvent) -> {
        if (((Toggle) mouseEvent.getSource()).isSelected()) {
            mouseEvent.consume();
        }
    };
 
    private void addAlwaysOneSelectedSupport(final ToggleGroup toggleGroup) {
        toggleGroup.getToggles().addListener((Change<? extends Toggle> c) -> {
            while (c.next()) {
                for (final Toggle addedToggle : c.getAddedSubList()) {
                    addConsumeMouseEventfilter(addedToggle);
                }
            }
        });
        toggleGroup.getToggles().forEach(t -> {
            addConsumeMouseEventfilter(t);
        });
    }
 
    private void addConsumeMouseEventfilter(Toggle toggle) {
        ((ToggleButton) toggle).addEventFilter(MouseEvent.MOUSE_PRESSED, consumeMouseEventfilter);
        ((ToggleButton) toggle).addEventFilter(MouseEvent.MOUSE_RELEASED, consumeMouseEventfilter);
        ((ToggleButton) toggle).addEventFilter(MouseEvent.MOUSE_CLICKED, consumeMouseEventfilter);
    }
	
}
