package COVID19;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;




public class Game extends TimerTask{
	// 창의 크기 정보
	final static int w = 1280, h = 720;
	// 창의 위치를 가운데에 놓기 위함
	final int x = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - w / 2;
	final int y = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - h / 2;
	static int time;
	
	static JFrame f;
	BufferedImage b;
	Graphics g;
	
	VirusManager vm;
	
	public Game() {
		f = new JFrame("COVID19");
		f.setBounds(x, y, w, h);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.addKeyListener(KeyManager.getInstance());
		
		b = new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR);
		g = b.getGraphics();
		
		vm = new VirusManager();
		
		f.setVisible(true);
	}
	
	@Override
	public void run() {
		time++;
		
		draw();
		update();
		if(vm.isGameOver == true) {
			f.dispose();
			vm.isGameOver = false;
			vm.stage = 1;
			KeyManager k = KeyManager.getInstance();
			k.instance = null;
			new GameOver();
		}
		if(vm.isGameClear == true) {
			f.dispose();
			vm.isGameClear = false;
			vm.stage++;
			KeyManager k = KeyManager.getInstance();
			k.instance = null;
			if(vm.stage == 1)
				new GameMenu();
			else if(vm.stage == 2)
				new Stage2();
			else
				new Stage3();
		}
	}
	
	public void draw() {
		g.setColor(Color.white);
		g.fillRect(0, 0, w, h);
		
		vm.draw(g);
		
		f.getGraphics().drawImage(b, 0, 0, w, h, f);
	}
	
	public void update() {
		vm.update();
	}
}
