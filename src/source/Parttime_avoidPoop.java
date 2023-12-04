package source;

import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


import javax.swing.*;
import java.util.ArrayList;

public class Parttime_avoidPoop extends JFrame {
    public static ArrayList<DroppingObject> poopList = new ArrayList<>();
    Avoider chacha = new Avoider();
    OnPlayingScreen playingScreen = new OnPlayingScreen(chacha);
    ProducingObject producingPoopThread = new ProducingObject(playingScreen, chacha);
    DroppingThread droppingThread = new DroppingThread(playingScreen, chacha);

    Parttime_avoidPoop() {
        setTitle("똥 피하기 게임");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StartScreen startScreen = new StartScreen(this, playingScreen, producingPoopThread, droppingThread);
        add(startScreen);
        setSize(700, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Parttime_avoidPoop();
    }
}

class StartScreen extends JPanel {

		OnPlayingScreen playingScreen;
		Parttime_avoidPoop mainScreen;
		ProducingObject producingPoopThread;
		DroppingThread droppingThread;
		static long start;
		
		StartScreen(Parttime_avoidPoop mainScreen,OnPlayingScreen playingScreen, ProducingObject producingPoopThread , DroppingThread droppingThread){
			this.mainScreen = mainScreen;
			this.playingScreen = playingScreen;
			this.producingPoopThread = producingPoopThread;
			this.droppingThread = droppingThread;
			
			JButton avoidPoopBtn = new JButton("똥피하기"); //똥피하기 버튼
            JButton vocaQuizBtn = new JButton("단어 퀴즈"); //단어 퀴즈 버튼
            JButton BallGameBtn = new JButton("공 누르기 게임"); //공 누르기 게임 버튼
			
			avoidPoopBtn.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					// 메인에 있는 panel을 지우고 현재 panel을 삽입한다.
					start = System.currentTimeMillis();
					mainScreen.add(playingScreen);
					producingPoopThread.start();
					droppingThread.start();
					setVisible(false);
				};
				
			}); // 시작 버튼 클릭
			
			add(avoidPoopBtn);
            add(vocaQuizBtn);
            add(BallGameBtn);
		}
}


class Avoider extends JPanel {
    int life = 1;

