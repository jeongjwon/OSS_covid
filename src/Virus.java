package COVID19;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;


public class Virus {
   Image image;
   Toolkit tool;
   
   //���̷��� �����Ӽ�
   int x, y, w, h, r;
   int size;
   
   //���̷��� ������ �Ӽ�
   boolean up, down, left, right;
   boolean isVertex;
   
   int virusType;
   int speed_y, speed_x;
   int speed;
   
   public Virus(int x, int y, int speed, boolean isVertex, int size, int virusType) {
      this.x = x;
      this.y = y;
      this.speed = speed;
      this.size = size;
      this.w = size;
      this.h = size;
      this.r = size/2;
      this.isVertex = isVertex;
      this.virusType = virusType;
      
      up = false;
      down = false;
      left = false;
      right = false;
      speed_y = 0;
      speed_x = 0;
      tool = Toolkit.getDefaultToolkit();
      image = tool.getImage("virus/1.png");
      
   }
   
   public void draw(Graphics g) {
//      g.setColor(c);
//      g.fillOval(x, y, w, h);

      g.drawImage(image, x, y, w, h, null);
   }
   
   public void update() {
      if(isVertex == false)
         x += speed;
      else
         y += speed;
   }

}
