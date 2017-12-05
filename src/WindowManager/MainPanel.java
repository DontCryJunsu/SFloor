package WindowManager;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SFDesignManager.Design;
import SFDesignManager.Sound;

public class MainPanel extends JPanel  implements ActionListener{
	/**���� ������ �ν��Ͻ�*/
	MainWindow window;
	/**��ư�� ������ ������ ���� ������ �ν��Ͻ� ����*/
	Design design = new Design();
	/**��ư�� ���� ������ ���� ���� �ν��Ͻ� ����*/
	Sound effect = new Sound("sounds/bt_cursorOn.wav");
	/**���� ������ ���� ��ư*/
	JButton ToGameBtn;
	/**��ũ �г��� ���� ���� ��ư*/
	JButton ToRankBtn;
	/**ȯ�� ���� �г��� ���� ���� ��ư*/
	JButton ToConfigBtn;
	/**���� ���̾�α׸� ��� ��ư*/
	JButton ExitBtn;
	/**���� ���̾�α�*/
	ExitDialog exit;
	/**���� ȭ�� �̹���*/
	ImageIcon mainIcon = new ImageIcon("images/Slippery_floor.png");
	/**���� �̸� �̹���*/
	ImageIcon GameNameIcon = new ImageIcon("images/SF_MAIN.png");
	/**paintComponent �޼ҵ� �������̵�
	 * @author ȫ����
	 * @param g �׷���
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Image img = mainIcon.getImage();
		g.drawImage(img, 200, 70, this);
		img = GameNameIcon.getImage();
		g.drawImage(img, 550, 50, this);

	}
	/**���� �г� �ʱ�ȭ
	 * @author ȫ����
	 * @param window ���� ������ �ν��Ͻ�
	 */
	public MainPanel(MainWindow window){
		this.window = window;

		setmain();

		setBackground(Color.BLACK);
		setLayout(null);
	}
	/**��ư ũ��,��ġ,������ ����,������ ���λ��� ó��.
	 * @author ȫ����
	 */
	public void setmain( ){
		ToGameBtn = design.setbutton(ToGameBtn, 0);
		ToRankBtn = design.setbutton(ToRankBtn, 1);
		ToConfigBtn = design.setbutton(ToConfigBtn, 2);
		ExitBtn = design.setbutton(ExitBtn, 3);

		effect.setbutton(ToGameBtn);
		effect.setbutton(ToRankBtn);
		effect.setbutton(ToConfigBtn);
		effect.setbutton(ExitBtn);

		add(ToGameBtn);
		add(ToRankBtn);
		add(ToConfigBtn);
		add(ExitBtn);

		ToGameBtn.addActionListener(this);
		ToRankBtn.addActionListener(this);
		ToConfigBtn.addActionListener(this);
		ExitBtn.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e){
		if((JButton)e.getSource() == ToGameBtn){
			window.Bgm.stop();
			window.Bgm.loop("sounds/BackgroundMusic_tutorial.wav", Clip.LOOP_CONTINUOUSLY);
			window.changePanel(window.gamePanel);
		}
		if((JButton)e.getSource() == ToRankBtn) {
			window.rankPanel.updateRank();
			window.changePanel(window.rankPanel);
		}
		if((JButton)e.getSource() == ToConfigBtn){
			window.changePanel(window.configPanel);
		}
		if((JButton)e.getSource() == ExitBtn) {
			exit = new ExitDialog(window.contentPane);
			exit.setVisible(true);
		}
	}


	/**Ŭ���� ����
	 * <br>
	 * ���� ��ư�� ������ �� ��Ÿ���� ���̾�α�
	 * @author �ڻ��
	 * @see javax.swing.JDialog
	 */
	public class ExitDialog extends JDialog implements ActionListener{
		JPanel labelPanel = new JPanel();
		JPanel btnPanel = new JPanel();
		JLabel label = new JLabel("������ �����Ͻðڽ��ϱ�?");
		Font labelFont = new Font(Font.SANS_SERIF, Font.BOLD, 20);
		Design design = new Design();
		JButton yes;
		JButton no;
		private Sound effect = new Sound("sounds/bt_cursorOn.wav");
		ExitDialog(Container c){
			setTitle("���� ����");
			setSize(300,200);
			setLayout(new BorderLayout());
			setAlwaysOnTop(true);
			setLocationRelativeTo(c);
			setBackground(Color.BLACK);
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
			btnPanel.setBackground(Color.BLACK);
			btnPanel.add(yes);
			btnPanel.add(no);
			add(labelPanel, BorderLayout.NORTH);
			add(btnPanel, BorderLayout.CENTER);
		}
		public void actionPerformed(ActionEvent e){
			if((JButton)e.getSource() == yes) System.exit(0);
			else dispose();
		}
	}
}