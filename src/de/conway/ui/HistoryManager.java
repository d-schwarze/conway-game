package de.conway.ui;

import java.util.ArrayList;

public class HistoryManager {

	private ArrayList<HistoryEvent> history;
	
	private HistoryEvent restoredEvent;
	
	public HistoryManager() {
		
		history = new ArrayList<>();
		
	}
	
	public void addHistoryEvent(HistoryEvent ev) {
		
		if(ev != null) {
			
			history.add(ev);
			
			restoredEvent = null;
			
		}
	}
	
	public void restoreLast() {
		
		if(history.size() >= 1) {
			
			HistoryEvent ev = history.get(history.size() - 1);
			
			ev.restore();
			
			restoredEvent = ev;
			
			history.remove(ev);
			
		}
	}
	
	public void undoRestored() {
		
		if(restoredEvent != null) {
			
			restoredEvent.action();
			
			this.addHistoryEvent(restoredEvent);
			
		}
	}
}
