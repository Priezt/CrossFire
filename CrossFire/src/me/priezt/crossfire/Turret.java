package me.priezt.crossfire;

import com.badlogic.gdx.graphics.Color;

public abstract class Turret extends Unit {
	public boolean isAiming = false;
	public static Color AIMING_COLOR = Color.YELLOW;
	public static float BULLET_FIRE_DISTANCE = 5f;
	
	public boolean powerOn = false;
	
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
	
	public void fire(Bullet bullet, float relativeAngle){
		float absoluteAngle = angle + relativeAngle;
		Point bulletSpawnPoint = this.getPointByRadiusAndAngle(radius + bullet.radius + BULLET_FIRE_DISTANCE, relativeAngle);
		bullet.x = bulletSpawnPoint.x;
		bullet.y = bulletSpawnPoint.y;
		bullet.setAngle(absoluteAngle);
		battleground.addUnit(bullet);
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
		drawing.line(x - hitBarHalfLength, y, x + hitBarHalfLength, y, Color.GREEN);
	}
}
