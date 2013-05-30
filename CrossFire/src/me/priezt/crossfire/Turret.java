package me.priezt.crossfire;

public class Turret extends Unit {

	public Turret(float _x, float _y, float _angle, Team _team) {
		super(_x, _y, _angle, _team);
		radius = 50f;
	}

	@Override
	public void draw(Drawing drawing) {
		drawing.circle(x, y, radius, getTeamColor(team));
		drawing.line(x, y, (float)(x + radius * Math.sin(Math.toRadians(angle))), (float)(y + radius * Math.cos(Math.toRadians(angle))), getTeamColor(team));
	}

}
