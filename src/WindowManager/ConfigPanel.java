package WindowManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GamePlayManager.KeySet;
import SFDesignManager.Design;
import SFDesignManager.Sound;
/**Ŭ���� ����
 * <br>
 * ȯ�漳������� ����ϴ� �г�
 * @author �ڻ��
 * @see javax.swing.JPanel
 */
public class ConfigPanel extends JPanel implements KeyListener, ActionListener{
	/**���� �������� ������ �̿��ϱ� ���� �ν��Ͻ�*/
	private MainWindow window;
	/**Ű ������ �̿��� Ű�� �ν��Ͻ�*/
	private KeySet key = new KeySet();
	/**��ư�� ������ ������ ���� ������ �ν��Ͻ� ����*/
	private Design design = new Design();
	/**��ư�� ���� ������ ���� ���� �ν��Ͻ� ����*/
	private Sound effect = new Sound("sounds/bt_cursorOn.wav");
	/**"Ű �����ϱ�"��ư*/
	private JButton config;
	/**"����"��ư*/
	private JButton set;
	/**"�ڷΰ���"��ư*/
	private JButton back;
	/**����ڰ� �Է��� Ű ���� ������ �ؽ�Ʈ��*/;
	private JLabel keyLabel = new JLabel("Ű�� ǥ�õ˴ϴ�");
	private Font font = new Font(Font.SANS_SERIF, Font.BOLD, 30);
	/**��� ��ġ�� ���� �г�*/
	private JPanel topPanel = new JPanel();
	/**�߾� ��ġ�� ���� �г�*/
	private JPanel centerPanel = new JPanel();
	/**������� ���Ұ� ���θ� �����ϴ� üũ�ڽ�*/
	private JCheckBox bgmMute = new JCheckBox();
	/**Ű ������ ����� ��*/
	private int configUp, configDown, configLeft, configRight;
	/**Ű �Է����κ��� Ű �ڵ带 �޾Ƶ��� �ӽ� ��*/
	private int tmp;
	/**�����¿� Ű�� �Է� ������ ���� ��*/
	private int count = 0;
	/**ȯ�� ���� �г� �ʱ�ȭ
	 * @author �ڻ��
	 * @param window �ν��Ͻ��� ����� ����������
	 * */
	public ConfigPanel(MainWindow window){
		this.window = window;
		setLayout(new BorderLayout());
		addKeyListener(this);
		setBackground(Color.BLACK);
		keyLabel.setFont(font);
		keyLabel.setForeground(Color.WHITE);
		config= design.setbutton(config, 8);
		set = design.setbutton(set, 9);
		back = design.setbutton(back, 7);
		effect.setbutton(config);
		effect.setbutton(set);
		effect.setbutton(back);
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				keyLabel.setText("Ű�� ǥ�õ˴ϴ�");
				window.changePanel(window.mainPanel);
			}
		});
		bgmMute.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					window.Bgm.control.setValue(-80);
					window.Bgm.vol = window.Bgm.control.getValue();
				}
				else {
					window.Bgm.control.setValue(0);
					window.Bgm.vol = window.Bgm.control.getValue();
				}
			}
		});
		bgmMute.setBackground(Color.BLACK);
		bgmMute.setIcon(new ImageIcon("images/Buttons/BGM_img1.png"));
		bgmMute.setSelectedIcon(new ImageIcon("images/Buttons/BGM_img2.png"));
		config.addActionListener(this);
		set.addActionListener(this);
		set.setEnabled(false);

		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 0));
		topPanel.setBackground(Color.BLACK);
		topPanel.add(back);
		topPanel.add(config);
		topPanel.add(set);
		topPanel.add(bgmMute);
		centerPanel.setBackground(Color.BLACK);
		centerPanel.add(keyLabel);
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
	}
	/**Ű�� �Է��ϸ� �ؽ�Ʈ�󺧿� �Է¹��� Ű�� ����ϴ� �޼ҵ�
	 * @author �ڻ��
	 */
	public void keyPressed(KeyEvent e) {
		tmp = e.getKeyCode();
		set.setEnabled(true);
		keyLabel.setText(e.getKeyText(e.getKeyCode()));
	}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
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
			back.setEnabled(false);
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
					back.setEnabled(true);
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
	public KeySet getKeySet() {
		return key;
	}
	public JCheckBox getBgmMute() {
		return bgmMute;
	}
	public void setBgmMute(JCheckBox bgmMute) {
		this.bgmMute = bgmMute;
	}
}