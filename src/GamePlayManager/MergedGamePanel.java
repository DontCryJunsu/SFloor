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
/**Ŭ���� ����
 * <br>
 * ���� ����â, ����â, �ð� ���â�� ��� �����ϴ� �г�
 * @author �ڻ��
 * @see javax.swing.JPanel
 */
public class MergedGamePanel extends JPanel{
	/**����Ʈ���� ������ �̿��ϱ� ���� ���������� �ν��Ͻ�*/
	MainWindow window;
	/**���� ���� â�� ������ �̿��ϱ� ���� �����÷��� �ν��Ͻ�*/
	private GamePlaying gamePlaying;
	/**���� â�� ������ �̿��ϱ� ���� �������ͽ� �г� �ν��Ͻ�*/
	private StatusPanel statusPanel;
	/**�ð� â�� ������ �̿��ϱ� ���� Ÿ�̸� �г� �ν��Ͻ�*/
	private TimerPanel timerPanel = new TimerPanel();
	/**���� �÷��� �ð��� �����ϱ� ���� ������*/
	private Thread timer = new Thread(timerPanel);
	/**���� �÷��� �� Ű ������ ��ȯ�� �� ����� Ű�� �ν��Ͻ�*/
	private KeySet key;
	/**���� �޴��� ���� ���� �޴� ���̾�α� �ʵ�*/
	protected MenuDialog menu;
	/**�� �гε��� �ʱ�ȭ�ϰ� ���� �гο� �߰�
	 * @author �ڻ��
	 * @param window �ν��Ͻ��� ����� ����������
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
	/**���� �÷��� �г� �ʱ�ȭ
	 * @author �ڻ��
	 * */
	public void initGamePlaying(){
		gamePlaying = new GamePlaying(this);
		add(gamePlaying);
	}
	/**�������ͽ� �г� �ʱ�ȭ
	 * @author �ڻ��
	 * */
	public void initStatusPanel(){
		statusPanel = new StatusPanel();
		add(statusPanel);
	}
	/**�޴� ���̾�α� �ʱ�ȭ
	 * @author �ڻ��
	 * */
	public void initMenuDialog(){
		menu = new MenuDialog(window.contentPane);
	}
	/**Ű�� �ʱ�ȭ
	 * @author �ڻ��
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
	/**Ŭ���� ����
	 * <br>
	 * ���� �޴����� �� �޴� ��ư�� �����ϸ� ��Ÿ���� ���̾�α�
	 * �� �޴��� ���Ʊ����� ����
	 * 
	 * @author �ڻ��
	 * @see javax.swing.JDialog
	 */
	protected class ToMenuDialog extends JDialog implements ActionListener{
		/**�ؽ�Ʈ ���� ����� �г�*/
		private JPanel labelPanel = new JPanel();
		/**��ư���� ������ �г�*/
		private JPanel btnPanel = new JPanel();
		/**�ؽ�Ʈ ��*/
		private JLabel label = new JLabel("�� �޴��� ���ư��ðڽ��ϱ�?");
		/**"��"��ư*/
		private JButton yes = new JButton("��");
		/**"�ƴϿ�"��ư*/
		private JButton no = new JButton("�ƴϿ�");
		/**���̾�α��� �̸��� ũ�� ���� �� �ʱ�ȭ
		 * @author �ڻ��
		 * */
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
		/**��ư�� ������ �� ����� ��ɵ�
		 * "��"��ư ���ý� �������̴� �����带 ���߰� �ٽ� �����带 ������ �غ�
		 * �� �� �� �޴��� ȭ����ȯ
		 * "�ƴϿ�"��ư ���ý� ���̾�α� ����
		 * @author �ڻ��
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
	/**Ŭ���� ����
	 * <br>
	 * ���� �߿� ESCAPE Ű�� �Է¹����� ��Ÿ���� ���̾�α�
	 * �� �޴��� ���ư����� �����ϴ� ��ư�� ȯ�� ���� ��ư�� ����
	 * ���� �÷��� Ŭ�������� �����ϱ����� protected ���� ����
	 * @author �ڻ��
	 * @see javax.swing.JDialog
	 */
	protected class MenuDialog extends JDialog{
		/**"�� �޴�"��ư*/
		private JButton toMain = new JButton("�� �޴�");
		/**"ȯ�� ����"��ư*/
		private JButton toConfig = new JButton("ȯ�� ����");
		/**�޴� ���̾�α� �ʱ�ȭ
		 * @author �ڻ��
		 * @param c ��� ��ġ�� ������ �����̳�
		 * */
		MenuDialog(Container c){
			setTitle("�޴�");
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
	/**Ŭ���� ����
	 * <br>
	 * ���� �޴����� ȯ�� ���� ��ư�� �����ϸ� ��Ÿ���� ���̾�α�
	 * Ű ������ ���� ���� ���
	 * @author �ڻ��
	 * @see javax.swing.JDialog
	 */
	private class ConfigDialog extends JDialog implements ActionListener{
		/**"Ű �����ϱ�"��ư*/
		private JButton config = new JButton("Ű �����ϱ�");
		/**"����"��ư*/
		private JButton set = new JButton("����");
		/**�ؽ�Ʈ�� ǥ�õ� ��*/
		private JLabel keyLabel = new JLabel("Ű�� ǥ�õ˴ϴ�");
		/**��ܿ� ǥ�õ� �г�*/
		private JPanel topPanel = new JPanel();
		/**�߾ӿ� ǥ�õ� �г�*/
		private JPanel centerPanel = new JPanel();
		/**Ű ������ ����� ��*/
		private int configUp, configDown, configLeft, configRight;
		/**Ű �Է����κ��� Ű �ڵ带 �޾Ƶ��� �ӽ� ��*/
		private int tmp;
		/**�����¿� Ű�� �Է� ������ ���� ��*/
		private int count = 0;
		/**ȯ�� ���� ���̾�α� �ʱ�ȭ
		 * @author �ڻ��
		 * */
		ConfigDialog(){
			setTitle("ȯ�� ���� �޴�");
			setSize(600, 600);
			setLayout(new BorderLayout());
			addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					tmp = e.getKeyCode();
					set.setEnabled(true);
					keyLabel.setText(e.getKeyText(e.getKeyCode()));
				}
			});
			topPanel.add(new JLabel("ȯ�� ���� �޴�"));
			config.addActionListener(this);
			set.addActionListener(this);
			set.setEnabled(false);
			topPanel.add(config);
			topPanel.add(set);
			centerPanel.add(keyLabel);
			add(topPanel, BorderLayout.NORTH);
			add(centerPanel, BorderLayout.CENTER);
		}
		/**���� ��ư���� ����� �׼Ǹ������� �޼ҵ�
		 * Ű �����ϱ� ��ư�� ������ �����¿� ������ Ű������ ����
		 * �ߺ� Ű �Է����ϸ� �ش� Ű ���� �ٽ� �Է¹���
		 * ������ ����� Ű�� �Է��ϰ� ���� ��ư�� ������ ����
		 * ������ ��ư���� �����ϸ� ������ �Ϸ�ǰ� �ܺη� ����
		 * @author �ڻ�� 
		 */
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == config){
				keyLabel.setText("�� ����Ű�� ����� Ű�� �Է��ϼ���");
				setFocusable(true);
				//set.setEnabled(true);
				config.setEnabled(false);
				requestFocus();
			}
			if(e.getSource() == set){
				if(count == 0) {
					configUp = tmp;
					count++;
					keyLabel.setText("�Ʒ� ����Ű�� ����� Ű�� �Է��ϼ���");
					set.setEnabled(false);
				}
				else if(count == 1) {
					configDown = tmp;
					count++;
					keyLabel.setText("���� ����Ű�� ����� Ű�� �Է��ϼ���");
					set.setEnabled(false);
					if(configUp == configDown) {
						keyLabel.setText("�ߺ� Ű �Է��� ������ �ʽ��ϴ�, �ٽ� �Է��ϼ���");
						set.setEnabled(false);
						count = 1;
					}
				}
				else if(count == 2) {
					configLeft = tmp;
					count++;
					keyLabel.setText("������ ����Ű�� ����� Ű�� �Է��ϼ���");
					set.setEnabled(false);
					if(configUp == configLeft || configDown == configLeft) {
						keyLabel.setText("�ߺ� Ű �Է��� ������ �ʽ��ϴ�, �ٽ� �Է��ϼ���");
						set.setEnabled(false);
						count = 2;
					}
				}
				else if(count == 3){
					configRight = tmp;
					count++;
					if(configUp == configRight || configDown == configRight || configLeft == configRight) {
						keyLabel.setText("�ߺ� Ű �Է��� ������ �ʽ��ϴ�, �ٽ� �Է��ϼ���");
						set.setEnabled(false);
						count = 3;
					}
					if(count == 4) {
						changeAndSave();
						updateKey(key);
						keyLabel.setText("Ű ������ �Ϸ�Ǿ����ϴ�.");
						set.setEnabled(false);
						config.setEnabled(true);
						setFocusable(false);
						count = 0;
					}
				}
				requestFocus();
			}
		}
		/**�Էµ� Ű �ڵ� ������ �ٽ� ȯ�漳�� ������ �����ϴ� �޼ҵ�
		 * @author �ڻ��
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
		/**����� Ű ���� �����κ��� Ű �ڵ带 �ٽ� �ҷ����� ���� �޼ҵ�
		 * @author �ڻ��
		 * @param key ������Ʈ�� Ű��
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