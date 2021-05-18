package COVID19;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class VirusManager {
	Virus player;
	ArrayList<Virus> virus;
	boolean isGameOver = false;
	boolean isGameClear = false;
	boolean up = false, down = false, left = false, right = false;
	static int stage = 1;
	int count = 0;
	
	public VirusManager() {
		player = new Virus(Game.w/2, Game.h/2, 0, true, 30, Color.cyan) {
			public void update() { 
				KeyManager k = KeyManager.getInstance();
				if(k.up) {
					up = true;
					if(speed_y > -10) speed_y -= playerSpeed;
				} 
				else if(up) {
					if(speed_y == 0) up = false;
					else if(speed_y < 0) speed_y += playerSpeed;
				}
				
				if(k.down) {
					down = true;
					if(speed_y < 10) speed_y += playerSpeed;
				} 
				else if(down) {
					if(speed_y == 0) down = false;
					else if(speed_y > 0) speed_y -= playerSpeed;
				}
				
				if(k.right) {
					right = true;
					if(speed_x < 10) speed_x += playerSpeed;
				} 
				else if(right) {
					if(speed_x == 0) right = false;
					else if(speed_x > 0) speed_x -= playerSpeed;
				}
				
				if(k.left) {
					left = true;
					if(speed_x > -10) speed_x-=playerSpeed;
				} 
				else if(left) {
					if(speed_x == 0) left = false;
					else if(speed_x < 0) speed_x += playerSpeed;
				}
				
				x += speed_x;
				y += speed_y;
				
			}
		};
		
		virus = new ArrayList<Virus>();
	}
	
	public void draw(Graphics g) {
		player.draw(g);
		for(int i = 0; i < virus.size(); i++) {
			virus.get(i).draw(g);
		}
	}
	
	public void update() {
		player.update();
		for(int i = 0; i < virus.size(); i++) {
			virus.get(i).update();
			if(crashCheck(i)) {
				if(virus.get(i).size < player.size) {
					virus.remove(i);
					player.size++;
					count++;
					player.w = player.size;
					player.h = player.size;
					if(i >= virus.size()) break;
				}
				else isGameOver = true/*System.exit(0)*/;
			}
			//잡아먹은 적의 숫자가2개가 넘을 시 게임 클리어
			if(count == 2)
				isGameClear = true;
			
			
			//화면밖으로 나갈 시 
			if(virus.get(i).x + virus.get(i).w < 0 || virus.get(i).x > Game.w)
				virus.remove(i);
		}
		// 크기가 커질 수록 생성 주기가 길어짐
		if(Game.time % (player.size) == 0) createVirus();
	}
	
	public void createVirus() {
		// randLocation = 상하좌우를 판단 (0 = 좌, 1 = 우, 2 = 상, 3 = 하)
		int randLocation = 1;
		
		if(stage == 2) randLocation = (int) (Math.random() * 2);
		if(stage == 3) randLocation = (int) (Math.random() * 4);
		
		int speed = (int) (Math.random() * 10 + 3);
		if(randLocation == 1 || randLocation == 3) speed *= -1;
		
		// 
		if(randLocation < 2)
			virus.add(new Virus(randLocation * 1280, (int) (Math.random() * 720), speed, false, (int) (Math.random() * 10 + player.size - 5), new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255))));
		else
			virus.add(new Virus((int) (Math.random() * 1280), (randLocation-2) * 720, speed, true, (int) (Math.random() * 10 + player.size - 5), new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255))));			
	}
	
	public boolean crashCheck(int i) {
		if(Math.sqrt((player.x-virus.get(i).x)*(player.x-virus.get(i).x)+(player.y-virus.get(i).y)*(player.y-virus.get(i).y))
				< player.r + virus.get(i).r)
			return true;
		
		return false;
	}
}
