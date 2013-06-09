package me.priezt.crossfire;

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
}
