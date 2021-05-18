package COVID19;
import java.awt.Color;
import java.awt.Graphics;


public class Virus {
	//바이러스 고유속성
	int x, y, w, h, r;
	int size;
	Color c;
	
	//바이러스 움직임 속성
	boolean up, down, left, right;
	boolean isVertex;
	int speed_y, speed_x;
	int speed;
	int playerSpeed = 1;
	
	public Virus(int x, int y, int speed, boolean isVertex, int size, Color c) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.size = size;
		this.w = size;
		this.h = size;
		this.r = size/2;
		this.c = c;
		this.isVertex = isVertex;
		
		up = false;
		down = false;
		left = false;
		right = false;
		speed_y = 0;
		speed_x = 0;
		
		if(this.x == 0) {
			this.x -= w;
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(c);
		g.fillOval(x, y, w, h);
	}
	
	public void update() {
		if(isVertex == false)
			x += speed;
		else
			y += speed;
	}
}
