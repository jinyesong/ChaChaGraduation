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
import javax.swing.JPanel;

public class UI extends JFrame {
	final int Main_WIDTH = 550;
	final int Main_HEIGHT = 700;

	String season; // 계절 이름
	String grade; // 학년
	int money; // 돈
	int clicks; // 클릭수(계절 당 클릭수를 경험치처럼 표현하기 위함)
	int knowledge; // 지식 스탯
	int happiness; // 행복 스탯

	public UI() {
		setSize(Main_WIDTH, Main_HEIGHT);
		setLayout(null);

		season = "string"; // 초기 계절 = 봄
		grade = "1학년"; // 초기 학년 = 1학년
		money = 1000; // 초기 돈 액수 = 1000원
		clicks = 0; // 초기 클릭수 = 0
		knowledge = 0; // 초기 지식 스탯
		happiness = 70; // 초기 행복 스탯

		ChaCha chaChaPanel = new ChaCha();
		chaChaPanel.setBounds(0, 0, Main_WIDTH, Main_HEIGHT);
		add(chaChaPanel);

		Stat statPanel = new Stat();
		statPanel.setBounds(0, 0, Main_WIDTH, 100); // ChaCha 패널과 크기를 맞춤
		chaChaPanel.add(statPanel); // ChaCha 패널에 Stat 패널 추가

		Below belowPanel = new Below();
		belowPanel.setBounds(0, 470, Main_WIDTH, Main_HEIGHT);
		chaChaPanel.add(belowPanel);

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new UI();
	}

	class ChaCha extends JPanel {
		JLabel gradeLabel;

		public ChaCha() {
			setSize(550, 700);
			setLayout(null);

			gradeLabel = new JLabel(grade);
			gradeLabel.setFont(new Font("Serif", Font.BOLD, 25));
			gradeLabel.setHorizontalAlignment(JLabel.CENTER);
			gradeLabel.setVerticalAlignment(JLabel.CENTER);

			// money 레이블을 패널에 추가
			add(gradeLabel);

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
			ImageIcon chachaIcon = new ImageIcon("./차차.png");
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
			g.setColor(Color.BLACK);
			g.drawRect(season_frameX, season_frameY, season_frameWidth, season_frameHeight);
		}

		private ImageIcon getFrameIconForSeason(String season) {
			if (season != null) {
				switch (season) {
				case "Spring":
					return new ImageIcon("path_to_spring_icon.png");
				case "Summer":
					return new ImageIcon("path_to_summer_icon.png");
				case "Autumn":
					return new ImageIcon("path_to_autumn_icon.png");
				case "Winter":
					return new ImageIcon("path_to_winter_icon.png");
				default:
					return new ImageIcon("path_to_default_icon.png");
				}
			} else {
				// season이 null인 경우에 대한 처리
				return new ImageIcon("path_to_default_icon.png");
			}
		}
	}

	class Stat extends JPanel {

		JLabel moneyLabel;

