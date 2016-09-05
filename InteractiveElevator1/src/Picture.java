import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Picture extends JPanel{
   private static ImageIcon image = new ImageIcon("img/sun.png");
   static int x = 0, y = 120;
   public Picture(){
      this.setBounds(820,400,200,150);
   }
   public static void runa(int h, int m){
	   if(h >= 6 && h < 18)
	   {
		   image = new ImageIcon("img/sun.png");
		   if(h == 6 && m == 0)
		   {
			   x = 0; y = 120;
		   }
	   }
	   else
	   {
		   image = new ImageIcon("img/moon.png");
		   if(h == 18 && m == 0)
		   {
			   x = 0; y = 120;
		   }
	   }
	   if(h == 6 || h == 18){
		   x = 0; y = 120;
	   }
	   else if (h == 7 || h == 19){
		   x = 15; y = 100;
	   }
	   else if (h == 8 || h == 20){
		   x = 30; y = 80;
	   }
	   else if (h == 9 || h == 21){
		   x = 45; y = 60;
	   }
	   else if (h == 10 || h == 22){
		   x = 60; y = 40;
	   }
	   else if (h == 11 || h == 23){
		   x = 75; y = 20;
	   }
	   else if (h == 12 || h == 0){
		   x = 85; y = 0;
	   }
	   else if (h == 13 || h == 1){
		   x = 100; y = 20;
	   }
	   else if (h == 14 || h == 2){
		   x = 115; y = 40;
	   }
	   else if (h == 15 || h == 3){
		   x = 130; y = 60;
	   }
	   else if (h == 16 || h == 4){
		   x = 145; y = 80;
	   }
	   else if (h == 17 || h == 5){
		   x = 160; y = 100;
	   }
	   else if (h == 18 || h == 6){
		   x = 170; y = 120;
	   } 
   }
   public Picture(int h){
      this.setBounds(600,400,400,170);
   }

   @Override
   public void paintComponent(Graphics g) {
      g.drawImage(image.getImage(), x, y, null);
      setOpaque(false);
      super.paintComponent(g);
      super.repaint();
   }
}