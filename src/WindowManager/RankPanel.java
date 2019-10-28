package WindowManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**Class Description of RankPanel
 * <br>
 * ���� ������ ����ϴ� �г�
 * @author �ڻ��
 * @see javax.swing.JPanel
 */
class RankPanel extends JPanel{
	MainWindow window;
	JButton back = new JButton("�ڷΰ���");

	RankPanel(MainWindow window){
		this.window = window;
		add(new JLabel("��ũ �г�"));
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				window.changePanel(window.mainPanel);
			}
		});
		add(back);
	}
}