package WindowManager;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GamePlayManager.KeySet;
/**클래스 설명
 * <br>
 * 환경설정기능을 담당하는 패널
 * 
 * @author 박상우
 * @see javax.swing.JPanel
 */
public class ConfigPanel extends JPanel implements KeyListener, ActionListener{
	/**메인 윈도우의 정보를 이용하기 위한 인스턴스*/
	private MainWindow window;
	/**키 설정에 이용할 키셋 인스턴스*/
	private KeySet key = new KeySet();
	/**"키 설정하기"버튼*/
	private JButton config = new JButton("키 설정 하기");
	/**"설정"버튼*/
	private JButton set = new JButton("설정");
	/**"뒤로가기"버튼*/
	private JButton back = new JButton("뒤로가기");
	/**사용자가 입력한 키 값을 보여줄 텍스트라벨*/;
	private JLabel keyLabel = new JLabel("키가 표시됩니다");
	/**상단 배치를 위한 패널*/
	private JPanel topPanel = new JPanel();
	/**중앙 배치를 위한 패널*/
	private JPanel centerPanel = new JPanel();
	/**키 설정에 사용할 값*/
	private int configUp, configDown, configLeft, configRight;
	/**키 입력으로부터 키 코드를 받아들일 임시 값*/
	private int tmp;
	/**상하좌우 키의 입력 순서를 위한 값*/
	private int count = 0;
	/**환경 설정 패널 초기화
	 * @author 박상우
	 * @param window 인스턴스로 사용할 메인윈도우
	 * */
	public ConfigPanel(MainWindow window){
		this.window = window;
		setLayout(new BorderLayout());
		addKeyListener(this);
		topPanel.add(new JLabel("환경설정 패널"));
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				keyLabel.setText("키가 표시됩니다");
				window.changePanel(window.mainPanel);
			}
		});
		config.addActionListener(this);
		set.addActionListener(this);
		set.setEnabled(false);
		topPanel.add(back);
		topPanel.add(config);
		topPanel.add(set);
		centerPanel.add(keyLabel);
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
	}
	/**키를 입력하면 텍스트라벨에 입력받은 키를 출력하는 메소드
	 * @author 박상우
	 */
	public void keyPressed(KeyEvent e) {
		tmp = e.getKeyCode();
		set.setEnabled(true);
		keyLabel.setText(e.getKeyText(e.getKeyCode()));
	}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
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
			back.setEnabled(false);
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
					back.setEnabled(true);
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
	public KeySet getKeySet() {
		return key;
	}
}
