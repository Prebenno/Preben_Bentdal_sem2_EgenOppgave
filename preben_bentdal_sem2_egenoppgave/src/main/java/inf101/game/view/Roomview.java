package inf101.game.view;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JPanel;

import inf101.backround.Floor;
import inf101.grid.itemWithCoordinate;
import inf101.model.Pixel;

import java.awt.Dimension;

public class Roomview extends JComponent {
    public iRoomview view;

    public Roomview(iRoomview myfloor){
        this.view = myfloor;
    }


    @Override
    public void paint(Graphics canvas) {
        super.paint(canvas);
        int componentWidth = this.getWidth();
        int componentHeight = this.getHeight();
        this.drawRoom(canvas, componentWidth, componentHeight);
        this.drawPlayer(canvas,componentWidth,componentHeight);
        
    }
    @Override
    public Dimension getPreferredSize() {
        int width = 700;
        int height = 750;
        return new Dimension(width, height);
    }

    public void drawRoom(Graphics canvas,int width, int height){
        int x = 0;
        int y = 0;
        canvas.setColor(Color.RED); // check if whole backround is drawn
        canvas.fillRect(x, y, width, height);
        for (itemWithCoordinate<Pixel> pixel : view.getPixels()) {
            int row = pixel.getCoordinate().getRow();
            int col = pixel.getCoordinate().getColumn();
            Color PixelColor = pixel.getItem().getColor();
            int tileX = x + row * width / this.view.getRows() ; //inspired by sampleview
            int tileY = y + col * height / this.view.getColumns();
            int nextTileX = x + (row + 1) * width /this.view.getRows();
            int nextTileY = y + (col + 1) * height / this.view.getColumns();
            int tileWidth = nextTileX - tileX;
            int tileHeight = nextTileY - tileY;
            this.drawPixel(canvas, tileX, tileY, tileHeight, tileWidth,PixelColor);

            
        }

    }

    public void drawPlayer(Graphics canvas,int width,int height){
        int x_Position = view.getCenter().getRow();
        int y_Position = view.getCenter().getColumn();
        int width2 = this.view.getWidth();
        int height2 = this.view.getHeight();
        for (itemWithCoordinate<Pixel> pixel : this.view.getSpritePixels()) {
            int row = pixel.getCoordinate().getRow();
            int col = pixel.getCoordinate().getColumn();
            Color PixelColor = pixel.getItem().getColor();
            int tileX = x_Position + row * width / this.view.getRows() ; //inspired by sampleview
            int tileY = y_Position + col * height / this.view.getColumns();
            int nextTileX = x_Position + (row + 1) * width /this.view.getRows();
            int nextTileY = y_Position + (col + 1) * height / this.view.getColumns();
            int tileWidth = nextTileX - tileX;
            int tileHeight = nextTileY - tileY;
            this.drawPixel(canvas, tileX, tileY, tileHeight, tileWidth,PixelColor); 
        }
    }

    private void drawPixel(Graphics canvas, int tileX, int tileY, int tileHeight, int tileWidth, Color PixelColor) {
        canvas.setColor(PixelColor);
        canvas.fillRect(tileX, tileY, tileWidth, tileHeight);
    }

    
}