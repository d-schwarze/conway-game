package de.conway;

import java.util.ArrayList;

public class ConwayGame {
	
	private Startgeneration generation;
	
	private Generation currentGeneration;
	
	private ArrayList<Generation> previousGenerations;
	
	private ConwayPrinter printer;
	
	private boolean stop = true;
	
	private int maxAmountOfGenerations = 0;
	
	private int period = 0;
	
	private Mode mode;
	
	private boolean started = false;
	
	private Thread t;
	
	public ConwayGame(Startgeneration startgen, Rulebook rulebook) {
		
		startgen.setRulebook(rulebook);
		this.generation = startgen;
		this.currentGeneration = startgen;
		
		previousGenerations = new ArrayList<>();
		
	}
	
	public void start(int maxAmountOfGenerations, int period, Mode mode) {
		
		this.maxAmountOfGenerations = maxAmountOfGenerations;
		this.period = period;
		this.mode = mode;
		this.started = true;
		this.stop = false;
		
		if(this.mode == Mode.RUN) {
			t = new Thread(() -> {
				
				while(!stop) {
					
					if(previousGenerations.size() <= this.maxAmountOfGenerations || this.maxAmountOfGenerations == 0) {
						System.out.println("cur" + currentGeneration.getGenerationTime());
						if(printer != null)
							printer.onPrint(currentGeneration.getCells(), currentGeneration.getGenerationTime());
						long before = System.nanoTime();
						previousGenerations.add(currentGeneration);
						currentGeneration = currentGeneration.generateNextGeneration();
						double nanno = System.nanoTime() - before;
						System.out.println(nanno / 1000000000);
						try {
							Thread.sleep(this.period);
						} catch (InterruptedException e) {
							stop = true;
						} 
					} else
						stop = true;
				}
			});
			
			t.start();
			
		} else if(this.mode == Mode.STEP) {
			
			nextStep();
			
		}
	}
	
	public void nextStep() {
		
		if(mode == Mode.STEP) {
			
			if(started && !stop) {
				
				if(previousGenerations.size() <= this.maxAmountOfGenerations || this.maxAmountOfGenerations == 0) {
					
					if(printer != null)
						printer.onPrint(currentGeneration.getCells(), currentGeneration.getGenerationTime());
					
					previousGenerations.add(currentGeneration);
					currentGeneration = currentGeneration.generateNextGeneration();
					System.out.println("GENERATION TIME:" + currentGeneration.getGenerationTime());
					
				}
				
			} else if(started && stop) {
				
				resume();
				
			}
		}
	}
	
	public void stop() {
		
		this.stop = true;
		
	}
	
	public void resume() {
		
		if(started && stop) {
			
			start(this.maxAmountOfGenerations, this.period, this.mode);
			
		}
		
	}
	
	public void changeMode(Mode mode) {
		
		if(stop) {
			
			System.out.println("NEW MODE: " + mode);
			this.mode = mode;
			
		}
	}
	
	public void quit() {
		
		if(started) {
			
			stop();
			started = false;
			
			reset();
			
		}
	}
	
	public void reset() {
		
		if(stop) {
			
			this.previousGenerations.clear();
			this.currentGeneration = generation;
			
		}
	}
	
	public void print() {
		
		System.out.println("GEN: " + currentGeneration);
		for(Cell c : currentGeneration.getCells()) {
			
			System.out.println(c.toString() + " " + c.getPosition().toString());
			
		}
	}

	public Startgeneration getGeneration() {
		
		return generation;
		
	}

	public void setGeneration(Startgeneration generation) {
		
		this.generation = generation;
		
	}

	public Generation getCurrentGeneration() {
		
		return currentGeneration;
		
	}

	public void setCurrentGeneration(Generation currentGeneration) {
		
		this.currentGeneration = currentGeneration;
		
	}

	public ArrayList<Generation> getPreviousGenerations() {
		
		return previousGenerations;
		
	}

	public void setPreviousGenerations(ArrayList<Generation> previousGenerations) {
		
		this.previousGenerations = previousGenerations;
		
	}

	public ConwayPrinter getPrinter() {
		
		return printer;
		
	}

	public void setPrinter(ConwayPrinter printer) {
		
		this.printer = printer;
		
	}
	
	public boolean hasStarted() {
		
		return started;
		
	}
}
