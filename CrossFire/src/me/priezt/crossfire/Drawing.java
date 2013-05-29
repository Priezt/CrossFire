package me.priezt.crossfire;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Drawing {
	private ShapeRenderer lineRenderer;
	private ShapeRenderer circleRenderer;
	
	public Drawing(){
		lineRenderer = new ShapeRenderer();
		circleRenderer = new ShapeRenderer();
	}
	
	public void begin(){
		lineRenderer.begin(ShapeType.Line);
		circleRenderer.begin(ShapeType.Circle);
	}
	
	public void end(){
		lineRenderer.end();
		circleRenderer.end();
	}
	
	public void line(float x1, float y1, float x2, float y2, Color color){
		lineRenderer.setColor(color);
		lineRenderer.line(x1, y1, x2, y2);
	}
	
	public void circle(float x, float y, float radius, Color color){
		circleRenderer.setColor(color);
		circleRenderer.circle(x, y, radius);
	}
}
