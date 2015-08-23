package cam;

import javax.swing.SwingUtilities;

import cam.menu.Menu;

public class Start {
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new Menu();
			}
		});
	}

}
