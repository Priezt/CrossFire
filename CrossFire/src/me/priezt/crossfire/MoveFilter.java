package me.priezt.crossfire;

public abstract class MoveFilter {
	public abstract Point filter(Point targetPoint, Bullet bullet);
}
