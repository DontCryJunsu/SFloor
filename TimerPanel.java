package GamePlayManager;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
/**클래스 설명
 * <br>
 * 게임실행 시간을 출력할 패널
 * <br>
 * 게임이 종료되면 이 클래스의 시간 값을 이용하여 랭크와 연동
 * @author 박상우
 * @see javax.swing.JPanel
 */
public class TimerPanel extends JPanel implements Runnable{
	/**시간을 출력할 텍스트라벨*/
	private JLabel timerLabel = new JLabel();
	/**증가될 시간 값*/
	private int count = 0;
	/**타이머 패널 초기화
	 * @author 박상우
	 * */
	public TimerPanel(){
		setSize(200, 50);
		setLocation(1200, 0);
		setBackground(Color.DARK_GRAY);
		timerLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC | Font.BOLD, 20));
		timerLabel.setForeground(Color.WHITE);
		add(timerLabel);
	}
	/**Runnable run() 메소드 구현
	 * <br>
	 * 쓰레드 인터럽트 발생시 리턴
	 * @author 박상우
	 * */
	public void run() {
		while(!Thread.currentThread().isInterrupted()){
			timerLabel.setText("시간 : " + Integer.toString(count));
			count++;
			try{
				Thread.sleep(10);
			}catch(InterruptedException e){return;}
		}
	}
	/**게임 중 메뉴로 돌아가거나 게임이 끝났을 때 카운트 값을 리셋하는 메소드
	 * @author 박상우
	 * */
	public void resetCount(){
		count = 0;
	}
	public int getCount(){
		return count;
	}
}
