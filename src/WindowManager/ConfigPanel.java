package WindowManager;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GamePlayManager.KeySet;
/**Class Description of ConfigPanel
 * <br>
 * ȯ�漳������� ����ϴ� �г�
 * 
 * @author �ڻ��
 * @see javax.swing.JPanel
 */
class ConfigPanel extends JPanel implements KeyListener, ActionListener{
	MainWindow window;
	//Container contentPane;
	KeySet key = new KeySet();
	JButton config = new JButton("Ű ���� �ϱ�");
	JButton set = new JButton("����");
	JButton back = new JButton("�ڷΰ���");
	JLabel keyLabel = new JLabel("Ű�� ǥ�õ˴ϴ�.");
	int configUp, configDown, configLeft, configRight;
	int tmp;
	int count = 0;
	ConfigPanel(MainWindow window){
		this.window = window;
		//contentPane = window.contentPane;
		addKeyListener(this);
		add(new JLabel("ȯ�漳�� �г�"));
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				window.changePanel(window.mainPanel);
			}
		});
		config.addActionListener(this);
		set.addActionListener(this);
		set.setEnabled(false);
		add(back);
		add(config);
		add(set);
		add(keyLabel);
		setFocusable(true);
	}
	public void keyPressed(KeyEvent e) {
		tmp = e.getKeyCode();
		keyLabel.setText(e.getKeyText(e.getKeyCode()));
	}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == config){
			set.setEnabled(true);
			config.setEnabled(false);
			requestFocus();
		}
		if(e.getSource() == set){
			if(count == 0) {
				configUp = tmp;
				count++;
			}
			else if(count == 1) {
				configDown = tmp;
				count++;
			}
			else if(count == 2) {
				configLeft = tmp;
				count++;
			}
			else if(count == 3){
				configRight = tmp;
				count = 0;
				changeAndSave();
				keyLabel.setText("Ű ���� �Ϸ�");
				set.setEnabled(false);
				config.setEnabled(true);
				setFocusable(false);
			}
			requestFocus();
		}
	}
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
		}catch(Exception e){}
	}

}

