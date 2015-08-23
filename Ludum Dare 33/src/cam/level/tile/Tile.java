package cam.level.tile;

import cam.graphics.Screen;
import cam.graphics.Sprite;

public abstract class Tile {
	public Sprite sprite;
	
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile dirt = new DirtTile(Sprite.dirt);
	public static Tile brick = new BrickTile(Sprite.brick);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	public static Tile water = new WaterTile(Sprite.water);
	
	public static final int col_dirt = 0xff9C5306;
	public static final int col_grass = 0xff0FD63D;
	public static final int col_brick = 0xffD6690F;
	public static final int col_water = 0xff0026FF;
	
	public Tile(Sprite sprite){
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen){
		
	}
	
	public boolean solid(){
		return false;
	}

}
