package COVID19;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Virus {
	// ���̷��� ���� �Ӽ�
	int x, y, r;
	int size;

	// �̹����� �ֱ� ���� ����
	Image image;
	Toolkit tool;

	// ���̷��� ������ �Ӽ�
	boolean up, down, left, right;
	boolean isVertex;
	int speed_y, speed_x;
	int speed;

	public Virus(int x, int y, int speed, boolean isVertex, int size) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.size = size;
		this.r = size / 2;
		this.isVertex = isVertex;

		up = false;
		down = false;
		left = false;
		right = false;
		speed_y = 0;
		speed_x = 0;
		tool = Toolkit.getDefaultToolkit();
		image = tool.getImage("virus/6.png");
	}

	public void draw(Graphics g) {
		g.drawImage(image, x, y, 2 * r, 2 * r, null);
	}

	// ���̷����� �����̰� ��ǥ ���� �ٲ��ִ� �Լ�
	public void update() {
		if (isVertex == false)
			x += speed;
		else
			y += speed;
	}

}
