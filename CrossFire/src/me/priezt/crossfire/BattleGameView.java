package me.priezt.crossfire;

import me.priezt.crossfire.Unit.Team;

public class BattleGameView extends GameView {
	private Battleground battleground;
	
	public void testInit(){
		battleground.addUnit(new MachineGun(0, 0, 0, Unit.Team.RED), -0.5f, -0.5f);
		battleground.addUnit(new RocketLauncher(0, 0, 0, Unit.Team.RED), 0.5f, -0.5f);
		battleground.addUnit(new SlowDown(0, 0, 0, Unit.Team.RED), 0f, 0.5f);
		
		battleground.addUnit(new MachineGun(0, 0, 0, Unit.Team.BLUE), -0.5f, -0.5f);
		battleground.addUnit(new Cerberus(0, 0, 0, Unit.Team.BLUE), 0.5f, -0.5f);
		battleground.addUnit(new Jammer(0, 0, 0, Unit.Team.BLUE), 0f, 0.5f);
	}
	
	public BattleGameView(){
		battleground = new Battleground();
		testInit();
	}
	
	@Override
	public void draw(Drawing drawing) {
		battleground.drawCenterSplitLine(drawing);
		for(Unit unit : battleground.units){
			unit.draw(drawing);
		}
		for(Effect effect : battleground.effects){
			effect.draw(drawing);
		}
		battleground.drawEnergyBar(drawing);
	}
	

	
	@Override
	public void update(){
		battleground.tick();
	}
	
	@Override
	public void touchDragged(float x, float y, float originX, float originY){
//		Tool.info("Drag: (" + (x - originX) + "," + (y - originY) + ")");
		Turret turret = getAimableTurret(x, y, originX, originY);
		if(turret == null) return;
		if(turret.containsPoint(x, y)) return;
		float newAngle = 90 - (float)Math.toDegrees(Math.atan2(y - turret.y, x - turret.x));
		turret.angle = newAngle;
		turret.showAiming();
	}
	
	@Override
	public void touchUp(float x, float y, float originX, float originY){
//		Tool.info("Click: (" + originX + "," + originY + ") -> (" + x + "," + y + ")");
		Turret turret = getClickableTurret(x, y, originX, originY);
		if(turret == null) return;
		turret.hideAiming();
		if(turret.containsPoint(x, y)){
			turret.clicked();
		}
	}
	
	private Turret getClickableTurret(float x, float y, float originX, float originY){
		Unit unitAtPoint = battleground.getSomeUnitAtPoint(originX, originY);
		if(unitAtPoint == null) return null;
		if(! Turret.class.isAssignableFrom(unitAtPoint.getClass())) return null;
		Turret turret = (Turret)unitAtPoint;
		if(! turret.isClickable()) return null;
		return turret;
	}
	
	private Turret getAimableTurret(float x, float y, float originX, float originY){
		Unit unitAtPoint = battleground.getSomeUnitAtPoint(originX, originY);
		if(unitAtPoint == null) return null;
		if(! Turret.class.isAssignableFrom(unitAtPoint.getClass())) return null;
		Turret turret = (Turret)unitAtPoint;
		if(! turret.isAimable()) return null;
		return turret;
	}
}
