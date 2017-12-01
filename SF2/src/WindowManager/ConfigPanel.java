package WindowManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


class ConfigPanel extends JPanel{
	MainWindow window;
	JButton back = new JButton("뒤로가기");
	ConfigPanel(MainWindow window){
		this.window = window;
		add(new JLabel("환경설정 패널"));
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				window.changePanel(window.mainPanel);
			}
		});
		add(back);
	}
}

