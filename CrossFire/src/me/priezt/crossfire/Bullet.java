package me.priezt.crossfire;

import java.util.HashSet;
import java.util.Set;

public abstract class Bullet extends Unit {
	public float vx = 0f;
	public float vy = 0f;
	public float speed = 0f;
	public float hitDamage = 2f;
	public float weight = 1f;
	
	public HashSet<MoveFilter> moveFilter = new HashSet<MoveFilter>();
	public HashSet<TurnFilter> turnFilter = new HashSet<TurnFilter>();
	
	public Bullet(){
		super(0f, 0f, 0f, Team.NEUTRAL);
		radius = 10f;
		hitpoint = 5f;
	}

	@Override
	public abstract void draw(Drawing drawing);
	
	@Override
	public void tick(){
		doMove();
		doTurn();
		if(checkOutsideBoard()) return;
		if(checkCollision()) return;
	}
	
	public void doTurn(){
		float ta = angle;
		for(TurnFilter tf : turnFilter) ta = tf.filter(ta, this);
		setAngle(ta);
	}
	
	public void doMove(){
		Point tp = new Point(x + vx, y + vy);
		for(MoveFilter mf : moveFilter)	tp= mf.filter(tp, this);
		moveFilter = new HashSet<MoveFilter>();
		move(tp.x, tp.y);
	}
	
	public void move(float tx, float ty){
		battleground.groundMatrix.remove(this, x, y, radius);
		x = tx;
		y = ty;
		battleground.groundMatrix.add(this, x, y, radius);
	}
	
	public boolean checkCollision(){
		Unit targetUnit = battleground.getSomeUnitAtPointExcept(x, y, this);
		if(targetUnit == null) return false;
		if(! isHittable(targetUnit)) return false;
		causeDamage(targetUnit, hitDamage);
		destroy();
		new HitEffect(x, y).action();
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
