package source;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class EatBackground extends JFrame {
	
	private Image background = new ImageIcon("src/img/eat_background.png").getImage();
	
	public EatBackground() {
		setTitle("차차 학식 먹기");
		setSize(564,737);
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, 564, 737, null);
	}
}
