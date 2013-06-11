package me.priezt.crossfire;

public class NormalBullet extends Bullet {

	public NormalBullet(){
		super();
		setSpeed(10f);
		radius = 5f;
		hitDamage = 2f;
	}

	@Override
	public void draw(Drawing drawing) {
		// TODO Auto-generated method stub
		drawing.line(this.getPointByRadiusAndAngle(radius, 0f), this.getPointByRadiusAndAngle(radius, 180f), getTeamColor());
	}

}
