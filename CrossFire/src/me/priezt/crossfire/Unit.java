package me.priezt.crossfire;

import com.badlogic.gdx.graphics.Color;

public abstract class Unit {
	public static enum Team { RED, BLUE, NEUTRAL, HOSTILE };
	public static Battleground battleground;
	
	public Team team = Team.RED; 
	public float x = 0f;
	public float y = 0f;
	public float radius = 50f;
	public float angle = 0f;
	public float hitpoint = 100f;
	public boolean alive = true;
	
	public Unit(float _x, float _y, float _angle, Team _team){
		team = _team;
		x = _x;
		y = _y;
		angle = _angle;
	}
	
	public Point getPointByRadiusAndAngle(float rds, float ang){
		return new Point((float)(x + rds * Math.sin(Math.toRadians(angle + ang))), (float)(y + rds * Math.cos(Math.toRadians(angle + ang))));
	}
	
	public boolean containsPoint(float px, float py){
		if(Math.abs(px - x) > radius) return false; 
		if(Math.abs(py - y) > radius) return false;
		if(Math.pow(px - x, 2) + Math.pow(py - y, 2) > Math.pow(radius, 2)) return false;
		return true;
	}
	
	public Color getTeamColor(){
		return getColorByTeam(team);
	}
	
	public static Color getColorByTeam(Team t){
		if(t.equals(Team.RED)){
			return Color.RED;
		}else if(t.equals(Team.BLUE)){
			return Color.BLUE;
		}else if(t.equals(Team.NEUTRAL)){
			return Color.ORANGE;
		}else if(t.equals(Team.HOSTILE)){
			return Color.WHITE;
		}
		return Color.BLACK;
	}
	
	public abstract void draw(Drawing drawing);
	
	public void tick(){
		
	}
	
	public void destroy(){
		alive = false;
	}
	
	public float xpart(){
		return (float)Math.sin(Math.toRadians(angle));
	}
	
	public float ypart(){
		return (float)Math.cos(Math.toRadians(angle));
	}
	
	public boolean isHittable(Unit targetUnit){
		if(team == Team.NEUTRAL || targetUnit.team == Team.NEUTRAL) return false;
		if(team == Team.HOSTILE|| targetUnit.team == Team.HOSTILE) return true;
		if(team == targetUnit.team) return false;
		return true;
	}
	
	public void takeDamage(float damage){
		hitpoint -= damage;
		if(hitpoint <= 0){
			destroy();
		}
	}
}
