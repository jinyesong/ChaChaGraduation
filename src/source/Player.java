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
		setHappiness(50);
		this.timeManager = new TimeManager(id);
	}
	
	public Player(String id, int money, int knowledge, int happiness, TimeManager tm) { //기존
		this.id = id;
		this.setMoney(money);
		this.setKnowledge(knowledge);
		this.setHappiness(happiness);
		this.timeManager = tm;
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
		if(100 < knowledge) {
			this.knowledge = 100;
		}
		else {
			this.knowledge = knowledge;
		}
	}
	
	public int getHappiness() {
		return happiness;
	}
	
	public void setHappiness(int happiness) {
		if(100 < happiness) {
			this.happiness = 100;
		}
		else {
			this.happiness = happiness;
		}
	}
}
