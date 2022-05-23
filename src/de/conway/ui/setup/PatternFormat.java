package de.conway.ui.setup;

import javafx.scene.input.DataFormat;

public class PatternFormat extends DataFormat {

	public final static PatternFormat FORMAT = new PatternFormat("obj/pattern");
	
	private PatternFormat(String format) {
		super(format);
	}
	
}
