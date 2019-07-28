package cam.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	private String path;
	public final int size;
	
	public int[] pixels;
	
	public static SpriteSheet main = new SpriteSheet("/textures/main.png", 256);
	public static SpriteSheet entities = new SpriteSheet("/textures/entities.png", 1024);

	public SpriteSheet(String path, int size){
		this.path = path;
		this.size = size;
		
		pixels = new int[size * size];
		load();
	}
	
	private void load(){
		try{
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			
			pixels = image.getRGB(0, 0, w, h, pixels, 0, w);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
