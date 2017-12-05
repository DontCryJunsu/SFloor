package WindowManager;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SFDesignManager.Design;
import SFDesignManager.Sound;

public class MainPanel extends JPanel  implements ActionListener{
	/**메인 윈도우 인스턴스*/
	MainWindow window;
	/**버튼에 디자인 설정을 위한 디자인 인스턴스 생성*/
	Design design = new Design();
	/**버튼에 사운드 설정을 위한 사운드 인스턴스 생성*/
	Sound effect = new Sound("sounds/bt_cursorOn.wav");
	/**게임 시작을 위한 버튼*/
	JButton ToGameBtn;
	/**랭크 패널을 보기 위한 버튼*/
	JButton ToRankBtn;
	/**환경 설정 패널을 보기 위한 버튼*/
	JButton ToConfigBtn;
	/**종료 다이얼로그를 띄울 버튼*/
	JButton ExitBtn;
	/**종료 다이얼로그*/
	ExitDialog exit;
	/**메인 화면 이미지*/
	ImageIcon mainIcon = new ImageIcon("images/Slippery_floor.png");
	/**게임 이름 이미지*/
	ImageIcon GameNameIcon = new ImageIcon("images/SF_MAIN.png");
	/**paintComponent 메소드 오버라이드
	 * @author 홍예진
	 * @param g 그래픽
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Image img = mainIcon.getImage();
		g.drawImage(img, 200, 70, this);
		img = GameNameIcon.getImage();
		g.drawImage(img, 550, 50, this);

	}
	/**메인 패널 초기화
	 * @author 홍예진
	 * @param window 메인 윈도우 인스턴스
	 */
	public MainPanel(MainWindow window){
		this.window = window;

		setmain();

		setBackground(Color.BLACK);
		setLayout(null);
	}
	/**버튼 크기,위치,디자인 조정,부착의 세부사항 처리.
	 * @author 홍예진
	 */
	public void setmain( ){
		ToGameBtn = design.setbutton(ToGameBtn, 0);
		ToRankBtn = design.setbutton(ToRankBtn, 1);
		ToConfigBtn = design.setbutton(ToConfigBtn, 2);
		ExitBtn = design.setbutton(ExitBtn, 3);

		effect.setbutton(ToGameBtn);
		effect.setbutton(ToRankBtn);
		effect.setbutton(ToConfigBtn);
		effect.setbutton(ExitBtn);

		add(ToGameBtn);
		add(ToRankBtn);
		add(ToConfigBtn);
		add(ExitBtn);

		ToGameBtn.addActionListener(this);
		ToRankBtn.addActionListener(this);
		ToConfigBtn.addActionListener(this);
		ExitBtn.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e){
		if((JButton)e.getSource() == ToGameBtn){
			window.Bgm.stop();
			window.Bgm.loop("sounds/BackgroundMusic_tutorial.wav", Clip.LOOP_CONTINUOUSLY);
			window.changePanel(window.gamePanel);
		}
		if((JButton)e.getSource() == ToRankBtn) {
			window.rankPanel.updateRank();
			window.changePanel(window.rankPanel);
		}
		if((JButton)e.getSource() == ToConfigBtn){
			window.changePanel(window.configPanel);
		}
		if((JButton)e.getSource() == ExitBtn) {
			exit = new ExitDialog(window.contentPane);
			exit.setVisible(true);
		}
	}


	/**클래스 설명
	 * <br>
	 * 종료 버튼을 눌렀을 때 나타나는 다이얼로그
	 * @author 박상우
	 * @see javax.swing.JDialog
	 */
	public class ExitDialog extends JDialog implements ActionListener{
		JPanel labelPanel = new JPanel();
		JPanel btnPanel = new JPanel();
		JLabel label = new JLabel("정말로 종료하시겠습니까?");
		Font labelFont = new Font(Font.SANS_SERIF, Font.BOLD, 20);
		Design design = new Design();
		JButton yes;
		JButton no;
		private Sound effect = new Sound("sounds/bt_cursorOn.wav");
		ExitDialog(Container c){
			setTitle("게임 종료");
			setSize(300,200);
			setLayout(new BorderLayout());
			setAlwaysOnTop(true);
			setLocationRelativeTo(c);
			setBackground(Color.BLACK);
			label.setFont(labelFont);
			label.setForeground(Color.WHITE);
			labelPanel.add(label);
			labelPanel.setBackground(Color.BLACK);
			yes = design.setbutton(yes,4);
			no = design.setbutton(no,5);
			effect.setbutton(yes);
			effect.setbutton(no);
			yes.addActionListener(this);
			no.addActionListener(this);
			btnPanel.setBackground(Color.BLACK);
			btnPanel.add(yes);
			btnPanel.add(no);
			add(labelPanel, BorderLayout.NORTH);
			add(btnPanel, BorderLayout.CENTER);
		}
		public void actionPerformed(ActionEvent e){
			if((JButton)e.getSource() == yes) System.exit(0);
			else dispose();
		}
	}
}