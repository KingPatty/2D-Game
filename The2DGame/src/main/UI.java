package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_Key;

public class UI {
	
	GamePanel gp;
	Font georgia_40, georgia_90;
	BufferedImage keyImage;
	public boolean messageOn = false;
	public String message = "";
	public int messageCounter = 0;
	public boolean gameOver = false;
	
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	public UI(GamePanel gp) {
		
		this.gp = gp;
		georgia_40 = new Font("Georgia", Font.BOLD, 40);
		georgia_90 = new Font("Georgia", Font.BOLD, 90);
		OBJ_Key key = new OBJ_Key();
		keyImage = key.image;
	}
	public void showMessage(String text) {
		
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		
		if(gameOver == true) {
			
			g2.setFont(georgia_40);
			g2.setColor(Color.white);
			
			String text;
			int textLength;
			int x;
			int y;
			
			text = "You found the treasure!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 - (gp.tileSize*3);
			g2.drawString(text, x, y);
			
			text = "Your time is : " + dFormat.format(playTime) + "!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 + (gp.tileSize*4);
			g2.drawString(text, x, y);
			
			g2.setFont(georgia_90);
			g2.setColor(Color.red);
			text = "Congratulation!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 + (gp.tileSize*2);
			g2.drawString(text, x, y);
			
			gp.gameThread = null;
			
		}
		else {
		g2.setFont(georgia_40);
		g2.setColor(Color.white);
		g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
		g2.drawString("x = "+ gp.player.hasKey, 74, 65);
		
		//Time 
		playTime +=(double)1/60;
		g2.drawString("Time:"+ dFormat.format(playTime), gp.tileSize*11, 65);
		
		//message
		if(messageOn == true ) {
			
			g2.setFont(g2.getFont().deriveFont(15F));
			g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
			
			messageCounter++;
			
			if(messageCounter > 120) {
				messageCounter = 0;
				messageOn = false;
				}
			}
		}
	}
}
