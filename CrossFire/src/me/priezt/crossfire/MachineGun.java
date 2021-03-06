package me.priezt.crossfire;

public class MachineGun extends AimableTurret {

	public MachineGun(float _x, float _y, float _angle, Team _team) {
		super(_x, _y, _angle, _team);
		radius = 30f;
		actionCost = 2f;
	}
	
	@Override
	public void drawBody(Drawing drawing){
		drawing.circle(x, y, radius, getTeamColor());
		drawing.line(getPointByRadiusAndAngle(radius, 10f), getPointByRadiusAndAngle(radius * 1.5f, 0f), getTeamColor());
		drawing.line(getPointByRadiusAndAngle(radius, -10f), getPointByRadiusAndAngle(radius * 1.5f, 0f), getTeamColor());
	}
	
	@Override
	public void tick(){
		if((battleground.counter % 10 == 0) && powerOn){
			if(cost()) fire(new NormalBullet(), 0f);
		}
	}
}
