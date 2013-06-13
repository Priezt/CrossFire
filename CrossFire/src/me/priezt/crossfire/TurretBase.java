package me.priezt.crossfire;

import java.util.ArrayList;

import me.priezt.crossfire.Unit.Team;

public class TurretBase {
	public static ArrayList<Turret> turrets;
	
	public static ArrayList<Turret> getAllTurrets(){
		if(turrets == null) initTurretList();
		return turrets;
	}
	
	public static void initTurretList(){
		turrets = new ArrayList<Turret>();
		turrets.add(new MachineGun(0, 0, 0, Team.NEUTRAL));
		turrets.add(new Cerberus(0, 0, 0, Team.NEUTRAL));
		turrets.add(new SlowDown(0, 0, 0, Team.NEUTRAL));
		turrets.add(new Jammer(0, 0, 0, Team.NEUTRAL));
	}
}
