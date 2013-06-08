package me.priezt.crossfire;

import java.util.ArrayList;

public class Battleground {
	public ArrayList<Unit> units;
	public GroundMatrix groundMatrix;
	public int counter = 0;
	
	public void tick(){
		counter++;
		checkDeadUnit();
		unitTick();
	}
	
	public void checkDeadUnit(){
		ArrayList<Unit> unitsToCheck= (ArrayList<Unit>)(units.clone());
		int deadFound = 0;
		for(Unit unit : unitsToCheck){
			if(! unit.alive){
				removeUnit(unit);
				deadFound++;
			}
		}
		if(deadFound > 0){
//			Tool.info(deadFound + " dead units found");
//			Tool.info("all " + units.size() + " units left");
		}
	}
	
	public void unitTick(){
		ArrayList<Unit> unitsToTick = (ArrayList<Unit>)(units.clone());
		for(Unit unit : unitsToTick){
			unit.tick();
		}
	}
	
	public Battleground(){
		units = new ArrayList<Unit>();
		groundMatrix = new GroundMatrix(Conf.screenWidth, Conf.screenHeight);
		Unit.battleground = this;
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
		ArrayList possibleUnits = groundMatrix.getObjectsInRange(x, y, Conf.COMMON_MATRIX_CHECK_RANGE);
		for(Object obj : possibleUnits){
			Unit unit = (Unit)obj;
			if(unit.containsPoint(x, y)){
				return unit;
			}
		}
		return null;
	}
}
