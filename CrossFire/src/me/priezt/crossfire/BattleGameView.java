package me.priezt.crossfire;

public class BattleGameView extends GameView {
	private Battleground battleground;
	
	public BattleGameView(Battleground bg){
		battleground = bg;
	}
	
	@Override
	public void draw(Drawing drawing) {
		for(Unit unit : battleground.units){
			unit.draw(drawing);
		}
	}
}
