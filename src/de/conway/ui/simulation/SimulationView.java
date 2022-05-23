package de.conway.ui.simulation;

import de.conway.ui.ConwayView;

public class SimulationView extends ConwayView {

private SimulationSidebar simulationSidebar;
	
	private SimulationCanvas simulationCanvas;
	
	@Override
	public void init() {
		super.init();
		
		simulationSidebar = new SimulationSidebar();
		
		simulationCanvas = new SimulationCanvas();
		
		this.setSidebar(simulationSidebar);
		this.setContent(simulationCanvas.getBindedPane());
				
	}
	
	public SimulationSidebar getSimulationSidebar() {
		
		return simulationSidebar;
		
	}
	
	public SimulationCanvas getSimulationCanvas() {
		
		return simulationCanvas;
		
	}
}
