package source;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ranking extends JFrame{
	//지식, 행복도, 돈 각각의 랭킹을 아이디 이름으로 나열함
	//각각의 함수로 구현하고 모든 파일의 특정 줄을 읽어서 ArrayList에 저장
	Map<String, Integer> knowMap;
	Map<String, Integer> happMap;
	Map<String, Integer> moneyMap;
	
	Ranking(){
		//File
		File dir = new File("src/user");
		//전체 파일 리스트
		ArrayList<File> files = new ArrayList<File>(Arrays.asList(dir.listFiles()));
		//지식 랭킹 리스트
		knowMap = new HashMap<String, Integer>();
		//행복도 랭킹 리스트
		happMap = new HashMap<String, Integer>();
		//머니 랭킹 리스트
		moneyMap = new HashMap<String, Integer>();
		
		for(File f : files) {
			int count = 0;
			try (BufferedReader br = new BufferedReader(new FileReader(f))) {
				String line;
				while((line = br.readLine()) != null) {
					String fileName = f.getName().substring(0, f.getName().lastIndexOf('.'));
					if(count == 0) { //돈
						moneyMap.put(fileName, Integer.parseInt(line));
						//System.out.println(fileName+ Integer.parseInt(line));
					}
					else if(count == 1) { //지식
						knowMap.put(fileName, Integer.parseInt(line));
					}
					else if(count == 2) { //행복도
						happMap.put(fileName, Integer.parseInt(line));
						break;
					}
					count++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//UI
		setSize(564, 737);
		setTitle("차차 키우기 랭킹");
		setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel knowledgeRanking = new KnowledgeRanking(knowMap);
		knowledgeRanking.setBounds(50, 100, 500, 500);
		add(knowledgeRanking);
		knowledgeRanking.setVisible(true); //default
		
		JPanel happinessRanking = new HappinessRanking(happMap);
		happinessRanking.setBounds(50, 100, 500, 500);
		add(happinessRanking);
		happinessRanking.setVisible(false);
		
		JPanel moneyRanking = new MoneyRanking(moneyMap);
		moneyRanking.setBounds(50, 100, 500, 500);
		add(moneyRanking);
		moneyRanking.setVisible(false);
		
		JButton showKnowledgeBtn = new JButton("지식 랭킹");
		showKnowledgeBtn.addActionListener(e -> {
			knowledgeRanking.setVisible(true);
			happinessRanking.setVisible(false);
			moneyRanking.setVisible(false);
		});
		JButton showHappinessBtn = new JButton("행복도 랭킹");
		showHappinessBtn.addActionListener(e -> {
			knowledgeRanking.setVisible(false);
			happinessRanking.setVisible(true);
			moneyRanking.setVisible(false);
		});
		JButton showMoneyBtn = new JButton("머니 랭킹");
		showMoneyBtn.addActionListener(e -> {
			knowledgeRanking.setVisible(false);
			happinessRanking.setVisible(false);
			moneyRanking.setVisible(true);
		});
		
		showKnowledgeBtn.setBounds(10, 10, 90, 40);
		add(showKnowledgeBtn);
		showHappinessBtn.setBounds(110, 10, 110, 40);
		add(showHappinessBtn);
		showMoneyBtn.setBounds(230, 10, 90, 40);
		add(showMoneyBtn);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	class KnowledgeRanking extends JPanel{
		KnowledgeRanking(Map<String, Integer> knowMap){
			knowMap.entrySet().stream()
				.sorted(Map.Entry.comparingByValue())
				.forEach(entry -> {
					JLabel rankText = new JLabel(entry.getKey() + " : " + entry.getValue());
					add(rankText);
				});
		}
	}
	
	class HappinessRanking extends JPanel{
		HappinessRanking(Map<String, Integer> happMap){
			happMap.entrySet().stream()
			.sorted(Map.Entry.comparingByValue())
			.forEach(entry -> {
				JLabel rankText = new JLabel(entry.getKey() + " : " + entry.getValue());
				add(rankText);
			});
		}
	}
	
	class MoneyRanking extends JPanel{
		MoneyRanking(Map<String, Integer> moneyMap){
			moneyMap.entrySet().stream()
			.sorted(Map.Entry.comparingByValue())
			.forEach(entry -> {
				JLabel rankText = new JLabel(entry.getKey() + " : " + entry.getValue());
				add(rankText);
			});
		}
	}
}
