package COVID19;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class SpecialVirus {
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
	int virusType; // ���̷��� ����

	// ���̷����� ���� �ð� �������� �����̰� �� ����
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
		// �Լ� ȣ�⸶�� count�� ������Ų��.
		count++;

		// ���̷��� Ÿ�Կ� ���� ȿ�� �ο�
		switch (virusType) {

		// count�� 30�� ����� �� ������ ���� ��ȯ�� ��
		case (0):
			if (count % 30 == 0)
				countFlag *= -1;

			if (countFlag == -1) {
				// �������� �����̴� ���̷����� ���
				if (isVertex == false) {
					x += speed;
					y -= speed;
				} else {
					x -= speed;
					y += speed;
				}
			} else {
				// �������� �����̴� ���̷����� ���
				if (isVertex == false) {
					x += speed;
					y += speed;
				} else {
					x += speed;
					y += speed;
				}
			}
			break;

		// count�� 60�� �Ǹ� ũ�Ⱑ 3��� Ŀ��
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
		// count�� 50�� �Ǹ� �ӵ��� 3��� ������
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
