package source;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Event { //시험 이벤트 구현 (레벨업과 같은 로직이라 수정이 필요할듯...)
    
    class FestivalEventDialog extends JDialog {
        private Player player;

        public FestivalEventDialog(Player player) {
            super((JFrame) null, "백마 대동제", true);
            this.player = player;

            JLabel festivalLabel = new JLabel("축제 기간입니다!");
            JButton enjoyFestivalButton = new JButton("축제 즐기러 가기");
            JButton studyAtLibraryButton = new JButton("도서관에서 공부하기");

            enjoyFestivalButton.addActionListener(e -> {
            	player.setKnowledge(player.getKnowledge()-50);
            	player.setHappiness(player.getHappiness()+40);
                dispose(); // 창 닫기
            });

            studyAtLibraryButton.addActionListener(e -> {
            	player.setKnowledge(player.getKnowledge()+40);
            	player.setHappiness(player.getHappiness()-50);
                dispose(); // 창 닫기
            });

            setLayout(new GridLayout(3, 1));
            add(festivalLabel);
            add(enjoyFestivalButton);
            add(studyAtLibraryButton);

            setLocationRelativeTo(null);
            setSize(300, 150);
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }
    
    public void festivalEvent(Player player) {
    	FestivalEventDialog fed = new FestivalEventDialog(player);
    	fed.setVisible(true);
    }
    
    public void WiterExam(Player player) {
        int currentLevel = player.timeManager.getLevel();
        int currentKnowledge = player.getKnowledge();

        if (currentLevel == 1 && currentKnowledge >= 30) { //각 학년 조건에 맞게 진학 여부 결정
            player.timeManager.setLevel(2);
            player.setKnowledge(0);    //지식을 0으로 초기화
        } else if (currentLevel == 2 && currentKnowledge >= 60) {
            player.timeManager.setLevel(3);
            player.setKnowledge(0); 
        } else if (currentLevel == 3 && currentKnowledge >= 90) {
            player.timeManager.setLevel(4);
            player.setKnowledge(0); 
        }                                                   //4학년의 경우에는 어떻게 할건지?
    }
}
