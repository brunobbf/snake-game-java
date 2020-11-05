package br.com.softblue.snake.scene;

import static br.com.softblue.snake.core.Direction.NONE;
import static br.com.softblue.snake.util.Constants.SNAKE_COLOR;
import static br.com.softblue.snake.util.Constants.SNAKE_ELONGATE_PIECES;
import static br.com.softblue.snake.util.Constants.SNAKE_INITIAL_SIZE;
import static br.com.softblue.snake.util.Constants.SNAKE_PIECE_SIZE;
import static br.com.softblue.snake.util.Constants.SNAKE_START_X;
import static br.com.softblue.snake.util.Constants.SNAKE_START_Y;

import java.awt.Dimension;
import java.awt.Point;

import br.com.softblue.snake.core.Direction;
import br.com.softblue.snake.graphics.Rect;
import br.com.softblue.snake.graphics.Shape;
import br.com.softblue.snake.util.GameUtils;

public class Snake extends Shape {
	
	private Direction direction;
	private int piecesToElongate;

	public Snake() {
		super(SNAKE_COLOR);
		direction = NONE;

		Point location = new Point(SNAKE_START_X, SNAKE_START_Y);
		Dimension dimension = new Dimension(SNAKE_PIECE_SIZE, SNAKE_PIECE_SIZE);
		
		Rect rect = new Rect(location, dimension);
		addRect(rect);

		for (int i = 1; i < SNAKE_INITIAL_SIZE; i++) {
			rect = duplicateRect(rect, Direction.LEFT);
			addRect(rect);
		}
	}
	
	public void move() {
		if (direction != NONE) {
			Rect head = getFirstRect();
			Rect tail = getLastRect();
			GameUtils.moveRects(getRects());
			
			Rect newHead = duplicateRect(head, direction);
			getRects().set(0, newHead);
			
			if (piecesToElongate > 0) {
				getRects().add(tail);
				piecesToElongate--;
			}
		}
	}

	public synchronized void left() {
		if (direction.canChangeTo(Direction.LEFT)) {
			direction = Direction.LEFT;
		}
	}

	public synchronized void right() {
		if (direction.canChangeTo(Direction.RIGHT)) {
			direction = Direction.RIGHT;
		}
	}

	public synchronized void up() {
		if (direction.canChangeTo(Direction.UP)) {
			direction = Direction.UP;
		}
	}

	public synchronized void down() {
		if (direction.canChangeTo(Direction.DOWN)) {
			direction = Direction.DOWN;
		}
	}
	
	public void elongate() {
		piecesToElongate = SNAKE_ELONGATE_PIECES;
	}
	
	public boolean collidesWithItself() {
		Rect head = getFirstRect();
		
		for (int i = 1; i < getRects().size(); i++) {
			if (head.intersects(getRects().get(i))) {
				return true;
			}
		}
		
		return false;
	}
}
