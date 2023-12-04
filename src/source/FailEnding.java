package source;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class FailEnding extends JFrame {
	
	private Image background = new ImageIcon("src/img/졸업실패엔딩.png").getImage();
	
	public FailEnding() {
		setTitle("졸업실패 엔딩");
		setSize(564,737);
		setLayout(null);
		//프레임의 위치를 화면 중앙으로 설정
		setLocationRelativeTo(null);
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, 564, 737, null);
	}
}
