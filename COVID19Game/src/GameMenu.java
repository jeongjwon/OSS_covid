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

class Stage1 extends JFrame implements KeyListener { // 첫 시작 화면 프레임 클래스 (firstBackground 이미지)

	static int w = 1050, h = 750; // 게임 창의 크기 정보

	static Clip clip; // 배경음악 재생을 위한 clip 클래스

	MyPanel1 panel1 = new MyPanel1();

	Stage1() { // 첫 시작 화면 프레임 생성자
		setTitle("Anti Corona"); // 프레임 객체의 타이틀생성
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 객체의 X버튼을 눌렀을 때 정상적으로 닫히게 한다.
		setContentPane(panel1); // 패널을 프레임에 단다.

		setSize(w, h); // 초기에 지정한 사이즈로 프레임크기를 맞춘다.

		setVisible(true); // 패널과 프레임을 visible 하게 한다.

		this.addKeyListener(this); // 키보드 키입력을 받을 수 있게 한다.
		Play("gameMenu.wav"); // 배경음악 재생
	}

	class MyPanel1 extends JPanel {
		ImageIcon image1 = new ImageIcon("background/firstBackground.jpg"); // 패널에 이미지를 넣어서 Graphics 를 사용해 출력한다.
		Image img = image1.getImage(); // 이미지 객체에 이미지를 불러온다.

		public void paintComponent(Graphics g) {
			super.paintComponent(g); // 상속받은 부모의 이미지를 먼저그린다.
			g.drawImage(img, 0, 0, w, h, null); // 이미지를 그린다.
		}
	}

