package source;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import source.UI.Stat;

public class WorkBackground extends JFrame {
	private Player player;
	private Stat ui;
	private Image background;
	private ImageIcon poopAvoidButtonImage, ballClickButtonImage, hangManButtonImage;
	private JButton poopAvoidButton, ballClickButton, hangManButton;

	public WorkBackground(Player player, Stat ui) {
		this.player = player;
		this.ui = ui;
		background = new ImageIcon("src/img/work_background.png").getImage();
		poopAvoidButtonImage = new ImageIcon("src/img/poopgame_button.png");
		poopAvoidButton = new JButton(poopAvoidButtonImage);
		ballClickButtonImage = new ImageIcon("src/img/ballclickgame_button.png");
		ballClickButton = new JButton(ballClickButtonImage);
		hangManButtonImage = new ImageIcon("src/img/hangman_button.png");
		hangManButton = new JButton(hangManButtonImage);

		setTitle("차차 과외 알바하기");
		setSize(564, 737);
		setLayout(null);

		for (JButton button : new JButton[] { poopAvoidButton, ballClickButton, hangManButton }) {
			button.setBorderPainted(false);
			button.setFocusPainted(false);
			button.setContentAreaFilled(false);
			add(button);
		}

		// 똥 피하기 게임 누르면 실행될 프레임 불러오기
		poopAvoidButton.addActionListener(e -> {
			new Parttime_avoidPoop(player, ui);
			// TODO: 실행될 똥 피하기 게임 프레임 입력
			dispose(); // 버튼을 누르면 알바 프레임이 닫힘
		});

		// 공 클릭하기 게임 누르면 실행될 프레임 불러오기
		ballClickButton.addActionListener((e -> {
			// TODO: 실행될 공 클릭하기 프레임 입력
			new Parttime_Ball(player, ui);
			dispose();
		}));

		// 공 클릭하기 게임 누르면 실행될 프레임 불러오기
		hangManButton.addActionListener((e -> {
			//TODO: 행맨 게임 프레임 입력
			dispose();
		}));

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, 564, 737, null);
		
		int gap = 15;
		poopAvoidButton.setBounds(170, 100, poopAvoidButtonImage.getIconWidth(), poopAvoidButtonImage.getIconHeight());
		ballClickButton.setBounds(170, 100 + poopAvoidButtonImage.getIconHeight() + gap, ballClickButtonImage.getIconWidth(),
				ballClickButtonImage.getIconHeight());
		gap += 15;
		hangManButton.setBounds(170, 100 + 2*ballClickButtonImage.getIconHeight() + gap, hangManButtonImage.getIconWidth(), hangManButtonImage.getIconHeight());
	}
}
