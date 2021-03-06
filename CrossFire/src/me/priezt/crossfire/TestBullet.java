package me.priezt.crossfire;

public class TestBullet extends Bullet {

	public TestBullet(float _x, float _y, float _angle, Team _team) {
		super();
		setSpeed(10f);
		radius = 5f;
	}

	@Override
	public void draw(Drawing drawing) {
//		drawing.circle(x, y, radius, getTeamColor());
		drawing.line(this.getPointByRadiusAndAngle(radius, 0f), this.getPointByRadiusAndAngle(radius, 180f), getTeamColor());
	}
}
