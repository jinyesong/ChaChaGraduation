package source;

public class Player {
	String id;
	int money;
	int knowledge;
	int happiness;
	int level;
	
	public Player(String id) { //�ű�
		this.id = id;
		money = 0;
		knowledge = 0;
		happiness = 70;
		level = 1;
	}
	
	public Player(String id, int money, int knowledge, int happiness, int level) { //����
		this.id = id;
		this.money = money;
		this.knowledge = knowledge;
		this.happiness = happiness;
		this.level = level;
	}
	
	public int[] getPlayerInfo() {
		int[] info = {money, knowledge, happiness, level};
		return info;
	}
	public String getId() {
		return id;
	}
    public void setMoney(int money) {
		this.money += money;
	}
    public void setKnowledge(int knowledge) {
    	this.knowledge += knowledge;
    }
    public void setHappiness(int happiness) {
    	this.happiness += happiness;
    }
	public int getMoney(){
		return money;
	}
	public int getHappiness(){
		return money;
	}
	public int getKnowledge(){
		return knowledge;
	}
    public void levelUp() {
    	level++;
    }
    public String checkEndingConditions() {
    	if(money < 500) { //ȭ�� ����(BAD)
    		return "BAD";
    	}
    	else if(500 < money && money < 800) { //���� ����(GOOD)
    		return "GOOD";
    	}
    	else { //���п� ����(HAPPY)
    		return "HAPPY";
    	}
    }
}
