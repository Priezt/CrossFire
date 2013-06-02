package me.priezt.crossfire;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Drawing {
	private ShapeRenderer lineRenderer;
	private ShapeRenderer circleRenderer;
	private ArrayList<ShapeRenderer> renderers;
	
	public Drawing(){
		lineRenderer = new ShapeRenderer();
		circleRenderer = new ShapeRenderer();
		renderers = new ArrayList<ShapeRenderer>();
		renderers.add(lineRenderer);
		renderers.add(circleRenderer);
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
	
	public void line(Point p1, Point p2, Color color){
		line(p1.x, p1.y, p2.x, p2.y, color);
	}
	
	public void circle(float x, float y, float radius, Color color){
		circleRenderer.setColor(color);
		circleRenderer.circle(x, y, radius);
	}
}
