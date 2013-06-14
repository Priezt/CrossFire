package me.priezt.crossfire;

import com.badlogic.gdx.graphics.Color;

public class Jammer extends Turret {
	public static float JAMMER_RADIUS = 200f;
	public static float JAMMER_ANGLE = 0.5f;

	public Jammer(float _x, float _y, float _angle, Team _team) {
		super(_x, _y, _angle, _team);
		radius = 40f;
		actionCost = 0.2f;
	}
	
	@Override
	public boolean isClickable(){
		return true;
	}

	@Override
	public void drawBody(Drawing drawing) {
		if(powerOn){
			drawing.fillCircle(x, y, JAMMER_RADIUS, getAuraColor());
		}
		drawing.circle(x, y, radius, getTeamColor());
		drawing.line(getPointByRadiusAndAngle(radius, 0f), new Point(x, y), getTeamColor());
		drawing.line(getPointByRadiusAndAngle(radius, 120f), new Point(x, y), getTeamColor());
		drawing.line(getPointByRadiusAndAngle(radius, 240f), new Point(x, y), getTeamColor());
	}

	@Override
	public void tick(){
		if(!this.powerOn) return;
		if(!cost()){
			powerOn = false;
			return;
		}
		for(Bullet bullet : this.bulletOnly(this.enemyOnly(battleground.getUnitsInRange(x, y, JAMMER_RADIUS)))){
			bullet.turnFilter.add(new TurnFilter(){
				@Override
				public float filter(float targetAngle, Bullet bullet) {
					float ang = targetAngle - Tool.getAngle(x - bullet.x, y - bullet.y);
					ang = ang % 360;
					if(ang < 0) ang += 360;
					float resultAngle = bullet.angle;
					float realJammerAngle = JAMMER_ANGLE / bullet.weight;
					if(ang >= 0 && ang <= 90f) resultAngle = targetAngle + realJammerAngle;
					if(ang >= 270f && ang < 360f) resultAngle = targetAngle - realJammerAngle;
					return resultAngle;
				}
			});
		}
	}
}
