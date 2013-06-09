package me.priezt.crossfire;

import com.badlogic.gdx.graphics.Color;

public class HitEffect extends Effect {

	public HitEffect(float _x, float _y) {
		super(_x, _y);
		lifespan = 6;
	}

	@Override
	public void draw(Drawing drawing) {
		float circleRadius = 12f;
		if(counter < 2) circleRadius = 3f;
		else if(counter < 4) circleRadius = 6f;
		else if(counter < 6) circleRadius = 9f;
		drawing.circle(x, y, circleRadius, EFFECT_COLOR);
	}

}