		public Stat() {
			// money 레이블 초기화 및 설정
			moneyLabel = new JLabel("" + money);
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
			ImageIcon moneyIcon = new ImageIcon("./money.png");
			ImageIcon knowledgeIcon = new ImageIcon("./knowledge.png");
			ImageIcon happinessIcon = new ImageIcon("./Chappiness.png");

			// 간격 설정
			int gap = 10;
			int y = 10;
			int iconWidth = moneyIcon.getIconWidth();
			int iconHeight = moneyIcon.getIconHeight();
			int rectangleWidth = 2 * iconWidth + 3;

			// 돈 아이콘 그리기
			moneyIcon.paintIcon(this, g, 0, y);
			gap += iconWidth;

			// 돈 스탯 표시하는 직사각형 그리기
			g.setColor(Color.white);
			g.fillRoundRect(gap - 13, y + 6, gap + iconWidth - 10, iconHeight - 10, 10, 10);
			g.setColor(Color.black);
			g.drawRoundRect(gap - 13, y + 6, gap + iconWidth - 10, iconHeight - 10, 10, 10);

			// 돈 스탯 표시하는 직사각형 그리기
			g.setColor(Color.white);
			g.fillRoundRect(gap - 13, y + 6, gap + iconWidth - 10, iconHeight - 10, 10, 10);
			g.setColor(Color.black);
			g.drawRoundRect(gap - 13, y + 6, gap + iconWidth - 10, iconHeight - 10, 10, 10);

			// 돈 레이블 위치 설정
			moneyLabel.setBounds(gap - 13, y + 6, gap + iconWidth - 10, iconHeight - 10);

			gap += gap + iconWidth;

			// 지식 아이콘 그리기
			knowledgeIcon.paintIcon(this, g, gap - 16, y);
			gap += iconWidth;

			// 지식 스탯 표시하는 직사각형 그리기
			drawStatRectangle(g, knowledge, gap - 13, y + 6, rectangleWidth, iconHeight - 10);
			gap += 120;

			// 행복도 아이콘 그리기
			happinessIcon.paintIcon(this, g, gap - 16, y);
			gap += iconWidth;

			// 행복도 스탯 표시하는 직사각형 그리기
			drawStatRectangle(g, happiness, gap - 13, y + 6, rectangleWidth, iconHeight - 10);
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
		JLabel study_label, work_label, sleep_label, eat_label, play_label;
		JButton studyButton, workButton, sleepButton, eatButton, playButton;
		Action eventListener = new Action();

		public Below() {
			setLayout(null);

			// 차차 액션 아이콘 및 버튼 불러오기
			ImageIcon studyIcon = new ImageIcon("./study.png");
			ImageIcon workIcon = new ImageIcon("./work.png");
			ImageIcon sleepIcon = new ImageIcon("./sleep.png");
			ImageIcon eatIcon = new ImageIcon("./eat.png");
			ImageIcon playIcon = new ImageIcon("./play.png");

			studyButton = new JButton(new ImageIcon("./study_button.png"));
			workButton = new JButton(new ImageIcon("./work_button.png"));
			sleepButton = new JButton(new ImageIcon("./sleep_button.png"));
			eatButton = new JButton(new ImageIcon("./eat_button.png"));
			playButton = new JButton(new ImageIcon("./play_button.png"));

			// 각 버튼에 액션 이벤트 리스너 추가
			studyButton.addActionListener(eventListener);
			workButton.addActionListener(eventListener);
			sleepButton.addActionListener(eventListener);
			eatButton.addActionListener(eventListener);
			playButton.addActionListener(eventListener);

			study_label = new JLabel();
			work_label = new JLabel();
			sleep_label = new JLabel();
			eat_label = new JLabel();
			play_label = new JLabel();

			// 이미지 레이블 등록하기
			study_label.setIcon(studyIcon);
			work_label.setIcon(workIcon);
			sleep_label.setIcon(sleepIcon);
			eat_label.setIcon(eatIcon);
			play_label.setIcon(playIcon);

			// 각 버튼 및 차차 액션 아이콘 위치 설정
			int icon_gap = 7;
			int button_gap = 23;
			int y = 10;
			int iconWidth = studyIcon.getIconWidth();
			int iconHeight = studyIcon.getIconHeight();
			int buttonWidth = studyButton.getIcon().getIconWidth();
			int buttonHeight = studyButton.getIcon().getIconHeight();

			study_label.setBounds(icon_gap, y, iconWidth, iconHeight);
			studyButton.setBounds(button_gap, y + iconHeight, buttonWidth, buttonHeight);

			icon_gap += iconWidth + 10;
			button_gap += buttonWidth + 13;

			work_label.setBounds(icon_gap - 30, y, iconWidth, iconHeight);
			workButton.setBounds(button_gap, y + iconHeight, buttonWidth, buttonHeight);

			icon_gap += iconWidth + 5;
			button_gap += buttonWidth + 13;

			sleep_label.setBounds(icon_gap - 43, y - 4, iconWidth, iconHeight);
			sleepButton.setBounds(button_gap, y + iconHeight, buttonWidth, buttonHeight);

			icon_gap += iconWidth + 5;
			button_gap += buttonWidth + 13;

			eat_label.setBounds(icon_gap - 80, y, iconWidth, iconHeight);
			eatButton.setBounds(button_gap, y + iconHeight, buttonWidth, buttonHeight);

			icon_gap += iconWidth + 5;
			button_gap += buttonWidth + 13;

			play_label.setBounds(icon_gap - 90, y, iconWidth, iconHeight);
			playButton.setBounds(button_gap, y + iconHeight, buttonWidth, buttonHeight);

			for (JButton button : new JButton[] { studyButton, workButton, sleepButton, eatButton, playButton }) {
				button.setBorderPainted(false);
				button.setFocusPainted(false);
				button.setContentAreaFilled(false);
			}

			add(study_label);
			add(work_label);
			add(sleep_label);
			add(eat_label);
			add(play_label);

			add(studyButton);
			add(workButton);
			add(sleepButton);
			add(eatButton);
			add(playButton);

			setOpaque(true); // 패널 배경을 투명하게 설정
		}

		@Override
		protected void paintComponent(Graphics g) {
			drawClickRectangle(g, clicks, 0, getHeight() - 30, getWidth(), getHeight());
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
