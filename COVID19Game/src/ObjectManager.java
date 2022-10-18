package COVID19;

import java.awt.Graphics;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

// 화면의 모든 객체를 다루는 관리자 역할
public class ObjectManager {
	// 플레이어, 바이러스, 아이템 객체를 담을 변수 및 배열
	Player player;
	ArrayList<Virus> virus;
	ArrayList<Item> item;
	ArrayList<SpecialVirus> specialVirus;

	// 게임 스테이지를 결정하는 변수
	static int stage = 1;

	// 목숨과 점수
	int lifeCount = 3;
	int score = 0;

	// 게임오버, 게임 클리어, 방향에 대한 정보를 알려주는 boolean 변수
	boolean isGameOver = false;
	boolean isGameClear = false;
	boolean up = false, down = false, left = false, right = false;
	boolean isReverse = false; // 방향키 좌우 반전 아이템을 획득했는지 체크하기 위한 변수

	Clip clip;

	// 효과음을 재생하기 위한 함수
	public void Play(String fileName) {
		try {
			Clip clip = AudioSystem.getClip();
			URL url = getClass().getResource("" + fileName);
			AudioInputStream ais = AudioSystem.getAudioInputStream(url);
			clip.open(ais);
			clip.start();
		} catch (Exception ex) {
		}
	}

	// ObjectManager 클래스 생성자
	public ObjectManager() {
		// 플레이어 객체 생성 및 KeyManager 인스턴스를 받아 키 입력 정보를 얻는다.
		player = new Player(Game.w / 2, Game.h / 2, 0, 40) {
			public void update() {
				KeyManager k = KeyManager.getInstance();
				// 1. 가속도를 위해 speed가 10이 될 때 까지 점점 speed 값을 늘린다.
				// 2. 해당 키를 뗄 경우 완전 멈추는 것이 아닌 속도가 점점 줄어들게 한다.
				if (k.up) { // 1
					up = true;
					if (speed_y > -10)
						speed_y -= playerSpeed;
				} else if (up) { // 2
					if (speed_y == 0)
						up = false;
					else if (speed_y < 0)
						speed_y += playerSpeed;
				}

				if (k.down) {
					down = true;
					if (speed_y < 10)
						speed_y += playerSpeed;
				} else if (down) {
					if (speed_y == 0)
						down = false;
					else if (speed_y > 0)
						speed_y -= playerSpeed;
				}

				// 리버스 아이템을 획득하지 않았을 경우
				if (isReverse == false) {
					if (k.right) {
						right = true;
						if (speed_x < 10)
							speed_x += playerSpeed;
					} else if (right) {
						if (speed_x == 0)
							right = false;
						else if (speed_x > 0)
							speed_x -= playerSpeed;
					}

					if (k.left) {
						left = true;
						if (speed_x > -10)
							speed_x -= playerSpeed;
					} else if (left) {
						if (speed_x == 0)
							left = false;
						else if (speed_x < 0)
							speed_x += playerSpeed;
					}
				}

				// 리버스 아이템을 획득했을 경우 => 좌,우 키에 대해 반대로 움직이게 한다.
				else {
					if (k.right) {
						right = true;
						if (speed_x > -10)
							speed_x -= playerSpeed;
					} else if (right) {
						if (speed_x == 0)
							right = false;
						else if (speed_x < 0)
							speed_x += playerSpeed;
					}

					if (k.left) {
						left = true;
						if (speed_x < 10)
							speed_x += playerSpeed;
					} else if (left) {
						if (speed_x == 0)
							left = false;
						else if (speed_x > 0)
							speed_x -= playerSpeed;
					}
				}
				// 좌표에 speed값을 더해 움직이게 한다.
				x += speed_x;
				y += speed_y;

			}
		};

		// 바이러스, 특별 바이러스, 아이템 객체를 생성
		virus = new ArrayList<Virus>();
		item = new ArrayList<Item>();
		specialVirus = new ArrayList<SpecialVirus>();

	}

	// 움직이는 객체를 그리는 함수
	public void draw(Graphics g) {
		player.draw(g);
		for (int i = 0; i < virus.size(); i++)
			virus.get(i).draw(g);
		for (int i = 0; i < item.size(); i++)
			item.get(i).draw(g);
		for (int i = 0; i < specialVirus.size(); i++)
			specialVirus.get(i).draw(g);
	}

