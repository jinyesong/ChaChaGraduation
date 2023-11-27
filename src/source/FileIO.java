package source;

import java.io.FileWriter;
import java.io.IOException;
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
	
    public Player loadGame() {
    	Player player = new Player("test", 500, 100, 100, 2);
    	return player;
    }
    
    public static void createPlayerFile(String playerId) { 
    	JSONObject json = new JSONObject();
		json.put("money", 4000);
		json.put("knowledge", 100);
		json.put("happiness", 100);
		json.put("level", 2);

        try (FileWriter file = new FileWriter( "src/user/" + playerId + ".txt")) {
            file.write(json.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //public void deletePlayerFile(String playerID);
}
