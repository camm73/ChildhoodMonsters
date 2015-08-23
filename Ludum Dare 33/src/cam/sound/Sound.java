package cam.sound;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	
	private String path;
	public Clip clip;
	private AudioInputStream stream;
	
	public static Sound menuMusic = new Sound("/sounds/menuMusic.wav");
	public static Sound gameMusic = new Sound("/sounds/gameMusic.wav");
	public static Sound scream = new Sound("/sounds/scream.wav");
	public static Sound evilLaugh = new Sound("/sounds/evilLaugh.wav");
	
	public boolean playing = false;
	private long lastTime;
	private long now;

	
	public Sound(String path){
		this.path = path;
		load();
	}
	
	private void load(){
			try {
				clip = AudioSystem.getClip();
				stream = AudioSystem.getAudioInputStream(Sound.class.getResource(path));
				clip.open(stream);
			} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void loop(){
		clip.loop(10000000);
	}
	
	public void play(){
		if(!playing){
			clip.loop(1);
		}
		playing = true;
		lastTime = System.currentTimeMillis();
		
	}
	
	public void stopLoop(){
		clip.stop();
	}
	
	public void update(){
		if(playing){
			now = System.currentTimeMillis();
			if(now - lastTime >= 2350){
				stopLoop();
				playing = false;
			}
		}
	}
	
	public static void stopAll(){
		Sound.gameMusic.stopLoop();
		Sound.menuMusic.stopLoop();
		Sound.evilLaugh.stopLoop();
		Sound.scream.stopLoop();
	}
	

}
