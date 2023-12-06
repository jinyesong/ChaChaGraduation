package source;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class StudyBackground extends JFrame {
	
	private Image background = new ImageIcon("src/img/study_background.png").getImage();
	
	public StudyBackground() {
		setTitle("차차 공부하기");
		setSize(564,737);
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, 564, 737, null);
	}
}
