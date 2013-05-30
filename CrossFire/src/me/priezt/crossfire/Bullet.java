package me.priezt.crossfire;

public class Bullet extends Unit {

	public Bullet(float _x, float _y, float _angle, Team _team) {
		super(_x, _y, _angle, _team);
		radius = 10f;
	}

	@Override
	public void draw(Drawing drawing) {
		// TODO Auto-generated method stub

	}

}
