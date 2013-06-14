package me.priezt.crossfire;

public class Rocket extends Bullet {
	public Rocket(){
		super();
		setSpeed(5f);
		radius = 15f;
		hitDamage = 20f;
		hitpoint = 30f;
		weight = 3f;
	}
	
	@Override
	public void draw(Drawing drawing) {
		drawing.line(getPointByRadiusAndAngle(radius, 0f), getPointByRadiusAndAngle(radius, 160f), getTeamColor());
		drawing.line(getPointByRadiusAndAngle(radius, 0f), getPointByRadiusAndAngle(radius, 200f), getTeamColor());
		drawing.line(getPointByRadiusAndAngle(radius, 160f), getPointByRadiusAndAngle(radius, 200f), getTeamColor());
	}

}
