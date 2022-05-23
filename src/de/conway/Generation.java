package de.conway;

import java.util.ArrayList;
import java.util.List;

import de.conway.pattern.Pattern;
import de.conway.pattern.io.*;

public class Generation implements CSVBean {
	
	private Rulebook rulebook;
	
	private List<Cell> cells;
	
	private double generationTime = 0.0;
	
	public Generation(List<Cell> cells, Rulebook rulebook) {
		this.rulebook = rulebook;
		this.cells = new ArrayList<>(cells);
		updateMotherGeneration();
	}
	
	public void updateMotherGeneration() {
		for(Cell c : cells) {
			c.setMotherGeneration(this);
		}
	}
	
	public Rulebook getRulebook() {
		return rulebook;
	}

	public void setRulebook(Rulebook rulebook) {
		this.rulebook = rulebook;
	}

	public List<Cell> getCells() {
		return cells;
	}
	
	public void killCell(Cell cell) {
		cells.remove(cell);
	}
	
	public void addCell(Cell cell) {
		cells.add(cell);
		cell.setMotherGeneration(this);
	}
	
	public ArrayList<Pattern> getPatterns() {
		return null;
	}
	
	public Generation generateNextGeneration() {
		
		if(this.rulebook == null)
			throw new GenerationException("Rulebook was not found!");
		
		long before = System.nanoTime();
	
		Generation gen = new Generation(this.cells, this.rulebook);
		
		rulebook.applyRules(gen);
		
		double genTime = System.nanoTime() - before;
		genTime = genTime / 1000000000;
		System.out.println("Bef" + genTime);
		
		gen.generationTime = genTime;
		
		return gen;
		
	}
	
	public double getGenerationTime() {
		
		return generationTime;
		
	}

	@Override
	public String getCsvString() {
		
		return cells.size() + ";" + generationTime;
		
	}

	@Override
	public String getCsvHeader() {
		
		return "Zellen;Generierungsdauer";
		
	}
}
