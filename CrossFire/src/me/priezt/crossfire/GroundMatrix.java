package me.priezt.crossfire;

import java.util.ArrayList;

public class GroundMatrix {
	public static float GRID_WIDTH = 50f;
	private ArrayList[][] matrix;
	private int width;
	private int height;
	
	public int getMinX(float x, float y, float radius){
		int result = (int)Math.floor((x - radius) / GRID_WIDTH);
		if(result < 0) result = 0;
		return result;
	}
	
	public int getMaxX(float x, float y, float radius){
		int result = (int)Math.floor((x + radius) / GRID_WIDTH);
		if(result >= width) result = width - 1;
		return result;
	}
	
	public int getMinY(float x, float y, float radius){
		int result = (int)Math.floor((y - radius) / GRID_WIDTH);
		if(result < 0) result = 0;
		return result;
	}
	
	public int getMaxY(float x, float y, float radius){
		int result = (int)Math.floor((y + radius) / GRID_WIDTH);
		if(result >= height) result = height - 1;
		return result;
	}
	
	public GroundMatrix(float w, float h){
		width = (int)Math.ceil(w / GRID_WIDTH);
		height = (int)Math.ceil(h / GRID_WIDTH);
		Tool.info("Matrix: (" + width + "," + height + ")");
		matrix = new ArrayList[width][];
		for(int i=0;i<width;i++){
			matrix[i] = new ArrayList[height];
			for(int j=0;j<height;j++){
				matrix[i][j] = new ArrayList();
			}
		}
	}
	
	public void add(Object obj, float x, float y, float radius){
		int minX = getMinX(x, y, radius);
		int maxX = getMaxX(x, y, radius);
		int minY = getMinY(x, y, radius);
		int maxY = getMaxY(x, y, radius);
//		Tool.info("add object in [" + minX + "][" + minY + "], [" + maxX + "][" + maxY + "] : " + obj);
		for(int i=minX; i<=maxX; i++){
			for(int j=minY; j<=maxY; j++){
				matrix[i][j].add(obj);
			}
		}
	}
	
	public void remove(Object obj, float x, float y, float radius){
		int minX = getMinX(x, y, radius);
		int maxX = getMaxX(x, y, radius);
		int minY = getMinY(x, y, radius);
		int maxY = getMaxY(x, y, radius);
//		Tool.info("remove object from [" + minX + "][" + minY + "], [" + maxX + "][" + maxY + "] : " + obj);
		for(int i=minX; i<=maxX; i++){
			for(int j=minY; j<=maxY; j++){
				matrix[i][j].remove(obj);
			}
		}
	}
	
	public ArrayList getObjectsInRange(float x, float y, float radius){
		ArrayList resultArrayList = new ArrayList();
		int minX = getMinX(x, y, radius);
		int maxX = getMaxX(x, y, radius);
		int minY = getMinY(x, y, radius);
		int maxY = getMaxY(x, y, radius);
//		Tool.info("search object in [" + minX + "][" + minY + "], [" + maxX + "][" + maxY + "]");
		for(int i=minX; i<=maxX; i++){
			for(int j=minY; j<=maxY; j++){
				resultArrayList.addAll(matrix[i][j]);
			}
		}
		return resultArrayList;
	}
}
