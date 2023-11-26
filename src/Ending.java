import java.util.function.Predicate;

public class Ending {
    private String endingType;
    private Predicate<Player> conditions;

    // 생성자
    public Ending(String endingType, Predicate<Player> conditions) {
        this.endingType = endingType;
        this.conditions = conditions;
    }

    // 플레이어가 종료 조건을 충족했는지 확인
    public boolean isEndingAchieved(Player player) {
        return conditions.test(player);
    }

}
//    ⭐대학원, 졸업, 졸업실패 엔딩은 4학년 졸업식 이벤트 때만 조건 체크
//    자퇴 엔딩은 조건을 항상 체크해야 함. => 어떻게 구현??
//
//    "대학원" 엔딩
//    Ending graduateSchoolEnding = new Ending("대학원", player -> player.getMoney() >= 800);
//
//    "졸업" 엔딩
//    Ending graduationEnding = new Ending("졸업", player -> player.getMoney() >= 500 && player.getMoney() < 800);
//
//    "졸업 실패" 엔딩
//    Ending graduationFailedEnding = new Ending("졸업 실패", player -> player.getMoney() < 500);
//
//    특정 행복도 이하일 때 "자퇴" 엔딩
//    Ending dropOutEnding = new Ending("자퇴", player -> player.getHappiness() < 30);

