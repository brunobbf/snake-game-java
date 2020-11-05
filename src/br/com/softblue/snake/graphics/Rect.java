package br.com.softblue.snake.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Rect extends Drawable {
	private Rectangle rect;
	
	public Rect() {
		this.rect = new Rectangle(0,0);
	}
	
	public Rect(Point location, Dimension dimension) {
		this.rect = new Rectangle(location, dimension);
	}
	
	public Rect(int x, int y, int width, int height) {
		this.rect = new Rectangle(x, y, width, height);
	}
	
	public Point getLocation() {
		return rect.getLocation();
	}
	
	public void setLocation(Point location) {
		rect.setLocation(location);
	}

	public Dimension getDimension() {
		return rect.getSize();
	}
	
	public void setDimension(Dimension dimension) {
		rect.setSize(dimension);
	}
	
	public boolean intersects(Rect other) {
		return rect.intersects(other.rect);
	}
	
	@Override
	public void draw(Graphics g) {
		g.fillRect(
				(int) rect.getLocation().getX(), 
				(int) rect.getLocation().getY(), 
				(int) rect.getSize().getWidth(), 
				(int) rect.getSize().getHeight()
		);
	}
}
