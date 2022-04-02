package inf101.game.view;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

public class WallBorder implements Border{
    public int width;
    public int height;

    public WallBorder(int width, int height){
        this.width = width;
        this.height = height;
    }


  

    @Override
	public Insets getBorderInsets(Component c) {
		return new Insets(20, 20, 20, 20);
    }

	

    @Override
    public boolean isBorderOpaque() {
        return true;
    }




    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
       g.drawLine(x, y, width, 0); // roof
       g.drawLine(x, y, 0, height); // righth wall
       g.drawLine(width-1,0,width-1 ,height); //height,width); //  // roof
       g.drawLine(0, height-1, width, height-1);
       //g.drawLine(x, y, 0, height); // righth wall


        
    }
}
