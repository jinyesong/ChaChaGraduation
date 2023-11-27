package source;

public class Game {
	Player player;
	
	public void startGame(Player player) {
		//loadGame에서 호출?
		this.player = player;
		//메인 UI 불러와서 setting
	}
    public void endGame() {
    	//졸업 엔딩 확인
    	String ending = player.checkEndingConditions();
    	//Ending 함수 호출
    };
    public void seasonChange() {
    	//TimeManager에서 호출?
    	//UI 변경
    	//이벤트 처리
    	//레벨 변경 -> 장학금 여부
    };
}
