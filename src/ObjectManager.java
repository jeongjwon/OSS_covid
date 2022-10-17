package COVID19;

import java.awt.Graphics;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

// ȭ���� ��� ��ü�� �ٷ�� ������ ����
public class ObjectManager {
	// �÷��̾�, ���̷���, ������ ��ü�� ���� ���� �� �迭
	Player player;
	ArrayList<Virus> virus;
	ArrayList<Item> item;
	ArrayList<SpecialVirus> specialVirus;

	// ���� ���������� �����ϴ� ����
	static int stage = 1;

	// ����� ����
	int lifeCount = 3;
	int score = 0;

	// ���ӿ���, ���� Ŭ����, ���⿡ ���� ������ �˷��ִ� boolean ����
	boolean isGameOver = false;
	boolean isGameClear = false;
	boolean up = false, down = false, left = false, right = false;
	boolean isReverse = false; // ����Ű �¿� ���� �������� ȹ���ߴ��� üũ�ϱ� ���� ����

	Clip clip;

	// ȿ������ ����ϱ� ���� �Լ�
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

	// ObjectManager Ŭ���� ������
	public ObjectManager() {
		// �÷��̾� ��ü ���� �� KeyManager �ν��Ͻ��� �޾� Ű �Է� ������ ��´�.
		player = new Player(Game.w / 2, Game.h / 2, 0, 40) {
			public void update() {
				KeyManager k = KeyManager.getInstance();
				// 1. ���ӵ��� ���� speed�� 10�� �� �� ���� ���� speed ���� �ø���.
				// 2. �ش� Ű�� �� ��� ���� ���ߴ� ���� �ƴ� �ӵ��� ���� �پ��� �Ѵ�.
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

				// ������ �������� ȹ������ �ʾ��� ���
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

				// ������ �������� ȹ������ ��� => ��,�� Ű�� ���� �ݴ�� �����̰� �Ѵ�.
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
				// ��ǥ�� speed���� ���� �����̰� �Ѵ�.
				x += speed_x;
				y += speed_y;

			}
		};

		// ���̷���, Ư�� ���̷���, ������ ��ü�� ����
		virus = new ArrayList<Virus>();
		item = new ArrayList<Item>();
		specialVirus = new ArrayList<SpecialVirus>();

	}

	// �����̴� ��ü�� �׸��� �Լ�
	public void draw(Graphics g) {
		player.draw(g);
		for (int i = 0; i < virus.size(); i++)
			virus.get(i).draw(g);
		for (int i = 0; i < item.size(); i++)
			item.get(i).draw(g);
		for (int i = 0; i < specialVirus.size(); i++)
			specialVirus.get(i).draw(g);
	}

	// �÷��̾�� ���̷��� �� �ƾ��� �� �浹�� �ľ��ϴ� �Լ�
	public void update() {
		player.update();
		// ���̷����� �浹
		for (int i = 0; i < virus.size(); i++) {
			virus.get(i).update();
			if (virusCrashCheck(i)) {
				// �÷��̾��� ũ�Ⱑ �� ũ�ٸ� �÷��̾� ũ�Ⱑ Ŀ���� ������ ����
				if (virus.get(i).size <= player.size) {
					Play("goodSound.wav");
					virus.remove(i);
					player.size += 4;
					score += 10;
					if (i >= virus.size())
						break;
					// �÷��̾��� ũ�Ⱑ �� �۰� ����� 1�� �̻��̸� ũ�Ⱑ �۾����� ������ ����
				} else if ((virus.get(i).size > player.size) && (lifeCount > 1)) {
					Play("badSound.wav");
					lifeCount--;
					player.size -= 4;
					virus.remove(i);
					// ������ 0�� ������ �������� �ʰ� �ϱ� ����
					if (score <= 10)
						score = 0;
					else
						score -= 10;
					if (i >= virus.size())
						break;
				}

				// ����� �ϳ��� �� �÷��̾ �ε��� ���̷������� ���� ��� ���� ���� (GameŬ�������� �̸� Ȯ���Ͽ� ���� â�� ����)
				else if ((virus.get(i).size > player.size) && (lifeCount <= 1)) {
					Play("badSound.wav");
					lifeCount = 0;
					isGameOver = true;
				}

			}
			// ������ �� �ܰ躰 200, 400, 600���� ���� ��� ���� Ŭ���� (���� ���� GameŬ������ �̸� Ȯ��)
			if ((stage == 1 && score >= 200) || (stage == 2 && score >= 400) || (stage == 3 && score >= 600))
				isGameClear = true;

			// ȭ������� ���� �� ���̷����� ���ش�.
			if (virus.get(i).x + virus.get(i).size < 0 || virus.get(i).x > Game.w)
				virus.remove(i);
		}

		// Ư�� ���̷����� �浹
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

			// ȭ������� ���� �� ���̷����� ���ش�.
			if (specialVirus.get(i).x + specialVirus.get(i).size < 0 || specialVirus.get(i).x > Game.w)
				specialVirus.remove(i);
		}

