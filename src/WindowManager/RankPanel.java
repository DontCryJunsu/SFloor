package WindowManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**Class Description of RankPanel
 * <br>
 * 순위 관리를 담당하는 패널
 * @author 박상우
 * @see javax.swing.JPanel
 */
class RankPanel extends JPanel{
	MainWindow window;
	JButton back = new JButton("뒤로가기");

	RankPanel(MainWindow window){
		this.window = window;
		add(new JLabel("랭크 패널"));
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				window.changePanel(window.mainPanel);
			}
		});
		add(back);
	}
}