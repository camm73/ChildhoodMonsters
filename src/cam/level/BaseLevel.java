package cam.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import cam.entities.life.Child;
import cam.entities.life.Player;

public class BaseLevel extends Level {

	public BaseLevel(String path) {
		super(path);
	}

	protected void loadLevel(String path){
		try{
			BufferedImage image = ImageIO.read(BaseLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	protected void generateLevel(){
	}

}
