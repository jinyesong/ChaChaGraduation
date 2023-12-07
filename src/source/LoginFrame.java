package source;
 
import java.awt.Color;
import java.awt.Font;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
 
public class LoginFrame extends JFrame implements ActionListener{
 
    BufferedImage img = null;
    JTextField loginTextField;
    JPasswordField passwordField;
    JButton loginButton;
    JButton rankingButton;
 
    // 메인
    public static void main(String[] args) {
        new LoginFrame();
    }
 
    // 생성자
    public LoginFrame() {
        setTitle("대학생 차차 키우기");
        setSize(550, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 
        // 레이아웃 설정
        setLayout(null);
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1600, 900);
        layeredPane.setLayout(null);
 
        // 이미지 받아오기
        try {
            img = ImageIO.read(new File("src/img/login_page.png"));
        } catch (IOException e) {
            System.out.println("이미지 불러오기 실패");
            System.exit(0);
        }
         
        MyPanel panel = new MyPanel();
        panel.setBounds(0, 0, 1600, 900);
         
 
        // 로그인 필드
        loginTextField = new JTextField(15);
        loginTextField.setBounds(150, 390, 290, 50);
        loginTextField.setFont(new Font("Ariel", Font.PLAIN, 16));
        layeredPane.add(loginTextField);
        loginTextField.setOpaque(false);
        loginTextField.setForeground(Color.blue);
        loginTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        
        // 로그인버튼 추가
        loginButton = new JButton(new ImageIcon("src/img/login_button.png"));
        loginButton.setBounds(280, 450, 100, 55);
        loginButton.addActionListener(this);
 
        // 버튼 배경 투명처리
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setContentAreaFilled(false);
        layeredPane.add(loginButton);
        
        // 랭킹버튼 추가 및 배경 투명 처리
        rankingButton = new JButton(new ImageIcon("src/img/ranking_button.png"));
        rankingButton.setBounds(150, 450, 100, 55);
        rankingButton.addActionListener(this);
        rankingButton.setBorderPainted(false);
        rankingButton.setFocusPainted(false);
        rankingButton.setContentAreaFilled(false);
        layeredPane.add(rankingButton);
 
        // 마지막 추가들
        layeredPane.add(panel);
        add(layeredPane);
        setVisible(true);
    }
 
    class MyPanel extends JPanel {
        public void paint(Graphics g) {
            g.drawImage(img, 0, 0, null);
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loginButton) {
			String id = loginTextField.getText();
			FileIO fi = new FileIO();
//			if(id == "") { //빈문자열
//				System.out.println("비어있음");
//				actionPerformed(e);
//			}
			Player player;
			if(fi.findPlayer(id)) { //존재하는 아이디
				JOptionPane.showMessageDialog(null, "로그인 성공!");
				player = fi.loadGame(id);
				this.setVisible(false); //프레임 전환
				new UI(player);
			}else { //존재하지 않는 아이디
				int result = 0;
				result = JOptionPane.showConfirmDialog(null, "존재하지 않는 아이디입니다. 새로 생성하시겠습니까?");
				System.out.println(result);
				if(result == 0) { //새로 생성
					player = fi.createPlayerFile(id); 
					this.setVisible(false); //프레임 전환
					new UI(player);
				}
			}
		}
	}
}