	public void Play(String fileName) {
		try { // 오류 방지를 위한 try catch 문
			clip = AudioSystem.getClip(); // 클립 객체에 음악을 불러온다.
			URL url = getClass().getResource("" + fileName); // URL을 이용하여 음악파일을 불러온다.
			AudioInputStream ais = AudioSystem.getAudioInputStream(url);
			clip.open(ais); // 음악파일을 재생
			clip.loop(-1);
		} catch (Exception ex) {
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) { // 스페이스바 입력시 다음 화면의 객체를 호출해 다음 화면이 보일 수 있게 한다.
			this.setVisible(false); // 현재 객체는 닫는다.
			new Stage1_1();

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}

class Stage1_1 extends JFrame implements KeyListener { // 두번째 화면 프레임 클래스 (gameExplain 이미지)

	static int w = 1050, h = 750; // 게임 창의 크기 정보

	MyPanel1 panel1 = new MyPanel1();

	Stage1_1() { // 첫 시작 화면 프레임 생성자
		setTitle("Game Explain"); // 프레임 객체의 타이틀생성
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 객체의 X버튼을 눌렀을 때 정상적으로 닫히게 한다.
		setContentPane(panel1); // 패널을 프레임에 단다.

		setSize(w, h); // 초기에 지정한 사이즈로 프레임크기를 맞춘다.

		setVisible(true); // 패널과 프레임을 visible 하게 한다.

		this.addKeyListener(this); // 키보드 키입력을 받을 수 있게 한다.
	}

	class MyPanel1 extends JPanel {
		ImageIcon image1 = new ImageIcon("background/gameExplain.jpg"); // 패널에 이미지를 넣어서 Graphics 를 사용해 출력한다.
		Image img = image1.getImage(); // 이미지 객체에 이미지를 불러온다.

		public void paintComponent(Graphics g) {
			super.paintComponent(g); // 상속받은 부모의 이미지를 먼저그린다.
			g.drawImage(img, 0, 0, w, h, null); // 이미지를 그린다.
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) { // 스페이스바 입력시 다음 화면의 객체를 호출해 다음 화면이 보일 수 있게 한다.
			this.setVisible(false); // 현재 객체는 닫는다.
			new Stage1_2();

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}

class Stage1_2 extends JFrame implements KeyListener { // 세번째 화면 프레임 클래스 (itemExplain 이미지)

	static int w = 1050, h = 750; // 게임 창의 크기 정보

	MyPanel1 panel1 = new MyPanel1();

	Stage1_2() { // 첫 시작 화면 프레임 생성자
		setTitle("Item Explain"); // 프레임 객체의 타이틀생성
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 객체의 X버튼을 눌렀을 때 정상적으로 닫히게 한다.
		setContentPane(panel1); // 패널을 프레임에 단다.

		setSize(w, h); // 초기에 지정한 사이즈로 프레임크기를 맞춘다.

		setVisible(true); // 패널과 프레임을 visible 하게 한다.

		this.addKeyListener(this); // 키보드 키입력을 받을 수 있게 한다.
	}

	class MyPanel1 extends JPanel {
		ImageIcon image1 = new ImageIcon("background/itemExplain.jpg"); // 패널에 이미지를 넣어서 Graphics 를 사용해 출력한다.
		Image img = image1.getImage(); // 이미지 객체에 이미지를 불러온다.

		public void paintComponent(Graphics g) {
			super.paintComponent(g); // 상속받은 부모의 이미지를 먼저그린다.
			g.drawImage(img, 0, 0, w, h, null); // 이미지를 그린다.
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) { // 스페이스바 입력시 다음 화면의 객체를 호출해 다음 화면이 보일 수 있게 한다.
			this.setVisible(false); // 현재 객체는 닫는다.
			new Stage1_3();

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}

class Stage1_3 extends JFrame implements KeyListener { // 네번째 화면 프레임 클래스 (stage1 이미지)

	static int w = 1050, h = 750; // 게임 창의 크기 정보

	MyPanel1 panel1 = new MyPanel1();

	Stage1_3() { // 첫 시작 화면 프레임 생성자
		setTitle("Stage 1"); // 프레임 객체의 타이틀생성
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 객체의 X버튼을 눌렀을 때 정상적으로 닫히게 한다.
		setContentPane(panel1); // 패널을 프레임에 단다.

		setSize(w, h); // 초기에 지정한 사이즈로 프레임크기를 맞춘다.

		setVisible(true); // 패널과 프레임을 visible 하게 한다.

		this.addKeyListener(this); // 키보드 키입력을 받을 수 있게 한다.
	}

	class MyPanel1 extends JPanel {
		ImageIcon image1 = new ImageIcon("background/stage1.jpg"); // 패널에 이미지를 넣어서 Graphics 를 사용해 출력한다.
		Image img = image1.getImage(); // 이미지 객체에 이미지를 불러온다.

		public void paintComponent(Graphics g) {
			super.paintComponent(g); // 상속받은 부모의 이미지를 먼저그린다.
			g.drawImage(img, 0, 0, w, h, null); // 이미지를 그린다.
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_SPACE) { // 게임 시작전 마지막 화면 클래스이므로 이번 화면에서 스페이스바 입력시
			Game game = new Game(); // 게임 클래스를 호출해 게임 시작
			Timer t = new Timer();
			t.schedule(game, 0, 33);
			this.setVisible(false);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}

class Stage2 extends JFrame implements KeyListener { // stage 2 이미지 프레임 클래스

	static int w = 1050, h = 750; // 게임 창의 크기 정보

	MyPanel panel = new MyPanel();

	Stage2() { // 첫 시작 화면 프레임 생성자
		setTitle("Stage 2"); // 프레임 객체의 타이틀생성
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 객체의 X버튼을 눌렀을 때 정상적으로 닫히게 한다.
		setContentPane(panel); // 패널을 프레임에 단다.

		setSize(w, h); // 초기에 지정한 사이즈로 프레임크기를 맞춘다.

		setVisible(true); // 패널과 프레임을 visible 하게 한다.

		this.addKeyListener(this); // 키보드 키입력을 받을 수 있게 한다.
	}

	class MyPanel extends JPanel {
		ImageIcon image = new ImageIcon("background/stage2.jpg"); // 패널에 이미지를 넣어서 Graphics 를 사용해 출력한다.
		Image img = image.getImage(); // 이미지 객체에 이미지를 불러온다.

		public void paintComponent(Graphics g) {
			super.paintComponent(g); // 상속받은 부모의 이미지를 먼저그린다.
			g.drawImage(img, 0, 0, w, h, null); // 이미지를 그린다.
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) { // 게임 시작전 마지막 화면 클래스이므로 이번 화면에서 스페이스바 입력시
			Game game = new Game(); // 게임 클래스를 호출해 게임 시작
			Timer t = new Timer();
			t.schedule(game, 0, 33);
			this.setVisible(false);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}

class Stage3 extends JFrame implements KeyListener { // stage 3 이미지 프레임 클래스

	static int w = 1050, h = 750; // 게임 창의 크기 정보

	MyPanel panel = new MyPanel();

	Stage3() { // 첫 시작 화면 프레임 생성자
		setTitle("Stage 3"); // 프레임 객체의 타이틀생성
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 객체의 X버튼을 눌렀을 때 정상적으로 닫히게 한다.
		setContentPane(panel); // 패널을 프레임에 단다.

		setSize(w, h); // 초기에 지정한 사이즈로 프레임크기를 맞춘다.

		setVisible(true); // 패널과 프레임을 visible 하게 한다.

		this.addKeyListener(this); // 키보드 키입력을 받을 수 있게 한다.
	}

	class MyPanel extends JPanel {
		ImageIcon image = new ImageIcon("background/stage3.jpg"); // 패널에 이미지를 넣어서 Graphics 를 사용해 출력한다.
		Image img = image.getImage(); // 이미지 객체에 이미지를 불러온다.

		public void paintComponent(Graphics g) {
			super.paintComponent(g); // 상속받은 부모의 이미지를 먼저그린다.
			g.drawImage(img, 0, 0, w, h, null); // 이미지를 그린다.
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) { // 게임 시작전 마지막 화면 클래스이므로 이번 화면에서 스페이스바 입력시
			Game game = new Game(); // 게임 클래스를 호출해 게임 시작
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

	static int w = 1050, h = 750; // 게임 창의 크기 정보

	MyPanel1 panel1 = new MyPanel1();

	GameVictory() { // 첫 시작 화면 프레임 생성자
		setTitle("Game Victory"); // 프레임 객체의 타이틀생성
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 객체의 X버튼을 눌렀을 때 정상적으로 닫히게 한다.
		setContentPane(panel1); // 패널을 프레임에 단다.

		setSize(w, h); // 초기에 지정한 사이즈로 프레임크기를 맞춘다.

		setVisible(true); // 패널과 프레임을 visible 하게 한다.

		this.addKeyListener(this); // 키보드 키입력을 받을 수 있게 한다.
	}

	class MyPanel1 extends JPanel {
		ImageIcon image1 = new ImageIcon("background/gameVictory.jpg"); // 패널에 이미지를 넣어서 Graphics 를 사용해 출력한다.
		Image img = image1.getImage(); // 이미지 객체에 이미지를 불러온다.

		public void paintComponent(Graphics g) {
			super.paintComponent(g); // 상속받은 부모의 이미지를 먼저그린다.
			g.drawImage(img, 0, 0, w, h, null); // 이미지를 그린다.
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
		if (e.getKeyCode() == KeyEvent.VK_SPACE) { // 게임 시작전 마지막 화면 클래스이므로 이번 화면에서 스페이스바 입력시
			Game game = new Game(); // 게임 클래스를 호출해 게임 시작
			Timer t = new Timer();
			t.schedule(game, 0, 33);
			this.setVisible(false);
		}
	}
}

class GameOver extends JFrame implements KeyListener {

	static int w = 1050, h = 750; // 게임 창의 크기 정보

	MyPanel1 panel1 = new MyPanel1();

	GameOver() { // 첫 시작 화면 프레임 생성자
		setTitle("Game Over"); // 프레임 객체의 타이틀생성
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 객체의 X버튼을 눌렀을 때 정상적으로 닫히게 한다.
		setContentPane(panel1); // 패널을 프레임에 단다.

		setSize(w, h); // 초기에 지정한 사이즈로 프레임크기를 맞춘다.

		setVisible(true); // 패널과 프레임을 visible 하게 한다.

		this.addKeyListener(this); // 키보드 키입력을 받을 수 있게 한다.
	}

	class MyPanel1 extends JPanel {
		ImageIcon image1 = new ImageIcon("background/gameOver.jpg"); // 패널에 이미지를 넣어서 Graphics 를 사용해 출력한다.
		Image img = image1.getImage(); // 이미지 객체에 이미지를 불러온다

		public void paintComponent(Graphics g) {
			super.paintComponent(g); // 상속받은 부모의 이미지를 먼저그린다.
			g.drawImage(img, 0, 0, w, h, null); // 이미지를 그린다.
			g.setFont(new Font("궁서체", Font.PLAIN, 60));
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(Game.score), 550, 315); // score 출력부분, gameOver 일때 전체 스코어를 출력한다.
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
		if (e.getKeyCode() == KeyEvent.VK_SPACE) { // 게임 시작전 마지막 화면 클래스이므로 이번 화면에서 스페이스바 입력시
			Game game = new Game(); // 게임 클래스를 호출해 게임 시작
			Timer t = new Timer();
			t.schedule(game, 0, 33);
			this.setVisible(false);
		}
	}

}