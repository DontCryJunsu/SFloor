package GamePlayManager;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
/**Ŭ���� ����
 * <br>
 * ���ӽ��� �ð��� ����� �г�
 * <br>
 * ������ ����Ǹ� �� Ŭ������ �ð� ���� �̿��Ͽ� ��ũ�� ����
 * @author �ڻ��
 * @see javax.swing.JPanel
 */
public class TimerPanel extends JPanel implements Runnable{
	/**�ð��� ����� �ؽ�Ʈ��*/
	private JLabel timerLabel = new JLabel();
	/**������ �ð� ��*/
	private int count = 0;
	/**Ÿ�̸� �г� �ʱ�ȭ
	 * @author �ڻ��
	 * */
	public TimerPanel(){
		setSize(200, 50);
		setLocation(1200, 0);
		setBackground(Color.DARK_GRAY);
		timerLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC | Font.BOLD, 20));
		timerLabel.setForeground(Color.WHITE);
		add(timerLabel);
	}
	/**Runnable run() �޼ҵ� ����
	 * <br>
	 * ������ ���ͷ�Ʈ �߻��� ����
	 * @author �ڻ��
	 * */
	public void run() {
		while(!Thread.currentThread().isInterrupted()){
			timerLabel.setText("�ð� : " + Integer.toString(count));
			count++;
			try{
				Thread.sleep(10);
			}catch(InterruptedException e){return;}
		}
	}
	/**���� �� �޴��� ���ư��ų� ������ ������ �� ī��Ʈ ���� �����ϴ� �޼ҵ�
	 * @author �ڻ��
	 * */
	public void resetCount(){
		count = 0;
	}
	public int getCount(){
		return count;
	}
}
