package me.priezt.crossfire;

public class TestBullet extends Bullet {

	public TestBullet(float _x, float _y, float _angle, Team _team) {
		super(_x, _y, _angle, _team);
		setSpeed(10f);
		radius = 5f;
	}

	@Override
	public void draw(Drawing drawing) {
		drawing.circle(x, y, radius, getTeamColor());
	}
}
