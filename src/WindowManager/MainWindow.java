package WindowManager;

import java.awt.*;
import javax.swing.JFrame;

import GamePlayManager.MergedGamePanel;
import SFDesignManager.Design.MainPanel;
import SFDesignManager.Sound;
import SFDesignManager.Intro;
/**클래스 설명
 * <br>
 * 게임의 컴포넌트들을 포함할 메인 프레임 클래스
 * 각 패널들을 인스턴스로 포함
 * @author 박상우, 홍예진
 * @see javax.swing.JFrame
 */
public class MainWindow extends JFrame{
	/**컨텐트팬을 알아내기 위한 인스턴스*/
	public Container contentPane = getContentPane();
	// public Intro introPanel = new Intro(this);
	/**메인 패널 인스턴스*/
	public MainPanel mainPanel = new MainPanel(this);
	/**게임 패널 인스턴스*/
	public GamePanel gamePanel = new GamePanel(this);
	/**랭크 패널 인스턴스*/
	public RankPanel rankPanel = new RankPanel(this);
	/**환경 설정 패널 인스턴스*/
	public ConfigPanel configPanel = new ConfigPanel(this);
	/**통합 패널 인스턴스*/
	public MergedGamePanel mgp = new MergedGamePanel(this);
	public Sound sound = new Sound("BackgroundMusic_main.wav");
	public Sound effect = new Sound();
	/**메인 윈도우 클래스 초기화
	 * @author 박상우, 홍예진
	 * */
	public MainWindow(){
		setTitle("Slippery Floor");
		setSize(1400, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setLayout(null);
		setLocationRelativeTo(null);
		//mainPanel.setbutton(ToGameBtn, ToRankBtn, ToConfigBtn, ExitBtn);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("Slippery_floor.png");
		setIconImage(img);

		add(mainPanel);
		setVisible(true);
		sound.loop(); // 음악을 반복 재생한다.
	}
	/**화면 전환을 위한 메소드
	 * 다음에 불러올 패널을 매개변수로 받는다
	 * @author 박상우
	 * @param c 다음에 불러올 컴포넌트
	 */
	public void changePanel(Component c){
		contentPane.removeAll();
		contentPane.add(c);
		contentPane.revalidate();
		contentPane.repaint();
		if(c == rankPanel) rankPanel.updateRank();
	}
}
