package me.priezt.crossfire;

public class MachineGun extends AimableTurret {

	public MachineGun(float _x, float _y, float _angle, Team _team) {
		super(_x, _y, _angle, _team);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void drawBody(Drawing drawing){
		drawing.circle(x, y, radius, getTeamColor());
		drawing.line(getPointByRadiusAndAngle(radius, 10f), getPointByRadiusAndAngle(radius * 1.5f, 0f), getTeamColor());
		drawing.line(getPointByRadiusAndAngle(radius, -10f), getPointByRadiusAndAngle(radius * 1.5f, 0f), getTeamColor());
	}
	
	@Override
	public void tick(){
//		Tool.info("counter: " + battleground.counter);
		if((battleground.counter % 10 == 0) && powerOn){
//			Tool.info("fire");
			fire(new TestBullet(0f, 0f, 0f, team), 0f);
		}
	}
}
