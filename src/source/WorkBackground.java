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
	private JButton poopAvoidButton, ballClickButton, hangManButton;

	public WorkBackground(Player player, Stat ui) {
		this.player = player;
		this.ui = ui;
		
		JPanel pn = new JPanel(){
            Image background = new ImageIcon("src/img/work_background_temp.png").getImage();
            public void paintComponent(Graphics g) {
                g.drawImage(background, 0, 0, 555, 700, null);
        }
        };

		pn.setLayout(null);
		poopAvoidButton = new JButton();
		ballClickButton = new JButton();
		hangManButton = new JButton();

		setTitle("차차 과외 알바하기");
		setSize(564, 737);

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
			new Parttime_Hangman(player, ui);
			dispose();
		}));

		poopAvoidButton.setBounds(170, 100, 240, 70);
		ballClickButton.setBounds(170, 190, 240, 70);
		hangManButton.setBounds(170, 260, 240, 70);

		pn.add(poopAvoidButton);
		pn.add(ballClickButton);
		pn.add(hangManButton);

		add(pn);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
