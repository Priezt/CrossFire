package me.priezt.crossfire;

import java.util.logging.Level;
import java.util.logging.Logger;

import me.priezt.crossfire.Unit.Team;

public class Tool {
	public static float ZERO_ANGLE_RANGE = 0.5f;
	
	public static void info(String msg){
		Logger.getGlobal().log(Level.INFO, msg);
	}
	
	public static Point getBorderIntersectionPoint(float x, float y, float ang){
		
		float angle = (ang % 360f);
		if(angle <= 0) angle += 360;
		if(angle <= ZERO_ANGLE_RANGE || angle >= 360f - ZERO_ANGLE_RANGE) return new Point(x, Conf.screenHeight);
		if(Math.abs(angle - 180f) <= ZERO_ANGLE_RANGE) return new Point(x, 0f);
		if(Math.abs(angle - 90f) <= ZERO_ANGLE_RANGE) return new Point(Conf.screenWidth, y);
		if(Math.abs(angle - 270f) <= ZERO_ANGLE_RANGE) return new Point(0f, y);
		if(angle > 0 && angle < 90f){
			float intersectX = (float)((Conf.screenHeight - y) * Math.tan(Math.toRadians(angle)) + x);
			float intersectY = (float)((Conf.screenWidth - x) * Math.tan(Math.toRadians(90 - angle))) + y;
			if(intersectX > Conf.screenWidth){
				return new Point(Conf.screenWidth, intersectY);
			}else{
				return new Point(intersectX, Conf.screenHeight);
			}
		}else if(angle > 90f && angle < 180f){
			float intersectX = (float)(y * Math.tan(Math.toRadians(180 - angle)) + x);
			float intersectY = (float)(y - (Conf.screenWidth - x) * Math.tan(Math.toRadians(angle - 90)));
			if(intersectX > Conf.screenWidth){
				return new Point(Conf.screenWidth, intersectY);
			}else{
				return new Point(intersectX, 0f);
			}
		}else if(angle > 180f && angle < 270f){
			float intersectX = (float)(x - y * Math.tan(Math.toRadians(angle - 180)));
			float intersectY = (float)(y - x * Math.tan(Math.toRadians(270 - angle)));
			if(intersectX < 0){
				return new Point(0f, intersectY);
			}else{
				return new Point(intersectX, 0f);
			}
		}else if(angle > 270f && angle < 360f){
			float intersectX = (float)(x - (Conf.screenHeight - y) * Math.tan(Math.toRadians(360f - angle)));
			float intersectY = (float)(x * Math.tan(Math.toRadians(angle - 270f))) + y;
			if(intersectX < 0){
				return new Point(0f, intersectY);
			}else{
				return new Point(intersectX, Conf.screenHeight);
			}
		}
		Tool.info("get intersection point: impossible situation");
		return null;
	}
	
	public static float getAngle(float xd, float yd){
		float ang = (float)Math.toDegrees(Math.atan2(yd, xd));
		return 90f - ang;
	}
	
	// Point: (-1, -1) : (1, 1)
	public static Point getAbsolutePointByRelatedPoint(float rx, float ry, Unit.Team team){
		float x = Conf.screenWidth * 0.5f * (1 + rx);
		float y = Conf.screenHeight * 0.25f * (1 + ry);
		if(team == Team.RED){
			return new Point(x, y);
		}else if(team == Team.BLUE){
			return new Point(Conf.screenWidth - x, Conf.screenHeight - y);
		}
		return null;
	}
}
