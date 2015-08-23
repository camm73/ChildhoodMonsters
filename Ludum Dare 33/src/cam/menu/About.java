package cam.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class About extends JFrame {
	
	private JPanel panel = new JPanel(new BorderLayout());
	private JLabel label = new JLabel("About");
	private JButton menu = new JButton("Close");
	private JTextArea prompt = new JTextArea(15, 15);

	public About(){
		super("About");
		setSize(300, 200);
		setResizable(false);
		add(panel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		setVisible(true);
		
		content();
	}
	
	private void content(){
		panel.setBackground(Color.BLACK);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.PLAIN, 18));
		label.setForeground(Color.red);
		panel.add(label, BorderLayout.NORTH);
		
		prompt.setText("Remember those childhood nightmares of being chased by monsters. Now you can be the monster and experience the monster's POV. \n \n Thanks for playing!");
		prompt.setForeground(Color.red);
		prompt.setBackground(Color.black);
		prompt.setLineWrap(true);
		prompt.setWrapStyleWord(true);
		panel.add(prompt, BorderLayout.CENTER);
		
		panel.add(menu, BorderLayout.SOUTH);
		menu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
	}
	
	
}
