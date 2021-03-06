package me.priezt.crossfire;

import com.badlogic.gdx.InputProcessor;

public abstract class GameView{
	
	public abstract void draw(Drawing drawing);
	public abstract void update();
	
	public void touchDragged(float x, float y, float originX, float originY){}
	public void touchUp(float x, float y, float originX, float originY){}
}
