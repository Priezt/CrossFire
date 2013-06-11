package me.priezt.crossfire;

public class Cerberus extends AimableTurret {

	public Cerberus(float _x, float _y, float _angle, Team _team) {
		super(_x, _y, _angle, _team);
		radius = 40f;
		actionCost = 4f;
	}

	@Override
	public void drawBody(Drawing drawing) {
		drawing.circle(x, y, radius, getTeamColor());
		drawing.line(getPointByRadiusAndAngle(radius, 10f), getPointByRadiusAndAngle(radius * 1.5f, 0f), getTeamColor());
		drawing.line(getPointByRadiusAndAngle(radius, -10f), getPointByRadiusAndAngle(radius * 1.5f, 0f), getTeamColor());
		drawing.line(getPointByRadiusAndAngle(radius, 10f), getPointByRadiusAndAngle(radius * 1.5f, 20f), getTeamColor());
		drawing.line(getPointByRadiusAndAngle(radius, 30f), getPointByRadiusAndAngle(radius * 1.5f, 20f), getTeamColor());
		drawing.line(getPointByRadiusAndAngle(radius, -10f), getPointByRadiusAndAngle(radius * 1.5f, -20f), getTeamColor());
		drawing.line(getPointByRadiusAndAngle(radius, -30f), getPointByRadiusAndAngle(radius * 1.5f, -20f), getTeamColor());
	}

	@Override
	public void tick(){
		if((battleground.counter % 10 == 0) && powerOn){
			if(cost()){
				fire(new NormalBullet(), 0f);
				fire(new NormalBullet(), 20f);
				fire(new NormalBullet(), -20f);
			}
		}
	}
}
