package GamePlayManager;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import WindowManager.MainWindow;
/**클래스 설명
 * <br>
 * 게임 실행 중의 화면을 나타낼 패널
 * 게임 실행 창, 상태 창, 시간 창을 포함한다
 * 
 * @author 박상우
 * @see javax.swing.JPanel
 */
public class MergedGamePanel extends JPanel{
	/**컨텐트팬의 정보를 이용하기 위한 메인윈도우 인스턴스*/
	private MainWindow window;
	/**게임 실행 창의 정보를 이용하기 위한 게임플레잉 인스턴스*/
	private GamePlaying gamePlaying;
	/**상태 창의 정보를 이용하기 위한 스테이터스 패널 인스턴스*/
	private StatusPanel statusPanel;
	/**시간 창의 정보를 이용하기 위한 타이머 패널 인스턴스*/
	private TimerPanel timerPanel = new TimerPanel();
	/**게임 플레이 시간을 측정하기 위한 쓰레드*/
	private Thread timer = new Thread(timerPanel);
	/**서브 메뉴를 띄우기 위한 메뉴 다이얼로그 필드*/
	protected MenuDialog menu;
	public MergedGamePanel(MainWindow window){
		this.window = window;
		setLayout(null);
		initGamePlaying();
		initStatusPanel();
		initMenuDialog();
		add(timerPanel);
	}
	/**
	 * 게임플레잉 초기화
	 */
	public void initGamePlaying(){
		gamePlaying = new GamePlaying(this);
		add(gamePlaying);
	}
	/**
	 * 스테이터스 패널 초기화
	 */
	public void initStatusPanel(){
		statusPanel = new StatusPanel(gamePlaying);
		add(statusPanel);
	}
	/**
	 * 메뉴 다이얼로그 초기화
	 */
	public void initMenuDialog(){
		menu = new MenuDialog(window.contentPane);
	}
	public GamePlaying getGamePlaying(){
		return gamePlaying;
	}
	public StatusPanel getStatusPanel(){
		return statusPanel;
	}
	public TimerPanel getTimerPanel(){
		return timerPanel;
	}
	public Thread getTimer(){
		return timer;
	}
	public void setTimer(Thread th){
		timer = th;
	}
	/**클래스 설명
	 * <br>
	 * 서브 메뉴에서 주 메뉴 버튼을 선택하면 나타나는 다이얼로그
	 * 주 메뉴로 돌아길지를 선택한다
	 * 
	 * @author 박상우
	 * @see javax.swing.JDialog
	 */
	protected class ToMenuDialog extends JDialog implements ActionListener{
		JPanel labelPanel = new JPanel();
		JPanel btnPanel = new JPanel();
		JLabel label = new JLabel("주 메뉴로 돌아가시겠습니까?");
		JButton yes = new JButton("예");
		JButton no = new JButton("아니오");
		ToMenuDialog(){
			setTitle("주 메뉴");
			setSize(300,200);
			setLayout(new BorderLayout());
			labelPanel.add(label);
			yes.addActionListener(this);
			no.addActionListener(this);
			btnPanel.add(yes);
			btnPanel.add(no);
			add(labelPanel, BorderLayout.NORTH);
			add(btnPanel, BorderLayout.CENTER);
		}
		public void actionPerformed(ActionEvent e){
			if((JButton)e.getSource() == yes){ 
				timer.interrupt();
				timerPanel.resetCount();
				timer = new Thread(timerPanel);
				window.changePanel(window.mainPanel);
				dispose();
				menu.dispose();
			}
			else dispose();
		}
	}
	/**클래스 설명
	 * <br>
	 * 게임 중에 ESCAPE 키를 입력받으면 나타나는 다이얼로그
	 * 주 메뉴로 돌아갈지를 선택하는 버튼과 환경 설정 버튼을 가지고 있다 
	 * @author 박상우
	 * @see javax.swing.JDialog
	 */
	protected class MenuDialog extends JDialog{
		JButton toMain = new JButton("주 메뉴");
		MenuDialog(Container c){
			setTitle("메뉴");
			setSize(300, 400);
			setLocationRelativeTo(c);
			setLayout(new FlowLayout());
			toMain.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					ToMenuDialog ask = new ToMenuDialog();
					ask.setLocationRelativeTo(getContentPane());
					ask.setVisible(true);
				}
			});
			add(toMain);
		}
	}
}
