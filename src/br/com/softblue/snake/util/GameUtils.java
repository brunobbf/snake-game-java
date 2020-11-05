package br.com.softblue.snake.util;

import static br.com.softblue.snake.util.Constants.SLEEP_TIME;

import java.util.List;

import br.com.softblue.snake.graphics.Food;
import br.com.softblue.snake.graphics.Rect;

public class GameUtils {

	public static void sleep(int millis) {
		try {
			Thread.sleep(SLEEP_TIME);
		} catch(InterruptedException e) {
		}
	}
	
	public static void moveRects(List<Rect> rects) {
		for (int i = rects.size() - 1; i >= 1; i--) {
			rects.set(i, rects.get(i - 1));	
		}
	}
	
	public static int random(int min, int max) {
		return (int) (Math.random() * (max - min + 1)) + min;
	}
	
	public static void increaseSleepTime(Food food) {
		if (food.getEatenTimes() % 10 == 0) {
			SLEEP_TIME -= 5;
		}
	}
}
