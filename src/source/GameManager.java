package source;

public class GameManager { 
    public GameManager(Player player , UI ui) {
        this.player = player;
        this.ui = ui;

        Buttons(); //버튼을 정의 후
        checkHappiness(); //버튼이 눌릴 때마다 행복도 체크
    }

    public void Buttons() {
        studyButton.addActionListener(e -> performActionAndCheckHappiness(new StudyAction()));
        workButton.addActionListener(e -> performActionAndCheckHappiness(new WorkAction()));
        sleepButton.addActionListener(e -> performActionAndCheckHappiness(new SleepAction()));
        eatButton.addActionListener(e -> performActionAndCheckHappiness(new EatAction()));
        playButton.addActionListener(e -> performActionAndCheckHappiness(new HaveFunAction()));
    }

    public void performActionAndCheckHappiness(Action action) {
        
        checkHappiness();
        //클릭에 따른 ui 및 수치 변경
    }

    public void checkHappiness() {
        if (player.getHappiness() <= 30) {
            //행복도가 30 이하일 때 자퇴엔딩
        }
    }
}

//Game 클래스 와의 차이점을 구별해서 더 작성해야 할듯 함
