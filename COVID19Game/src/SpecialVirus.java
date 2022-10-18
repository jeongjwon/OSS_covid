package COVID19;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class SpecialVirus {
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
	int virusType; // 바이러스 종류

	// 바이러스를 일정 시간 간격으로 움직이게 할 변수
	int countFlag = 1;
	int count = 0;

	public SpecialVirus(int x, int y, int speed, boolean isVertex, int size, int virusType) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.size = size;
		this.r = size / 2;
		this.isVertex = isVertex;
		this.virusType = virusType;

		up = false;
		down = false;
		left = false;
		right = false;
		speed_y = 0;
		speed_x = 0;
		tool = Toolkit.getDefaultToolkit();

		if (virusType == 0)
			image = tool.getImage("virus/7.png");
		else if (virusType == 1)
			image = tool.getImage("virus/9.png");
		else
			image = tool.getImage("virus/8.png");

	}

	public void draw(Graphics g) {
		g.drawImage(image, x, y, 2 * r, 2 * r, null);
	}

	public void update() {
		// 함수 호출마다 count를 증가시킨다.
		count++;

		// 바이러스 타입에 따른 효과 부여
		switch (virusType) {

		// count가 30의 배수가 될 때마다 방향 전환을 함
		case (0):
			if (count % 30 == 0)
				countFlag *= -1;

			if (countFlag == -1) {
				// 수평으로 움직이는 바이러스일 경우
				if (isVertex == false) {
					x += speed;
					y -= speed;
				} else {
					x -= speed;
					y += speed;
				}
			} else {
				// 수평으로 움직이는 바이러스일 경우
				if (isVertex == false) {
					x += speed;
					y += speed;
				} else {
					x += speed;
					y += speed;
				}
			}
			break;

		// count가 60이 되면 크기가 3배로 커짐
		case (1):
			if (isVertex == false)
				x += speed;
			else
				y += speed;

			if (count == 60) {
				r *= 3;
				size *= 3;
			}
			break;
		// count가 50이 되면 속도가 3배로 증가함
		case (2):
			if (count % 50 == 0)
				speed *= 3;

			if (isVertex == false)
				x += speed;
			else
				y += speed;
			break;
		}
	}

}
