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
/**Ŭ���� ����
 * <br>
 * ���� ���� ���� ȭ���� ��Ÿ�� �г�
 * ���� ���� â, ���� â, �ð� â�� �����Ѵ�
 * 
 * @author �ڻ��
 * @see javax.swing.JPanel
 */
public class MergedGamePanel extends JPanel{
	/**����Ʈ���� ������ �̿��ϱ� ���� ���������� �ν��Ͻ�*/
	private MainWindow window;
	/**���� ���� â�� ������ �̿��ϱ� ���� �����÷��� �ν��Ͻ�*/
	private GamePlaying gamePlaying;
	/**���� â�� ������ �̿��ϱ� ���� �������ͽ� �г� �ν��Ͻ�*/
	private StatusPanel statusPanel;
	/**�ð� â�� ������ �̿��ϱ� ���� Ÿ�̸� �г� �ν��Ͻ�*/
	private TimerPanel timerPanel = new TimerPanel();
	/**���� �÷��� �ð��� �����ϱ� ���� ������*/
	private Thread timer = new Thread(timerPanel);
	/**���� �޴��� ���� ���� �޴� ���̾�α� �ʵ�*/
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
	 * �����÷��� �ʱ�ȭ
	 */
	public void initGamePlaying(){
		gamePlaying = new GamePlaying(this);
		add(gamePlaying);
	}
	/**
	 * �������ͽ� �г� �ʱ�ȭ
	 */
	public void initStatusPanel(){
		statusPanel = new StatusPanel(gamePlaying);
		add(statusPanel);
	}
	/**
	 * �޴� ���̾�α� �ʱ�ȭ
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
	/**Ŭ���� ����
	 * <br>
	 * ���� �޴����� �� �޴� ��ư�� �����ϸ� ��Ÿ���� ���̾�α�
	 * �� �޴��� ���Ʊ����� �����Ѵ�
	 * 
	 * @author �ڻ��
	 * @see javax.swing.JDialog
	 */
	protected class ToMenuDialog extends JDialog implements ActionListener{
		JPanel labelPanel = new JPanel();
		JPanel btnPanel = new JPanel();
		JLabel label = new JLabel("�� �޴��� ���ư��ðڽ��ϱ�?");
		JButton yes = new JButton("��");
		JButton no = new JButton("�ƴϿ�");
		ToMenuDialog(){
			setTitle("�� �޴�");
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
	/**Ŭ���� ����
	 * <br>
	 * ���� �߿� ESCAPE Ű�� �Է¹����� ��Ÿ���� ���̾�α�
	 * �� �޴��� ���ư����� �����ϴ� ��ư�� ȯ�� ���� ��ư�� ������ �ִ� 
	 * @author �ڻ��
	 * @see javax.swing.JDialog
	 */
	protected class MenuDialog extends JDialog{
		JButton toMain = new JButton("�� �޴�");
		MenuDialog(Container c){
			setTitle("�޴�");
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
