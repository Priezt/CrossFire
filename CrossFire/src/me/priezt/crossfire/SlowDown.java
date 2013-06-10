package me.priezt.crossfire;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;

public class SlowDown extends Turret {
	public static float SLOW_RADIUS = 150f;	

	public SlowDown(float _x, float _y, float _angle, Team _team) {
		super(_x, _y, _angle, _team);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawBody(Drawing drawing) {
		Color colorWithAlpha = getTeamColor().cpy();
		colorWithAlpha.set(colorWithAlpha.r, colorWithAlpha.g, colorWithAlpha.b, 0.05f);
		drawing.fillCircle(x, y, SLOW_RADIUS, colorWithAlpha);
		drawing.circle(x, y, radius, getTeamColor());
		drawing.line(getPointByRadiusAndAngle(radius, -60f), getPointByRadiusAndAngle(radius, 60f), getTeamColor());
		drawing.line(getPointByRadiusAndAngle(radius, -120f), getPointByRadiusAndAngle(radius, 120f), getTeamColor());
	}
	
	@Override
	public void tick(){
		if(battleground.getUnitsInRangeExcept(x, y, SLOW_RADIUS, this).size() > 0){
			Tool.info("here3");
		}
		for(Bullet bullet : this.bulletOnly(this.enemyOnly(battleground.getUnitsInRange(x, y, SLOW_RADIUS)))){
			Tool.info("here2");
			bullet.moveFilter.add(new MoveFilter(){
				@Override
				public Point filter(Point targetPoint, Bullet bullet) {
					Tool.info("here");
					return new Point(targetPoint.x + 1, targetPoint.y);
				}
			});
		}
	}
}
