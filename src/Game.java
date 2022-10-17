package COVID19;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends TimerTask { // ���� Ŭ����

	static int w = 1050, h = 750; // ���� â�� ũ�� ����

	static int time; // �����带 ����� Ÿ�̸Ӹ� ���� Ÿ�� �������
	static int score = 0; // ���� ���ھ�
	static JFrame f; // ���� ������

	BufferedImage b; // �̹��������� �ҷ����� ���� �����̹��� ������� ����
	Graphics g; // �гο� �̹����� �׸��� ���� Graphics ��ü

	Image image; // �ҷ��� �̹����� �����Ͽ� ����ϱ� ���� Image ��ü
	Image life; // life �κ��� �̹����� �����ϱ� ���� Image ��ü

	Toolkit tool; // getImage() ���� �޼ҵ带 ����ϱ� ���� tool ��ü
	Clip clip; // ������ ����ϱ� ���� Clip ��ü

	ObjectManager om; // ObjectManager ��ü
	Stage1 stage1;

	JPanel p; // �����ӿ� �� �гΰ�ü
	JLabel lifeLabel; // �������� �� ������ �гΰ�ü

	static boolean soundFlag = false; // ������ On/Off �� �����ϱ� ���� flag �������

	public void Play(String fileName) { // ���� ��� �޼ҵ�
		try { // ���� ������ ���� try catch ��
			clip = AudioSystem.getClip(); // clip ��ü�� ������ �ҷ��´�.
			URL url = getClass().getResource("" + fileName); // URL�� �̿��Ͽ� ���������� �ҷ��´�.
			AudioInputStream ais = AudioSystem.getAudioInputStream(url);
			clip.open(ais); // ������ ����
			clip.loop(-1);
		} catch (Exception ex) {
		}
	}

	public Game() {
		f = new JFrame("COVID19"); // �����ӻ���
		f.setBounds(0, 0, w, h); // ������ �ٿ�� ����
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �������� X��ư Ŭ���� �������� ���Ḧ ����
		f.addKeyListener(KeyManager.getInstance()); // �������� Ű���� Ű �Է��� ���� �� �ְ� �Ѵ�

		b = new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR); // �����̹����� �̿��Ͽ� �̹����� �ҷ��´�.
		g = b.getGraphics(); // �̹����� Graphics ��ü�� ����

		om = new ObjectManager(); // VirusManager ����

		p = new JPanel(); // �гλ���

		f.setVisible(true); // �������� Visible �ϰ��Ѵ�

		stage1.clip.stop(); // �ʱ�ȭ���� ������ Off �Ѵ�
		if (soundFlag == false) // �������� ������ �������� ���������� ����Ѵ�.
			Play("background.wav");
		soundFlag = true;
	}

	@Override
	public void run() { // Ÿ�̸� Ŭ������ run �޼ҵ� ����
		time++; // Ÿ���� ��� �帧

		draw(); // �� Ÿ�ӿ� �ѹ��� �̹����� �׸�
		update(); // �� Ÿ�Ӵ� �ѹ��� ���̷����� �浹�˻�

		if (om.isGameOver == true) { // isGameOver���� true ���� ������
			f.dispose(); // �� ������ �ϳ����� ����
			om.isGameOver = false; // VirusManager ��ü�� om�� isGameOver�� false���� �־� �ʱ�ȭ�Ѵ�.
			om.stage = 1; // om�� stage�� �ٽ� 1�� �ʱ�ȭ�Ѵ�.
			KeyManager k = KeyManager.getInstance(); // ���� �޸𸮿� ���ο� KeyManager�� ���� �� �Ҵ��Ѵ�.
			k.instance = null; // instance�� null�� �ʱ�ȭ�ϹǷ� Ű ���� �ʱ�ȭ�Ѵ�.
			score = om.score; // gameOver�ÿ� score�� ����ϱ� ���� game�� ������� score�� om�� ������� score ���� �����Ѵ�.
			new GameOver(); // GameOver ȭ���� �����Ѵ�.
		}
		if (om.isGameClear == true) { // isGameClear���� true ���� Ŭ�����
			f.dispose(); // �� ������ �ϳ����� ����
			om.isGameClear = false; // VirusManager ��ü�� om�� isGameClear�� false���� �־� �ʱ�ȭ�Ѵ�.
			om.stage++; // om�� �������������� �������� stage���� ������Ų��.
			KeyManager k = KeyManager.getInstance(); // ���� �޸𸮿� ���ο� KeyManager�� ���� �� �Ҵ��Ѵ�.
			k.instance = null;

			if (om.stage == 1) // � ������������ �˱����� stage ����������� ���� stage�� ȣ���Ѵ�.
				new Stage1(); // om�� stage���� 1�϶� Stage1 ȣ��
			else if (om.stage == 2)
				new Stage2(); // om�� stage���� 2�϶� Stage2 ȣ��
			else if (om.stage == 3) {
				new Stage3(); // om�� stage���� 3�϶� Stage3 ȣ��
			} else { // om�� stage���� 4�̻��϶� om�� stage���� 1�� �ʱ�ȭ �ϰ� GameVictory�� ����
				om.stage = 1;
				new GameVictory();
			}
		}
	}

	public void draw() {

		tool = Toolkit.getDefaultToolkit(); // ToolkitŬ������ �̿��� �̹��������� �ҷ��´�.
		if (om.stage == 1) // �������� ��������� ���� ���� ����̹���ȣ��
			image = tool.getImage("background/chinaBackground.jpg"); // Stage1 �߱�����̹����� �ҷ��´�.
		else if (om.stage == 2)
			image = tool.getImage("background/japanBackground.jpg"); // Stage2 �Ϻ�����̹����� �ҷ��´�.
		else if (om.stage == 3)
			image = tool.getImage("background/koreaBackground.jpg"); // Stage3 �ѱ�����̹����� �ҷ��´�.

		life = tool.getImage("items/item1.png"); // ������ �̹����� tool�� ����Ͽ� �ҷ��´�.
		g.drawImage(image, 0, 0, w, h, null); // ����̹����� �׸���.
		g.drawImage(life, 65, 75, 30, 30, null); // �������̹����� �׸���.
		om.draw(g); // ���̷�����ü�� �׸���.

		g.setColor(Color.black);
		g.setFont(new Font("����", Font.BOLD, 30));
		g.drawString("X " + om.lifeCount, 100, 100); // ��������
		g.drawString("SCORE : " + om.score, 800, 100); // ���ھ

		f.getGraphics().drawImage(b, 0, 0, w, h, f);

	}

	public void update() {
		om.update(); // om�� ������Ʈ �޼ҵ带 �ҷ��´�.
	}
}
