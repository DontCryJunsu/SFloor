package WindowManager;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GamePlayManager.Game;
/**Class Description of MainWindow
 * <br>
 * 전환될 패널들을 인스턴스로 가지는 메인 프레임 클래스
 * @author 박상우
 * @see javax.swing.JFrame
 */
public class MainWindow extends JFrame implements ActionListener{
	/**컨텐트팬을 알아내기위한 컨테이너 인스턴스*/
	public Container contentPane = getContentPane();
	/**초기 화면의 컴포넌트들을 포함할 패널 */
	public JPanel mainPanel = new JPanel();
	/**객체내의 인스턴스로 이 클래스를 가지는 게임패널 */
	GamePanel gamePanel = new GamePanel(this);
	/**객체내의 인스턴스로 이 클래스를 가지는 랭크패널 */
	RankPanel rankPanel = new RankPanel(this);
	/**객체내의 인스턴스로 이 클래스를 가지는 환경설정패널 */
	ConfigPanel configPanel = new ConfigPanel(this);
	/**게임 플레이를 담당하는 게임플레이패널*/
	Game MyGame = new Game(this);
	/**게임패널로 넘어가기위한 버튼*/
	JButton ToGameBtn = new JButton("게임 시작");
	/**랭크패널로 넘어가기위한 버튼*/
	JButton ToRankBtn = new JButton("순위 보기");
	/**환경설정패널로 넘어가기위한 버튼*/
	JButton ToConfigBtn = new JButton("환경 설정");
	/**프로그램 종료여부를 물어보기위한 버튼*/
	JButton ExitBtn = new JButton("게임 종료");
	/**메인 프레임의 이름, 크기를 설정하고 버튼들에 액션리스너를 적용
	 * <br>
	 * 각각의 버튼들을 메인패널에 추가하고 메인패널을 프레임에 추가
	 */
	public MainWindow(){
		setTitle("Slippery Floor");
		setSize(1280, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setLayout(null);
		setLocationRelativeTo(null);
		ToGameBtn.addActionListener(this);
		ToRankBtn.addActionListener(this);
		ToConfigBtn.addActionListener(this);
		ExitBtn.addActionListener(this);
		mainPanel.add(ToGameBtn);
		mainPanel.add(ToRankBtn);
		mainPanel.add(ToConfigBtn);
		mainPanel.add(ExitBtn);
		add(mainPanel);
		setVisible(true);
	}
	/**버튼마다 각각 불러오는 패널이 다르게 설정
	 * <br>
	 * 게임 종료버튼을 누르면 종료여부를 물어보는 프레임 생성
	 */
	public void actionPerformed(ActionEvent e){
		if((JButton)e.getSource() == ToGameBtn) changePanel(gamePanel);
		if((JButton)e.getSource() == ToRankBtn) changePanel(rankPanel);
		if((JButton)e.getSource() == ToConfigBtn) changePanel(configPanel);
		if((JButton)e.getSource() == ExitBtn) new SecFrm();
	}
	/**패널을 전환하는 메소드
	 * <br>
	 * 컨텐트팬의 모든 컴포넌트를 지운다음에 매개변수로 받은 패널을 컨텐트팬에 추가하여 다시그림
	 * 
	 * @param c 다음에 불러올 패널
	 */
	public void changePanel(Component c){
		contentPane.removeAll();
		contentPane.add(c);
		contentPane.revalidate();
		contentPane.repaint();
	}
	/**Class Description of SecFrm
	 * <br>
	 * 메인 프레임에서 게임 종료 버튼을 눌렀을 때 나타나는 서브 프레임
	 * @author 박상우
	 * @see javax.swing.JFrame
	 */
	class SecFrm extends JFrame implements ActionListener{
		/**텍스트 라벨을 포함할 라벨패널*/
		JPanel labelPanel = new JPanel();
		/**버튼을 포함할 버튼패널*/
		JPanel btnPanel = new JPanel();
		/**텍스트를 출력하기위한 라벨*/
		JLabel label = new JLabel("정말로 종료하시겠습니까?");
		/**종료를 선택하는 버튼*/
		JButton yes = new JButton("예");
		/**종료를 선택하지 않는 버튼*/
		JButton no = new JButton("아니오");
		/**서브 프레임의 이름과 크기설정
		 * <br>
		 * 각 패널에 라벨과 버튼을 추가하여 프레임에 추가
		 * 
		 */
		SecFrm(){
			setTitle("게임 종료");
			setSize(300,200);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLayout(new BorderLayout());
			setLocationRelativeTo(contentPane);
			labelPanel.add(label);
			yes.addActionListener(this);
			no.addActionListener(this);
			btnPanel.add(yes);
			btnPanel.add(no);
			add(labelPanel, BorderLayout.NORTH);
			add(btnPanel, BorderLayout.CENTER);
			setVisible(true);
		}
		/**버튼의 종류에 따라 종료여부를 결정하는 액션리스너의 메소드
		 * 
		 */
		public void actionPerformed(ActionEvent e){
			if((JButton)e.getSource() == yes) System.exit(0);
			else dispose();
		}
	}
}
