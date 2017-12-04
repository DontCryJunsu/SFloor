package WindowManager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**클래스 설명
 * <br>
 * 게임 플레이를 담당하는 패널
 * @author 박상우, 홍예진
 * @see javax.swing.JPanel
 */

// 튜토리얼을 담당하는 패널
public class GamePanel extends JPanel implements ActionListener{
	/** 패널 변환 메소드를 사용하기 위한 MainWindow 인스턴스 */
	MainWindow window;
	ImageIcon rollover_sk, pressedl_sk, normal_sk = new ImageIcon("images/BT_SK1.png");
	ImageIcon rollover_bk, pressedl_bk, normal_bk = new ImageIcon("images/BT_BK1.png");
	/** 게임 시작으로 넘어가기위한 버튼*/
	JButton skip = new JButton(normal_sk);
	/** 메인 메뉴로 돌아가기위한 버튼*/
	JButton back = new JButton(normal_bk);
	JLabel gp = new JLabel("게임패널-튜토리얼");
	JPanel pan_South = new JPanel();
	/**게임 패널 초기화
	 * @author 박상우, 홍예진
	 * @param window 인스턴스로 사용할 메인윈도우
	 * */
	GamePanel(MainWindow window){
		this.window = window;
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		add(gp,BorderLayout.NORTH);
		add(new Cartoon(),BorderLayout.CENTER);
		setSouth();
		setMain();
	}
	/**
	 * @author 홍예진
	 */
	public void setSouth(){
		setButton();
		pan_South.setBackground(Color.BLACK);
		pan_South.add(skip);
		pan_South.add(back);
		add(pan_South,BorderLayout.SOUTH);
	}
	/**
	 * @author 홍예진
	 */
	public void setButton(){
		pressedl_sk = new ImageIcon("images/BT_SK2.png");
		rollover_sk = new ImageIcon("images/BT_SK2.png");		
		skip.setPressedIcon(pressedl_sk);
		skip.setRolloverIcon(rollover_sk);
		skip.setOpaque(false);
		skip.setBorder(null);
		skip.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				window.effect.play("bt_cursorOn.wav");
			}
			public void mousePressed(MouseEvent e){
				window.effect.play("bt_selected.wav");
			}
		});

		rollover_bk = new ImageIcon("images/BT_BK2.png");
		pressedl_bk = new ImageIcon("images/BT_BK2.png");
		back.setPressedIcon(pressedl_bk);
		back.setRolloverIcon(rollover_bk);
		back.setOpaque(false);
		back.setBorder(null);
		back.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				window.effect.play("bt_cursorOn.wav");
			}
			public void mousePressed(MouseEvent e){
				window.effect.play("bt_selected.wav");
			}
		});

	}
	/**
	 * @author 홍예진
	 */
	public void setMain( ){
		skip.setBackground(Color.white);
		skip.setOpaque(false); 
		back.setBackground(Color.white); 
		back.setOpaque(false); 

		skip.addActionListener(this);
		back.addActionListener(this);
	}
	/**각각 버튼을 눌렀을 때 실행될 액션리스너 메소드 구현
	 * 통합 패널을 불러올 때 게임 쓰레드와 타이머 쓰레드가 시작
	 * @author 박상우, 홍예진
	 */
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == skip){
			window.changePanel(window.mgp);
			window.mgp.getGamePlaying().requestFocus();
			try{
				window.mgp.getTimer().start();
				window.mgp.getGamePlaying().getGamePlayingThread().start();
			}catch(Exception k){
				return;
			}
		}
		if(e.getSource() == back){
			window.sound.stop();
			window.sound.loop("BackgroundMusic_main.wav");
			window.changePanel(window.mainPanel);}
	}
	/**
	 * 
	 * @author 홍예진
	 *
	 */
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
						try{
							window.mgp.getTimer().start();
							window.mgp.getGamePlaying().getGamePlayingThread().start();
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