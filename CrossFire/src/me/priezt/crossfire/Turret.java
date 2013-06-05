package me.priezt.crossfire;

import com.badlogic.gdx.graphics.Color;

public class Turret extends Unit {
	public boolean isAiming = false;
	public static Color AIMING_COLOR = Color.YELLOW;
	
	public boolean isTouchable(){
		return true;
	}

	public Turret(float _x, float _y, float _angle, Team _team) {
		super(_x, _y, _angle, _team);
		radius = 50f;
	}

	@Override
	public void draw(Drawing drawing) {
		drawing.circle(x, y, radius, getTeamColor());
		drawing.line(getPointByRadiusAndAngle(radius, 10f), getPointByRadiusAndAngle(radius * 1.5f, 0f), getTeamColor());
		drawing.line(getPointByRadiusAndAngle(radius, -10f), getPointByRadiusAndAngle(radius * 1.5f, 0f), getTeamColor());
		drawAiming(drawing);
	}
	
	public void drawAiming(Drawing drawing){
		if(isAiming){
			drawing.line(new Point(x, y), Tool.getBorderIntersectionPoint(x, y, angle), AIMING_COLOR);
		}
	}

	public void clicked(){
		Tool.info("Turret(" + x + "," + y + ") clicked");
	}
	
	public void showAiming(){
		isAiming = true;
	}
	
	public void hideAiming(){
		isAiming = false;
	}
}
