package COVID19;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Virus {
	// 바이러스 고유 속성
	int x, y, r;
	int size;

	// 이미지를 넣기 위한 변수
	Image image;
	Toolkit tool;

	// 바이러스 움직임 속성
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

	// 바이러스를 움직이게 좌표 값을 바꿔주는 함수
	public void update() {
		if (isVertex == false)
			x += speed;
		else
			y += speed;
	}

}
