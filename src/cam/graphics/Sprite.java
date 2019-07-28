package cam.graphics;

public class Sprite {

	public final int size;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;

	public static Sprite dirt = new Sprite(16, 0, 0, SpriteSheet.main);
	public static Sprite grass = new Sprite(16, 1, 0, SpriteSheet.main);
	public static Sprite brick = new Sprite(16, 2, 0, SpriteSheet.main);
	public static Sprite voidSprite = new Sprite(16, 0xff04198F);
	public static Sprite water = new Sprite(16, 3, 0, SpriteSheet.main);
	
	public static Sprite player_up = new Sprite(32, 2, 0, SpriteSheet.entities);
	public static Sprite player_down = new Sprite(32, 0, 0, SpriteSheet.entities);
	public static Sprite player_left = new Sprite(32, 3, 0, SpriteSheet.entities);
	public static Sprite player_right = new Sprite(32, 1, 0, SpriteSheet.entities);
	
	public static Sprite boy_up = new Sprite(32, 6, 0, SpriteSheet.entities);
	public static Sprite boy_down = new Sprite(32, 4, 0, SpriteSheet.entities);
	public static Sprite boy_left = new Sprite(32, 7, 0, SpriteSheet.entities);
	public static Sprite boy_right = new Sprite(32, 5, 0, SpriteSheet.entities);
	
	public static Sprite girl_up = new Sprite(32, 8, 0, SpriteSheet.entities);
	public static Sprite girl_down = new Sprite(32, 10, 0, SpriteSheet.entities);
	public static Sprite girl_left = new Sprite(32, 9, 0, SpriteSheet.entities);
	public static Sprite girl_right = new Sprite(32, 11, 0, SpriteSheet.entities);
	
	public static Sprite player_up_1 = new Sprite(32, 2, 1, SpriteSheet.entities);
	public static Sprite player_up_2 = new Sprite(32, 2, 2, SpriteSheet.entities);
	public static Sprite player_down_1 = new Sprite(32, 0, 1, SpriteSheet.entities);
	public static Sprite player_down_2 = new Sprite(32, 0, 2, SpriteSheet.entities);
	public static Sprite player_left_1 = new Sprite(32, 3, 1, SpriteSheet.entities);
	public static Sprite player_left_2 = new Sprite(32, 3, 2, SpriteSheet.entities);
	public static Sprite player_right_1 = new Sprite(32, 1, 1, SpriteSheet.entities);
	public static Sprite player_right_2 = new Sprite(32, 1, 2, SpriteSheet.entities);
	
	public static Sprite girl_up_1 = new Sprite(32, 10, 1, SpriteSheet.entities);
	public static Sprite girl_up_2 = new Sprite(32, 10, 2, SpriteSheet.entities);
	public static Sprite girl_down_1 = new Sprite(32, 8, 1, SpriteSheet.entities);
	public static Sprite girl_down_2 = new Sprite(32, 8, 2, SpriteSheet.entities);
	public static Sprite girl_left_1 = new Sprite(32, 9, 1, SpriteSheet.entities);
	public static Sprite girl_left_2 = new Sprite(32, 9, 2, SpriteSheet.entities);
	public static Sprite girl_right_1 = new Sprite(32, 11, 1, SpriteSheet.entities);
	public static Sprite girl_right_2 = new Sprite(32, 11, 2, SpriteSheet.entities);
	
	public static Sprite boy_up_1 = new Sprite(32, 6, 1, SpriteSheet.entities);
	public static Sprite boy_up_2 = new Sprite(32, 6, 2, SpriteSheet.entities);
	public static Sprite boy_down_1 = new Sprite(32, 4, 1, SpriteSheet.entities);
	public static Sprite boy_down_2 = new Sprite(32, 4, 2, SpriteSheet.entities);
	public static Sprite boy_left_1 = new Sprite(32, 7, 1, SpriteSheet.entities);
	public static Sprite boy_left_2 = new Sprite(32, 7, 2, SpriteSheet.entities);
	public static Sprite boy_right_1 = new Sprite(32, 5, 1, SpriteSheet.entities);
	public static Sprite boy_right_2 = new Sprite(32, 5, 2, SpriteSheet.entities);
	
	
	public Sprite(int size, int x, int y, SpriteSheet sheet){
		this.size = size;
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		pixels = new int[size * size];
		load();
	}
	
	public Sprite(int size, int color){
		this.size = size;
		pixels = new int[size * size];
		setColor(color);
	}
	
	private void setColor(int color){
		for(int i = 0; i < size * size; i++){
			pixels[i] = color;
		}
	}
	
	public void load(){
		for(int y = 0; y < size; y++){
			for(int x = 0; x < size; x++){
				pixels[x + y * size] = sheet.pixels[((x+this.x) + (y + this.y) * sheet.size)];
			}
		}
	}
}
