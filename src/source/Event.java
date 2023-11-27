package source;

public class Event { //시험 이벤트 구현 (레벨업과 같은 로직이라 수정이 필요할듯...)
   
    public static void WiterExam(Player player) {
        int currentLevel = player.getLevel();
        int currentKnowledge = player.getKnowledge();

        if (currentLevel == 1 && currentKnowledge >= 30) { //각 학년 조건에 맞게 진학 여부 결정
            player.setLevel(2);
            player.setKnowledge(0);    //지식을 0으로 초기화
        } else if (currentLevel == 2 && currentKnowledge >= 60) {
            player.setLevel(3);
            player.setKnowledge(0); 
        } else if (currentLevel == 3 && currentKnowledge >= 90) {
            player.setLevel(4);
            player.setKnowledge(0); 
        }                                                   //4학년의 경우에는 어떻게 할건지?
    } 
}
