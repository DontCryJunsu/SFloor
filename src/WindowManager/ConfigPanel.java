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
 * 환경설정기능을 담당하는 패널
 * 
 * @author 박상우
 * @see javax.swing.JPanel
 */
class ConfigPanel extends JPanel implements KeyListener, ActionListener{
	MainWindow window;
	//Container contentPane;
	KeySet key = new KeySet();
	JButton config = new JButton("키 설정 하기");
	JButton set = new JButton("설정");
	JButton back = new JButton("뒤로가기");
	JLabel keyLabel = new JLabel("키가 표시됩니다.");
	int configUp, configDown, configLeft, configRight;
	int tmp;
	int count = 0;
	ConfigPanel(MainWindow window){
		this.window = window;
		//contentPane = window.contentPane;
		addKeyListener(this);
		add(new JLabel("환경설정 패널"));
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
				keyLabel.setText("키 설정 완료");
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

