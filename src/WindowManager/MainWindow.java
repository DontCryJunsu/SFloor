package WindowManager;

import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;

import javax.sound.sampled.Clip;
import javax.swing.JFrame;

import GamePlayManager.MergedGamePanel;
import SFDesignManager.Sound;
/**클래스 설명
 * <br>
 * 게임의 컴포넌트들을 포함할 메인 프레임 클래스
 * <br>
 * 각 패널들을 인스턴스로 포함
 * @author 박상우, 홍예진
 * @see javax.swing.JFrame
 */
public class MainWindow extends JFrame{
	/**컨텐트팬을 알아내기 위한 인스턴스*/
	public Container contentPane = getContentPane();
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
	/**사운드 클래스 인스턴스*/
	public Sound Bgm = new Sound("sounds/BackgroundMusic_main.wav");
	public Sound effect = new Sound("sounds/bt_cursorOn.wav");
		/**메인 윈도우 클래스 초기화
	 * @author 박상우, 홍예진
	 * */
	public MainWindow(){
		setTitle("Slippery Floor");
		setSize(1400, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		// 프로그램의 아이콘을 지정한다.
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("Slippery_floor.png");
		setIconImage(img);

		add(mainPanel);
		setVisible(true);
		Bgm.loop(Clip.LOOP_CONTINUOUSLY); // 음악을 반복 재생한다.
	}
	/**화면 전환을 위한 메소드
	 * <br>
	 * 다음에 불러올 패널을 매개변수로 받는다
	 * @author 박상우
	 * @param c 다음에 불러올 컴포넌트
	 */
	public void changePanel(Component c){	
		contentPane.removeAll();
		contentPane.add(c);
		contentPane.revalidate();
		contentPane.repaint();
	}
}