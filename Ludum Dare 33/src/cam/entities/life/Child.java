package cam.entities.life;

import java.util.Random;

import cam.Game;
import cam.graphics.Screen;
import cam.graphics.Sprite;
import cam.input.Key;
import cam.level.Level;
import cam.sound.Sound;

public class Child extends Life {

	private int anim;
	private boolean moving = false;
	private Random random = new Random();
	long timer;
	private int counter = 0;
	private boolean move = false;
	int xa = 0, ya = 0;
	private Player player;
	private boolean running =true;
	private int difficulty = 4;

	private int time = 0;
	int gender;

	public Child(int x, int y, int id) {
		this.x = x * 16;
		this.y = y * 16;
		this.id = id;
		
		if(random.nextInt(2) == 1){
			gender = 1;
		}else{
			gender = 0;
		}
	}

	public void update() {
		time++;

		if (anim < 7500) {
			anim++;
		} else {
			anim = 0;
		}
		
		if (time % (random.nextInt(40) + 30) == 0) {
			xa = random.nextInt(3) - 1;
			ya = random.nextInt(3) - 1;
			if (random.nextInt(5) == 0) {
				xa = 0;
				ya = 0;
			}
		}
	
		if (xa != 0 || ya != 0) {
			//System.out.println("x: " + x + " y: " + y + " xa: " + xa + " ya: " + ya);
			move(xa, ya);
			moving = true;
		} else {
			moving = false;
		}
		
		int playX = Math.abs((x) - Game.player.getX());
		int playY = Math.abs(y - Game.player.getY());
		int radius = 6;
		
		if(playX < (radius * 16)){
			if(x < Game.player.getX()){
				xa = -3 / 2;
			}
			if(x > Game.player.getX()){
				xa = 3 / 2;
			}
			if(y > Game.player.getY()){
				ya = -3 /2;
			}
			if(y > Game.player.getY()){
				ya = 3 /2;
			}
			
			int xTest = Math.abs((x * 16) - (Game.player.getX() * 16));
			int yTest = Math.abs((y * 16) - (Game.player.getY() * 16));
			//System.out.println(xTest);
			
			if(xTest <= (difficulty*16) && yTest <= (difficulty)*16){
				//level.getEntity(x, y);
				if(!Sound.evilLaugh.playing){
					Sound.evilLaugh.play();
				}
				level.remove(level.getEntity(x, y), level.getEntity(x,y).getId());
				Game.score += 50;
			}
		}
	}

	public void render(Screen screen) {

		int xx = x - 16;
		int yy = y - 16;
		
		if(gender==1){
		
			switch (dir) {
			case 0:
				sprite = Sprite.boy_up;
	
				if (moving) {
					if (anim % 20 > 5) {
						sprite = Sprite.boy_up_1;
					} else {
						sprite = Sprite.boy_up_2;
					}
				}
				break;
			case 1:
				sprite = Sprite.boy_right;
	
				if (moving) {
					if (anim % 20 > 5) {
						sprite = Sprite.boy_right_1;
					} else {
						sprite = Sprite.boy_right_2;
					}
				}
				break;
			case 2:
				sprite = Sprite.boy_down;
	
				if (moving) {
					if (anim % 20 > 5) {
						sprite = Sprite.boy_down_1;
					} else {
						sprite = Sprite.boy_down_2;
					}
				}
				break;
			case 3:
				sprite = Sprite.boy_left;
	
				if (moving) {
					if (anim % 20 > 5) {
						sprite = Sprite.boy_left_1;
					} else {
						sprite = Sprite.boy_left_2;
					}
				}
				break;
			default:
				sprite = Sprite.boy_down;
	
				if (moving) {
					if (anim % 20 > 5) {
						sprite = Sprite.boy_down_1;
					} else {
						sprite = Sprite.boy_down_2;
					}
				}
				break;
			}
			
			
		}else if(gender == 0){
			
			
			switch (dir) {
			case 0:
				sprite = Sprite.girl_up;
	
				if (moving) {
					if (anim % 20 > 5) {
						sprite = Sprite.girl_up_1;
					} else {
						sprite = Sprite.girl_up_2;
					}
				}
				break;
			case 1:
				sprite = Sprite.girl_right;
	
				if (moving) {
					if (anim % 20 > 5) {
						sprite = Sprite.girl_right_1;
					} else {
						sprite = Sprite.girl_right_2;
					}
				}
				break;
			case 2:
				sprite = Sprite.girl_down;
	
				if (moving) {
					if (anim % 20 > 5) {
						sprite = Sprite.girl_down_1;
					} else {
						sprite = Sprite.girl_down_2;
					}
				}
				break;
			case 3:
				sprite = Sprite.girl_left;
	
				if (moving) {
					if (anim % 20 > 5) {
						sprite = Sprite.girl_left_1;
					} else {
						sprite = Sprite.girl_left_2;
					}
				}
				break;
			default:
				sprite = Sprite.girl_down;
	
				if (moving) {
					if (anim % 20 > 5) {
						sprite = Sprite.girl_down_1;
					} else {
						sprite = Sprite.girl_down_2;
					}
				}
				break;
			}
		}
		
		screen.renderPlayer(xx, yy, sprite);
	}

}