	// 플레이어와 바이러스 및 아아템 간 충돌을 파악하는 함수
	public void update() {
		player.update();
		// 바이러스와 충돌
		for (int i = 0; i < virus.size(); i++) {
			virus.get(i).update();
			if (virusCrashCheck(i)) {
				// 플레이어의 크기가 더 크다면 플레이어 크기가 커지고 점수를 얻음
				if (virus.get(i).size <= player.size) {
					Play("goodSound.wav");
					virus.remove(i);
					player.size += 4;
					score += 10;
					if (i >= virus.size())
						break;
					// 플레이어의 크기가 더 작고 목숨이 1개 이상이면 크기가 작아지고 점수를 잃음
				} else if ((virus.get(i).size > player.size) && (lifeCount > 1)) {
					Play("badSound.wav");
					lifeCount--;
					player.size -= 4;
					virus.remove(i);
					// 점수가 0점 밑으로 내려가지 않게 하기 위함
					if (score <= 10)
						score = 0;
					else
						score -= 10;
					if (i >= virus.size())
						break;
				}

				// 목숨이 하나일 때 플레이어가 부딪힌 바이러스보다 작을 경우 게임 오버 (Game클래스에서 이를 확인하여 게임 창을 닫음)
				else if ((virus.get(i).size > player.size) && (lifeCount <= 1)) {
					Play("badSound.wav");
					lifeCount = 0;
					isGameOver = true;
				}

			}
			// 점수가 각 단계별 200, 400, 600점이 넘을 경우 게임 클리어 (위와 같이 Game클래스가 이를 확인)
			if ((stage == 1 && score >= 200) || (stage == 2 && score >= 400) || (stage == 3 && score >= 600))
				isGameClear = true;

			// 화면밖으로 나갈 시 바이러스를 없앤다.
			if (virus.get(i).x + virus.get(i).size < 0 || virus.get(i).x > Game.w)
				virus.remove(i);
		}

		// 특별 바이러스와 충돌
		for (int i = 0; i < specialVirus.size(); i++) {
			specialVirus.get(i).update();
			if (specialVirusCrashCheck(i)) {
				if (specialVirus.get(i).size <= player.size) {
					Play("goodSound.wav");
					specialVirus.remove(i);
					player.size += 4;
					score += 50;
					if (i >= specialVirus.size())
						break;
				} else if ((specialVirus.get(i).size > player.size) && (lifeCount > 1)) {
					Play("badSound.wav");
					lifeCount--;
					player.size -= 4;
					specialVirus.remove(i);
					if (score <= 50)
						score = 0;
					else
						score -= 50;
					if (i >= specialVirus.size())
						break;
				}

				else if ((specialVirus.get(i).size > player.size) && (lifeCount <= 1)) {
					Play("badSound.wav");
					lifeCount = 0;
					isGameOver = true;
				}

				if ((stage == 1 && score >= 200) || (stage == 2 && score >= 400) || (stage == 3 && score >= 600))
					isGameClear = true;

			}

			// 화면밖으로 나갈 시 바이러스를 없앤다.
			if (specialVirus.get(i).x + specialVirus.get(i).size < 0 || specialVirus.get(i).x > Game.w)
				specialVirus.remove(i);
		}

		// 아이템과 충돌
		for (int i = 0; i < item.size(); i++) {
			item.get(i).update();
			if (itemCrashCheck(i)) {
				// itemType이 0일 경우 목숨 증가
				if (item.get(i).itemType == 0) {
					Play("goodItem.wav");
					lifeCount++;
					// itemType이 1일 경우 점수 증가
				} else if (item.get(i).itemType == 1) {
					Play("goodItem.wav");
					score += 50;
					// itemType이 2일 경우 좌우 키 반전
				} else if (item.get(i).itemType == 2) {
					Play("badItem.wav");
					if (isReverse == true)
						isReverse = false;
					else
						isReverse = true;
					// itemType이 3일 경우 속도 증가
				} else if (item.get(i).itemType == 3) {
					Play("goodItem.wav");
					player.playerSpeed = (1.2) * player.playerSpeed;
				}

				item.remove(i);
				break;
			}

			// 화면밖으로 나갈 시
			if (item.get(i).x + item.get(i).size < 0 || item.get(i).x > Game.w)
				item.remove(i);
		}

		// 크기가 커질 수록 생성 주기가 길어짐
		if (Game.time % (player.size / 4) == 0)
			createVirus();
		if (Game.time % (player.size) == 0)
			createSpecialVirus();
		if (Game.time % (player.size * 2) == 0)
			createItem();
	}