		// �����۰� �浹
		for (int i = 0; i < item.size(); i++) {
			item.get(i).update();
			if (itemCrashCheck(i)) {
				// itemType�� 0�� ��� ��� ����
				if (item.get(i).itemType == 0) {
					Play("goodItem.wav");
					lifeCount++;
					// itemType�� 1�� ��� ���� ����
				} else if (item.get(i).itemType == 1) {
					Play("goodItem.wav");
					score += 50;
					// itemType�� 2�� ��� �¿� Ű ����
				} else if (item.get(i).itemType == 2) {
					Play("badItem.wav");
					if (isReverse == true)
						isReverse = false;
					else
						isReverse = true;
					// itemType�� 3�� ��� �ӵ� ����
				} else if (item.get(i).itemType == 3) {
					Play("goodItem.wav");
					player.playerSpeed = (1.2) * player.playerSpeed;
				}

				item.remove(i);
				break;
			}

			// ȭ������� ���� ��
			if (item.get(i).x + item.get(i).size < 0 || item.get(i).x > Game.w)
				item.remove(i);
		}

		// ũ�Ⱑ Ŀ�� ���� ���� �ֱⰡ �����
		if (Game.time % (player.size / 4) == 0)
			createVirus();
		if (Game.time % (player.size) == 0)
			createSpecialVirus();
		if (Game.time % (player.size * 2) == 0)
			createItem();
	}

	public void createVirus() {
		// randLocation : �����¿츦 �Ǵ� (0 = ��, 1 = ��, 2 = ��, 3 = ��)
		int randLocation = 1;

		if (stage == 2)
			randLocation = (int) (Math.random() * 2);
		if (stage == 3)
			randLocation = (int) (Math.random() * 4);

		int speed = (int) (Math.random() * 5 + 5);
		if (randLocation == 1 || randLocation == 3)
			speed *= -1;

		// �ش� ��ġ�� ���̷��� ����
		if (randLocation < 2)
			virus.add(new Virus(randLocation * 1050, (int) (Math.random() * 750), speed, false,
					(int) (Math.random() * 20 + player.size - 8)));
		else
			virus.add(new Virus((int) (Math.random() * 1050), (randLocation - 2) * 750, speed, true,
					(int) (Math.random() * 20 + player.size - 8)));
	}

	public void createSpecialVirus() {
		// randLocation = �����¿츦 �Ǵ� (0 = ��, 1 = ��, 2 = ��, 3 = ��)
		int randLocation = 1;

		if (stage == 2)
			randLocation = (int) (Math.random() * 2);
		if (stage == 3)
			randLocation = (int) (Math.random() * 4);

		int speed = (int) (Math.random() * 5 + 5);
		if (randLocation == 1 || randLocation == 3)
			speed *= -1;

		// �ش� ��ġ�� Ư�� ���̷��� ����
		if (randLocation < 2)
			specialVirus.add(new SpecialVirus(randLocation * 1050, (int) (Math.random() * 750), speed, false,
					(int) (Math.random() * 20 + player.size - 8), (int) (Math.random() * 3)));
		else
			specialVirus.add(new SpecialVirus((int) (Math.random() * 1050), (randLocation - 2) * 750, speed, true,
					(int) (Math.random() * 20 + player.size - 8), (int) (Math.random() * 3)));
	}

	public void createItem() {
		// randLocation = �����¿츦 �Ǵ� (0 = ��, 1 = ��, 2 = ��, 3 = ��)
		int randLocation = 1;

		if (stage == 2)
			randLocation = (int) (Math.random() * 2);
		if (stage == 3)
			randLocation = (int) (Math.random() * 4);

		int speed = (int) (Math.random() * 5 + 5);
		if (randLocation == 1 || randLocation == 3)
			speed *= -1;

		// �ش� ��ġ�� ������ ����
		if (randLocation < 2)
			item.add(new Item(randLocation * 1050, (int) (Math.random() * 750), speed, false, player.size - 5,
					(int) (Math.random() * 4)));
		else
			item.add(new Item((int) (Math.random() * 1050), (randLocation - 2) * 750, speed, true, player.size - 5,
					(int) (Math.random() * 4)));
	}

	// ���̷����� �÷��̾� �浹 Ȯ�� �Լ�
	public boolean virusCrashCheck(int i) {
		if (Math.sqrt((player.x - virus.get(i).x) * (player.x - virus.get(i).x)
				+ (player.y - virus.get(i).y) * (player.y - virus.get(i).y)) < player.r + virus.get(i).r)
			return true;

		return false;
	}

	// Ư�� ���̷����� �÷��̾� �浹 Ȯ�� �Լ�
	public boolean specialVirusCrashCheck(int i) {
		if (Math.sqrt((player.x - specialVirus.get(i).x) * (player.x - specialVirus.get(i).x)
				+ (player.y - specialVirus.get(i).y) * (player.y - specialVirus.get(i).y)) < player.r
						+ specialVirus.get(i).r)
			return true;

		return false;
	}

	// �����۰� �÷��̾� �浹 Ȯ�� �Լ�
	public boolean itemCrashCheck(int i) {
		if (Math.sqrt((player.x - item.get(i).x) * (player.x - item.get(i).x)
				+ (player.y - item.get(i).y) * (player.y - item.get(i).y)) < player.r + item.get(i).r)
			return true;

		return false;
	}
}
