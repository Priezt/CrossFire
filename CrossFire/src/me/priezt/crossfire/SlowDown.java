package me.priezt.crossfire;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;

public class SlowDown extends Turret {
	public static float SLOW_RADIUS = 250f;	
	public static float SLOW_RATE = 0.2f;

	@Override
	public boolean isClickable(){
		return true;
	}
	
	public SlowDown(float _x, float _y, float _angle, Team _team) {
		super(_x, _y, _angle, _team);
		radius = 40f;
		actionCost = 0.1f;
	}

	@Override
	public void drawBody(Drawing drawing) {
		if(powerOn){
			drawing.fillCircle(x, y, SLOW_RADIUS, getAuraColor());
		}
		drawing.circle(x, y, radius, getTeamColor());
		drawing.line(getPointByRadiusAndAngle(radius, -60f), getPointByRadiusAndAngle(radius, 60f), getTeamColor());
		drawing.line(getPointByRadiusAndAngle(radius, -120f), getPointByRadiusAndAngle(radius, 120f), getTeamColor());
	}
	
	@Override
	public void tick(){
		if(! powerOn) return;
		if(! cost()){
			powerOn = false;
			return;
		}
		for(Bullet bullet : this.bulletOnly(this.enemyOnly(battleground.getUnitsInRange(x, y, SLOW_RADIUS)))){
			bullet.moveFilter.add(new MoveFilter(){
				@Override
				public Point filter(Point targetPoint, Bullet bullet) {
					return new Point(targetPoint.x * SLOW_RATE + bullet.x * (1 - SLOW_RATE), targetPoint.y * SLOW_RATE + bullet.y * (1 - SLOW_RATE));
				}
			});
		}
	}
}
