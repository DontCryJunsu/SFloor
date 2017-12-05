package GamePlayManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SFDesignManager.Design;
import SFDesignManager.Sound;
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
	/**���� ȭ�� �ʱ�ȭ �޼ҵ�
	 * @author ���ؼ�
	 */
	public void resetGame() {
		gamePlaying.A.init();
		gamePlaying.A.map();
		gamePlaying.stage = 1;
		gamePlaying.x = 6*50;
		gamePlaying.y = 10*50;  
		gamePlaying.A.key[3][2] = 1;
		gamePlaying.KeyS = 0;
		gamePlaying.keyReset();
		gamePlaying.player_Status = 1;
	}
	/**Ŭ���� ����
	 * <br>
	 * ���� �޴����� �� �޴� ��ư�� �����ϸ� ��Ÿ���� ���̾�α�
	 * <br>
	 * �� �޴��� ���Ʊ����� ����
	 * 
	 * @author �ڻ��
	 * @see javax.swing.JDialog
	 */
	protected class ToMenuDialog extends JDialog implements ActionListener{
		/**��ư�� ������ ������ ���� ������ �ν��Ͻ� ����*/
		private Design design = new Design();
		/**��ư�� ���� ������ ���� ���� �ν��Ͻ� ����*/
		private Sound effect = new Sound("sounds/bt_cursorOn.wav");
		/**�ؽ�Ʈ ���� ����� �г�*/
		private JPanel labelPanel = new JPanel();
		/**��ư���� ������ �г�*/
		private JPanel btnPanel = new JPanel();
		/**�ؽ�Ʈ ��*/
		private JLabel label = new JLabel("�� �޴��� ���ư��ðڽ��ϱ�?");
		Font labelFont = new Font(Font.SANS_SERIF, Font.BOLD, 20);
		/**"��"��ư*/
		private JButton yes = new JButton();
		/**"�ƴϿ�"��ư*/
		private JButton no = new JButton();
		/**���̾�α��� �̸��� ũ�� ���� �� �ʱ�ȭ
		 * @author �ڻ��
		 * @param c ��� ��ġ�� ������ �����̳�
		 * */
		ToMenuDialog(Container c){
			setTitle("�� �޴�");
			setSize(300,200);
			setLayout(new BorderLayout());
			setLocationRelativeTo(c);
			setAlwaysOnTop(true);
			label.setFont(labelFont);
			label.setForeground(Color.WHITE);
			labelPanel.add(label);
			labelPanel.setBackground(Color.BLACK);
			yes = design.setbutton(yes,4);
			no = design.setbutton(no,5);
			effect.setbutton(yes);
			effect.setbutton(no);
			yes.addActionListener(this);
			no.addActionListener(this);
			btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 0));
			btnPanel.setBackground(Color.BLACK);
			
			btnPanel.add(yes);
			btnPanel.add(no);
			add(labelPanel, BorderLayout.NORTH);
			add(btnPanel, BorderLayout.CENTER);
		}
		
		/**��ư�� ������ �� ����� ��ɵ�
		 * <br>
		 * "��"��ư ���ý� �������̴� �����带 ���߰� �ٽ� �����带 ������ �غ�
		 * <br>
		 * �� �� �� �޴��� ȭ����ȯ
		 * <br>
		 * "�ƴϿ�"��ư ���ý� ���̾�α� ����
		 * <br>
		 * ù �޴��� ���ƿ����� �������� �ʱ�ȭ
		 * @author �ڻ��, ���ؼ�
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
				resetGame();	
				window.changePanel(window.mainPanel);
				window.Bgm.stop();
				window.Bgm.loop("sounds/BackgroundMusic_main.wav", Clip.LOOP_CONTINUOUSLY);
				dispose();
				menu.dispose();
			}
			else dispose();
		}
	}
	/**Ŭ���� ����
	 * <br>
	 * ���� �߿� ESCAPE Ű�� �Է¹����� ��Ÿ���� ���̾�α�
	 * <br>
	 * �� �޴��� ���ư����� �����ϴ� ��ư�� ȯ�� ���� ��ư�� ����
	 * <br>
	 * ���� �÷��� Ŭ�������� �����ϱ����� protected ���� ����
	 * @author �ڻ��
	 * @see javax.swing.JDialog
	 */
	protected class MenuDialog extends JDialog{
		/**��ư�� ������ ������ ���� ������ �ν��Ͻ� ����*/
		private Design design = new Design();
		/**��ư�� ���� ������ ���� ���� �ν��Ͻ� ����*/
		private Sound effect = new Sound("sounds/bt_cursorOn.wav");
		/**"�� �޴�"��ư*/
		private JButton toMain;
		/**"ȯ�� ����"��ư*/
		private JButton toConfig;
		/**���̾�α׿� �߰��� �г�*/
		private JPanel topPanel = new JPanel();
		private JPanel bottomPanel = new JPanel();
		/**�޴� ���̾�α� �ʱ�ȭ
		 * @author �ڻ��
		 * @param c ��� ��ġ�� ������ �����̳�
		 * */
		MenuDialog(Container c){
			setTitle("�޴�");
			setSize(350, 130);
			setLocationRelativeTo(c);
			setLayout(new BorderLayout());
			setAlwaysOnTop(true);
			setBackground(Color.BLACK);
			toMain = design.setbutton(toMain, 11);
			toConfig = design.setbutton(toConfig, 2);
			effect.setbutton(toMain);
			effect.setbutton(toConfig);
			toMain.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					ToMenuDialog askDialog = new ToMenuDialog(c);
					askDialog.setLocationRelativeTo(getContentPane());
					askDialog.setVisible(true);
				}
			});
			toConfig.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ConfigDialog configDialog = new ConfigDialog(c);
					configDialog.setLocationRelativeTo(getContentPane());
					configDialog.setVisible(true);
				}
			});	

			topPanel.setBackground(Color.BLACK);
			bottomPanel.setBackground(Color.BLACK);
			topPanel.add(toMain);
			bottomPanel.add(toConfig);
			add(topPanel, BorderLayout.NORTH);
			add(bottomPanel, BorderLayout.CENTER);
		}
	
	}
	/**Ŭ���� ����
	 * <br>
	 * ���� �޴����� ȯ�� ���� ��ư�� �����ϸ� ��Ÿ���� ���̾�α�
	 * <br>
	 * Ű ������ ���� ���� ���
	 * @author �ڻ��
	 * @see javax.swing.JDialog
	 */
	private class ConfigDialog extends JDialog implements ActionListener{
		/**��ư�� ������ ������ ���� ������ �ν��Ͻ� ����*/
		private Design design = new Design();
		/**��ư�� ���� ������ ���� ���� �ν��Ͻ� ����*/
		private Sound effect = new Sound("sounds/bt_cursorOn.wav");
		/**"Ű �����ϱ�"��ư*/
		private JButton config;
		/**"����"��ư*/
		private JButton set;
		/**�ؽ�Ʈ�� ǥ�õ� ��*/
		private JLabel keyLabel = new JLabel("Ű�� ǥ�õ˴ϴ�");
		private Font font = new Font(Font.SANS_SERIF, Font.BOLD, 30);
		/**��ܿ� ǥ�õ� �г�*/
		private JPanel topPanel = new JPanel();
		/**�߾ӿ� ǥ�õ� �г�*/
		private JPanel centerPanel = new JPanel();
		/**ȯ�� ���� �гΰ� ������� ���Ұ� ���θ� �����ϴ� üũ�ڽ�*/
		private JCheckBox bgmMute = new JCheckBox(new ImageIcon("images/Buttons/BGM_img1.png"), window.configPanel.getBgmMute().isSelected());
		/**Ű ������ ����� ��*/
		private int configUp, configDown, configLeft, configRight;
		/**Ű �Է����κ��� Ű �ڵ带 �޾Ƶ��� �ӽ� ��*/
		private int tmp;
		/**�����¿� Ű�� �Է� ������ ���� ��*/
		private int count = 0;
		/**ȯ�� ���� ���̾�α� �ʱ�ȭ
		 * @author �ڻ��
		 * @param c ��� ��ġ�� ������ �����̳�
		 * */
		ConfigDialog(Container c){
			setTitle("ȯ�� ���� �޴�");
			setSize(700, 300);
			setLayout(new BorderLayout());
			setLocationRelativeTo(c);
			setAlwaysOnTop(true);
			setBackground(Color.BLACK);
			keyLabel.setFont(font);
			keyLabel.setForeground(Color.WHITE);
			
			config = design.setbutton(config, 12);
			set = design.setbutton(set, 13);
			effect.setbutton(config);
			effect.setbutton(set);
			
			addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					tmp = e.getKeyCode();
					set.setEnabled(true);
					keyLabel.setText(e.getKeyText(e.getKeyCode()));
				}
			});
			bgmMute.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED) {
						window.Bgm.control.setValue(-80);
						window.Bgm.vol = window.Bgm.control.getValue();
						window.configPanel.getBgmMute().setSelected(true);
					}
					else {
						window.Bgm.control.setValue(0);
						window.Bgm.vol = window.Bgm.control.getValue();
						window.configPanel.getBgmMute().setSelected(false);
					}
				}
			});
			bgmMute.setBackground(Color.BLACK);
			bgmMute.setSelectedIcon(new ImageIcon("images/Buttons/BGM_img2.png"));
			config.addActionListener(this);
			set.addActionListener(this);
			set.setEnabled(false);
			topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 0));
			topPanel.setBackground(Color.BLACK);
			
			topPanel.add(config);
			topPanel.add(set);
			topPanel.add(bgmMute);
			centerPanel.setBackground(Color.BLACK);
			centerPanel.add(keyLabel);
			add(topPanel, BorderLayout.NORTH);
			add(centerPanel, BorderLayout.CENTER);
		}
		/**���� ��ư���� ����� �׼Ǹ������� �޼ҵ�
		 * <br>
		 * Ű �����ϱ� ��ư�� ������ �����¿� ������ Ű������ ����
		 * <br>
		 * �ߺ� Ű �Է����ϸ� �ش� Ű ���� �ٽ� �Է¹���
		 * <br>
		 * ������ ����� Ű�� �Է��ϰ� ���� ��ư�� ������ ����
		 * <br>
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