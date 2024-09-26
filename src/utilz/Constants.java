package utilz;

import java.awt.PrintGraphics;

public class Constants {
	
	public static class Directions{
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}
	
	public static class PlayerConstants{
		public static final int IDLE = 0;
		public static final int RUNNING = 1;
		public static final int JUMP = 2;
		public static final int FALLING = 3;
		public static final int ATTACK_1 = 4;
		public static final int HIT = 5;
		public static final int DYING = 6;
		
		public static int GetSpriteAmount(int player_action) {
			switch (player_action) {
			case DYING:
				return 8;
			case RUNNING:
				return 6;
			case IDLE:
				return 5;
			case HIT:
				return 4;
			case ATTACK_1:
			case JUMP:
				return 3;
			case FALLING:
			default:
				return 1;
			}
		}
	}
}
