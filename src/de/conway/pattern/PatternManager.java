package de.conway.pattern;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.conway.Cell;
import de.conway.Cells;
import de.conway.Position;
import de.conway.Vector2D;
import de.conway.pattern.io.XmlClassException;
import de.conway.pattern.io.XmlIO;

public class PatternManager {

	private ArrayList<DynamicPattern> dynamicPatterns;
	
	private ArrayList<StaticPattern> staticPatterns;
	
	private ArrayList<Pattern> patterns;
	
	private static PatternManager instance;
	
	private XmlIO io;
	
	private PatternManager() {
		
		io = new XmlIO();
		read();
		
	}
	
	public void read() {
		
		staticPatterns = new ArrayList<>();
		dynamicPatterns = new ArrayList<>();
		patterns = new ArrayList<>();
		
		try {
			
			staticPatterns = (ArrayList<StaticPattern>) io.read("staticPatterns.xml");
			
		} catch (FileNotFoundException ex) {
			
			staticPatterns = new ArrayList<>();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} 
		
		try {
			
			dynamicPatterns = (ArrayList<DynamicPattern>) io.read("dynamicPatterns.xml");
			
		} catch (FileNotFoundException ex) {
			
			dynamicPatterns = new ArrayList<>();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} 

		try {
			
			patterns = (ArrayList<Pattern>) io.read("patterns.xml");
			
		} catch (FileNotFoundException ex) {
			
			patterns = new ArrayList<>();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} 
		
	}
	
	public void write() {
		
		try {
			
			io.write("staticPatterns.xml", staticPatterns);
			io.write("dynamicPatterns.xml", dynamicPatterns);
			io.write("patterns.xml", patterns);
			
		} catch (FileNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
	
	public static PatternManager getInstance() {
		
		if(instance == null) {
			
			instance = new PatternManager();
			
		}
		
		return instance;
		
	}
	
	public int isPatternValid(Pattern pattern) {
		
		if(pattern == null)
			return 1;
		
		if(pattern.getState() == null || pattern.getState().getCells().size() <= 0)
			return 2;
		
		if(pattern.getPosition() == null)
			return 3;
		
		return 0;
		
	}
	
	public int isStaticPatternValid(StaticPattern pattern) {
		
		return isPatternValid(pattern);
		
	}
	
	public int isDynamicPatternValid(DynamicPattern pattern) {
		
		if(pattern == null)
			return 1;
		
		if(pattern.getState() == null || pattern.getState().getCells().size() <= 0)
			return 2;
		
		if(pattern.getStates().isEmpty())
			return 3;
		
		for(State s : pattern.getStates()) {
			
			if(s == null || s.getCells().size() <= 0)
				return 4;
			
		}
		
		if(pattern.getPosition() == null)
			return 5;
		
		return 0;
		
	}
	
	public boolean removePattern(Pattern pattern) {
		
		if(pattern == null)
			return false;
		
		return this.patterns.remove(pattern);
		
	}
	
	public boolean addPattern(Pattern pattern) {
		
		if(isPatternValid(pattern) == 0) {
			
			this.patterns.add(pattern);
			return true;
			
		}
		
		return false;
		
	}
	
	public boolean addStaticPattern(StaticPattern pattern) {
		
		if(isStaticPatternValid(pattern) == 0) {
			
			this.staticPatterns.add(pattern);
			return true;
			
		}
		
		return false;
		
	}
	
	public boolean addDynamicPattern(DynamicPattern pattern) {
		
		if(isDynamicPatternValid(pattern) == 0) {
			
			this.dynamicPatterns.add(pattern);
			return true;
			
		}
		
		return false;
		
	}
	
	public void saveNewPattern(String name, List<Cell> cells) {
		
		if(cells != null && !cells.isEmpty()) {
			
			State state = new State();
			
			state.setCells(cells);
			
			patterns.add(new Pattern(name, new Vector2D(0,0), state));
			
			write();
			
		}	
	}

	public ArrayList<DynamicPattern> getDynamicPatterns() {
		return dynamicPatterns;
	}

	public void setDynamicPatterns(ArrayList<DynamicPattern> dynamicPatterns) {
		this.dynamicPatterns = dynamicPatterns;
	}

	public ArrayList<StaticPattern> getStaticPatterns() {
		return staticPatterns;
	}

	public void setStaticPatterns(ArrayList<StaticPattern> staticPatterns) {
		this.staticPatterns = staticPatterns;
	}

	public ArrayList<Pattern> getPatterns() {
		return patterns;
	}

	public void setPatterns(ArrayList<Pattern> patterns) {
		this.patterns = patterns;
	}
	
	
	
}
