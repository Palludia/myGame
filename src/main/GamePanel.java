package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.INPUT_STREAM;
import javax.swing.JPanel;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

public class GamePanel extends JPanel{
	
	private MouseInputs mouseInputs;
	private float xDelta = 100;
	private float yDelta = 100;
	private BufferedImage img;
	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 15;
	private int playerAction = IDLE;
	private int playerDir = -1;
	private boolean moving = false;
	public GamePanel() {
		mouseInputs = new MouseInputs(this);
		importImg();
		loadAnimations();
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}
	
	private void loadAnimations() {
		animations = new BufferedImage[7][8];
		for(int j = 0; j<animations.length; j++) {
			for(int i = 0; i<animations[j].length;i++) {
				animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
			}
		}
	}

	private void importImg() {
		InputStream is = getClass().getResourceAsStream("/player_sprites.png");
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void setPanelSize() {
		Dimension size = new Dimension(1280,720);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}
	
	public void setDirection(int direction) {
		this.playerDir = direction;
		moving = true;
	}
	
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		updateAnimationTick();
		setAnimation();
		updatePos();
		g.drawImage(animations[playerAction][aniIndex], (int)xDelta, (int)yDelta, 256, 160, null);
	}

	private void updatePos() {
		if(moving) {
			switch(playerDir) {
			case LEFT:
				xDelta -=5;
				break;
			case UP:
				yDelta -=5;
				break;
			case RIGHT:
				xDelta += 5;
				break;
			case DOWN:
				yDelta += 5;
				break;
			}
		}
	}

	private void setAnimation() {
		if(moving) {
			playerAction = RUNNING;
		}else {
			playerAction = IDLE;
		}
	}

	private void updateAnimationTick() {
		aniTick++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if(aniIndex >= GetSpriteAmount(playerAction)) {
				aniIndex = 0;
			}
		}
	}
	
}
