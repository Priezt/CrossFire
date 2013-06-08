package me.priezt.crossfire;

import com.badlogic.gdx.graphics.Color;

public abstract class AimableTurret extends Turret {

	
	public AimableTurret(float _x, float _y, float _angle, Team _team) {
		super(_x, _y, _angle, _team);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isAimable(){
		return true;
	}
	
	@Override
	public boolean isClickable(){
		return true;
	}
}
