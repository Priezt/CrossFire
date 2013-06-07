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
	private final static String TAG = CrossFireGame.class.getSimpleName();
	
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
		drawing = new Drawing();
	}
	
	private void initTestStuff(){
		battleground = new Battleground();
		currentGameView = new BattleGameView(battleground);
		battleground.addUnit(new MachineGun(250f, 150f, 90f, Unit.Team.RED));
		battleground.addUnit(new MachineGun(400f, 800f, 0, Unit.Team.BLUE));
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
		initTestStuff();
		initInputProcessor();
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
//		drawing.line(0f, 0f, Conf.screenWidth, Conf.screenHeight, Color.RED);
//		drawing.circle(100f, 100f, 50f, Color.BLUE);
		currentGameView.draw(drawing);
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
