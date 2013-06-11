package me.priezt.crossfire;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;

import me.priezt.crossfire.Unit.Team;

public class Battleground {
	public ArrayList<Unit> units;
	public ArrayList<Effect> effects;
	public GroundMatrix groundMatrix;
	public int counter = 0;
	public float redEnergy = Conf.MAX_ENERGY;
	public float blueEnergy = Conf.MAX_ENERGY;
	public boolean redOverloaded = false;
	public boolean blueOverloaded = false;
	public static float ENERGY_BAR_WIDTH = 10f;
	public static Color CENTER_SPLIT_LINE_COLOR = Color.MAGENTA;
	
	public void drawCenterSplitLine(Drawing drawing){
		drawing.line(0f, Conf.screenHeight / 2, Conf.screenWidth, Conf.screenHeight / 2, CENTER_SPLIT_LINE_COLOR);
	}
	
	public boolean getOverloaded(Unit.Team team){
		if(team == Unit.Team.RED){
			return redOverloaded;
		}else if(team == Unit.Team.BLUE){
			return blueOverloaded;
		}
		return false;
	}
	
	public void setOverloaded(Unit.Team team, boolean isOverloaded){
		if(team == Unit.Team.RED){
			redOverloaded = isOverloaded;
		}else if(team == Unit.Team.BLUE){
			blueOverloaded = isOverloaded;
		}
	}
	
	public float getEnergy(Unit.Team team){
		if(team == Unit.Team.RED){
			return redEnergy;
		}else if(team == Unit.Team.BLUE){
			return blueEnergy;
		}
		return 0f;
	}
	
	public void setEnergy(Unit.Team team, float newEnergy){
		if(team == Unit.Team.RED){
			redEnergy = newEnergy;
		}else if(team == Unit.Team.BLUE){
			blueEnergy = newEnergy;
		}
	}
	public void drawEnergyBar(Drawing drawing){
		drawing.fillRect(0f, 0f, Conf.screenWidth * redEnergy / Conf.MAX_ENERGY, ENERGY_BAR_WIDTH, Unit.getColorByTeam(Team.RED));
		drawing.fillRect(Conf.screenWidth * (1 - blueEnergy / Conf.MAX_ENERGY), Conf.screenHeight - ENERGY_BAR_WIDTH, Conf.screenWidth * blueEnergy / Conf.MAX_ENERGY, ENERGY_BAR_WIDTH, Unit.getColorByTeam(Team.BLUE));
	}
	
	public void tick(){
		counter++;
		checkDeadUnit();
		unitTick();
		effectTick();
		testStuff();
		generateEnergy();
	}
	
	public void generateEnergy(){
		redEnergy += Conf.ENERGY_GENERATION;
		blueEnergy += Conf.ENERGY_GENERATION;
		if(redEnergy > Conf.MAX_ENERGY) redEnergy = Conf.MAX_ENERGY;
		if(blueEnergy > Conf.MAX_ENERGY) blueEnergy = Conf.MAX_ENERGY;
		if(redEnergy >= Conf.ENERGY_RECOVER) redOverloaded = false;
		if(blueEnergy >= Conf.ENERGY_RECOVER) blueOverloaded = false;
	}
	
	public void testStuff(){
//		if(counter % 50 == 0){
//			new DestroyEffect(500f, 500f).action();
//		}
	}
	
	public void effectTick(){
		for(Effect effect : (ArrayList<Effect>)(effects.clone())){
			effect.tick();
		}
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
//		Tool.info(unitsToTick.size() + " units");
		for(Unit unit : unitsToTick){
			unit.tick();
		}
	}
	
	public Battleground(){
		units = new ArrayList<Unit>();
		effects = new ArrayList<Effect>();
		groundMatrix = new GroundMatrix(Conf.screenWidth, Conf.screenHeight);
		Unit.battleground = this;
		Effect.battleground = this;
	}
	
	public void addUnit(Unit unit){
		units.add(unit);
		groundMatrix.add(unit, unit.x, unit.y, unit.radius);
	}
	
	public void addUnit(Unit unit, float x, float y){
		Point absolutePoint = Tool.getAbsolutePointByRelatedPoint(x, y, unit.team);
		unit.x = absolutePoint.x;
		unit.y = absolutePoint.y;
		if(unit.team == Unit.Team.RED){
			unit.angle = 0f;
		}else if(unit.team == Unit.Team.BLUE){
			unit.angle = 180f;
		}
		addUnit(unit);
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
	
	public Unit getSomeUnitAtPoint(float x, float y){
		ArrayList possibleUnits = groundMatrix.getObjectsInRange(x, y, Conf.COMMON_MATRIX_CHECK_RANGE);
		for(Object obj : possibleUnits){
			Unit unit = (Unit)obj;
			if(unit.containsPoint(x, y)){
				return unit;
			}
		}
		return null;
	}
	
	public Unit getSomeUnitAtPointExcept(float x, float y, Unit exceptUnit){
		ArrayList possibleUnits = groundMatrix.getObjectsInRange(x, y, Conf.COMMON_MATRIX_CHECK_RANGE);
		possibleUnits.remove(exceptUnit);
		for(Object obj : possibleUnits){
			Unit unit = (Unit)obj;
			if(unit.containsPoint(x, y)){
				return unit;
			}
		}
		return null;
	}
	
	public ArrayList<Unit> getUnitsInRange(float x, float y, float radius){
		ArrayList<Unit> result = new ArrayList<Unit>();
//		Tool.info(groundMatrix.getObjectsInRange(x, y, radius).size() + " found");
		for(Unit unit : (ArrayList<Unit>)(groundMatrix.getObjectsInRange(x, y, radius))){
//			Tool.info("a: " + unit.getClass().getSimpleName());
			if(Math.abs(x - unit.x) > radius) continue;
			if(Math.abs(y - unit.y) > radius) continue;
			if(Math.pow(x - unit.x, 2) + Math.pow(y - unit.y, 2) > Math.pow(radius, 2)) continue;
			result.add(unit);
		}
		return result;
	}
	
	public ArrayList<Unit> getUnitsInRangeExcept(float x, float y, float radius, Unit exceptUnit){
		/*
		ArrayList<Unit> result = new ArrayList<Unit>();
		for(Unit unit : getUnitsInRange(x, y, radius)){
			if(unit == exceptUnit) continue;
			result.add(unit);
		}
		return result;
		*/
		ArrayList<Unit> units = getUnitsInRange(x, y, radius);
		units.remove(exceptUnit);
		return units;
	}
}
