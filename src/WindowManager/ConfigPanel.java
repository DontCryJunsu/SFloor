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
/**Ŭ���� ����
 * <br>
 * ȯ�漳������� ����ϴ� �г�
 * 
 * @author �ڻ��
 * @see javax.swing.JPanel
 */
public class ConfigPanel extends JPanel implements KeyListener, ActionListener{
	/**���� �������� ������ �̿��ϱ� ���� �ν��Ͻ�*/
	private MainWindow window;
	/**Ű ������ �̿��� Ű�� �ν��Ͻ�*/
	private KeySet key = new KeySet();
	/**"Ű �����ϱ�"��ư*/
	private JButton config = new JButton("Ű ���� �ϱ�");
	/**"����"��ư*/
	private JButton set = new JButton("����");
	/**"�ڷΰ���"��ư*/
	private JButton back = new JButton("�ڷΰ���");
	/**����ڰ� �Է��� Ű ���� ������ �ؽ�Ʈ��*/;
	private JLabel keyLabel = new JLabel("Ű�� ǥ�õ˴ϴ�");
	/**��� ��ġ�� ���� �г�*/
	private JPanel topPanel = new JPanel();
	/**�߾� ��ġ�� ���� �г�*/
	private JPanel centerPanel = new JPanel();
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
		topPanel.add(new JLabel("ȯ�漳�� �г�"));
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				keyLabel.setText("Ű�� ǥ�õ˴ϴ�");
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
}
