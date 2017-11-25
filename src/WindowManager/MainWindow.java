package WindowManager;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GamePlayManager.Game;
/**Class Description of MainWindow
 * <br>
 * ��ȯ�� �гε��� �ν��Ͻ��� ������ ���� ������ Ŭ����
 * @author �ڻ��
 * @see javax.swing.JFrame
 */
public class MainWindow extends JFrame implements ActionListener{
	/**����Ʈ���� �˾Ƴ������� �����̳� �ν��Ͻ�*/
	public Container contentPane = getContentPane();
	/**�ʱ� ȭ���� ������Ʈ���� ������ �г� */
	public JPanel mainPanel = new JPanel();
	/**��ü���� �ν��Ͻ��� �� Ŭ������ ������ �����г� */
	GamePanel gamePanel = new GamePanel(this);
	/**��ü���� �ν��Ͻ��� �� Ŭ������ ������ ��ũ�г� */
	RankPanel rankPanel = new RankPanel(this);
	/**��ü���� �ν��Ͻ��� �� Ŭ������ ������ ȯ�漳���г� */
	ConfigPanel configPanel = new ConfigPanel(this);
	/**���� �÷��̸� ����ϴ� �����÷����г�*/
	Game MyGame = new Game(this);
	/**�����гη� �Ѿ������ ��ư*/
	JButton ToGameBtn = new JButton("���� ����");
	/**��ũ�гη� �Ѿ������ ��ư*/
	JButton ToRankBtn = new JButton("���� ����");
	/**ȯ�漳���гη� �Ѿ������ ��ư*/
	JButton ToConfigBtn = new JButton("ȯ�� ����");
	/**���α׷� ���Ῡ�θ� ��������� ��ư*/
	JButton ExitBtn = new JButton("���� ����");
	/**���� �������� �̸�, ũ�⸦ �����ϰ� ��ư�鿡 �׼Ǹ����ʸ� ����
	 * <br>
	 * ������ ��ư���� �����гο� �߰��ϰ� �����г��� �����ӿ� �߰�
	 */
	public MainWindow(){
		setTitle("Slippery Floor");
		setSize(1280, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setLayout(null);
		setLocationRelativeTo(null);
		ToGameBtn.addActionListener(this);
		ToRankBtn.addActionListener(this);
		ToConfigBtn.addActionListener(this);
		ExitBtn.addActionListener(this);
		mainPanel.add(ToGameBtn);
		mainPanel.add(ToRankBtn);
		mainPanel.add(ToConfigBtn);
		mainPanel.add(ExitBtn);
		add(mainPanel);
		setVisible(true);
	}
	/**��ư���� ���� �ҷ����� �г��� �ٸ��� ����
	 * <br>
	 * ���� �����ư�� ������ ���Ῡ�θ� ����� ������ ����
	 */
	public void actionPerformed(ActionEvent e){
		if((JButton)e.getSource() == ToGameBtn) changePanel(gamePanel);
		if((JButton)e.getSource() == ToRankBtn) changePanel(rankPanel);
		if((JButton)e.getSource() == ToConfigBtn) changePanel(configPanel);
		if((JButton)e.getSource() == ExitBtn) new SecFrm();
	}
	/**�г��� ��ȯ�ϴ� �޼ҵ�
	 * <br>
	 * ����Ʈ���� ��� ������Ʈ�� ��������� �Ű������� ���� �г��� ����Ʈ�ҿ� �߰��Ͽ� �ٽñ׸�
	 * 
	 * @param c ������ �ҷ��� �г�
	 */
	public void changePanel(Component c){
		contentPane.removeAll();
		contentPane.add(c);
		contentPane.revalidate();
		contentPane.repaint();
	}
	/**Class Description of SecFrm
	 * <br>
	 * ���� �����ӿ��� ���� ���� ��ư�� ������ �� ��Ÿ���� ���� ������
	 * @author �ڻ��
	 * @see javax.swing.JFrame
	 */
	class SecFrm extends JFrame implements ActionListener{
		/**�ؽ�Ʈ ���� ������ ���г�*/
		JPanel labelPanel = new JPanel();
		/**��ư�� ������ ��ư�г�*/
		JPanel btnPanel = new JPanel();
		/**�ؽ�Ʈ�� ����ϱ����� ��*/
		JLabel label = new JLabel("������ �����Ͻðڽ��ϱ�?");
		/**���Ḧ �����ϴ� ��ư*/
		JButton yes = new JButton("��");
		/**���Ḧ �������� �ʴ� ��ư*/
		JButton no = new JButton("�ƴϿ�");
		/**���� �������� �̸��� ũ�⼳��
		 * <br>
		 * �� �гο� �󺧰� ��ư�� �߰��Ͽ� �����ӿ� �߰�
		 * 
		 */
		SecFrm(){
			setTitle("���� ����");
			setSize(300,200);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLayout(new BorderLayout());
			setLocationRelativeTo(contentPane);
			labelPanel.add(label);
			yes.addActionListener(this);
			no.addActionListener(this);
			btnPanel.add(yes);
			btnPanel.add(no);
			add(labelPanel, BorderLayout.NORTH);
			add(btnPanel, BorderLayout.CENTER);
			setVisible(true);
		}
		/**��ư�� ������ ���� ���Ῡ�θ� �����ϴ� �׼Ǹ������� �޼ҵ�
		 * 
		 */
		public void actionPerformed(ActionEvent e){
			if((JButton)e.getSource() == yes) System.exit(0);
			else dispose();
		}
	}
}
