package cam.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cam.Game;
import cam.sound.Sound;

public class Menu extends JFrame{
	
	private final int width = 500, height = 350;
	public static final String title = "Childhood Monsters";
	private JPanel panel = new JPanel(new GridBagLayout());
	private JLabel titleLabel = new JLabel(title);
	private JButton play = new JButton("Play");
	private JButton about = new JButton("About");
	private JButton exit = new JButton("Exit");
	
	public Menu(){
		super(title);
		setSize(width, height);
		setResizable(false);
		add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		content();
		Sound.menuMusic.loop();
	}
	
	private void content(){
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 1.0;
		panel.setBackground(Color.black);
		
		titleLabel.setForeground(Color.RED);
		titleLabel.setFont(new Font("Chiller", Font.PLAIN, 46));
		panel.add(titleLabel, c);
		
		c.gridy++;
		
		play.setForeground(Color.RED);
		play.setBackground(Color.black);
		play.setSize(100, 12);
		panel.add(play, c);
		play.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Sound.stopAll();;
				Game game = new Game();
				Game.score = 0;
				game.begin();
				dispose();
			}
			
		});
		
		c.gridy++;
		
		about.setForeground(Color.RED);
		about.setBackground(Color.BLACK);
		panel.add(about, c);
		about.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new About();
			}
		});
		
		c.gridy++;
		
		exit.setForeground(Color.RED);
		exit.setBackground(Color.black);
		panel.add(exit, c);
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
	}
	
}
