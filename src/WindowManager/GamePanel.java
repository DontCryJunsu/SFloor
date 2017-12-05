package WindowManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SFDesignManager.Design;
import SFDesignManager.Sound;

/**클래스 설명
 * <br>
 * 게임 플레이를 담당하는 패널
 * @author 박상우, 홍예진
 * @see javax.swing.JPanel
 */
public class GamePanel extends JPanel implements ActionListener{
	/** 패널 변환 메소드를 사용하기 위한 MainWindow 인스턴스 */
	MainWindow window;
	/** 게임 시작으로 넘어가기위한 버튼*/
	JButton skip;
	/** 메인 메뉴로 돌아가기위한 버튼*/
	JButton back;
	/**버튼에 디자인 설정을 위한 디자인 인스턴스 생성*/
	Design design = new Design();
	/**버튼에 사운드 설정을 위한 사운드 인스턴스 생성*/
	Sound effect = new Sound("sounds/bt_cursorOn.wav");
	/**텍스트 라벨*/
	JLabel gp = new JLabel("게임패널-튜토리얼<스토리 만화 및 조작법>");
	/**하단에 장착될 패널*/
	JPanel pan_South = new JPanel();
	/**튜토리얼과 스토리에 사용할 이미지 객체*/
	ImageIcon toonIcon[] = new ImageIcon[21];
	/**이미지 인스턴스*/
	Image img;
	/**이미지 번호에 사용할 값*/
	int i = 0;
	/**게임 패널 초기화
	 * @author 박상우, 홍예진
	 * @param window 인스턴스로 사용할 메인윈도우
	 * */
	GamePanel(MainWindow window){
		this.window = window;
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		gp.setForeground(Color.WHITE);
		add(gp,BorderLayout.NORTH);
		add(new Cartoon(),BorderLayout.CENTER);
		setSouth();
		setMain();
	}
	/**하단 패널 초기화
	 * @author 홍예진
	 */
	public void setSouth(){
		pan_South.setBackground(Color.BLACK);

		skip= design.setbutton(skip, 6);
		back = design.setbutton(back, 7);
		effect.setbutton(skip);
		effect.setbutton(back);
		pan_South.add(skip);
		pan_South.add(back);
		add(pan_South,BorderLayout.SOUTH);
	}
	/**패널의 배경과 버튼 액션리스너 설정
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
	 * <br>
	 * 통합 패널을 불러올 때 게임 쓰레드와 타이머 쓰레드가 시작
	 * @author 박상우, 홍예진
	 */
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == skip){
			i = 0;
			img = toonIcon[0].getImage();
			repaint();
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
			i = 0;
			img = toonIcon[0].getImage();
			repaint();
			window.Bgm.stop();
			window.Bgm.loop("sounds/BackgroundMusic_main.wav", Clip.LOOP_CONTINUOUSLY);
			window.changePanel(window.mainPanel);}
	}
	/**클래스 설명
	 * <br>
	 * 마우스 클릭에 따라 이미지를 변경할 패널
	 * @author 홍예진
	 * @see javax.swing.JPanel
	 */
	class Cartoon extends JPanel {
		Cartoon(){
			setToonPanel();
			setBackground(Color.BLACK);
			addMouseListener(new MouseListener() {
				public void mouseClicked (MouseEvent e) {
					if(i<21){
						img = toonIcon[i].getImage();
						repaint();
					}
					else if(i == 21){
						i = 0;
						img = toonIcon[0].getImage();
						repaint();
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
			for(int k =0; k<21;k++){
				toonIcon[k] = new ImageIcon("images/cartoons/cartoon"+(k+1)+".png");
			}
			img = toonIcon[0].getImage();
		}
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			i++;
			g.drawImage(img, 0, 0, this);
		}
	}
}