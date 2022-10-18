package COVID19;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Item {
	// 아이템 고유 속성
	int x, y, r;
	int size;

	// 이미지를 넣기 위한 변수
	Image image;
	Toolkit tool;

	// 아이템 움직임 속성
	boolean up, down, left, right;
	boolean isVertex;
	int speed_y, speed_x;
	int speed;
	int itemType; // 아이템의 종류를 알려주는 변수

	public Item(int x, int y, int speed, boolean isVertex, int size, int itemType) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.size = size;
		this.r = size / 2;
		this.isVertex = isVertex;
		this.itemType = itemType;

		up = false;
		down = false;
		left = false;
		right = false;
		speed_y = 0;
		speed_x = 0;

		tool = Toolkit.getDefaultToolkit();
		if (itemType == 0)
			image = tool.getImage("items/item1.png");
		else if (itemType == 1)
			image = tool.getImage("items/item2.png");
		else if (itemType == 2)
			image = tool.getImage("items/item3.png");
		else
			image = tool.getImage("items/item4.png");

	}

	public void draw(Graphics g) {
		g.drawImage(image, x, y, 2 * r, 2 * r, null);
	}

	public void update() {
		if (isVertex == false)
			x += speed;
		else
			y += speed;
	}

}
