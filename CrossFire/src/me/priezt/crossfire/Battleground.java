package me.priezt.crossfire;

import java.util.ArrayList;

public class Battleground {
	public ArrayList<Unit> units;
	
	public Battleground(){
		units = new ArrayList<Unit>();
	}
	
	public void putUnit(Unit unit){
		units.add(unit);
	}
	
	public void purgeUnit(Unit unit){
		units.remove(unit);
	}
}
