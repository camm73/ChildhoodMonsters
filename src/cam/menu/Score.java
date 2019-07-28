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

import cam.sound.Sound;

public class Score extends JFrame {
	
	private JPanel panel = new JPanel(new GridBagLayout());
	private int score;
	private JLabel label = new JLabel();
	private JButton menu = new JButton("Return to menu");
	
	public Score(int score){
		this.score = score;
		setSize(450, 150);
		setTitle("Score: "+ score);
		setResizable(false);
		add(panel);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		content();
		startMusic();
	}
	
	private void startMusic() {
		Sound.menuMusic.loop();
	}

	private void content(){
		GridBagConstraints c = new GridBagConstraints()	;
		
		panel.setBackground(Color.black);
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 1.0;
		
		label.setText("Congratulation your score is: " + score);
		label.setForeground(Color.red);
		label.setFont(new Font("Stencil", Font.PLAIN, 22));
		panel.add(label, c);
		
		c.gridy++;
		menu.setForeground(Color.red);
		menu.setBackground(Color.black);
		panel.add(menu, c);
		menu.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new Menu();
				dispose();
			}
			
		});
	}
}
