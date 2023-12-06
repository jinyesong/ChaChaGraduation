package source;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class SleepBackground extends JFrame {
	
	private Image background = new ImageIcon("src/img/sleep_background.png").getImage();
	
	public SleepBackground() {
		setTitle("차차 잠자기");
		setSize(564,737);
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, 564, 737, null);
	}
}
