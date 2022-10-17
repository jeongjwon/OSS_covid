package COVID19;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Item {
   Image image;
   Toolkit tool;
   
   //������ �����Ӽ�
   int x, y, w, h, r;
   int itemType;
   
   //���̷��� ������ �Ӽ�
   boolean up, down, left, right;
   boolean isVertex;
   int speed_y, speed_x;
   int speed;
   
   public Item(int x, int y, int speed, boolean isVertex, int size, int itemType) {
      this.x = x;
      this.y = y;
      this.speed = speed;
      this.w = size;
      this.h = size;
      this.r = size/2;
      this.isVertex = isVertex;
      this.itemType = itemType;
      
      up = false;
      down = false;
      left = false;
      right = false;
      speed_y = 0;
      speed_x = 0;
      
      tool = Toolkit.getDefaultToolkit();
      if(itemType == 1)
         image = tool.getImage("items/item1.png");
      else if(itemType == 2)
         image = tool.getImage("items/item2.png");
      else if(itemType == 3)
         image = tool.getImage("items/item3.png");
      else
         image = tool.getImage("items/item4.png");

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
