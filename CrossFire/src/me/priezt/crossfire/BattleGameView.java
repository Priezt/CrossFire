package me.priezt.crossfire;

public class BattleGameView extends GameView {
	private Battleground battleground;
	
	public BattleGameView(Battleground bg){
		battleground = bg;
	}
	
	@Override
	public void draw(Drawing drawing) {
		for(Unit unit : battleground.units){
			unit.draw(drawing);
		}
	}
	
	@Override
	public void touchDragged(float x, float y, float originX, float originY){
//		Tool.info("Drag: (" + (x - originX) + "," + (y - originY) + ")");
		Turret turret = getTouchableTurret(x, y, originX, originY);
		if(turret == null) return;
		if(turret.containsPoint(x, y)) return;
		float newAngle = 90 - (float)Math.toDegrees(Math.atan2(y - turret.y, x - turret.x));
		turret.angle = newAngle;
	}
	
	@Override
	public void touchUp(float x, float y, float originX, float originY){
//		Tool.info("Click: (" + originX + "," + originY + ") -> (" + x + "," + y + ")");
		Turret turret = getTouchableTurret(x, y, originX, originY);
		if(turret == null) return;
		turret.clicked();
	}
	
	private Turret getTouchableTurret(float x, float y, float originX, float originY){
		Unit unitAtPoint = battleground.getUnitAtPoint(originX, originY);
		if(unitAtPoint == null) return null;
		if(! unitAtPoint.getClass().isAssignableFrom(Turret.class)) return null;
		Turret turret = (Turret)unitAtPoint;
		if(! turret.isTouchable()) return null;
		return turret;
	}
}
