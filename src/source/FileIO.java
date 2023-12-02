package source;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class FileIO {
	
	public void saveGame(Player player) {
		int[] timeInfo = player.getTimeManagerInfo();
		
        try (FileWriter file = new FileWriter( player.getId() + ".txt")) {
        	file.write(player.getMoney()+"\n");
        	file.write(player.getKnowledge()+"\n");
        	file.write(player.getHappiness()+"\n");
        	file.write(player.getLevel()+"\n");
        	file.write(timeInfo[0]+"\n"+timeInfo[1]+"\n"); //0:clickcount 1:season
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
    public Player loadGame(String id) {
    	ArrayList<String> readArr = new ArrayList<String>();
    	try {
    		String filePath = "src/user/" + id + ".txt";
    		BufferedReader in = new BufferedReader(new FileReader(filePath));
    		String line;
    		while((line = in.readLine())!=null) {
    			readArr.add(line);
    		}
        } catch (Exception e) {
            e.printStackTrace();
        }
    	int money = Integer.parseInt(readArr.get(0)); //파일의 첫번째 줄에 money 정보 저장
    	int knowledge = Integer.parseInt(readArr.get(1)); //파일의 두번째 줄에 knowledge 정보 저장
    	int happiness = Integer.parseInt(readArr.get(2)); //파일의 세번째 줄에 happiness 정보 저장
    	int level = Integer.parseInt(readArr.get(3)); //파일의 네번째 줄에 level 정보 저장
    	int clickCount = Integer.parseInt(readArr.get(4)); //파일의 다섯번째 줄에 clickCount 정보 저장
    	int season = Integer.parseInt(readArr.get(5)); //파일의 여섯번째 줄에 season 정보 저장
    	TimeManager tm = new TimeManager(id, clickCount, season);
    	Player player = new Player(id, money, knowledge, happiness, level, tm);
    	return player;
    }
    
    public Player createPlayerFile(String id) { 
        try (FileWriter file = new FileWriter("src/user/" + id + ".txt")) {
            file.write(0+"\n"+0+"\n"+70+"\n"+1+"\n"); //money, knowledge, happiness, level 순서로 저장
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Player player = new Player(id);
        return player;
    }
    
    public boolean findPlayer(String id) {
    	String filePath = "src/user/" + id + ".txt";
    	File file = new File(filePath);
    	if(!file.exists()) { //파일 이름이 존재하면 false
    		return false; 
    	}
    	return true;
    }
    
    //public void deletePlayerFile(String playerID);
}
