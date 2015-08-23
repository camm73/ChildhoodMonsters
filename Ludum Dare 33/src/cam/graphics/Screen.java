package cam.graphics;

import java.util.Random;

import cam.level.tile.Tile;

public class Screen {

	public int width, height;
	public int[] pixels;

	public int xOffset, yOffset;

	public final int mapSize = 64;

	public int[] tiles = new int[mapSize * mapSize];

	private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;

		pixels = new int[width * height];

		for (int i = 0; i < mapSize * mapSize; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}

	public void clear() {
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = 0;
		}
	}

	public void renderPlayer(int xPos, int yPos, Sprite sprite) {
		xPos -= xOffset;
		yPos -= yOffset;

		for (int y = 0; y < sprite.size; y++) {
			int ya = y + yPos;
			for (int x = 0; x < sprite.size; x++) {
				int xa = x + xPos;
				if (xa < -sprite.size || xa >= width || ya < 0 || ya >= height)
					break;
				if (xa < 0)
					xa = 0;

				int col = sprite.pixels[x + y * sprite.size];
				if(col != 0xffff00ff){
					pixels[xa + ya * width] = col;
				}
			}
		}
	}

	public void renderTile(int xPos, int yPos, Tile tile) {
		xPos -= xOffset;
		yPos -= yOffset;

		for (int y = 0; y < tile.sprite.size; y++) {
			int ya = y + yPos;

			for (int x = 0; x < tile.sprite.size; x++) {
				int xa = x + xPos;
				if (xa < -tile.sprite.size || xa >= width || ya < 0 || ya >= height)
					break;
				if (xa < 0)
					xa = 0;
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.size];
			}
		}
	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}
