package de.conway.ui;

public abstract class Controller<V extends View<?>> {

	protected V view;
	
	protected final CentralUI superController;
	
	protected final HistoryManager history;
	
	public Controller() {
		
		superController = CentralUI.getInstance();
		
		history = new HistoryManager();
		
		superController.addController(this);
		
		init();
		
		if(view == null)
			throw new NullPointerException("The view was not created!");
		
	}
	
	public abstract void init();
	
	public abstract void onReceivedData(TransferObject obj);
	
	public V getView() {
		
		return view;
		
	}
	
}
