package me.priezt.crossfire;

import com.badlogic.gdx.graphics.Color;

public abstract class Turret extends Unit {
	public boolean isAiming = false;
	public static Color AIMING_COLOR = Color.YELLOW;
	public static float POWER_OFF_ALPHA = 0.15f;
	public static float AURA_ALPHA = 0.05f;
	
	public boolean powerOn = false;
	public float actionCost = 0f;
	
	public boolean cost(){
		return super.cost(actionCost);
	}
	
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
		toggle();
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
	

	public void toggle(){
		powerOn = !powerOn;
		if(powerOn){
			Tool.info("power on");
		}else{
			Tool.info("power off");
		}
	}
	
	public abstract void drawBody(Drawing drawing);
	
	@Override
	public void draw(Drawing drawing){
		drawBody(drawing);
		drawHitpoint(drawing);
		drawAiming(drawing);
	}
	
	public void drawHitpoint(Drawing drawing){
		float hitBarHalfLength = radius * hitpoint / 100f;
		drawing.line(getPointByRadiusAndAngle(hitBarHalfLength, 90f), getPointByRadiusAndAngle(hitBarHalfLength, -90f), Color.GREEN);
	}
	
	@Override
	public void onDestroy(){
//		Tool.info("turret destroy");
		new DestroyEffect(x, y).action();
	}
	
	@Override
	public Color getTeamColor(){
		Color color = super.getTeamColor().cpy();
		if(!powerOn){
			color.set(color.r, color.g, color.b, POWER_OFF_ALPHA);
		}
		return color;
	}
	
	public Color getAuraColor(){
		Color color = super.getTeamColor().cpy();
		color.set(color.r, color.g, color.b, AURA_ALPHA);
		return color;
	}
}
