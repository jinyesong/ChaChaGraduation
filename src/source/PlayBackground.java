package source;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class PlayBackground extends JFrame {
	
	private Image background = new ImageIcon("src/img/play_background.png").getImage();
	
	public PlayBackground() {
		setTitle("차차 놀기");
		setSize(564,737);
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, 564, 737, null);
	}
}
