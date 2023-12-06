package source;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TimeManager {
	private String PlayerId;
    private int season;
    private int clickCount;
    private int level;

    TimeManager(String id) { //신규
    	this.PlayerId = id;
        this.setSeason(1); // 1: Spring, 2: Summer, 3: Fall, 4: Winter
        this.setClickCount(0);
        this.setLevel(1);
    }
    
    TimeManager(String id, int clickCount, int season, int level) { //누적
    	this.PlayerId = id;
        this.setSeason(season);
        this.setClickCount(clickCount);
        this.setLevel(level);
    }

    public int getSeason() {
        return season;
    }
    public void setSeason(int season) {
		this.season = season;
	}

	public int getClickCount() {
		return clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}
	
	public void addClickCount() {
		clickCount++;
		System.out.println(clickCount + "계절: " + season);
		if (getClickCount() % 10 == 0) { //10이 될 때마다 계절 변경
			setSeason((getSeason() % 4) + 1); // 계절이 순환하도록 함
        }
		if (getClickCount() % 40 == 0 ) { //레벨업
			setLevel(getLevel()+1);
		}
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

//    private void showFestivalEvent() {
//        Event festivalEvent = new Event();
//        festivalEvent.festivalEvent(PlayerId);
//    }
}
