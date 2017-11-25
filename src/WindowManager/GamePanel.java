package WindowManager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
/**Class Description of GamePanel
 * <br>
 * ���� �÷��̸� ����ϴ� �г�
 * @author �ڻ��
 * @see javax.swing.JPanel
 */
class GamePanel extends JPanel implements ActionListener{
	/** �г� ��ȯ �޼ҵ带 ����ϱ� ���� MainWindow �ν��Ͻ� */
	MainWindow window;
	/** ���� �������� �Ѿ������ ��ư*/
	JButton start = new JButton("���� ����");
	/** ���� �޴��� ���ư������� ��ư*/
	JButton back = new JButton("�ڷΰ���");
	/** ������ �Ű������� MainWindow ��ü�� �޾� Ŭ�������� window�� ����
	 * @param window ���� �г��� �ν��Ͻ��� ����� MainWindow ��ü*/
	GamePanel(MainWindow window){
		this.window = window;
		add(new JLabel("���� �г�"));
		start.addActionListener(this);
		back.addActionListener(this);
		add(start);
		add(back);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == start){
			window.changePanel(window.MyGame);
			window.MyGame.requestFocus();
		}
		if(e.getSource() == back) window.changePanel(window.mainPanel);
	}

}