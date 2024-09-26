package inputs;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import static utilz.Constants.Directions.*;
import main.GamePanel;

public class KeyboardInputs implements KeyListener{
	private GamePanel gamePanel;
	private Set<Integer> keysHeld = new HashSet<Integer>();
	
	public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keysHeld.add(e.getKeyCode());
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
			gamePanel.setDirection(UP);
			gamePanel.setMoving(true);
			break;
		case KeyEvent.VK_A:
			gamePanel.setDirection(LEFT);
			gamePanel.setMoving(true);
			break;
		case KeyEvent.VK_S:
			gamePanel.setDirection(DOWN);
			gamePanel.setMoving(true);
			break;
		case KeyEvent.VK_D:
			gamePanel.setDirection(RIGHT);
			gamePanel.setMoving(true);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keysHeld.remove(e.getKeyCode());
		if(keysHeld.isEmpty()) {
			gamePanel.setMoving(false);
			return;
		}
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
		case KeyEvent.VK_A:
		case KeyEvent.VK_S:
		case KeyEvent.VK_D:
			if(keysHeld.contains(KeyEvent.VK_W)) {
				gamePanel.setDirection(UP);
			}else if(keysHeld.contains(KeyEvent.VK_A)) {
				gamePanel.setDirection(LEFT);
			}else if(keysHeld.contains(KeyEvent.VK_S)) {
				gamePanel.setDirection(DOWN);
			}else if(keysHeld.contains(KeyEvent.VK_D)) {
				gamePanel.setDirection(RIGHT);
			}
			break;
		}
		
	}
	
}
