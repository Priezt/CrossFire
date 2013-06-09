package me.priezt.crossfire;

import com.badlogic.gdx.graphics.Color;

public abstract class Effect {
	public int counter = 0;
	public int lifespan = 10;
	public float x;
	public float y;
	public static Battleground battleground;
	public static Color EFFECT_COLOR = Color.YELLOW;
	
	public Effect(float _x, float _y){
		x = _x;
		y = _y;
		lifespan = 10;
	}
	
	public abstract void draw(Drawing drawing);
	
	public void tick(){
		counter++;
		if(counter > lifespan){
			cut();
		}
	}
	
	public void action(){
		battleground.effects.add(this);
	}
	
	public void cut(){
		battleground.effects.remove(this);
	}
}
