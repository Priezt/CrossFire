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
	
	@Override
	public void touchDragged(float x, float y, float originX, float originY){
		Tool.info("Drag: (" + (x - originX) + "," + (y - originY) + ")");
	}
	
	@Override
	public void touchUp(float x, float y, float originX, float originY){
		Tool.info("Click: (" + originX + "," + originY + ") -> (" + x + "," + y + ")");
	}
}
