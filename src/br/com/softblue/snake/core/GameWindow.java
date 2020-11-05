package br.com.softblue.snake.core;

import static br.com.softblue.snake.util.Constants.WINDOW_HEIGTH;
import static br.com.softblue.snake.util.Constants.WINDOW_WIDTH;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import br.com.softblue.snake.graphics.Renderer;
import br.com.softblue.snake.scene.Snake;
import br.com.softblue.snake.util.Constants;

@SuppressWarnings("serial")
public class GameWindow extends JFrame implements KeyListener {
	private Image buffer;
	private Graphics gImage;
	private Snake snake;
	private Renderer renderer;
	private Rectangle drawingArea;
	private long lastKeyboardEventTime;

	public GameWindow(Snake snake) {
		setSize(WINDOW_WIDTH, WINDOW_HEIGTH);
		setResizable(false);
		setTitle(Constants.WINDOW_TITLE);
		setVisible(true);
		addKeyListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		this.snake = snake;
		buffer = createImage(WINDOW_WIDTH, WINDOW_HEIGTH);
		gImage = buffer.getGraphics();
		renderer = new Renderer(gImage);
		
		defineDrawingArea();
	}
	
	private void defineDrawingArea() {
		int upperY = (int) (WINDOW_HEIGTH - getContentPane().getSize().getHeight());
		drawingArea = new Rectangle(0, upperY, WINDOW_WIDTH, WINDOW_HEIGTH - upperY);
	}
	
	public Rectangle getDrawingArea() {
		return drawingArea;
	}
	
	@Override
	public void paint(Graphics gScreen) {
		if (gImage == null || renderer == null) {
			return;
		}
		
		renderer.render();
		gScreen.drawImage(buffer, 0, 0, null);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		long now = System.currentTimeMillis();
		
		if (now - lastKeyboardEventTime < 40) {
			return;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			snake.up();
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			snake.down();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			snake.left();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			snake.right();
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		
		lastKeyboardEventTime = now;
	}
	
	public Renderer getRenderer() {
		return renderer;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
