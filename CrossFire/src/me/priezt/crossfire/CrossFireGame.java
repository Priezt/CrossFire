package me.priezt.crossfire;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
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
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

public class CrossFireGame implements ApplicationListener {
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	private ShapeRenderer shapeRenderer2;
	private Color backgroundColor = Color.GREEN;
	private Drawing drawing;
	private GameView currentGameView;
	private Battleground battleground;
	
	private void initConf(){
		Conf.screenWidth = Gdx.graphics.getWidth();
		Conf.screenHeight = Gdx.graphics.getHeight();
		Tool.info("screenWidth: " + Conf.screenWidth);
		Tool.info("screenHeight: " + Conf.screenHeight);
	}
	
	private void initDrawingStuff(){
		// left bottom is (0, 0)
		Gdx.gl10.glLineWidth(Conf.LINE_WIDTH);
		drawing = new Drawing();
	}
	
	private void initInputProcessor(){
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(new InputProcessor(){
			Point[] touchPoints = new Point[32];
			
			@Override
			public boolean keyDown(int keycode) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean keyUp(int keycode) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean keyTyped(char character) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer,
					int button) {
//				Tool.info("touchup: " + pointer + " : (" + screenX + "," + screenY + ")");
				touchPoints[pointer] = new Point(screenX, Conf.screenHeight - screenY);
				return false;
			}

			@Override
			public boolean touchUp(int screenX, int screenY, int pointer,
					int button) {
//				Tool.info("touchdown: " + pointer + " : (" + screenX + "," + screenY + ")");
				CrossFireGame.this.currentGameView.touchUp(
						screenX,
						Conf.screenHeight - screenY,
						touchPoints[pointer].x,
						touchPoints[pointer].y);
				touchPoints[pointer] = null;
				return false;
			}

			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
//				Tool.info("touchdragged: " + pointer + " : (" + screenX + "," + screenY + ")");
				CrossFireGame.this.currentGameView.touchDragged(
						screenX,
						Conf.screenHeight - screenY,
						touchPoints[pointer].x,
						touchPoints[pointer].y);
				return false;
			}

			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean scrolled(int amount) {
				// TODO Auto-generated method stub
				return false;
			}
			
		});
		multiplexer.addProcessor(new GestureDetector(new GestureListener(){

			@Override
			public boolean touchDown(float x, float y, int pointer, int button) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean tap(float x, float y, int count, int button) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean longPress(float x, float y) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean fling(float velocityX, float velocityY, int button) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean pan(float x, float y, float deltaX, float deltaY) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean zoom(float initialDistance, float distance) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean pinch(Vector2 initialPointer1,
					Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
				// TODO Auto-generated method stub
				return false;
			}
			
		}));
		Gdx.input.setInputProcessor(multiplexer);
	}
	
	@Override
	public void create() {
		initConf();
		initDrawingStuff();
		initInputProcessor();
		camera = new OrthographicCamera(1, Conf.screenHeight/Conf.screenWidth);
		currentGameView = new BattleGameView();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {
		update();
		Gdx.gl.glClearColor(Conf.backgroundColor.r, Conf.backgroundColor.g, Conf.backgroundColor.b, Conf.backgroundColor.a);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		Gdx.gl.glEnable(GL10.GL_BLEND);
		Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		drawing.begin();
//		drawing.line(0f, 0f, Conf.screenWidth, Conf.screenHeight, Color.RED);
//		drawing.circle(100f, 100f, 50f, Color.BLUE);
		currentGameView.draw(drawing);
		drawing.end();
		Gdx.gl.glDisable(GL10.GL_BLEND);
	}
	
	public void update(){
		currentGameView.update();
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
