package me.priezt.crossfire;

public class DestroyEffect extends Effect {
	public static int PIECE_NUMBER = 10;
	public DestroyEffect(float _x, float _y) {
		super(_x, _y);
		lifespan = 15;
	}

	@Override
	public void draw(Drawing drawing) {
		float fromRadius = counter * 4;
		float toRadius = (counter + 5) * 4;
		for(int i=0; i<PIECE_NUMBER; i++){
			float ang = (360f / PIECE_NUMBER) * i;
			Point startPoint = new Point((float)(x + fromRadius * Math.sin(Math.toRadians(ang))), (float)(y + fromRadius * Math.cos(Math.toRadians(ang))));
			Point endPoint = new Point((float)(x + toRadius * Math.sin(Math.toRadians(ang))), (float)(y + toRadius * Math.cos(Math.toRadians(ang))));
			drawing.line(startPoint, endPoint, EFFECT_COLOR);
		}
	}

}
