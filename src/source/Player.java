package source;

public class Player {
	String id;
	private int money;
	private int knowledge;
	private int happiness;
	private int level;
	
	public Player(String id) { //신규
		this.id = id;
		setMoney(0);
		setKnowledge(0);
		setHappiness(70);
		setLevel(1);
	}
	
	public Player(String id, int money, int knowledge, int happiness, int level) { //기존
		this.id = id;
		this.setMoney(money);
		this.setKnowledge(knowledge);
		this.setHappiness(happiness);
		this.setLevel(level);
	}
	
	public int[] getPlayerInfo() {
		int[] info = {getMoney(), getKnowledge(), getHappiness(), getLevel()};
		return info;
	}
	
    public void levelUp() {
    	setLevel(getLevel() + 1);
    }
    
    public String checkEndingConditions() {
    	if(getMoney() < 500) { //화석엔딩(BAD)
    		return "BAD";
    	}
    	else if(500 < getMoney() && getMoney() < 800) { //졸업 엔딩(GOOD)
    		return "GOOD";
    	}
    	else { //대학원 엔딩(HAPPY)
    		return "HAPPY";
    	}
    }
    
	public String getId() {
		return id;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(int knowledge) {
		this.knowledge = knowledge;
	}
	
	public int getHappiness() {
		return happiness;
	}
	
	public void setHappiness(int happiness) {
		this.happiness = happiness;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
