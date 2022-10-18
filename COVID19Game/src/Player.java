package COVID19;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Player {
	// 플레이어 고유 속성
	int x, y, r;
	int size;

	// 이미지를 넣기 위한 변수
	Image image;
	Toolkit tool;

	// 플레이어 움직임 속성
	boolean up, down, left, right;
	double speed_y, speed_x;
	double speed;
	double playerSpeed = 0.5;

	public Player(int x, int y, int speed, int size) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.size = size;
		this.r = size / 2;

		up = false;
		down = false;
		left = false;
		right = false;
		speed_y = 0;
		speed_x = 0;

		tool = Toolkit.getDefaultToolkit();
		image = tool.getImage("background/player.png");

	}

	// 플레이어를 그리는 draw 함수
	public void draw(Graphics g) {
		g.drawImage(image, x, y, size, size, null);
	}

	public void update() {
	}
}