    public Avoider() {
        try {
            ImageIcon icon = new ImageIcon("src/img/차차.png");
            Image image = icon.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
            JLabel pic = new JLabel(new ImageIcon(image));
            add(pic);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class DroppingObject extends JPanel {
    public DroppingObject() {
        try {
            ImageIcon icon = new ImageIcon("src/img/poop.png");
            Image image = icon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);

            JLabel pic = new JLabel(new ImageIcon(image));
            add(pic);
            setBounds(130, 180, 70, 70); // 크기 설정
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class OnPlayingScreen extends JPanel { //플레이 화면

	 Avoider chacha;
	 public static boolean gameEnd = false;
	 
	public OnPlayingScreen(Avoider chacha) {
		setLayout(null);
		
		this.chacha = chacha;
		chacha.setBounds(330, 680, 70, 70);   // chacha(extends JPanel)의 크기 및 위치 설정
		add(chacha);
		
		setFocusable(true);
		requestFocus(); 
		addKeyListener(new KeyListenerForAvoiding()); //방향키 이벤트
		
		setSize(700,800);
	}
	
	class KeyListenerForAvoiding extends KeyAdapter{// chacha 방향키 리스너

		public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			int coordX = chacha.getX();
			coordX = coordX - 40;
			if(coordX > -20) {
				chacha.setBounds(coordX, 680, 70,70 );
			}
		} //왼쪽 이동
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			int coordX = chacha.getX();
			coordX = coordX + 40;
			if(coordX<630) {
				chacha.setBounds(coordX, 680, 70,70 );
			}
		} //오른쪽 이동
	}
		
	}
}
class DroppingThread extends Thread {

	OnPlayingScreen playingScreen;
	Avoider chacha;
	DroppingObject poopSample = new DroppingObject();
	boolean vForCrushingCount;
    volatile boolean terminationFlag = true;
	
	DroppingThread(OnPlayingScreen playingScreen, Avoider chacha){
		this.playingScreen = playingScreen;
		this.chacha = chacha;
	}
	
	
	public void delay(int time) {
		try {
			Thread.sleep(time);
		} catch(InterruptedException e){} 
	}
	
	
	public void handleCrushing(DroppingObject poopForHandling, boolean crushingCount) {
		
		int poopX = poopForHandling.getX();
		int poopY = poopForHandling.getY();
		int chachaX = chacha.getX();
		int chachaY = chacha.getY();
		
			if(chachaX >= poopX) {
			if(((chachaX+chacha.getWidth())-poopX-(chacha.getWidth() + poopSample.getWidth()) <= 0)&&(((chachaY+chacha.getHeight()) - poopY - (chacha.getHeight() + poopSample.getHeight()) <=0))) {
				if(crushingCount == false) {
					chacha.life = chacha.life - 1;
					crushingCount = true;
					vForCrushingCount = crushingCount;
				}
			} 
		} else if (chachaX <= poopX) {
			if(((poopX+poopSample.getWidth())-chachaX-(chacha.getWidth() + poopSample.getWidth()) <= 0)&&(((chachaY+chacha.getHeight()) - poopY - (chacha.getHeight() + poopSample.getHeight()) <=0))) {
				if(crushingCount == false) {
					chacha.life = chacha.life - 1;
					crushingCount = true;
					vForCrushingCount = crushingCount;
				}
			}
		} 
		
			
		if(chacha.life ==0) {
			long end = System.currentTimeMillis();
			    JOptionPane aa=new JOptionPane();
			    float aliveTime = (end - StartScreen.start)/1000.0f; 
			    aa.showMessageDialog(null, "탈락입니다. 버틴 초: "+ aliveTime);
		}
		
	}       

	public void run(){
			delay(500);
		while(terminationFlag) {
			
			if(chacha.life == 0) {
				stopThread();
			}
			try {
				for(int i = 0; i<Parttime_avoidPoop.poopList.size(); i++) {
					
					if(chacha.life > 0) {
					DroppingObject poop = Parttime_avoidPoop.poopList.get(i);
					int droppingY = poop.getY() + 50;
					poop.setBounds(poop.getX(), droppingY, poop.getWidth(), poop.getHeight()); 
					playingScreen.add(poop);
					playingScreen.revalidate();
					playingScreen.repaint();
					delay(70);
					handleCrushing(poop,vForCrushingCount);
					vForCrushingCount = false;
					
					if(poop.getY() >= playingScreen.getHeight()) {
						if(Parttime_avoidPoop.poopList.size() == 1) {
							continue;
						}
						else {
						Parttime_avoidPoop.poopList.remove(i);
						playingScreen.remove(poop);
						}
					}
					}}// for문 끝. list에 있는 객체를 반복문으로 돌면서 위치를 아래로 이동시킨다. 
			} catch(ArrayIndexOutOfBoundsException e){}
		}
	}
	
	
    public void stopThread() {
        terminationFlag = false;
    }
	
	
}

class ProducingObject extends Thread{

	OnPlayingScreen playingScreen;
    volatile boolean terminationFlag = true;
    Avoider chacha;
    		
	ProducingObject(OnPlayingScreen playingScreen, Avoider chacha ){
		this.playingScreen = playingScreen;
		this.chacha =chacha;
	}
	
	public void delay(int time) {
		try {
			Thread.sleep(time);
		} catch(InterruptedException e){} 
	}
	
	public void run() {
		try {
			while(terminationFlag) {
				  DroppingObject poop = new DroppingObject();
				  int min = poop.getWidth();
				  int max = playingScreen.getWidth() - min;
				  int randomX = (int)(Math.random() * (max-min)) + min;
			      poop.setBounds(randomX, poop.getY(),poop.getWidth(), poop.getHeight());
			      Parttime_avoidPoop.poopList.add(poop);
			      playingScreen.add(poop);
			      
			      
			      if(chacha.life == 0) {
			    	  stopThread();
			      }
			      
			      playingScreen.revalidate();
			      playingScreen.repaint();
			      delay(1000);
			}// 객체를 생성해서 list에 넣고 화면에도 표시한다. 1초마다 시행한다.
			
		}catch(ArrayIndexOutOfBoundsException e) {}
		
	}
	
    public void stopThread() {
        terminationFlag = false;
    }
	
}