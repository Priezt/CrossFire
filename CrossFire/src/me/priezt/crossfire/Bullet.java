package me.priezt.crossfire;

public abstract class Bullet extends Unit {
	public float vx = 0f;
	public float vy = 0f;
	public float speed = 0f;
	public float hitDamage = 2f;
	
	public Bullet(float _x, float _y, float _angle, Team _team) {
		super(_x, _y, _angle, _team);
		radius = 10f;
		hitpoint = 5f;
	}

	@Override
	public abstract void draw(Drawing drawing);
	
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
		Unit targetUnit = battleground.getUnitAtPoint(x, y);
		if(targetUnit == null) return false;
		if(! isHittable(targetUnit)) return false;
		causeDamage(targetUnit, hitDamage);
		destroy();
		return true;
	}
	
	public void causeDamage(Unit targetUnit, float damage){
		targetUnit.takeDamage(damage);
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
	
	public void setSpeed(float newSpeed){
		speed = newSpeed;
		updateVelocity();
	}
	
	public void updateVelocity(){
		vx = speed * xpart();
		vy = speed * ypart();
	}
	
	public void setAngle(float newAngle){
		angle = newAngle;
		updateVelocity();
	}
}
