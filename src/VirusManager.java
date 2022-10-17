package COVID19;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class VirusManager {
   Player player;
   ArrayList<Virus> virus;
   ArrayList<Item> item;
   
   int lifeCount = 3;
   boolean isGameOver = false;
   boolean isGameClear = false;
   boolean up = false, down = false, left = false, right = false;
   int stage = 1;
   int count = 0;
   int score = 0;
   
   public VirusManager() {
      player = new Player(Game.w/2, Game.h/2, 0, 50) {
         public void update() { 
            KeyManager k = KeyManager.getInstance();
            if(k.up) {
               up = true;
               if(speed_y > -10) speed_y -= playerSpeed;
            } 
            else if(up) {
               if(speed_y == 0) up = false;
               else if(speed_y < 0) speed_y += playerSpeed;
            }
            
            if(k.down) {
               down = true;
               if(speed_y < 10) speed_y += playerSpeed;
            } 
            else if(down) {
               if(speed_y == 0) down = false;
               else if(speed_y > 0) speed_y -= playerSpeed;
            }
            
            if(k.right) {
               right = true;
               if(speed_x < 10) speed_x += playerSpeed;
            } 
            else if(right) {
               if(speed_x == 0) right = false;
               else if(speed_x > 0) speed_x -= playerSpeed;
            }
            
            if(k.left) {
               left = true;
               if(speed_x > -10) speed_x-=playerSpeed;
            } 
            else if(left) {
               if(speed_x == 0) left = false;
               else if(speed_x < 0) speed_x += playerSpeed;
            }
            
            x += speed_x;
            y += speed_y;
            
         }
      };
      
      virus = new ArrayList<Virus>();
      item= new ArrayList<Item>();
      
   }
   
   public void draw(Graphics g) {
      player.draw(g);
      for(int i = 0; i < virus.size(); i++) {
         virus.get(i).draw(g);
      }
      for(int i = 0; i < item.size(); i++) {
         item.get(i).draw(g);
      }
   }
   
   public void update() {
      player.update();
      //���̷����� �浹
      for(int i = 0; i < virus.size(); i++) {
         virus.get(i).update();
         if(virusCrashCheck(i)) {
            if(virus.get(i).size < player.size) {
               virus.remove(i);
               player.size += 5;
               count++;
               score += 100;
               if(i >= virus.size()) break;
            }
               else if((virus.get(i).size >= player.size ) && (lifeCount > 1)) {
                   lifeCount--;
                   player.size -= 5;
                   virus.remove(i);
                   if(i >= virus.size()) break;
                }
                
                else if((virus.get(i).size >= player.size ) && (lifeCount <= 1)) {
                   lifeCount = 0;
                   isGameOver = true/*System.exit(0)*/;
                }

         }
         //��Ƹ��� ���� ���ڰ�4���� ���� �� ���� Ŭ����
         if(count == 2)
            isGameClear = true;
         
         
         //ȭ������� ���� �� 
         if(virus.get(i).x + virus.get(i).w < 0 || virus.get(i).x > Game.w)
            virus.remove(i);
      }
      
      //�����۰� �浹
      for(int i = 0; i < item.size(); i++) {
         item.get(i).update();
         if(itemCrashCheck(i)) {
            item.remove(i);
            break;
         }
               
         //ȭ������� ���� �� 
         if(item.get(i).x + item.get(i).w < 0 || item.get(i).x > Game.w)
            item.remove(i);
      }
      
      // ũ�Ⱑ Ŀ�� ���� ���� �ֱⰡ �����
      if(Game.time % (player.size) == 0) createVirus();
      if(Game.time % (player.size) == 0) createItem();
   }
   
   public void createVirus() {
      // randLocation = �����¿츦 �Ǵ� (0 = ��, 1 = ��, 2 = ��, 3 = ��)
      int randLocation = 1;
      
      if(stage == 2) randLocation = (int) (Math.random() * 2);
      if(stage == 3) randLocation = (int) (Math.random() * 4);
      
      int speed = (int) (Math.random() * 10 + 3);
      if(randLocation == 1 || randLocation == 3) speed *= -1;
      
      // 
      if(randLocation < 2)
         virus.add(new Virus(randLocation * 1050, (int) (Math.random() * 650 + 100), speed, false, (int) (Math.random() * 20 + player.size - 10), (int)(Math.random() * 5)));
      else
         virus.add(new Virus((int) (Math.random() * 1050), (randLocation-2) * 650 + 100, speed, true, (int) (Math.random() * 20 + player.size - 10), (int)(Math.random() * 5)));         
   }
   
   
   public void createItem() {
      // randLocation = �����¿츦 �Ǵ� (0 = ��, 1 = ��, 2 = ��, 3 = ��)
      int randLocation = 1;
      
      if(stage == 2) randLocation = (int) (Math.random() * 2);
      if(stage == 3) randLocation = (int) (Math.random() * 4);
      
      int speed = (int) (Math.random() * 10 + 3);
      if(randLocation == 1 || randLocation == 3) speed *= -1;
      
      // 
      if(randLocation < 2)
         item.add(new Item(randLocation * 1050, (int) (Math.random() * 650 + 100), speed, false, player.size - 5, (int)(Math.random() * 4)));
      else
         item.add(new Item((int) (Math.random() * 1050), (randLocation-2) * 650 + 100, speed, true, player.size - 5, (int)(Math.random() * 4)));         
   }
   
   
   public boolean virusCrashCheck(int i) {
      if(Math.sqrt((player.x-virus.get(i).x)*(player.x-virus.get(i).x)+(player.y-virus.get(i).y)*(player.y-virus.get(i).y))
            < player.r + virus.get(i).r)
         return true;
      
      return false;
   }
   
   public boolean itemCrashCheck(int i) {
      if(Math.sqrt((player.x-item.get(i).x)*(player.x-item.get(i).x)+(player.y-item.get(i).y)*(player.y-item.get(i).y))
            < player.r + item.get(i).r)
         return true;
      
      return false;
   }
}
