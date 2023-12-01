package source;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

//import TimeManager.FestivalEventDialog;

public class TimeManager {
	private Player player;
    private int season;
    private int clickCount;
    private boolean festivalEventOccurred; // 이벤트가 발생했는지 여부를 나타내는 변수

    TimeManager(Player player) { //신규
    	this.player = player;
        this.setSeason(1); // 1: Spring, 2: Summer, 3: Fall, 4: Winter
        this.setClickCount(0);
        this.festivalEventOccurred = false;
    }
    
    TimeManager(Player player, int season, int clickCount, boolean isOccurred) { //누적
    	this.player = player;
        this.setSeason(season);
        this.setClickCount(clickCount);
        this.festivalEventOccurred = isOccurred;
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
		if (getClickCount() % 10 == 0) { //10이 될 때마다 계절 변경
            changeSeason();
        }
	}
    
    private void changeSeason() {
    	if (getSeason() == 3) { //가을 -> 겨울 계절 변경시 이벤트 발생
            showFestivalEvent();
            festivalEventOccurred = true; // 이벤트 발생 여부 업데이트
        } else {
            festivalEventOccurred = false; // 축제 이벤트를 초기화하여 다음 계절에도 발생하도록 함
        }
        setSeason((getSeason() % 4) + 1); // 계절이 순환하도록 함
    }

    private void showFestivalEvent() {
        Event festivalEvent = new Event();
        festivalEvent.festivalEvent(player);
    }
}
