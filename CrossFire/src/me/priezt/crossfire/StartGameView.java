package me.priezt.crossfire;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;

public class StartGameView extends GameView {
	Stage stage;
	
	public StartGameView(){
		stage = new Stage();
	}
	
	@Override
	public void draw(Drawing drawing) {
		// TODO Auto-generated method stub
		stage.draw();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
