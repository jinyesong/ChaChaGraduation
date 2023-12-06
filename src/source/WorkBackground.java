package source;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WorkBackground extends JFrame {
	private Image background;
	private ImageIcon poopAvoidButtonImage, ballClickButtonImage;
	private JButton poopAvoidButton, ballClickButton;
	
	public WorkBackground() {
		
		background = new ImageIcon("src/img/work_background.png").getImage();
		poopAvoidButtonImage = new ImageIcon("src/img/poopgame_button.png");
		poopAvoidButton = new JButton(poopAvoidButtonImage);
		ballClickButtonImage = new ImageIcon("src/img/ballclickgame_button.png");
		ballClickButton = new JButton(ballClickButtonImage);
		
		
		setTitle("차차 과외 알바하기");
		setSize(564,737);
		setLayout(null);
		
		//똥 피하기 게임 누르면 실행될 프레임 불러오기
		poopAvoidButton.addActionListener(e -> {
			dispose(); //버튼을 누르면 알바 프레임이 닫힘
			//TODO: 실행될 똥 피하기 게임 프레임 입력
		});
		
		//공 클릭하기 게임 누르면 실행될 프레임 불러오기
		ballClickButton.addActionListener(( e -> {
			dispose();
			//TODO: 실행될 공 클릭하기 프레임 입력
		}));
		
		for (JButton button : new JButton[] { poopAvoidButton, ballClickButton }) {
			button.setBorderPainted(false);
			button.setFocusPainted(false);
			button.setContentAreaFilled(false);
			add(button);
		}
		
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, 564, 737, null);
		poopAvoidButton.setBounds(170, 130, poopAvoidButtonImage.getIconWidth(), poopAvoidButtonImage.getIconHeight());
		ballClickButton.setBounds(170, 150 + poopAvoidButtonImage.getIconHeight(), ballClickButtonImage.getIconWidth(), ballClickButtonImage.getIconHeight());
	}
}
