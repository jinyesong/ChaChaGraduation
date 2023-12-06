package source;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class UI extends JFrame {
	final int Main_WIDTH = 550;
	final int Main_HEIGHT = 700;
	
	Stat statPanel;
	Below belowPanel;
	
	Player player;
	int season; // 계절 1: Spring, 2: Summer, 3: Fall, 4: Winter
//	int grade; // 학년
//	int money; // 돈
//	int clicks; // 클릭수(계절 당 클릭수를 경험치처럼 표현하기 위함)
//	int knowledge; // 지식 스탯
//	int happiness; // 행복 스탯

	public UI(Player player) {
		setSize(Main_WIDTH+14, Main_HEIGHT+37);
		setLayout(null);

		this.player = player;
		season = player.timeManager.getSeason(); // 초기 계절 = 봄
//		grade = player.getLevel(); // 초기 학년 = 1학년
//		money = player.getMoney(); // 초기 돈 액수 = 0원
//		clicks = 0; // 초기 클릭수 = 0
//		knowledge = player.getKnowledge(); // 초기 지식 스탯
//		happiness = player.getHappiness(); // 초기 행복 스탯

		ChaCha chaChaPanel = new ChaCha();
		chaChaPanel.setBounds(0, 0, Main_WIDTH, Main_HEIGHT);
		add(chaChaPanel);

		statPanel = new Stat();
		statPanel.setBounds(0, 0, Main_WIDTH, 100); // ChaCha 패널과 크기를 맞춤
		chaChaPanel.add(statPanel); // ChaCha 패널에 Stat 패널 추가

		belowPanel = new Below(this);
		belowPanel.setBounds(0, 470, Main_WIDTH, Main_HEIGHT);
		chaChaPanel.add(belowPanel);

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public Player getPlayer(){
		return player;
	}

	public void clickCheck() {
		// player의 click수 업데이트
		player.timeManager.addClickCount();
		// click수에 따른 이벤트 처리

		// player 정보 update되어 repaint
		statPanel.repaint(); // 액션 스탯 업데이트
		belowPanel.repaint(); // 클릭 스탯 업데이트
	}

	class ChaCha extends JPanel {
		JLabel gradeLabel;
		ImageIcon logoutIcon, saveIcon;
		JButton logoutButton, saveButton;

		public ChaCha() {
			setSize(550, 700);
			setLayout(null);

			// 학년 label 추가하기
			gradeLabel = new JLabel(Integer.toString(player.getLevel()));
			gradeLabel.setFont(new Font("Serif", Font.BOLD, 25));
			gradeLabel.setHorizontalAlignment(JLabel.CENTER);
			gradeLabel.setVerticalAlignment(JLabel.CENTER);
			add(gradeLabel);

			// 저장, 로그아웃 버튼 추가하기
			logoutIcon = new ImageIcon("src/img/logout_button.png");
			logoutButton = new JButton(logoutIcon);
			saveIcon = new ImageIcon("src/img/save_button.png");
			saveButton = new JButton(saveIcon);

			for (JButton button : new JButton[] { saveButton, logoutButton }) {
				button.setBorderPainted(false);
				button.setFocusPainted(false);
				button.setContentAreaFilled(false);

				add(button);
			}

			saveButton.addActionListener(e -> {
				FileIO fi = new FileIO();
				fi.saveGame(player);
				JOptionPane.showMessageDialog(null, "저장 완료!.");
			});
			logoutButton.addActionListener(e -> {
				int result = 0;
				result = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?");
				if (result == 0) { // 로그아웃
					dispose(); // 현재 윈도우가 닫힘
					new LoginFrame();
				}
			});
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			// 화면 중앙
			int centerX = getWidth() / 2;
			int centerY = getHeight() / 2;

			// 바닥 색깔 채우기
			g.setColor(new Color(0, 160, 186));
			g.fillRect(0, centerY + 30, getWidth(), getHeight() - centerY);

			// 위 영역을 하얀색으로 채우기
			g.setColor(Color.white);
			g.fillRect(0, 0, getWidth(), centerY + 30);

			// 선의 굵기를 3으로 설정.
			((Graphics2D) g).setStroke(new BasicStroke(3));

			// 벽과 바닥을 구분할 가로선 설정.
			g.setColor(Color.BLACK);
			g.drawLine(0, centerY + 30, getWidth(), centerY + 30);

			// 중간에 차차 넣기.
			ImageIcon chachaIcon = new ImageIcon("src/img/차차.png");
			Image chacha = chachaIcon.getImage();
			int imageWidth = chacha.getWidth(this);
			int imageHeight = chacha.getHeight(this);
			int x = centerX - imageWidth / 2;
			int y = centerY - imageHeight / 2;
			g.drawImage(chacha, x, y, this);

			// 학년 창문 네모 그리기.
			int grade_windowWidth = getWidth() * 2 / 10;
			int grade_windowHeight = getHeight() * 2 / 10;
			int grade_windowX = getWidth() - 150;
			int grade_windowY = getHeight() - 550;

			g.setColor(Color.BLACK);
			g.drawRect(grade_windowX, grade_windowY, grade_windowWidth, grade_windowHeight);

			// 학년 창문 창틀 그리기
			int grade_frameWidth = grade_windowWidth * 9 / 10; // 창 너비의 9/10
			int grade_frameHeight = grade_windowHeight * 9 / 10; // 창 높이의 9/10
			int grade_frameX = grade_windowX + (grade_windowWidth - grade_frameWidth) / 2;
			int grade_frameY = grade_windowY + (grade_windowHeight - grade_frameHeight) / 2;
			g.drawRect(grade_frameX, grade_frameY, grade_frameWidth, grade_frameHeight);

			// 학년 레이블 그리기
			gradeLabel.setBounds(grade_frameX, grade_frameY, grade_frameWidth, grade_frameHeight);

			// 계절 창문 네모 그리기.
			int season_windowWidth = getHeight() * 25 / 100;
			int season_windowHeight = getWidth() * 25 / 100;
			int season_windowX = getWidth() - 500;
			int season_windowY = getHeight() - 600;

			g.setColor(Color.BLACK);
			g.drawRect(season_windowX, season_windowY, season_windowWidth, season_windowHeight);

			// 계절 사진 넣기
			int season_frameWidth = season_windowWidth * 9 / 10; // 창 너비의 9/10
			int season_frameHeight = season_windowHeight * 9 / 10; // 창 높이의 9/10
			int season_frameX = season_windowX + (season_windowWidth - season_frameWidth) / 2;
			int season_frameY = season_windowY + (season_windowHeight - season_frameHeight) / 2;

			ImageIcon seasonIcon = getFrameIconForSeason(season);
			Image seasonImage = seasonIcon.getImage();
			g.drawImage(seasonImage, season_frameX, season_frameY, season_frameWidth, season_frameHeight, this);
			
			// 계절 창문 창틀 그리기
			g.drawLine(season_frameX, season_frameY+season_frameHeight/2, season_frameX+season_frameWidth, season_frameY+season_frameHeight/2);
			g.drawLine(season_frameX+season_frameWidth/2, season_frameY, season_frameX+season_frameWidth/2, season_frameY+season_frameHeight);
			g.setColor(Color.BLACK);
			g.drawRect(season_frameX, season_frameY, season_frameWidth, season_frameHeight);

			// 저장, 로그아웃 버튼 위치시키기
			saveButton.setBounds(getWidth() - saveIcon.getIconWidth() - 5, centerY + 35 - saveIcon.getIconHeight(),
					saveIcon.getIconWidth(), saveIcon.getIconHeight());
			logoutButton.setBounds(getWidth() - saveIcon.getIconWidth() - 5, centerY + 35, logoutIcon.getIconWidth(),
					logoutIcon.getIconHeight());
		}

		private ImageIcon getFrameIconForSeason(int season) {
			switch (season) {
			case 1:
				return new ImageIcon("src/img/spring.jpg");
			case 2:
				return new ImageIcon("src/img/summer.jpg");
			case 3:
				return new ImageIcon("src/img/fall.jpg");
			case 4:
				return new ImageIcon("src/img/winter.jpg");
			default:
				return new ImageIcon("src/img/spring.jpg");
			}
		}
	}

	class Stat extends JPanel {

		JLabel moneyLabel;

		public Stat() {
			// money 레이블 초기화 및 설정
			moneyLabel = new JLabel();
			moneyLabel.setFont(new Font("Serif", Font.BOLD, 25));
			moneyLabel.setHorizontalAlignment(JLabel.CENTER);
			moneyLabel.setVerticalAlignment(JLabel.CENTER);

			// money 레이블을 패널에 추가
			add(moneyLabel);

			setOpaque(false); // 패널 배경을 투명하게 설정
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			// 이미지 아이콘 로드
			ImageIcon moneyIcon = new ImageIcon("src/img/money.png");
			ImageIcon knowledgeIcon = new ImageIcon("src/img/knowledge.png");
			ImageIcon happinessIcon = new ImageIcon("src/img/happiness.png");

			// 간격 설정
			int gap = 10;
			int y = 10;
			int iconWidth = moneyIcon.getIconWidth();
			int iconHeight = moneyIcon.getIconHeight();
			int rectangleWidth = 2 * iconWidth + 3;

			// 돈 아이콘 그리기
			moneyIcon.paintIcon(this, g, gap, y);
			gap += iconWidth;

			// 돈 스탯 표시하는 직사각형 그리기
			g.setColor(Color.white);
			g.fillRoundRect(gap, y + 6, gap + iconWidth - 10, iconHeight - 10, 10, 10);
			g.setColor(Color.black);
			g.drawRoundRect(gap, y + 6, gap + iconWidth - 10, iconHeight - 10, 10, 10);

			// 돈 레이블 위치 설정
			moneyLabel.setBounds(gap, y + 6, gap + iconWidth - 10, iconHeight - 10);
			moneyLabel.setText(Integer.toString(player.getMoney()));

			gap += gap + iconWidth;

			// 지식 아이콘 그리기
			knowledgeIcon.paintIcon(this, g, gap-4, y);
			gap += iconWidth;

			// 지식 스탯 표시하는 직사각형 그리기
			drawStatRectangle(g, player.getKnowledge(), gap, y + 6, rectangleWidth, iconHeight - 10);
			gap += 120;

			// 행복도 아이콘 그리기
			happinessIcon.paintIcon(this, g, gap, y);
			gap += iconWidth;

			// 행복도 스탯 표시하는 직사각형 그리기
			drawStatRectangle(g, player.getHappiness(), gap, y + 6, rectangleWidth, iconHeight - 10);
		}

		// 스탯에 따라 직사각형 색깔 채우기
		private void drawStatRectangle(Graphics g, int value, int x, int y, int width, int height) {
			// 스탯의 백분율에 따라 채워지는 너비 계산
			int filledWidth = width * value / 100;
			
			// 직사각형 그리기
			g.setColor(new Color(0, 160, 186));
			g.fillRoundRect(x, y, filledWidth, height, 10, 10);
			g.setColor(Color.black);
			g.drawRoundRect(x, y, width, height, 10, 10); // drawRect 크기는 고정

		}
	}

	class Below extends JPanel {
		JButton studyButton, workButton, sleepButton, eatButton, playButton;
		UI UI;
		
		// 차차 액션 아이콘 불러오기
		ImageIcon studyIcon = new ImageIcon("src/img/study.png");
		ImageIcon workIcon = new ImageIcon("src/img/work.png");
		ImageIcon sleepIcon = new ImageIcon("src/img/sleep.png");
		ImageIcon eatIcon = new ImageIcon("src/img/eat.png");
		ImageIcon playIcon = new ImageIcon("src/img/play.png");

		public Below(UI UI) {
			this.UI = UI;
			setLayout(null);
			
			//차차 버튼 불러오기
			studyButton = new JButton(new ImageIcon("src/img/study_button.png"));
			workButton = new JButton(new ImageIcon("src/img/work_button.png"));
			sleepButton = new JButton(new ImageIcon("src/img/sleep_button.png"));
			eatButton = new JButton(new ImageIcon("src/img/eat_button.png"));
			playButton = new JButton(new ImageIcon("src/img/play_button.png"));

			// 각 버튼에 액션 이벤트 리스너 추가
			studyButton.addActionListener(e -> {
				player.setKnowledge(player.getKnowledge() + 10);
				player.setHappiness(player.getHappiness() - 5);
				clickCheck();
			});
			workButton.addActionListener(e -> {
				// player.setMoney(player.getMoney() + 20);

				new Parttime_avoidPoop(UI);
				clickCheck();
			});
			sleepButton.addActionListener(e -> {
				player.setHappiness(player.getHappiness() + 15);
				clickCheck();
			});
			eatButton.addActionListener(e -> {
				player.setHappiness(player.getHappiness() + 20);
				player.setMoney(player.getMoney() - 10);
				clickCheck();
			});

			playButton.addActionListener(e -> {
				player.setKnowledge(player.getKnowledge() - 10);
				player.setHappiness(player.getHappiness() + 20);
				player.setMoney(player.getMoney() - 15);
				clickCheck();
			});

			for (JButton button : new JButton[] { studyButton, workButton, sleepButton, eatButton, playButton }) {
				button.setBorderPainted(false);
				button.setFocusPainted(false);
				button.setContentAreaFilled(false);
				add(button);
			}

			setOpaque(false); // 패널 배경을 투명하게 설정
		}

		@Override
		protected void paintComponent(Graphics g) {

			// 각 버튼 및 차차 액션 아이콘 위치 설정
			int icon_gap = 25;
			int button_gap = 23;
			int y = 10;
			int iconWidth = studyIcon.getIconWidth();
			int iconHeight = studyIcon.getIconHeight();
			int buttonWidth = studyButton.getIcon().getIconWidth();
			int buttonHeight = studyButton.getIcon().getIconHeight()+70;

			studyIcon.paintIcon(this, g ,icon_gap, y+35);
			studyButton.setBounds(button_gap, y + iconHeight, buttonWidth, buttonHeight);

			icon_gap += iconWidth;
			button_gap += buttonWidth + 13;

			workIcon.paintIcon(this, g, icon_gap, y+35);
			workButton.setBounds(button_gap, y + iconHeight, buttonWidth, buttonHeight);

			icon_gap += iconWidth + 28;
			button_gap += buttonWidth + 13;

			sleepIcon.paintIcon(this, g, icon_gap, y+13);
			sleepButton.setBounds(button_gap, y + iconHeight, buttonWidth, buttonHeight);

			icon_gap += iconWidth + 13;
			button_gap += buttonWidth + 13;

			eatIcon.paintIcon(this, g, icon_gap, y+12);
			eatButton.setBounds(button_gap, y + iconHeight, buttonWidth, buttonHeight);

			icon_gap += iconWidth + 24;
			button_gap += buttonWidth + 13;

			playIcon.paintIcon(this, g, icon_gap, y+25);
			playButton.setBounds(button_gap, y + iconHeight, buttonWidth, buttonHeight);

			drawClickRectangle(g, player.timeManager.getClickCount(), 0, getHeight() - 30, getWidth(), getHeight());
		}

		// 클릭수에 따른 직사각형 모양 변화
		private void drawClickRectangle(Graphics g, int clicks, int x, int y, int width, int height) {
			// 클릭수의 백분율에 따라 채워지는 너비 계산
			int filledWidth = width * clicks / 10;

			// 직사각형 그리기
			g.setColor(Color.orange);
			g.fillRect(0, 220, filledWidth, height);

		}
	}
}
