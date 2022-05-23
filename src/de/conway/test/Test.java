package de.conway.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import de.conway.*;
import de.conway.pattern.Pattern;
import de.conway.pattern.PatternManager;
import de.conway.pattern.State;
import de.conway.pattern.StaticPattern;
import de.conway.pattern.io.XmlIO;

public class Test {

	//static TestData t = new Test.TestData("tset");
	
	public static void main(String[] args) {
		/*Rulebook rulebook = new ConwayRulebook();
		
		Startgeneration gen = new Startgeneration(new Cell(10, 15), new Cell(10, 14), new Cell(11, 12), new Cell(11, 13), new Cell(9, 13), new Cell(15, 13), new Cell(14, 16), new Cell(15, 16), new Cell(11, 10), new Cell(14, 13), new Cell(8, 13), new Cell(12, 13), new Cell(8, 12), new Cell(9, 12));

		
		ConwayGame game = new ConwayGame(gen, rulebook);
		
		
		game.start(500000000, 0, Mode.RUN);*/
		
		PatternManager con = PatternManager.getInstance();
		
		State state = new State();
		state.getCells().add(new Cell(new Vector2D(1, 0)));
		state.getCells().add(new Cell(new Vector2D(1, 1)));
		state.getCells().add(new Cell(new Vector2D(0, 1)));
		state.getCells().add(new Cell(new Vector2D(0, 0)));
		
		StaticPattern s = new StaticPattern("Block", new Vector2D(0,0), state);
		con.addStaticPattern(s);
		 state = new State();
		state.getCells().add(new Cell(new Vector2D(0, 1)));
		state.getCells().add(new Cell(new Vector2D(1, 0)));
		state.getCells().add(new Cell(new Vector2D(2, 0)));
		state.getCells().add(new Cell(new Vector2D(3, 1)));
		state.getCells().add(new Cell(new Vector2D(1, 2)));
		state.getCells().add(new Cell(new Vector2D(2, 2)));
		
		 s = new StaticPattern("Bienenstock", new Vector2D(0,0), state);
		 con.addStaticPattern(s);
		 state = new State();
			state.getCells().add(new Cell(new Vector2D(0, 2)));
			state.getCells().add(new Cell(new Vector2D(1, 1)));
			state.getCells().add(new Cell(new Vector2D(1, 3)));
			state.getCells().add(new Cell(new Vector2D(2, 0)));
			state.getCells().add(new Cell(new Vector2D(2, 3)));
			state.getCells().add(new Cell(new Vector2D(3, 1)));
			state.getCells().add(new Cell(new Vector2D(3, 2)));
			
			 s = new StaticPattern("Brot", new Vector2D(0,0), state);
			 con.addStaticPattern(s);
			 
					
				 state = new State();
						state.getCells().add(new Cell(new Vector2D(0, 1)));
						state.getCells().add(new Cell(new Vector2D(0, 2)));
						state.getCells().add(new Cell(new Vector2D(1, 0)));
						state.getCells().add(new Cell(new Vector2D(1, 2)));
						state.getCells().add(new Cell(new Vector2D(2, 1)));
						
						 s = new StaticPattern("Boot", new Vector2D(0,0), state);
						 con.addStaticPattern(s);
						 state = new State();
							state.getCells().add(new Cell(new Vector2D(0, 1)));
							state.getCells().add(new Cell(new Vector2D(1, 0)));
							state.getCells().add(new Cell(new Vector2D(1, 2)));
							state.getCells().add(new Cell(new Vector2D(2, 1)));
							
							s= new StaticPattern("Wanne", new Vector2D(0,0), state);
							
		
		con.addStaticPattern(s);
		//con.write();
		XmlIO io = new XmlIO();
		SaveData sd = new SaveData("test4.xml");
		ArrayList<Data> test = new ArrayList<>();
		Data o = new Data();
		o.setData("test");
		test.add(o);
		
		con.write();
		//sd.write(con.getPatterns());
		//sd.write(o);
	}
	
	 class TestData implements Serializable {
		
		private String test;
		
		public TestData(String test) {
			this.test = test;
		}

		public String getTest() {
			return test;
		}

		public void setTest(String test) {
			this.test = test;
		}
		
		
		
	}
	
}
