package source;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class FileIO {
	
	public void saveGame(Player player, GameManager game) {
		int[] info = player.getPlayerInfo();

		JSONObject json = new JSONObject();
		json.put("money", info[0]);
		json.put("knowledge", info[1]);
		json.put("happiness", info[2]);
		json.put("level", info[3]);

        try (FileWriter file = new FileWriter( player.getId() + ".txt")) {
            file.write(json.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
    public Player loadGame(String id) {
    	int money = 0;
    	int knowledge = 0;
    	int happiness = 70;
    	int level = 1;
    	try {
    		String filePath = "src/user/" + id + ".txt";
    		
    		JSONParser parser = new JSONParser();
    		Reader reader = new FileReader(filePath);
    		JSONObject json = (JSONObject)parser.parse(reader);
    		
            money = (int)json.get("money");
            knowledge = (int)json.get("knowledge");
            happiness = (int)json.get("happiness");
            level = (int)json.get("level"); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    	Player player = new Player(id, money, knowledge, happiness, level);
    	return player;
    }
    
    public Player createPlayerFile(String id) { 
    	JSONObject json = new JSONObject();
		json.put("money", 0);
		json.put("knowledge", 0);
		json.put("happiness", 70);
		json.put("level", 1);

        try (FileWriter file = new FileWriter( "src/user/" + id + ".txt")) {
            file.write(json.toJSONString());
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
