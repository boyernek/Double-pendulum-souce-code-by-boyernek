import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import bjars.engine.Game;

public class Main extends Game {

	private static final long serialVersionUID = -3867110997146146416L;
	public static final int WIDTH = 1920, HEIGHT = 1080;
	public LinkedList<DoublePendulum> doublePendulums;
	public DoublePendulumDraw doublePendulumDrawer;
	public static Main main;
	public BufferedImage background;
	public Graphics backgroundDraw;

	public Main(int w, int h, String t) {
		super(w, h, t);

		background = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		backgroundDraw = background.getGraphics();
		backgroundDraw.setColor(Color.white);
		backgroundDraw.fillRect(0, 0, WIDTH, HEIGHT);
	}

	public void init() {

		doublePendulums = new LinkedList<DoublePendulum>();
//		for (int i = 0; i < 4000; i++) {
//			
//			doublePendulums.add(new DoublePendulum(i * 0.001f, i * 0.0001f, i, 1000));
//		}
		doublePendulumDrawer = new DoublePendulumDraw(1, 1, 0, 1000);
	}

	public void tick() {

		for (int i = 0; i < doublePendulums.size(); i++) {

			doublePendulums.get(i).tick();
		}
		doublePendulumDrawer.tick();
	}

	public void render(Graphics g) {

		g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);

		Graphics2D g2d = (Graphics2D) g;

		g2d.translate(WIDTH / 2, 300);
		for (int i = 0; i < doublePendulums.size(); i++) {

			doublePendulums.get(i).render(g);
		}
		doublePendulumDrawer.render(g);
		g2d.translate(-WIDTH / 2, 300);
	}

	public static void main(String[] args) {

		main = new Main(WIDTH, HEIGHT, "Double Pendulum");
	}
}
