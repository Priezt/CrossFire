package me.priezt.crossfire;

import java.util.ArrayList;

public abstract class Job<Obj> {
	public abstract void process(Obj obj);
	public void each(ArrayList<Obj> objs){
		for(Obj obj : objs){
			process(obj);
		}
	}
}
