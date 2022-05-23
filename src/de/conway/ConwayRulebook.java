package de.conway;

import java.util.ArrayList;
import java.util.List;

public class ConwayRulebook implements Rulebook {

	public ConwayRulebook() { }
	
	
	public void generateNewCells2(Generation gen) {
		ArrayList<Cell> generatedCells = new ArrayList<>();
		
	
			//There could be the chance for the birth of a new cell.
			List<Cell> area = gen.getCells();

				List<Cell> cells = Cells.getAvailableCells(area);
				for(Cell c : cells) {
					if(c.getSmallArea(gen).size() == 3) {
						if(!generatedCells.contains(c))
							generatedCells.add(c);
					}
				}
			
		
		
		for(Cell c : generatedCells) {
			gen.addCell(c);
		}
	}
	
	
	@Override
	public ArrayList<Cell> getNewCells(Generation gen) {
		ArrayList<Cell> generatedCells = new ArrayList<>();
		
		for(Cell cell : gen.getCells()) {
			//There could be the chance for the birth of a new cell.
			List<Cell> area = cell.getBigArea();
			if(area.size() >= 2) {
				area.add(cell);
				List<Cell> cells = Cells.getAvailableCells(area);
				for(Cell c : cells) {
					if(c.getSmallArea(gen).size() == 3) {
						if(!generatedCells.contains(c))
							generatedCells.add(c);
					}
				}
			}
		}
		
		return generatedCells;
		
	}

	@Override
	public ArrayList<Cell> getLostCells(Generation gen) {
		ArrayList<Cell> lostCells = new ArrayList<>();
		
		for(Cell cell : gen.getCells()) {
			int smallAreaSize = cell.getSmallArea().size();
			if(smallAreaSize < 2 || smallAreaSize > 3)
				lostCells.add(cell);
		}
		
		return lostCells;
		
	}

	

}
