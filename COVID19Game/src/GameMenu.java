package COVID19;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.Timer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Stage1 extends JFrame implements KeyListener { // ù ���� ȭ�� ������ Ŭ���� (firstBackground �̹���)

	static int w = 1050, h = 750; // ���� â�� ũ�� ����

	static Clip clip; // ������� ����� ���� clip Ŭ����

	MyPanel1 panel1 = new MyPanel1();

	Stage1() { // ù ���� ȭ�� ������ ������
		setTitle("Anti Corona"); // ������ ��ü�� Ÿ��Ʋ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ������ ��ü�� X��ư�� ������ �� ���������� ������ �Ѵ�.
		setContentPane(panel1); // �г��� �����ӿ� �ܴ�.

		setSize(w, h); // �ʱ⿡ ������ ������� ������ũ�⸦ �����.

		setVisible(true); // �гΰ� �������� visible �ϰ� �Ѵ�.

		this.addKeyListener(this); // Ű���� Ű�Է��� ���� �� �ְ� �Ѵ�.
		Play("gameMenu.wav"); // ������� ���
	}

	class MyPanel1 extends JPanel {
		ImageIcon image1 = new ImageIcon("background/firstBackground.jpg"); // �гο� �̹����� �־ Graphics �� ����� ����Ѵ�.
		Image img = image1.getImage(); // �̹��� ��ü�� �̹����� �ҷ��´�.

		public void paintComponent(Graphics g) {
			super.paintComponent(g); // ��ӹ��� �θ��� �̹����� �����׸���.
			g.drawImage(img, 0, 0, w, h, null); // �̹����� �׸���.
		}
	}

	public void Play(String fileName) {
		try { // ���� ������ ���� try catch ��
			clip = AudioSystem.getClip(); // Ŭ�� ��ü�� ������ �ҷ��´�.
			URL url = getClass().getResource("" + fileName); // URL�� �̿��Ͽ� ���������� �ҷ��´�.
			AudioInputStream ais = AudioSystem.getAudioInputStream(url);
			clip.open(ais); // ���������� ���
			clip.loop(-1);
		} catch (Exception ex) {
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) { // �����̽��� �Է½� ���� ȭ���� ��ü�� ȣ���� ���� ȭ���� ���� �� �ְ� �Ѵ�.
			this.setVisible(false); // ���� ��ü�� �ݴ´�.
			new Stage1_1();

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}

class Stage1_1 extends JFrame implements KeyListener { // �ι�° ȭ�� ������ Ŭ���� (gameExplain �̹���)

	static int w = 1050, h = 750; // ���� â�� ũ�� ����

	MyPanel1 panel1 = new MyPanel1();

	Stage1_1() { // ù ���� ȭ�� ������ ������
		setTitle("Game Explain"); // ������ ��ü�� Ÿ��Ʋ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ������ ��ü�� X��ư�� ������ �� ���������� ������ �Ѵ�.
		setContentPane(panel1); // �г��� �����ӿ� �ܴ�.

		setSize(w, h); // �ʱ⿡ ������ ������� ������ũ�⸦ �����.

		setVisible(true); // �гΰ� �������� visible �ϰ� �Ѵ�.

		this.addKeyListener(this); // Ű���� Ű�Է��� ���� �� �ְ� �Ѵ�.
	}

	class MyPanel1 extends JPanel {
		ImageIcon image1 = new ImageIcon("background/gameExplain.jpg"); // �гο� �̹����� �־ Graphics �� ����� ����Ѵ�.
		Image img = image1.getImage(); // �̹��� ��ü�� �̹����� �ҷ��´�.

		public void paintComponent(Graphics g) {
			super.paintComponent(g); // ��ӹ��� �θ��� �̹����� �����׸���.
			g.drawImage(img, 0, 0, w, h, null); // �̹����� �׸���.
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) { // �����̽��� �Է½� ���� ȭ���� ��ü�� ȣ���� ���� ȭ���� ���� �� �ְ� �Ѵ�.
			this.setVisible(false); // ���� ��ü�� �ݴ´�.
			new Stage1_2();

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}

class Stage1_2 extends JFrame implements KeyListener { // ����° ȭ�� ������ Ŭ���� (itemExplain �̹���)

	static int w = 1050, h = 750; // ���� â�� ũ�� ����

	MyPanel1 panel1 = new MyPanel1();

	Stage1_2() { // ù ���� ȭ�� ������ ������
		setTitle("Item Explain"); // ������ ��ü�� Ÿ��Ʋ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ������ ��ü�� X��ư�� ������ �� ���������� ������ �Ѵ�.
		setContentPane(panel1); // �г��� �����ӿ� �ܴ�.

		setSize(w, h); // �ʱ⿡ ������ ������� ������ũ�⸦ �����.

		setVisible(true); // �гΰ� �������� visible �ϰ� �Ѵ�.

		this.addKeyListener(this); // Ű���� Ű�Է��� ���� �� �ְ� �Ѵ�.
	}

	class MyPanel1 extends JPanel {
		ImageIcon image1 = new ImageIcon("background/itemExplain.jpg"); // �гο� �̹����� �־ Graphics �� ����� ����Ѵ�.
		Image img = image1.getImage(); // �̹��� ��ü�� �̹����� �ҷ��´�.

		public void paintComponent(Graphics g) {
			super.paintComponent(g); // ��ӹ��� �θ��� �̹����� �����׸���.
			g.drawImage(img, 0, 0, w, h, null); // �̹����� �׸���.
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) { // �����̽��� �Է½� ���� ȭ���� ��ü�� ȣ���� ���� ȭ���� ���� �� �ְ� �Ѵ�.
			this.setVisible(false); // ���� ��ü�� �ݴ´�.
			new Stage1_3();

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}

class Stage1_3 extends JFrame implements KeyListener { // �׹�° ȭ�� ������ Ŭ���� (stage1 �̹���)

	static int w = 1050, h = 750; // ���� â�� ũ�� ����

	MyPanel1 panel1 = new MyPanel1();

	Stage1_3() { // ù ���� ȭ�� ������ ������
		setTitle("Stage 1"); // ������ ��ü�� Ÿ��Ʋ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ������ ��ü�� X��ư�� ������ �� ���������� ������ �Ѵ�.
		setContentPane(panel1); // �г��� �����ӿ� �ܴ�.

		setSize(w, h); // �ʱ⿡ ������ ������� ������ũ�⸦ �����.

		setVisible(true); // �гΰ� �������� visible �ϰ� �Ѵ�.

		this.addKeyListener(this); // Ű���� Ű�Է��� ���� �� �ְ� �Ѵ�.
	}

	class MyPanel1 extends JPanel {
		ImageIcon image1 = new ImageIcon("background/stage1.jpg"); // �гο� �̹����� �־ Graphics �� ����� ����Ѵ�.
		Image img = image1.getImage(); // �̹��� ��ü�� �̹����� �ҷ��´�.

		public void paintComponent(Graphics g) {
			super.paintComponent(g); // ��ӹ��� �θ��� �̹����� �����׸���.
			g.drawImage(img, 0, 0, w, h, null); // �̹����� �׸���.
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_SPACE) { // ���� ������ ������ ȭ�� Ŭ�����̹Ƿ� �̹� ȭ�鿡�� �����̽��� �Է½�
			Game game = new Game(); // ���� Ŭ������ ȣ���� ���� ����
			Timer t = new Timer();
			t.schedule(game, 0, 33);
			this.setVisible(false);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}

class Stage2 extends JFrame implements KeyListener { // stage 2 �̹��� ������ Ŭ����

	static int w = 1050, h = 750; // ���� â�� ũ�� ����

	MyPanel panel = new MyPanel();

	Stage2() { // ù ���� ȭ�� ������ ������
		setTitle("Stage 2"); // ������ ��ü�� Ÿ��Ʋ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ������ ��ü�� X��ư�� ������ �� ���������� ������ �Ѵ�.
		setContentPane(panel); // �г��� �����ӿ� �ܴ�.

		setSize(w, h); // �ʱ⿡ ������ ������� ������ũ�⸦ �����.

		setVisible(true); // �гΰ� �������� visible �ϰ� �Ѵ�.

		this.addKeyListener(this); // Ű���� Ű�Է��� ���� �� �ְ� �Ѵ�.
	}

	class MyPanel extends JPanel {
		ImageIcon image = new ImageIcon("background/stage2.jpg"); // �гο� �̹����� �־ Graphics �� ����� ����Ѵ�.
		Image img = image.getImage(); // �̹��� ��ü�� �̹����� �ҷ��´�.

		public void paintComponent(Graphics g) {
			super.paintComponent(g); // ��ӹ��� �θ��� �̹����� �����׸���.
			g.drawImage(img, 0, 0, w, h, null); // �̹����� �׸���.
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) { // ���� ������ ������ ȭ�� Ŭ�����̹Ƿ� �̹� ȭ�鿡�� �����̽��� �Է½�
			Game game = new Game(); // ���� Ŭ������ ȣ���� ���� ����
			Timer t = new Timer();
			t.schedule(game, 0, 33);
			this.setVisible(false);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}

class Stage3 extends JFrame implements KeyListener { // stage 3 �̹��� ������ Ŭ����

	static int w = 1050, h = 750; // ���� â�� ũ�� ����

	MyPanel panel = new MyPanel();

	Stage3() { // ù ���� ȭ�� ������ ������
		setTitle("Stage 3"); // ������ ��ü�� Ÿ��Ʋ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ������ ��ü�� X��ư�� ������ �� ���������� ������ �Ѵ�.
		setContentPane(panel); // �г��� �����ӿ� �ܴ�.

		setSize(w, h); // �ʱ⿡ ������ ������� ������ũ�⸦ �����.

		setVisible(true); // �гΰ� �������� visible �ϰ� �Ѵ�.

		this.addKeyListener(this); // Ű���� Ű�Է��� ���� �� �ְ� �Ѵ�.
	}

	class MyPanel extends JPanel {
		ImageIcon image = new ImageIcon("background/stage3.jpg"); // �гο� �̹����� �־ Graphics �� ����� ����Ѵ�.
		Image img = image.getImage(); // �̹��� ��ü�� �̹����� �ҷ��´�.

		public void paintComponent(Graphics g) {
			super.paintComponent(g); // ��ӹ��� �θ��� �̹����� �����׸���.
			g.drawImage(img, 0, 0, w, h, null); // �̹����� �׸���.
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) { // ���� ������ ������ ȭ�� Ŭ�����̹Ƿ� �̹� ȭ�鿡�� �����̽��� �Է½�
			Game game = new Game(); // ���� Ŭ������ ȣ���� ���� ����
			Timer t = new Timer();
			t.schedule(game, 0, 33);
			this.setVisible(false);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}

class GameVictory extends JFrame implements KeyListener {

	static int w = 1050, h = 750; // ���� â�� ũ�� ����

	MyPanel1 panel1 = new MyPanel1();

	GameVictory() { // ù ���� ȭ�� ������ ������
		setTitle("Game Victory"); // ������ ��ü�� Ÿ��Ʋ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ������ ��ü�� X��ư�� ������ �� ���������� ������ �Ѵ�.
		setContentPane(panel1); // �г��� �����ӿ� �ܴ�.

		setSize(w, h); // �ʱ⿡ ������ ������� ������ũ�⸦ �����.

		setVisible(true); // �гΰ� �������� visible �ϰ� �Ѵ�.

		this.addKeyListener(this); // Ű���� Ű�Է��� ���� �� �ְ� �Ѵ�.
	}

	class MyPanel1 extends JPanel {
		ImageIcon image1 = new ImageIcon("background/gameVictory.jpg"); // �гο� �̹����� �־ Graphics �� ����� ����Ѵ�.
		Image img = image1.getImage(); // �̹��� ��ü�� �̹����� �ҷ��´�.

		public void paintComponent(Graphics g) {
			super.paintComponent(g); // ��ӹ��� �θ��� �̹����� �����׸���.
			g.drawImage(img, 0, 0, w, h, null); // �̹����� �׸���.
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) { // ���� ������ ������ ȭ�� Ŭ�����̹Ƿ� �̹� ȭ�鿡�� �����̽��� �Է½�
			Game game = new Game(); // ���� Ŭ������ ȣ���� ���� ����
			Timer t = new Timer();
			t.schedule(game, 0, 33);
			this.setVisible(false);
		}
	}
}

class GameOver extends JFrame implements KeyListener {

	static int w = 1050, h = 750; // ���� â�� ũ�� ����

	MyPanel1 panel1 = new MyPanel1();

	GameOver() { // ù ���� ȭ�� ������ ������
		setTitle("Game Over"); // ������ ��ü�� Ÿ��Ʋ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ������ ��ü�� X��ư�� ������ �� ���������� ������ �Ѵ�.
		setContentPane(panel1); // �г��� �����ӿ� �ܴ�.

		setSize(w, h); // �ʱ⿡ ������ ������� ������ũ�⸦ �����.

		setVisible(true); // �гΰ� �������� visible �ϰ� �Ѵ�.

		this.addKeyListener(this); // Ű���� Ű�Է��� ���� �� �ְ� �Ѵ�.
	}

	class MyPanel1 extends JPanel {
		ImageIcon image1 = new ImageIcon("background/gameOver.jpg"); // �гο� �̹����� �־ Graphics �� ����� ����Ѵ�.
		Image img = image1.getImage(); // �̹��� ��ü�� �̹����� �ҷ��´�

		public void paintComponent(Graphics g) {
			super.paintComponent(g); // ��ӹ��� �θ��� �̹����� �����׸���.
			g.drawImage(img, 0, 0, w, h, null); // �̹����� �׸���.
			g.setFont(new Font("�ü�ü", Font.PLAIN, 60));
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(Game.score), 550, 315); // score ��ºκ�, gameOver �϶� ��ü ���ھ ����Ѵ�.
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) { // ���� ������ ������ ȭ�� Ŭ�����̹Ƿ� �̹� ȭ�鿡�� �����̽��� �Է½�
			Game game = new Game(); // ���� Ŭ������ ȣ���� ���� ����
			Timer t = new Timer();
			t.schedule(game, 0, 33);
			this.setVisible(false);
		}
	}

}