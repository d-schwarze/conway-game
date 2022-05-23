package de.conway.ui;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CentralUI {

	private ArrayList<Controller<? extends View<?>>> controllers;
	
	private static CentralUI instance;
	
	protected Scene scene;
	
	private CentralUI() {
		
		controllers = new ArrayList<>();
		
	}
	
	protected static CentralUI getInstance() {
		
		if(instance == null)
			instance = new CentralUI();
		
		return instance;
		
	}
	
	protected void addController(Controller<? extends View<?>> con) {
		
		controllers.add(con);
		
	}
	
	public Scene getScene() {
		
		return scene;
		
	}
	
	public void showMessage(MsgType type, String title, String msg) {
		
		Alert alert;
		
		switch(type) {
		
			case ERROR:
				alert = new Alert(AlertType.ERROR);
				alert.setHeaderText(title);
				alert.setContentText(msg);
				
			case WARNING:
				alert = new Alert(AlertType.WARNING);
				alert.setHeaderText(title);
				alert.setContentText(msg);
				
			case INFO:
				alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(title);
				alert.setContentText(msg);
			
			default:
				alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(title);
				alert.setContentText(msg);
				
		}
		
		alert.showAndWait();

		
	}
	
	public void sendData(Class<? extends Controller<? extends View<?>>> c, TransferObject data) {
		
		for(Controller<? extends View<?>> con : controllers) {
			
			if(con.getClass().isAssignableFrom(c)) {
				
				con.onReceivedData(data);
				
				break;
				
			}	
		}
	}
	
	public void showView(Class<? extends Controller<? extends View<?>>> c) {
		
		for(Controller<? extends View<?>> con : controllers) {
			
			if(con.getClass().isAssignableFrom(c)) {
				
				scene.setRoot(con.getView().getRoot());
				
				break;
				
			}	
		}
	}
}
