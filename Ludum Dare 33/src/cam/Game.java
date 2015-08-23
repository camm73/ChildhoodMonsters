package cam;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;

import cam.entities.life.Child;
import cam.entities.life.Player;
import cam.graphics.Screen;
import cam.input.Key;
import cam.level.BaseLevel;
import cam.level.Level;
import cam.menu.Menu;
import cam.menu.Score;
import cam.sound.Sound;

public class Game extends Canvas implements Runnable {

	public static final int WIDTH = 280;
	public static final int HEIGHT = WIDTH / 16 * 9;
	public static final int SCALE = 3;
	public static int childNum = 30;
	public static int score = 0;
	public int time = 120;
	private int counter;
	
	private JFrame frame;
	private Thread thread;

	private Screen screen;
	private Key keys;
	private Level level;
	public static Player player;
	private Child child;
	public boolean first = true;
	

	int x = 0, y = 0;

	private int fps = 0;

	private boolean running = false;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Game() {
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
		frame = new JFrame();
		level = Level.base;
		screen = new Screen(WIDTH, HEIGHT);
		keys = new Key();
		player = new Player(10, 4, keys);
		player.init(level);
		//child = new Child(5, 6);
		//child.init(level);
		//level.add(child); //TODO REMOVE
		
		addKeyListener(keys);
	}

	public synchronized void start() {
		System.out.println("Starting game");
		thread = new Thread(this, "Game");
		thread.start();
		running = true;
		Sound.gameMusic.loop();
	}

	public synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		running = false;
		first = true;
		time = 0;
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();

		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
			screen.clear();
			int xScroll = player.x - screen.width / 2;
			int yScroll = player.y - screen.height / 2;
			level.render(xScroll, yScroll, screen);
			player.render(screen);
			//child.render(screen);

			for (int i = 0; i < pixels.length; i++) {
				pixels[i] = screen.pixels[i];
			}

		Graphics g = bs.getDrawGraphics();
	
	
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.red);
		g.setFont(new Font("Arial", Font.BOLD, 18));
		g.drawString(new String(String.valueOf(fps)), 16, 25);
		g.setFont(new Font("Arial", Font.BOLD, 24));
		g.drawString("Score: " + score, getWidth() / 2 - 40, getHeight() - 20);
		g.drawString("Time: " +  time + " secs", getWidth() / 2 - 40, 20);
		g.setFont(new Font("Arial", Font.BOLD, 18));
		g.drawString("Chilren left: " + level.getEntitySize(), getWidth() - 150, 20);
		g.dispose();
		bs.show();
		
	}

	public void update() {
		counter++;
		keys.update();
		player.update();
		level.update();
		//child.update();
		Sound.scream.update();
		Sound.evilLaugh.update();
		if(counter % 60 == 0){
			time--;
		}
		
		if(first){
			addEntity();
			first = false;
		}
		
		if(Key.escape){
			level.removeAllEntities();
			Sound.stopAll();
			new Menu();
			frame.dispose();
			stop();
		}
		
		if(time == 0){
			new Score(score);
			frame.dispose();
			Sound.stopAll();
			stop();
		}
		
		
		if(level.getEntitySize() == 0){
			new Score(score);
			frame.dispose();
			Sound.stopAll();
			stop();
		}
		
	}
	
	public void addEntity(){
		for(int i = 0; i < childNum; i++){
			if(i < childNum / 3){
				level.add(new Child(30 + i, i * 2, i));
			}else if (i < (childNum /3) *2){
				level.add(new Child(BaseLevel.width /2 + i, i * 2, i));
			}else{
				level.add(new Child(70 - i, i * 2, i));
			}
		}
	}

	@Override
	public void run() {
		
		long prev = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double t = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;

		requestFocus();

		while (running) {
			long now = System.nanoTime();
			delta += (now - prev) / t;
			prev = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				fps = frames;
				frame.setTitle(Menu.title + " updates: "+ updates);
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}
	

	public void begin() {
		frame.setTitle(Menu.title);
		frame.setResizable(false);
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		start();
	}

}
