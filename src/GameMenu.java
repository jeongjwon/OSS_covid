package COVID19;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class GameOver extends JFrame implements KeyListener{
	 // â�� ũ�� ����
	   final static int w = 1050, h = 750;
	   // â�� ��ġ�� ����� ���� ����
	   final int x = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - w / 2;
	   final int y = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - h / 2;
	   MyPanel panel =new MyPanel();
	   GameOver() {
		   setTitle("Anti Corona");
		   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   setContentPane(panel);
		   setSize(w,h);
		   
		   setVisible(true);
		   
		   this.addKeyListener(this);
	   }
	  
	   
	   class MyPanel extends JPanel{
		   ImageIcon image = new ImageIcon("background/gameOver.jpg");
		   Image img = image.getImage();
		   public void paintComponent(Graphics g) {
			   super.paintComponent(g);
			   g.drawImage(img, 0, 0, w, h, null);
		   }
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
	 // â�� ũ�� ����
	   final static int w = 1050, h = 750;
	   // â�� ��ġ�� ����� ���� ����
	   final int x = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - w / 2;
	   final int y = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - h / 2;
	   MyPanel panel =new MyPanel();
	   Stage2() {
		   setTitle("Anti Corona");
		   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   setContentPane(panel);
		   setSize(w,h);
		   
		   setVisible(true);
		   
		   this.addKeyListener(this);
	   }
	  
	   
	   class MyPanel extends JPanel{
		   ImageIcon image = new ImageIcon("background/stage2.jpg");
		   Image img = image.getImage();
		   public void paintComponent(Graphics g) {
			   super.paintComponent(g);
			   g.drawImage(img, 0, 0, w, h, null);
		   }
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
	 // â�� ũ�� ����
	   final static int w = 1050, h = 750;
	   // â�� ��ġ�� ����� ���� ����
	   final int x = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - w / 2;
	   final int y = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - h / 2;
	   MyPanel panel =new MyPanel();
	   Stage3() {
		   setTitle("Anti Corona");
		   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   setContentPane(panel);
		   setSize(w,h);
		   
		   setVisible(true);
		   
		   this.addKeyListener(this);
	   }
	  
	   
	  class MyPanel extends JPanel{
		   ImageIcon image = new ImageIcon("background/stage3.jpg");
		   Image img = image.getImage();
		   public void paintComponent(Graphics g) {
			   super.paintComponent(g);
			   g.drawImage(img, 0, 0, w, h, null);
		   }
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
   // â�� ũ�� ����
   final static int w = 1050, h = 750;
   // â�� ��ġ�� ����� ���� ����
   final int x = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - w / 2;
   final int y = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - h / 2;
   MyPanel panel =new MyPanel();
   GameMenu() {
	   setTitle("Anti Corona");
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   setContentPane(panel);
	   setSize(w,h);
	   
	   setVisible(true);
	   
	   this.addKeyListener(this);
   }
  
   
   class MyPanel extends JPanel{
	   ImageIcon image = new ImageIcon("background/stage1.jpg");
	   Image img = image.getImage();
	   public void paintComponent(Graphics g) {
		   super.paintComponent(g);
		   g.drawImage(img, 0, 0, w, h, null);
	   }
   }
   
   @Override
   public void keyTyped(KeyEvent e) {}
   @Override
   public void keyPressed(KeyEvent e) {
      if(e.getKeyCode() == KeyEvent.VK_SPACE) {
         Game game = new Game();
         Timer t = new Timer();
//         KeyManager k = KeyManager.getInstance();
//         k.instance = null;
         t.schedule(game, 0, 33);
         this.setVisible(false);
      }
   }
   
   @Override
   public void keyReleased(KeyEvent e) {}
   public static void main(String [] args) {
	   new GameMenu();
	 
   }
   
}
