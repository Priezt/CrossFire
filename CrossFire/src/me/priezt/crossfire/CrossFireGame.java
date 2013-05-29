package me.priezt.crossfire;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class CrossFireGame implements ApplicationListener {
	private final static String TAG = CrossFireGame.class.getSimpleName();
	
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	private ShapeRenderer shapeRenderer2;
	private Color backgroundColor = Color.GREEN;
	private Drawing drawing;
	
	private void initConf(){
		Conf.screenWidth = Gdx.graphics.getWidth();
		Conf.screenHeight = Gdx.graphics.getHeight();
		Tool.info("screenWidth: " + Conf.screenWidth);
		Tool.info("screenHeight: " + Conf.screenHeight);
	}
	
	private void initDrawingStuff(){
		// left bottom is (0, 0)
		drawing = new Drawing();
	}
	
	@Override
	public void create() {
		initConf();
		initDrawingStuff();
		camera = new OrthographicCamera(1, Conf.screenHeight/Conf.screenWidth);
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		drawing.begin();
		drawing.line(0f, 0f, Conf.screenWidth, Conf.screenHeight, Color.RED);
		drawing.circle(100f, 100f, 50f, Color.BLUE);
		drawing.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
