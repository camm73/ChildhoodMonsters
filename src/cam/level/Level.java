package cam.level;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import cam.Game;
import cam.entities.Entity;
import cam.entities.life.Child;
import cam.entities.life.Player;
import cam.graphics.Screen;
import cam.level.tile.Tile;

public abstract class Level {

	public static int width, height;
	public int[] tiles;

	private List<Entity> entities = new ArrayList<Entity>();
	protected Player player;
	protected Child child;

	public static Level base = new BaseLevel("/levels/base.png");

	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		generateLevel();
	}

	protected void loadLevel(String path) {
	}

	protected void generateLevel() {

	}

	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
	}

	public int getEntitySize() {
		return entities.size();
	}

	public void add(Entity e) {
		e.init(this);
		entities.add(e);
	}

	public Entity getEntity(int x, int y) {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).getX() == x && entities.get(i).getY() == y) {
				return entities.get(i);
			}
		}
		return null;
	}

	public void remove(Entity e) {
		for (int i = 0; i < entities.size(); i++) {
			if (!entities.get(i).isRemoved())
				entities.remove(i);
		}
	}

	public void remove(Entity e, int id) {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).id == id) {
				entities.remove(i);
			}
		}
	}

	public void removeAllEntities() {
		for (int i = 0; i < entities.size(); i++) {
			entities.removeAll(entities);
		}
	}

	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);

		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}

	}

	public List<Entity> getEntities(Entity e, int radius) {
		List<Entity> result = new ArrayList<Entity>();
		int ex = e.getX();
		int ey = e.getY();
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			int x = entity.getX();
			int y = entity.getY();

			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);

			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius) {
				result.add(entity);
			}
		}
		return entities;
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.voidTile;
		if (tiles[x + y * width] == Tile.col_dirt)
			return Tile.dirt;
		if (tiles[x + y * width] == Tile.col_grass)
			return Tile.grass;
		if (tiles[x + y * width] == Tile.col_brick)
			return Tile.brick;

		if (tiles[x + y * width] == Tile.col_water)
			return Tile.water;

		if (tiles[x + y * width] == 0) {
			return Tile.dirt;
		}
		if (tiles[x + y * width] == 1) {
			return Tile.grass;
		}

		return Tile.voidTile;
	}

}
