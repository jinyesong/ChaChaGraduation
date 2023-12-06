package source;

import javax.swing.JOptionPane;

public class Player {
	String id;
	private int money;
	private int knowledge;
	private int happiness;
	TimeManager timeManager;
	
	public Player(String id) { //신규
		this.id = id;
		setMoney(0);
		setKnowledge(0);
		setHappiness(70);
		this.timeManager = new TimeManager(id);
	}
	
	public Player(String id, int money, int knowledge, int happiness, TimeManager tm) { //기존
		this.id = id;
		this.setMoney(money);
		this.setKnowledge(knowledge);
		this.setHappiness(happiness);
		this.timeManager = tm;
	}

//    public void levelUp() {
//    	setLevel(getLevel() + 1);
//    	
//    	if (knowledge >= 300) { //학년별로 다르게 수정해야 함
//            //JOptionPane.showMessageDialog(player, "레벨 업 성공! & 장학금 지급!");
//            setMoney(getMoney()+200);
//        }
//    }
    
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
    
    public int[] getTimeManagerInfo() {
    	int[] info = {timeManager.getClickCount(), timeManager.getSeason(), timeManager.getLevel()};
    	return info;
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
}
