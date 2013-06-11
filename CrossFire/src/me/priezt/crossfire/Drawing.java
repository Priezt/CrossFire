package me.priezt.crossfire;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Drawing {
	private ShapeRenderer lineRenderer;
	private ShapeRenderer circleRenderer;
	private ShapeRenderer fillCircleRenderer;
	private ShapeRenderer fillRectRenderer;
	private ArrayList<ShapeRenderer> renderers;
	
	public Drawing(){
		lineRenderer = new ShapeRenderer();
		circleRenderer = new ShapeRenderer();
		fillCircleRenderer = new ShapeRenderer();
		fillRectRenderer = new ShapeRenderer();
		renderers = new ArrayList<ShapeRenderer>();
		renderers.add(lineRenderer);
		renderers.add(circleRenderer);
		renderers.add(fillCircleRenderer);
		renderers.add(fillRectRenderer);
	}
	
	public void begin(){
		lineRenderer.begin(ShapeType.Line);
		circleRenderer.begin(ShapeType.Circle);
		fillCircleRenderer.begin(ShapeType.FilledCircle);
		fillRectRenderer.begin(ShapeType.FilledRectangle);
	}
	
	public void end(){
		lineRenderer.end();
		circleRenderer.end();
		fillCircleRenderer.end();
		fillRectRenderer.end();
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
	
	public void fillCircle(float x, float y, float radius, Color color){
		fillCircleRenderer.setColor(color);
		fillCircleRenderer.filledCircle(x, y, radius);
	}
	
	public void fillRect(float x, float y, float width, float height, Color color){
		fillRectRenderer.setColor(color);
		fillRectRenderer.filledRect(x, y, width, height);
	}
}
