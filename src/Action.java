
public class Action {
    private final int knowledgeEffect; // 지식에 미치는 영향
    private final int happinessEffect; // 행복도에 미치는 영향
    private final int moneyEffect;     // 돈에 미치는 영향

    // 생성자
    public Action(int knowledgeEffect, int happinessEffect, int moneyEffect) {
        this.knowledgeEffect = knowledgeEffect;
        this.happinessEffect = happinessEffect;
        this.moneyEffect = moneyEffect;
    }

    // 게터 메서드들 (각 속성의 값을 반환)

    public int getKnowledgeEffect() {
        return knowledgeEffect;
    }

    public int getHappinessEffect() {
        return happinessEffect;
    }

    public int getMoneyEffect() {
        return moneyEffect;
    }

    // 행동을 수행하는 메서드
    public void performAction(Player player) {
        // 플레이어의 속성을 행동에 따라 조정
//        player.setKnowledge(player.getKnowledge() + knowledgeEffect);
//        player.setHappiness(player.getHappiness() + happinessEffect);
//        player.setMoney(player.getMoney() + moneyEffect);
    }
}

