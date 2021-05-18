package COVID19;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JLabel;

class GameOver extends JFrame implements KeyListener{
	// 창의 크기 정보
	final static int w = 1280, h = 720;
	// 창의 위치를 가운데에 놓기 위함
	final int x = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - w / 2;
	final int y = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - h / 2;
	
	JLabel label1, label2;
	
	GameOver() {
		super("Game Over!");
		this.setVisible(true);
        this.setBounds(x, y, w, h);
        this.setLayout(null);
        label1 = new JLabel("Game Over!");
        label1.setBounds(170, 50, 600, 150);  
        label1.setFont(new Font("궁서체", 30, 50));   
        add(label1);
        
        label2 = new JLabel("<COVID-19>");
        label2.setBounds(150, 300, 800, 150);  
        label2.setFont(new Font("궁서체", 30, 70));   
        add(label2);
        
        this.addKeyListener(this);
        
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			Game game = new Game();
			Timer t = new Timer();
			
			t.schedule(game, 0, 33);
			this.setVisible(false);
		}	
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}

}

class Stage2 extends JFrame implements KeyListener{
	// 창의 크기 정보
	final static int w = 1280, h = 720;
	// 창의 위치를 가운데에 놓기 위함
	final int x = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - w / 2;
	final int y = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - h / 2;
	
	JLabel label1, label2;
	
	Stage2() {
		super("Stage2");
		this.setVisible(true);
        this.setBounds(x, y, w, h);
        this.setLayout(null);
        label1 = new JLabel("Stage2");
        label1.setBounds(170, 50, 600, 150);  
        label1.setFont(new Font("궁서체", 30, 50));   
        add(label1);
        
        label2 = new JLabel("<COVID-19>");
        label2.setBounds(150, 300, 800, 150);  
        label2.setFont(new Font("궁서체", 30, 70));   
        add(label2);
        
        this.addKeyListener(this);
        
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			Game game = new Game();
			Timer t = new Timer();
			
			t.schedule(game, 0, 33);
			this.setVisible(false);
		}	
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}

}

class Stage3 extends JFrame implements KeyListener{
	// 창의 크기 정보
	final static int w = 1280, h = 720;
	// 창의 위치를 가운데에 놓기 위함
	final int x = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - w / 2;
	final int y = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - h / 2;
	
	JLabel label1, label2;
	
	Stage3() {
		super("Stage3");
		this.setVisible(true);
        this.setBounds(x, y, w, h);
        this.setLayout(null);
        label1 = new JLabel("Stage3");
        label1.setBounds(170, 50, 600, 150);  
        label1.setFont(new Font("궁서체", 30, 50));   
        add(label1);
        
        label2 = new JLabel("<COVID-19>");
        label2.setBounds(150, 300, 800, 150);  
        label2.setFont(new Font("궁서체", 30, 70));   
        add(label2);
        
        this.addKeyListener(this);
        
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			Game game = new Game();
			Timer t = new Timer();
			
			t.schedule(game, 0, 33);
			this.setVisible(false);
		}	
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}

}


class GameMenu extends JFrame implements KeyListener{
	// 창의 크기 정보
	final static int w = 1280, h = 720;
	// 창의 위치를 가운데에 놓기 위함
	final int x = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - w / 2;
	final int y = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - h / 2;
	
	JLabel label1, label2, label3;
	JFrame gameMenu;
	
	GameMenu() {
		super("시작 화면");
		this.setVisible(true);
        this.setBounds(x, y, w, h);
        this.setLayout(null);
        label1 = new JLabel("Open Source");
        label1.setBounds(170, 50, 600, 150);  
        label1.setFont(new Font("궁서체", 30, 50));   
        add(label1);
        
        label2 = new JLabel("<COVID-19> STAGE 1");
        label2.setBounds(150, 300, 800, 150);  
        label2.setFont(new Font("궁서체", 30, 70));   
        add(label2);
        
        label3 = new JLabel("press spacebar to start...");
        label3.setBounds(190, 500, 800, 150);  
        label3.setFont(new Font("궁서체", 30, 30));   
        add(label3);
        
        this.addKeyListener(this);

		
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			Game game = new Game();
			Timer t = new Timer();
//			KeyManager k = KeyManager.getInstance();
//			k.instance = null;
			t.schedule(game, 0, 33);
			this.setVisible(false);
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {}
}
