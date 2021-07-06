import java.awt.Color;
import java.awt.Graphics;

import bjars.util.Console;

public class DoublePendulumDraw {

	float x1 = 0, x2 = 0, y1 = 0, y2 = 0;
	float m1 = 100, m2 = 200;
	float a1 = 0, a2 = 0;
	float l1 = 300, l2 = 300;
	float g = 1;
	float v1 = 0, v2 = 0, ac1 = 0, ac2 = 0;
	int i;
	int num;

	public DoublePendulumDraw(float theta1, float theta2, int i, int num) {
		
		a1 = theta1;
		a2 = theta2;
		this.i = i+1;
		this.num = num;
	}
	
	public void tick() {

		x1 = (float) (l1 * Math.sin(a1));
		y1 = (float) (l1 * Math.cos(a1));

		x2 = x1 + (float) (l2 * Math.sin(a2));
		y2 = y1 + (float) (l2 * Math.cos(a2));

		float anum1 = (float) (-g * (2 * m1 + m2) * Math.sin(a1));
		float anum2 = (float) (-m2 * g * Math.sin(a1 - 2 * a2));
		float anum3 = (float) (-2 * Math.sin(a1 - a2) * m2 * (v2 * v2 * l2 + v1 * v1 * l1 * Math.cos(a1 - a2)));
		float anum4 = (float) (l1 * (2 * m1 + m2 - m2 * Math.cos(2 * a1 - 2 * a2)));

		ac1 = (anum1 + anum2 + anum3) / anum4;

		float bnum1 = (float) (2 * Math.sin(a1 - a2)
				* (v1 * v1 * l1 * (m1 + m2) + g * (m1 + m2) * Math.cos(a1) + v2 * v2 * l2 * m2 * Math.cos(a1 - a2)));
		float bnum2 = (float) (l2 * (2 * m1 + m2 - m2 * Math.cos(2 * a1 - 2 * a2)));
		
		ac2 = bnum1 / bnum2;

		v1 += ac1;
		v2 += ac2;

		a1 += v1;
		a2 += v2;
	}

	public void render(Graphics g) {

		g.setColor(Color.red);
		g.drawLine(0, 0, (int) x1, (int) y1);
		g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
		Main.main.backgroundDraw.setColor(Color.red);
		Main.main.backgroundDraw.fillOval((int) x2 + Main.WIDTH / 2, (int) y2 + 300, 5, 5);
	}
}
