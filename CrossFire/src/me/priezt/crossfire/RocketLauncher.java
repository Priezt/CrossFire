package me.priezt.crossfire;

public class RocketLauncher extends AimableTurret {

	public RocketLauncher(float _x, float _y, float _angle, Team _team) {
		super(_x, _y, _angle, _team);
		radius = 50f;
	}

	@Override
	public void drawBody(Drawing drawing) {
		drawing.circle(x, y, radius, getTeamColor());
		drawing.line(getPointByRadiusAndAngle(radius, 10f), getPointByRadiusAndAngle(radius * 1.5f, 8f), getTeamColor());
		drawing.line(getPointByRadiusAndAngle(radius, -10f), getPointByRadiusAndAngle(radius * 1.5f, -8f), getTeamColor());
		drawing.line(getPointByRadiusAndAngle(radius * 1.5f, 8f), getPointByRadiusAndAngle(radius * 1.5f, -8f), getTeamColor());
	}

	@Override
	public void tick(){
		if((battleground.counter % 80 == 0) && powerOn){
			fire(new Rocket(), 0f);
		}
	}
}
