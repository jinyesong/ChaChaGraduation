package source;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import TimeManager.FestivalEventDialog;

public class TimeManager {
    private int season;
    private boolean festivalEventOccurred = false; // 이벤트가 발생했는지 여부를 나타내는 변수

    public TimeManager() {
        this.season = 1; // 1: Spring, 2: Summer, 3: Fall, 4: Winter
    }

    public void handleClick(int totalClicks, ChaChaGraduation chaChaGraduation) {
        if (totalClicks % 10 == 0) {
            changeSeason(chaChaGraduation);
        }
    }

    public int getSeason() {
        return season;
    }

    private void changeSeason(ChaChaGraduation chaChaGraduation) {
        int previousSeason = season;
        season = (season % 4) + 1; // 계절이 순환하도록 함

        if (previousSeason == 3 && season == 4) { // Fall to Winter transition
            showFestivalEventDialog(chaChaGraduation);
            festivalEventOccurred = true; // 이벤트 발생 여부 업데이트
        } else {
            festivalEventOccurred = false; // 축제 이벤트를 초기화하여 다음 계절에도 발생하도록 함
        }
    }

    private void showFestivalEventDialog(ChaChaGraduation chaChaGraduation) {
        FestivalEventDialog festivalEventDialog = new FestivalEventDialog(chaChaGraduation);
        festivalEventDialog.setVisible(true);
    }

    private class FestivalEventDialog extends JDialog {
        private ChaChaGraduation chaChaGraduation;

        public FestivalEventDialog(ChaChaGraduation chaChaGraduation) {
            super((JFrame) null, "백마 대동제", true);
            this.chaChaGraduation = chaChaGraduation;

            JLabel festivalLabel = new JLabel("축제 기간입니다!");
            JButton enjoyFestivalButton = new JButton("축제 즐기러 가기");
            JButton studyAtLibraryButton = new JButton("도서관에서 공부하기");

            enjoyFestivalButton.addActionListener(e -> {
                chaChaGraduation.enjoyFestival();
                dispose(); // 창 닫기
            });

            studyAtLibraryButton.addActionListener(e -> {
                chaChaGraduation.studyAtLibrary();
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
}
