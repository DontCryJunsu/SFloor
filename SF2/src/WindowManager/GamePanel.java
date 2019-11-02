package WindowManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * <br>
 * 게임 플레이를 담당하는 패널
 * @author 박상우
 * @see java.swing.JPanel
 */

// 튜토리얼을 담당하는 패널
class GamePanel extends JPanel implements ActionListener{
	/** 패널 변환 메소드를 사용하기 위한 MainWindow 인스턴스 */
	MainWindow window;
	/** 게임 시작으로 넘어가기위한 버튼*/
	JButton start;
	/** 메인 메뉴로 돌아가기위한 버튼*/
	JButton back;
	ImageIcon normal;
	JLabel gp = new JLabel("게임패널-튜토리얼");
	// gp.setForeground(Color.WHITE);
	/** 생성자 매개변수로 MainWindow 객체를 받아 클래스내의 window에 저장*/
	GamePanel(MainWindow window){
		this.window = window;
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		add(gp,BorderLayout.NORTH);
		setbutton();
		add(start,BorderLayout.SOUTH);
		//add(back,BorderLayout.SOUTH);
		add(new Cartoon(),BorderLayout.CENTER);
		setmain();
	}
	public void setbutton(){
		normal = new ImageIcon("images/BT_SK1.png");
		//rollover = new ImageIcon("BT_SK2.png");
		//pressedl = new ImageIcon("BT_SK3.png");
		start = new JButton(normal);
		normal = new ImageIcon("images/BT_BK1.png");
		//rollover = new ImageIcon("BT_SK2.png");
		//pressedl = new ImageIcon("BT_SK3.png");
		back = new JButton(normal);
	}
	public void setmain( ){
		//start.setLocation(700, 550);
		start.setSize(new Dimension(180,35));
		start.setBackground(Color.white); //btn의 배경색을 투명하게 만드려고 아무 색이나 넣었음.
		start.setOpaque(false); //btn의 배경색 투명하게 만듬.
		//back.setLocation(900, 550);
		back.setSize(new Dimension(180 ,35));
		back.setBackground(Color.white); //btn의 배경색을 투명하게 만드려고 아무 색이나 넣었음.
		back.setOpaque(false); //btn의 배경색 투명하게 만듬.
		start.addActionListener(this);
		back.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == start){
			window.changePanel(window.mgp);
			window.mgp.getGamePlaying().requestFocus();

			if(!window.mgp.getTimer().isInterrupted())
				try{
					window.mgp.getTimer().start();
				}catch(Exception k){
					return;
				}
		}
		if(e.getSource() == back) window.changePanel(window.mainPanel);
	}
	class Cartoon extends JPanel {
		ImageIcon toonIcon[] = new ImageIcon[8];
		Image img;
		int i = 0;
		// JPanel toon[] = new JPanel[8];
		// 각각 만화의 한컷한컷을 담당-총 10컷
		Cartoon(){
			setToonPanel();
			setBackground(Color.BLACK);
			addMouseListener(new MouseListener() {
				public void mouseClicked (MouseEvent e) {
					if(i<8){
						img = toonIcon[i].getImage();
						repaint();
					}
					else if(i == 8){
						i = 0;
						window.changePanel(window.mgp);
						window.mgp.getGamePlaying().requestFocus();
						if(!window.mgp.getTimer().isInterrupted())
							try{
								window.mgp.getTimer().start();
							}catch(Exception k){
								return;
							}
					}
				}
				public void mouseEntered(MouseEvent arg0) {}
				public void mouseExited(MouseEvent arg0) {}
				public void mousePressed(MouseEvent arg0) {}
				public void mouseReleased(MouseEvent arg0) {}
			});
		}
		public void setToonPanel(){
			// 테스트용 임시 그림. 다 보면 다클릭하면 게임화면으로 넘어감.
			toonIcon[0] = new ImageIcon("images/J1.png");
			toonIcon[1] = new ImageIcon("images/J2.png");
			toonIcon[2] = new ImageIcon("images/J1.png");
			toonIcon[3] = new ImageIcon("images/J2.png");
			toonIcon[4] = new ImageIcon("images/J3.png");
			toonIcon[5] = new ImageIcon("images/J4.png");
			toonIcon[6] = new ImageIcon("images/J3.png");
			toonIcon[7] = new ImageIcon("images/J4.png");
			img = toonIcon[0].getImage();
		}
		public void paintComponent(Graphics g){
			i++;
			super.paintComponent(g);
			g.drawImage(img, 200, 70, this);
		}
	}
}