	public void createVirus() {
		// randLocation : 상하좌우를 판단 (0 = 좌, 1 = 우, 2 = 상, 3 = 하)
		int randLocation = 1;

		if (stage == 2)
			randLocation = (int) (Math.random() * 2);
		if (stage == 3)
			randLocation = (int) (Math.random() * 4);

		int speed = (int) (Math.random() * 5 + 5);
		if (randLocation == 1 || randLocation == 3)
			speed *= -1;

		// 해당 위치에 바이러스 생성
		if (randLocation < 2)
			virus.add(new Virus(randLocation * 1050, (int) (Math.random() * 750), speed, false,
					(int) (Math.random() * 20 + player.size - 8)));
		else
			virus.add(new Virus((int) (Math.random() * 1050), (randLocation - 2) * 750, speed, true,
					(int) (Math.random() * 20 + player.size - 8)));
	}

	public void createSpecialVirus() {
		// randLocation = 상하좌우를 판단 (0 = 좌, 1 = 우, 2 = 상, 3 = 하)
		int randLocation = 1;

		if (stage == 2)
			randLocation = (int) (Math.random() * 2);
		if (stage == 3)
			randLocation = (int) (Math.random() * 4);

		int speed = (int) (Math.random() * 5 + 5);
		if (randLocation == 1 || randLocation == 3)
			speed *= -1;

		// 해당 위치에 특별 바이러스 생성
		if (randLocation < 2)
			specialVirus.add(new SpecialVirus(randLocation * 1050, (int) (Math.random() * 750), speed, false,
					(int) (Math.random() * 20 + player.size - 8), (int) (Math.random() * 3)));
		else
			specialVirus.add(new SpecialVirus((int) (Math.random() * 1050), (randLocation - 2) * 750, speed, true,
					(int) (Math.random() * 20 + player.size - 8), (int) (Math.random() * 3)));
	}

	public void createItem() {
		// randLocation = 상하좌우를 판단 (0 = 좌, 1 = 우, 2 = 상, 3 = 하)
		int randLocation = 1;

		if (stage == 2)
			randLocation = (int) (Math.random() * 2);
		if (stage == 3)
			randLocation = (int) (Math.random() * 4);

		int speed = (int) (Math.random() * 5 + 5);
		if (randLocation == 1 || randLocation == 3)
			speed *= -1;

		// 해당 위치에 아이템 생성
		if (randLocation < 2)
			item.add(new Item(randLocation * 1050, (int) (Math.random() * 750), speed, false, player.size - 5,
					(int) (Math.random() * 4)));
		else
			item.add(new Item((int) (Math.random() * 1050), (randLocation - 2) * 750, speed, true, player.size - 5,
					(int) (Math.random() * 4)));
	}

	// 바이러스와 플레이어 충돌 확인 함수
	public boolean virusCrashCheck(int i) {
		if (Math.sqrt((player.x - virus.get(i).x) * (player.x - virus.get(i).x)
				+ (player.y - virus.get(i).y) * (player.y - virus.get(i).y)) < player.r + virus.get(i).r)
			return true;

		return false;
	}

	// 특별 바이러스와 플레이어 충돌 확인 함수
	public boolean specialVirusCrashCheck(int i) {
		if (Math.sqrt((player.x - specialVirus.get(i).x) * (player.x - specialVirus.get(i).x)
				+ (player.y - specialVirus.get(i).y) * (player.y - specialVirus.get(i).y)) < player.r
						+ specialVirus.get(i).r)
			return true;

		return false;
	}

	// 아이템과 플레이어 충돌 확인 함수
	public boolean itemCrashCheck(int i) {
		if (Math.sqrt((player.x - item.get(i).x) * (player.x - item.get(i).x)
				+ (player.y - item.get(i).y) * (player.y - item.get(i).y)) < player.r + item.get(i).r)
			return true;

		return false;
	}
}
