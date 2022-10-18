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

public class Game extends TimerTask { // 게임 클래스

	static int w = 1050, h = 750; // 게임 창의 크기 정보

	static int time; // 스레드를 대신할 타이머를 위한 타임 멤버변수
	static int score = 0; // 게임 스코어
	static JFrame f; // 게임 프레임

	BufferedImage b; // 이미지파일을 불러오기 위해 버퍼이미지 멤버변수 선언
	Graphics g; // 패널에 이미지를 그리기 위한 Graphics 객체

	Image image; // 불러온 이미지를 저장하여 사용하기 위한 Image 객체
	Image life; // life 부분의 이미지를 저장하기 위한 Image 객체

	Toolkit tool; // getImage() 등의 메소드를 사용하기 위한 tool 객체
	Clip clip; // 음악을 재생하기 위한 Clip 객체

	ObjectManager om; // ObjectManager 객체
	Stage1 stage1;

	JPanel p; // 프레임에 달 패널객체
	JLabel lifeLabel; // 라이프를 달 라이프 패널객체

	static boolean soundFlag = false; // 음악의 On/Off 를 제어하기 위한 flag 멤버변수

	public void Play(String fileName) { // 음악 재생 메소드
		try { // 오류 방지를 위한 try catch 문
			clip = AudioSystem.getClip(); // clip 객체에 음악을 불러온다.
			URL url = getClass().getResource("" + fileName); // URL을 이용하여 음악파일을 불러온다.
			AudioInputStream ais = AudioSystem.getAudioInputStream(url);
			clip.open(ais); // 음악을 실행
			clip.loop(-1);
		} catch (Exception ex) {
		}
	}

	public Game() {
		f = new JFrame("COVID19"); // 프레임생성
		f.setBounds(0, 0, w, h); // 프레임 바운드 설정
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임의 X버튼 클릭시 정상적인 종료를 위함
		f.addKeyListener(KeyManager.getInstance()); // 프레임이 키보드 키 입력을 받을 수 있게 한다

		b = new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR); // 버퍼이미지를 이용하여 이미지를 불러온다.
		g = b.getGraphics(); // 이미지를 Graphics 객체에 저장

		om = new ObjectManager(); // VirusManager 생성

		p = new JPanel(); // 패널생성

		f.setVisible(true); // 프레임을 Visible 하게한다

		stage1.clip.stop(); // 초기화면의 음악을 Off 한다
		if (soundFlag == false) // 전음악을 종료한 시점에서 다음음악을 재생한다.
			Play("background.wav");
		soundFlag = true;
	}

	@Override
	public void run() { // 타이머 클래스의 run 메소드 구현
		time++; // 타임이 계속 흐름

		draw(); // 한 타임에 한번씩 이미지를 그림
		update(); // 한 타임당 한번씩 바이러스의 충돌검사

		if (om.isGameOver == true) { // isGameOver값이 true 게임 오버시
			f.dispose(); // 전 프레임 하나만을 종료
			om.isGameOver = false; // VirusManager 객체인 om의 isGameOver에 false값을 넣어 초기화한다.
			om.stage = 1; // om의 stage를 다시 1로 초기화한다.
			KeyManager k = KeyManager.getInstance(); // 기존 메모리에 새로운 KeyManager를 생성 및 할당한다.
			k.instance = null; // instance를 null로 초기화하므로 키 값을 초기화한다.
			score = om.score; // gameOver시에 score를 출력하기 위해 game의 멤버변수 score에 om의 멤버변수 score 값을 저장한다.
			new GameOver(); // GameOver 화면을 생성한다.
		}
		if (om.isGameClear == true) { // isGameClear값이 true 게임 클리어시
			f.dispose(); // 전 프레임 하나만을 종료
			om.isGameClear = false; // VirusManager 객체인 om의 isGameClear에 false값을 넣어 초기화한다.
			om.stage++; // om의 다음스테이지로 가기위해 stage값을 증가시킨다.
			KeyManager k = KeyManager.getInstance(); // 기존 메모리에 새로운 KeyManager를 생성 및 할당한다.
			k.instance = null;

			if (om.stage == 1) // 어떤 스테이지인지 알기위해 stage 멤버변수값에 따라 stage를 호출한다.
				new Stage1(); // om의 stage값이 1일때 Stage1 호출
			else if (om.stage == 2)
				new Stage2(); // om의 stage값이 2일때 Stage2 호출
			else if (om.stage == 3) {
				new Stage3(); // om의 stage값이 3일때 Stage3 호출
			} else { // om의 stage값이 4이상일때 om의 stage값을 1로 초기화 하고 GameVictory를 생성
				om.stage = 1;
				new GameVictory();
			}
		}
	}

	public void draw() {

		tool = Toolkit.getDefaultToolkit(); // Toolkit클래스를 이용해 이미지파일을 불러온다.
		if (om.stage == 1) // 스테이지 멤버변수의 값에 따른 배경이미지호출
			image = tool.getImage("background/chinaBackground.jpg"); // Stage1 중국배경이미지를 불러온다.
		else if (om.stage == 2)
			image = tool.getImage("background/japanBackground.jpg"); // Stage2 일본배경이미지를 불러온다.
		else if (om.stage == 3)
			image = tool.getImage("background/koreaBackground.jpg"); // Stage3 한국배경이미지를 불러온다.

		life = tool.getImage("items/item1.png"); // 라이프 이미지를 tool을 사용하여 불러온다.
		g.drawImage(image, 0, 0, w, h, null); // 배경이미지를 그린다.
		g.drawImage(life, 65, 75, 30, 30, null); // 라이프이미지를 그린다.
		om.draw(g); // 바이러스객체를 그린다.

		g.setColor(Color.black);
		g.setFont(new Font("고딕", Font.BOLD, 30));
		g.drawString("X " + om.lifeCount, 100, 100); // 라이프값
		g.drawString("SCORE : " + om.score, 800, 100); // 스코어값

		f.getGraphics().drawImage(b, 0, 0, w, h, f);

	}

	public void update() {
		om.update(); // om의 업데이트 메소드를 불러온다.
	}
}