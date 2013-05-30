package me.priezt.crossfire;

import com.badlogic.gdx.graphics.Color;

public abstract class Unit {
	public static enum Team { RED, BLUE, NEUTRAL, HOSTILE };
	
	public Team team = Team.RED; 
	public float x = 0f;
	public float y = 0f;
	public float radius = 50f;
	public float angle = 0f;
	
	public Unit(float _x, float _y, float _angle, Team _team){
		team = _team;
		x = _x;
		y = _y;
		angle = _angle;
	}
	
	public static Color getTeamColor(Team t){
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
}
