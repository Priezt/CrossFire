package me.priezt.crossfire;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Tool {
	public static void info(String msg){
		Logger.getGlobal().log(Level.INFO, msg);
	}
}
