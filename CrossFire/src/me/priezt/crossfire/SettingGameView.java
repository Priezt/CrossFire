package me.priezt.crossfire;

public class SettingGameView extends GameView {
	public Battleground battleground;
	
	public SettingGameView(){
		battleground = new Battleground();
	}
	
	@Override
	public void draw(Drawing drawing) {
		// TODO Auto-generated method stub
		battleground.drawCenterSplitLine(drawing);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
