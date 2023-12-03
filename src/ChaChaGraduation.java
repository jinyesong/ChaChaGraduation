import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChaChaGraduation extends JFrame {
    private int knowledge = 0;
    private int happiness = 70;
    private int money = 0;
    private int totalClicks = 0;
    private int year = 1;

    private JLabel knowledgeLabel;
    private JLabel happinessLabel;
    private JLabel moneyLabel;
    private JLabel levelLabel;
    private JLabel seasonLabel;

    private Level level;
    private TimeManager timeManager;

    public ChaChaGraduation() {
        super("대학생 키우기");

        level = new Level();
        timeManager = new TimeManager();

        knowledgeLabel = new JLabel("Knowledge: " + knowledge);
        happinessLabel = new JLabel("Happiness: " + happiness);
        moneyLabel = new JLabel("Money: " + money);
        levelLabel = new JLabel("Level: " + level.getCurrentLevel());
        seasonLabel = new JLabel("Season: Spring");

        JButton studyButton = new JButton("공부");
        studyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                study();
                handleClick(); // 클릭 횟수 증가
                updateLabels();
            }
        });

        JButton workButton = new JButton("알바");
        workButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                work();
                handleClick(); // 클릭 횟수 증가
                updateLabels();
            }
        });

        JButton sleepButton = new JButton("잠자기");
        sleepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sleep();
                handleClick(); // 클릭 횟수 증가
                updateLabels();
            }
        });

        JButton eatButton = new JButton("학식먹기");
        eatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eat();
                handleClick(); // 클릭 횟수 증가
                updateLabels();
            }
        });

        JButton playButton = new JButton("놀기");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                play();
                handleClick(); // 클릭 횟수 증가
                updateLabels();
            }
        });

        setLayout(new GridLayout(8, 1));
        add(knowledgeLabel);
        add(happinessLabel);
        add(moneyLabel);
        add(levelLabel);
        add(seasonLabel);
        add(studyButton);
        add(workButton);
        add(sleepButton);
        add(eatButton);
        add(playButton);

        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addMoney(int amount) {
        money += amount;
        updateLabels();
    }
    
    private void handleClick() {
        totalClicks++;
        timeManager.handleClick(totalClicks, this);

        if (totalClicks % 40 == 0) {
            if (level.levelUp(knowledge, this)) {
                // Level Up 여부에 따라 메시지 변경
                if (knowledge < 300) {
                    JOptionPane.showMessageDialog(this, "레벨 업 성공!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "유급입니다! 대학생의 본분은 공부! 공부하세요.");
            }
        }
    }

    private void study() {
        knowledge += 10;
        happiness -= 5;
       
        if (happiness < 0) {
            happiness = 0; // 행복도가 30 이하이면 자퇴?
        }
        if (happiness > 100) {
            happiness = 100;
        }
    }

    private void work() {
        money += 10;
        happiness -=5;
    
    if (happiness > 100) {
        happiness = 100;
    }
   }

    private void sleep() {
        happiness += 5;
        knowledge -= 1;
        if (happiness > 100) {
            happiness = 100;
        }
    }

    private void eat() {
        money -= 5;
        happiness += 5;
        if (money < 0) {
            money = 0;
        }
        if (happiness > 100) {
            happiness = 100;
        }
    }

    private void play() {
        money -= 10;
        happiness += 15;
        knowledge -= 10;
        if (money < 0) {
            money = 0;
        }
        if (happiness > 100) {
            happiness = 100;
        }
        if (knowledge < 0) {
            knowledge = 0;
        }
    }

    private void updateLabels() {
        knowledgeLabel.setText("Knowledge: " + knowledge);
        happinessLabel.setText("Happiness: " + happiness);
        moneyLabel.setText("Money: " + money);
        levelLabel.setText("Level: " + level.getCurrentLevel());
        seasonLabel.setText("Season: " + getSeasonName(timeManager.getSeason()));
    }

    private String getSeasonName(int season) {
        switch (season) {
            case 1:
                return "Spring";
            case 2:
                return "Summer";
            case 3:
                return "Fall";
            case 4:
                return "Winter";
            default:
                return "Unknown";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChaChaGraduation();
            }
        });
    }

    public void enjoyFestival() {
        happiness += 20;
        knowledge -= 50;

        if (happiness > 100) {
            happiness = 100;
        }
    }

    public void studyAtLibrary() {
        happiness -= 50;
        knowledge += 20;

        if (happiness > 100) {
            happiness = 100;
        }
    }
}

class Level {
    private int currentLevel;
    private int knowledgeThreshold;
    private boolean scholarshipAwarded; // 장학금이 지급되었는지 여부를 나타내는 변수

    public Level() {
        this.currentLevel = 1;
        this.knowledgeThreshold = 30;
        this.scholarshipAwarded = false;
    }

    public boolean levelUp(int knowledge, ChaChaGraduation chaChaGraduation) {
        if (knowledge >= knowledgeThreshold) {
            currentLevel++;
            knowledgeThreshold += 30;

            // Knowledge가 95 이상이면 장학금 지급
            if (knowledge >= 95 && !scholarshipAwarded) {
                JOptionPane.showMessageDialog(chaChaGraduation, "레벨 업 성공! & 장학금 지급!");
                chaChaGraduation.addMoney(200);
                scholarshipAwarded = true;
            }

            return true;
        } else {
            return false;
        }
    }

    public int getCurrentLevel() {
        return currentLevel;
    }
}

class TimeManager {
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