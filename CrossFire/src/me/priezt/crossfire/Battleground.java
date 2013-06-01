package me.priezt.crossfire;

import java.util.ArrayList;

public class Battleground {
	public ArrayList<Unit> units;
	public GroundMatrix groundMatrix;
	
	public Battleground(){
		units = new ArrayList<Unit>();
		groundMatrix = new GroundMatrix(Conf.screenWidth, Conf.screenHeight);
	}
	
	public void addUnit(Unit unit){
		units.add(unit);
		groundMatrix.add(unit, unit.x, unit.y, unit.radius);
	}
	
	public void removeUnit(Unit unit){
		units.remove(unit);
		groundMatrix.remove(unit, unit.x, unit.y, unit.radius);
	}
	
	public void moveUnit(Unit unit, float x, float y){
		groundMatrix.remove(unit, unit.x, unit.y, unit.radius);
		unit.x = x;
		unit.y = y;
		groundMatrix.add(unit, unit.x, unit.y, unit.radius);
	}
	
	public Unit getUnitAtPoint(float x, float y){
//		throw new UnsupportedOperationException();
		ArrayList possibleUnits = groundMatrix.getObjectsInRange(x, y, 100f);
		for(Object obj : possibleUnits){
			Unit unit = (Unit)obj;
			if(unit.containsPoint(x, y)){
				return unit;
			}
		}
		return null;
	}
}
