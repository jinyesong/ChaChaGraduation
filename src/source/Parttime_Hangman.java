package source;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import source.UI.Stat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Parttime_Hangman extends JFrame {
    private List<String> wordList = new ArrayList<>();  //문제 리스트
    private int limit;                                  //제한 횟수, 여기서는 10으로 고정
    private int index;                                  //문제 번호 저장
    private String question;                            //문제
    private String correct;                             //정답
    private String underscore;                          //표시될 밑줄
    private String answer;                              //내가 추측한 정답
    private String toUpperA , toUpperQ;                 //대소문자 구별하기 위함
    private JTextField inputField;                      //정답을 입력할 텍스트필드
    private JButton guessButton;                        //알파벳 입력 후 추측 버튼 클릭을 통해 확인
    private JTextArea outputArea;  
    private int waterFill;
    private Image currentImage;
    private Player player;
    private Stat ui;
    private static int startcnt;

    public Parttime_Hangman(Player player, Stat ui) {
        try {
        	this.player = player;
        	this.ui = ui;
            if (startcnt == 0) {
                firstScreen();
            }
            readText();                                                 //hangman.txt 에서 문제를 불러옴
            this.index = (int) (this.wordList.size() * Math.random());  //문제는 랜덤하게 지정
            this.question = wordList.get(index);                        
            this.limit = 10;                                            //제한횟수는 10으로 고정
            toUpperQ = question.toUpperCase();                          //문제의 알파벳을 모두 대문자로 변환
            underscoreMaker();                                          //문제의 처음 형태를 밑줄로 표시하는 함수
            
            componentsMaker();                                          //추측 버튼과 알파벳 입력 필드 구성

            setTitle("행맨 게임");      
            setSize(700,800);       
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);                              //모니터 중앙에 표시
            setLayout(new BorderLayout());                              
            addComponents();                                            //입력 결과와 차차그림(행맨) 표시
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void firstScreen() {
        startcnt = 1;
        JFrame fr = new JFrame("행맨 게임");
        fr.setSize(700,800);
        fr.setLocationRelativeTo(null);

        JPanel pn = new JPanel(){
            Image background = new ImageIcon("src/img/hangman_readyPanel.png").getImage();
            public void paintComponent(Graphics g) {
                g.drawImage(background, 0, 0, 690, 780, null);
        }
        };

        JButton startBtn = new JButton(); //시작하기 버튼

        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fr.dispose();
                new Parttime_Hangman(player, ui).setVisible(true);
            }
        });

        pn.setLayout(null);
        startBtn.setBounds(260, 700 , 160, 60);
        startBtn.setBorderPainted(false);
        startBtn.setFocusPainted(false);
        startBtn.setContentAreaFilled(false);

        pn.add(startBtn);

        fr.add(pn);
        fr.setVisible(true);
    }


    private void readText() throws IOException {                        //문제 불러오는 부분
        try (BufferedReader br = new BufferedReader(new FileReader("src/hangman.txt"))) {
            String line;
            while((line = br.readLine()) != null) {
                wordList.add(line.trim());
            }
        }
    }   

    private void underscoreMaker() {
       StringBuilder str1 = new StringBuilder();                        //str1에 문제 길이만큼의 밑줄을
       StringBuilder str2 = new StringBuilder();                        //str2에 문제를 저장
       for (int i = 0; i < question.length(); i++) {
        str1.append("_ ");
        str2.append(question.charAt(i)).append(" ");
       }
       this.underscore = new String(str1);                              //저장한 것을 각각 underscore와
       this.correct = new String(str2);                                 //correct에 저장
    }

    private void componentsMaker() {
        inputField = new JTextField(1);                                           //알파벳 입력 필드
        guessButton = new JButton(new ImageIcon("src/img/guess_button.png"));   //추측 버튼
        guessButton.setBorderPainted(false);                                           //버튼 다듬기 작업
        guessButton.setFocusPainted(false);             
		guessButton.setContentAreaFilled(false);
        guessButton.setOpaque(false);
        outputArea = new JTextArea();                                                   
        
        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processGuess();
            }
        }); 
    }

    private void addComponents() {
        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("문자를 입력해주세요(한글자 영어) : ");
        inputPanel.add(label);
        inputPanel.add(inputField);
        inputPanel.add(guessButton);
        inputPanel.setBackground(new Color(0, 160, 186));

            JPanel hangmanPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            int rectWidth = 214;
            int rectHeight = 251;
            int x = (getWidth() - rectWidth) / 2;
            int y = (getHeight() - rectHeight) / 2;

            
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.BLACK);

                // 차차그림 들어갈 곳
            g2d.setStroke(new BasicStroke(5));
            g2d.drawRect(x, y, rectWidth, rectHeight);

            g2d.setColor(new Color(0, 160, 186));
            int waterHeight = rectHeight * waterFill / 100;
            g2d.fillRect(x,y + (rectHeight - waterHeight) , rectWidth , waterHeight);

            // 그릴 이미지 로드
            currentImage = new ImageIcon("src/img/차차.png").getImage();

            if (limit == 0) { //오답 시
                currentImage = new ImageIcon("src/img/틀렸차차.png").getImage();
            } 
            if (underscore.equals(correct)) { //정답 시
                currentImage = new ImageIcon("src/img/해냈차차.png").getImage();
                waterFill = 0;
                repaint();
            }
            // 이미지를 사각형 안에 그리기
            g.drawImage(currentImage, x, y, rectWidth, rectHeight, this);
            }
        };
        
        
        add(inputPanel, BorderLayout.SOUTH);
        add(outputArea , BorderLayout.NORTH);
        add(hangmanPanel, BorderLayout.CENTER);
        
    }

    private void processGuess() { //알파벳을 받는 순간 시작
        answer = inputField.getText();
        if (answer.length() != 1) {
            outputArea.append("한글자만 입력 가능합니다!.\n");
            return;
        }

        toUpperA = answer.toUpperCase();
        compare();

        
        
        if (underscore.equals(correct)) {
            endGame(true); //정답을 맞추면 true
            
            
        } else {
            limit--;
            waterFill = 100 - (limit * 10);
            outputArea.setText("현재 상태 : " + underscore + "\n" + "남은 횟수 : " + (limit));
            
        }
        
        if (limit == 0) {
                outputArea.setText("정답을 맞추지 못했습니다 ㅠㅠ \n");
                outputArea.append("정답 : " + this.question + "\n");

                endGame(false); //실패
            }
        

        repaint();
    }
    private void compare() { //정답에 내가 입력한 알파벳이 있는지 확인
        StringBuilder updatedUnderscore = new StringBuilder();

        for (int i = 0; i < question.length(); i++) {
            if(toUpperA.charAt(0) == toUpperQ.charAt(i)) {
                updatedUnderscore.append(question.charAt(i)).append(" ");
            } else {
                
                updatedUnderscore.append(underscore.charAt(2 * i)).append(" ");
            }
        }

        underscore = updatedUnderscore.toString();
    }

    private void endGame(boolean isSuccess) { //게임이 끝나면 버튼 및 알파벳 입력칸 비활성화
        inputField.setEnabled(false);
        guessButton.setEnabled(false);
        repaint();
        
        //알바비 지급
        int earnedMoney;
        if(isSuccess) {
        	earnedMoney = 15;
        }
        else {
        	earnedMoney = 0;
        }
        
        JOptionPane.showMessageDialog(null, "게임 종료! "+ earnedMoney + "만큼 머니 스탯이 상승합니다!");
        player.setMoney(player.getMoney() + earnedMoney);
        ui.repaint();
        dispose();
    }

    @Override
    public void dispose() {            //게임이 종료될 때마다 설명화면 재출력을 위해 startcnt 0으로 초기화
        super.dispose();
        startcnt = 0;
    }
}
