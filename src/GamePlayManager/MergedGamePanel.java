package GamePlayManager;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import WindowManager.MainWindow;
/**클래스 설명
 * <br>
 * 게임 실행창, 상태창, 시간 출력창을 모두 포함하는 패널
 * @author 박상우
 * @see javax.swing.JPanel
 */
public class MergedGamePanel extends JPanel{
	/**컨텐트팬의 정보를 이용하기 위한 메인윈도우 인스턴스*/
	MainWindow window;
	/**게임 실행 창의 정보를 이용하기 위한 게임플레잉 인스턴스*/
	private GamePlaying gamePlaying;
	/**상태 창의 정보를 이용하기 위한 스테이터스 패널 인스턴스*/
	private StatusPanel statusPanel;
	/**시간 창의 정보를 이용하기 위한 타이머 패널 인스턴스*/
	private TimerPanel timerPanel = new TimerPanel();
	/**게임 플레이 시간을 측정하기 위한 쓰레드*/
	private Thread timer = new Thread(timerPanel);
	/**게임 플레이 중 키 설정을 변환할 때 사용할 키셋 인스턴스*/
	private KeySet key;
	/**서브 메뉴를 띄우기 위한 메뉴 다이얼로그 필드*/
	protected MenuDialog menu;
	/**각 패널들을 초기화하고 통합 패널에 추가
	 * @author 박상우
	 * @param window 인스턴스로 사용할 메인윈도우
	 * */
	public MergedGamePanel(MainWindow window){
		this.window = window;
		setLayout(null);
		initGamePlaying();
		initStatusPanel();
		initMenuDialog();
		initKeySet();
		add(timerPanel);
	}
	/**게임 플레잉 패널 초기화
	 * @author 박상우
	 * */
	public void initGamePlaying(){
		gamePlaying = new GamePlaying(this);
		add(gamePlaying);
	}
	/**스테이터스 패널 초기화
	 * @author 박상우
	 * */
	public void initStatusPanel(){
		statusPanel = new StatusPanel();
		add(statusPanel);
	}
	/**메뉴 다이얼로그 초기화
	 * @author 박상우
	 * */
	public void initMenuDialog(){
		menu = new MenuDialog(window.contentPane);
	}
	/**키셋 초기화
	 * @author 박상우
	 * */
	public void initKeySet() {
		key = window.configPanel.getKeySet();
	}
	public MainWindow getMainWindow() {
		return window;
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
	public KeySet getKeySet() {
		return key;
	}
	public void setTimer(Thread th){
		timer = th;
	}
	/**클래스 설명
	 * <br>
	 * 서브 메뉴에서 주 메뉴 버튼을 선택하면 나타나는 다이얼로그
	 * 주 메뉴로 돌아길지를 선택
	 * 
	 * @author 박상우
	 * @see javax.swing.JDialog
	 */
	protected class ToMenuDialog extends JDialog implements ActionListener{
		/**텍스트 라벨을 출력할 패널*/
		private JPanel labelPanel = new JPanel();
		/**버튼들을 포함할 패널*/
		private JPanel btnPanel = new JPanel();
		/**텍스트 라벨*/
		private JLabel label = new JLabel("주 메뉴로 돌아가시겠습니까?");
		/**"예"버튼*/
		private JButton yes = new JButton("예");
		/**"아니오"버튼*/
		private JButton no = new JButton("아니오");
		/**다이얼로그의 이름과 크기 설정 및 초기화
		 * @author 박상우
		 * */
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
		/**버튼을 눌렀을 때 실행될 기능들
		 * "예"버튼 선택시 진행중이던 쓰레드를 멈추고 다시 쓰레드를 실행할 준비
		 * 그 후 주 메뉴로 화면전환
		 * "아니오"버튼 선택시 다이얼로그 종료
		 * @author 박상우
		 */
		public void actionPerformed(ActionEvent e){
			if((JButton)e.getSource() == yes){ 
				timer.interrupt();
				gamePlaying.getGamePlayingThread().interrupt();
				timerPanel.resetCount();
				statusPanel.initText();
				timer = new Thread(timerPanel);
				gamePlaying.setGamePlayingThread(new Thread(gamePlaying));
				gamePlaying.resetPauseCounter();
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
	 * 주 메뉴로 돌아갈지를 선택하는 버튼과 환경 설정 버튼을 가짐
	 * 게임 플레잉 클래스에서 접근하기위해 protected 형식 지정
	 * @author 박상우
	 * @see javax.swing.JDialog
	 */
	protected class MenuDialog extends JDialog{
		/**"주 메뉴"버튼*/
		private JButton toMain = new JButton("주 메뉴");
		/**"환경 설정"버튼*/
		private JButton toConfig = new JButton("환경 설정");
		/**메뉴 다이얼로그 초기화
		 * @author 박상우
		 * @param c 상대 위치를 설정할 컨테이너
		 * */
		MenuDialog(Container c){
			setTitle("메뉴");
			setSize(300, 400);
			setLocationRelativeTo(c);
			setLayout(new FlowLayout());
			toMain.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					ToMenuDialog askDialog = new ToMenuDialog();
					askDialog.setLocationRelativeTo(getContentPane());
					askDialog.setVisible(true);
				}
			});
			toConfig.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ConfigDialog configDialog = new ConfigDialog();
					configDialog.setLocationRelativeTo(getContentPane());
					configDialog.setVisible(true);
				}
			});
			add(toMain);
			add(toConfig);
		}
	}
	/**클래스 설명
	 * <br>
	 * 서브 메뉴에서 환경 설정 버튼을 선택하면 나타나는 다이얼로그
	 * 키 설정과 음향 설정 기능
	 * @author 박상우
	 * @see javax.swing.JDialog
	 */
	private class ConfigDialog extends JDialog implements ActionListener{
		/**"키 설정하기"버튼*/
		private JButton config = new JButton("키 설정하기");
		/**"설정"버튼*/
		private JButton set = new JButton("설정");
		/**텍스트가 표시될 라벨*/
		private JLabel keyLabel = new JLabel("키가 표시됩니다");
		/**상단에 표시될 패널*/
		private JPanel topPanel = new JPanel();
		/**중앙에 표시될 패널*/
		private JPanel centerPanel = new JPanel();
		/**키 설정에 사용할 값*/
		private int configUp, configDown, configLeft, configRight;
		/**키 입력으로부터 키 코드를 받아들일 임시 값*/
		private int tmp;
		/**상하좌우 키의 입력 순서를 위한 값*/
		private int count = 0;
		/**환경 설정 다이얼로그 초기화
		 * @author 박상우
		 * */
		ConfigDialog(){
			setTitle("환경 설정 메뉴");
			setSize(600, 600);
			setLayout(new BorderLayout());
			addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					tmp = e.getKeyCode();
					set.setEnabled(true);
					keyLabel.setText(e.getKeyText(e.getKeyCode()));
				}
			});
			topPanel.add(new JLabel("환경 설정 메뉴"));
			config.addActionListener(this);
			set.addActionListener(this);
			set.setEnabled(false);
			topPanel.add(config);
			topPanel.add(set);
			centerPanel.add(keyLabel);
			add(topPanel, BorderLayout.NORTH);
			add(centerPanel, BorderLayout.CENTER);
		}
		/**각각 버튼에서 사용할 액션리스너의 메소드
		 * 키 설정하기 버튼을 누르면 상하좌우 순서로 키설정이 시작
		 * 중복 키 입력을하면 해당 키 부터 다시 입력받음
		 * 각각의 사용할 키를 입력하고 설정 버튼을 누르면 설정
		 * 마지막 버튼까지 설정하면 설정이 완료되고 외부로 저장
		 * @author 박상우 
		 */
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == config){
				keyLabel.setText("윗 방향키로 사용할 키를 입력하세요");
				setFocusable(true);
				//set.setEnabled(true);
				config.setEnabled(false);
				requestFocus();
			}
			if(e.getSource() == set){
				if(count == 0) {
					configUp = tmp;
					count++;
					keyLabel.setText("아래 방향키로 사용할 키를 입력하세요");
					set.setEnabled(false);
				}
				else if(count == 1) {
					configDown = tmp;
					count++;
					keyLabel.setText("왼쪽 방향키로 사용할 키를 입력하세요");
					set.setEnabled(false);
					if(configUp == configDown) {
						keyLabel.setText("중복 키 입력은 허용되지 않습니다, 다시 입력하세요");
						set.setEnabled(false);
						count = 1;
					}
				}
				else if(count == 2) {
					configLeft = tmp;
					count++;
					keyLabel.setText("오른쪽 방향키로 사용할 키를 입력하세요");
					set.setEnabled(false);
					if(configUp == configLeft || configDown == configLeft) {
						keyLabel.setText("중복 키 입력은 허용되지 않습니다, 다시 입력하세요");
						set.setEnabled(false);
						count = 2;
					}
				}
				else if(count == 3){
					configRight = tmp;
					count++;
					if(configUp == configRight || configDown == configRight || configLeft == configRight) {
						keyLabel.setText("중복 키 입력은 허용되지 않습니다, 다시 입력하세요");
						set.setEnabled(false);
						count = 3;
					}
					if(count == 4) {
						changeAndSave();
						updateKey(key);
						keyLabel.setText("키 설정이 완료되었습니다.");
						set.setEnabled(false);
						config.setEnabled(true);
						setFocusable(false);
						count = 0;
					}
				}
				requestFocus();
			}
		}
		/**입력된 키 코드 값들을 다시 환경설정 값으로 저장하는 메소드
		 * @author 박상우
		 * */
		public void changeAndSave(){
			try{
				BufferedWriter writer = new BufferedWriter(new FileWriter("configs/configs.txt"));
				writer.write(Integer.toString(configUp));
				writer.newLine();
				writer.write(Integer.toString(configDown));
				writer.newLine();
				writer.write(Integer.toString(configLeft));
				writer.newLine();
				writer.write(Integer.toString(configRight));
				writer.close();
			}catch(Exception e){return;}
		}
		/**저장된 키 세팅 값으로부터 키 코드를 다시 불러오기 위한 메소드
		 * @author 박상우
		 * @param key 업데이트할 키셋
		 */
		public void updateKey(KeySet key){
			try{
				BufferedReader reader = new BufferedReader(new FileReader("configs/configs.txt"));
				key.setUp(Integer.parseInt(reader.readLine()));
				key.setDown(Integer.parseInt(reader.readLine()));
				key.setLeft(Integer.parseInt(reader.readLine()));
				key.setRight(Integer.parseInt(reader.readLine()));
				reader.close();
			}catch(Exception e){return;}
		}
	}
}