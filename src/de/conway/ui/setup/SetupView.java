package de.conway.ui.setup;

import de.conway.ui.ConwayView;

public class SetupView extends ConwayView {

private SetupTopBar setupTopBar;
	
	private PatternPane patternPane;
	
	private SetupCanvas setupCanvas;
	
	private SettingsPane settingsPane;
	
	private RulebookPane rulebookPane;
	
	@Override
	public void init() {
		super.init();
		
		setupTopBar = new SetupTopBar();
		this.setTop(setupTopBar);
		
		patternPane = new PatternPane();
		this.setSidebar(patternPane);
		
		setupCanvas = new SetupCanvas();
		
		settingsPane = new SettingsPane();
		
		rulebookPane = new RulebookPane();
		
		
		
		this.setContent(setupCanvas.getBindedPane());
		
		initNavigation();
		
	}
	
	private void initNavigation() {
		
		this.setupTopBar.settingsButton.setOnAction(ev -> {
			
			this.setSidebar(settingsPane);
			
		});
		
		this.setupTopBar.patternButton.setOnAction(ev -> {
			
			this.setSidebar(patternPane);
			
		});
		
		this.setupTopBar.rulebookButton.setOnAction(ev -> {
			
			this.setSidebar(rulebookPane);
			
		});
		
	}


	public SetupTopBar getSetupTopBar() {
		return setupTopBar;
	}


	public PatternPane getPatternPane() {
		return patternPane;
	}
	
	public SetupCanvas getSetupCanvas() {
		return setupCanvas;
	}
	
	public SettingsPane getSettingsPane() {
		
		return settingsPane;
		
	}
}
