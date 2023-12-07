package source;

import javax.swing.*;
import java.awt.*;


class FestivalEventDialog extends JDialog {
    private Player player;
    private JLabel festivalLabel;

    public FestivalEventDialog(Player player) {
        super((JFrame) null, "백마 대동제", true);
        this.player = player;

        festivalLabel = new JLabel();
        ImageIcon festivalIcon = new ImageIcon("src/img/festival_page.png");
        festivalLabel.setIcon(festivalIcon);
        
        JButton enjoyFestivalButton = new JButton(new ImageIcon("src/img/enjoyFestival_button.png"));
        JButton studyForFinalButton = new JButton(new ImageIcon("src/img/studyForFinal_button.png"));

        // 버튼이 버튼 이미지로만 보이도록 구현
        enjoyFestivalButton.setBorderPainted(false);
        studyForFinalButton.setBorderPainted(false);
        
        enjoyFestivalButton.setFocusPainted(false);
        studyForFinalButton.setFocusPainted(false);

        enjoyFestivalButton.setContentAreaFilled(false);
        studyForFinalButton.setContentAreaFilled(false);

        // 배경 이미지와 버튼 겹치기
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(festivalIcon.getIconWidth() + 50, festivalIcon.getIconHeight() + 100));

        // 배경 이미지를 추가
        festivalLabel.setBounds(0, 0, festivalIcon.getIconWidth() + 20, festivalIcon.getIconHeight() + 20);
        layeredPane.add(festivalLabel, new Integer(0));

        // 버튼 추가하고 위치 설정
        int buttonWidth = 150;
        int buttonHeight = 40;
        int buttonSpacing = 100;
        int buttonX = (festivalIcon.getIconWidth() + 20 - (2 * buttonWidth + buttonSpacing)) / 2;
        int buttonY = 505;

        enjoyFestivalButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        layeredPane.add(enjoyFestivalButton, new Integer(1));

        studyForFinalButton.setBounds(buttonX + buttonWidth + buttonSpacing, buttonY, buttonWidth, buttonHeight);
        layeredPane.add(studyForFinalButton, new Integer(1));

        // 창에 JLayeredPane 추가
        setLayout(new BorderLayout());
        add(layeredPane, BorderLayout.CENTER);
        
        // 플레이어의 선택 별로 스탯 조절
        enjoyFestivalButton.addActionListener(e -> {
            player.setKnowledge(player.getKnowledge() - 40);
            player.setHappiness(player.getHappiness() + 20);
            dispose(); // 창 닫기
        });

        studyForFinalButton.addActionListener(e -> {
            player.setKnowledge(player.getKnowledge() + 20);
            player.setHappiness(player.getHappiness() - 40);
            dispose(); // 창 닫기
        });

        // 창 크기를 이미지 크기에 맞게 조절
        setSize(festivalIcon.getIconWidth() + 20, festivalIcon.getIconHeight() + 45);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
}

//    public void WiterExam(Player player) {
//        int currentLevel = player.timeManager.getLevel();
//        int currentKnowledge = player.getKnowledge();
//
//        if (currentLevel == 1 && currentKnowledge >= 30) {
//            player.setLevel(2);
//            player.setKnowledge(0);
//        } else if (currentLevel == 2 && currentKnowledge >= 60) {
//            player.setLevel(3);
//            player.setKnowledge(0);
//        } else if (currentLevel == 3 && currentKnowledge >= 90) {
//            player.setLevel(4);
//            player.setKnowledge(0);
//        }
//        // 4학년의 경우에는 어떻게 할건지?
//    }