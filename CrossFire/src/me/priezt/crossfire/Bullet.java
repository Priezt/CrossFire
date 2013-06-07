package me.priezt.crossfire;

public abstract class Bullet extends Unit {
	public float vx = 0f;
	public float vy = 0f;
	
	public Bullet(float _x, float _y, float _angle, Team _team) {
		super(_x, _y, _angle, _team);
		radius = 10f;
	}

	@Override
	public void draw(Drawing drawing) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void tick(){
		doMove();
		if(checkOutsideBoard()) return;
		if(checkCollision()) return;
	}
	
	public void doMove(){
		x += vx;
		y += vy;
	}
	
	public boolean checkCollision(){
		return false;
	}
	
	public boolean checkOutsideBoard(){
		if(x < 0) {
			destroy();
			return true;
		}
		if(x > Conf.screenWidth) {
			destroy();
			return true;
		}
		if(y < 0) {
			destroy();
			return true;
		}
		if(y > Conf.screenHeight) {
			destroy();
			return true;
		}
		return false;
	}
}
