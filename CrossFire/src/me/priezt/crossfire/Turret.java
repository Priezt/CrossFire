package me.priezt.crossfire;

import com.badlogic.gdx.graphics.Color;

public abstract class Turret extends Unit {
	public boolean isAiming = false;
	public static Color AIMING_COLOR = Color.YELLOW;
	
	public boolean isClickable(){
		return false;
	};
	
	public boolean isAimable(){
		return false;
	};

	public Turret(float _x, float _y, float _angle, Team _team) {
		super(_x, _y, _angle, _team);
		radius = 50f;
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
	
	public void drawAiming(Drawing drawing){
		if(isAiming){
			drawing.line(new Point(x, y), Tool.getBorderIntersectionPoint(x, y, angle), AIMING_COLOR);
		}
	}
}
