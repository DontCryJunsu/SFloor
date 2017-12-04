package WindowManager;

import java.awt.*;
import javax.swing.JFrame;

import GamePlayManager.MergedGamePanel;
import SFDesignManager.Design.MainPanel;
import SFDesignManager.Sound;
import SFDesignManager.Intro;
/**Ŭ���� ����
 * <br>
 * ������ ������Ʈ���� ������ ���� ������ Ŭ����
 * �� �гε��� �ν��Ͻ��� ����
 * @author �ڻ��, ȫ����
 * @see javax.swing.JFrame
 */
public class MainWindow extends JFrame{
	/**����Ʈ���� �˾Ƴ��� ���� �ν��Ͻ�*/
	public Container contentPane = getContentPane();
	// public Intro introPanel = new Intro(this);
	/**���� �г� �ν��Ͻ�*/
	public MainPanel mainPanel = new MainPanel(this);
	/**���� �г� �ν��Ͻ�*/
	public GamePanel gamePanel = new GamePanel(this);
	/**��ũ �г� �ν��Ͻ�*/
	public RankPanel rankPanel = new RankPanel(this);
	/**ȯ�� ���� �г� �ν��Ͻ�*/
	public ConfigPanel configPanel = new ConfigPanel(this);
	/**���� �г� �ν��Ͻ�*/
	public MergedGamePanel mgp = new MergedGamePanel(this);
	public Sound sound = new Sound("BackgroundMusic_main.wav");
	public Sound effect = new Sound();
	/**���� ������ Ŭ���� �ʱ�ȭ
	 * @author �ڻ��, ȫ����
	 * */
	public MainWindow(){
		setTitle("Slippery Floor");
		setSize(1400, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setLayout(null);
		setLocationRelativeTo(null);
		//mainPanel.setbutton(ToGameBtn, ToRankBtn, ToConfigBtn, ExitBtn);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("Slippery_floor.png");
		setIconImage(img);

		add(mainPanel);
		setVisible(true);
		sound.loop(); // ������ �ݺ� ����Ѵ�.
	}
	/**ȭ�� ��ȯ�� ���� �޼ҵ�
	 * ������ �ҷ��� �г��� �Ű������� �޴´�
	 * @author �ڻ��
	 * @param c ������ �ҷ��� ������Ʈ
	 */
	public void changePanel(Component c){
		contentPane.removeAll();
		contentPane.add(c);
		contentPane.revalidate();
		contentPane.repaint();
		if(c == rankPanel) rankPanel.updateRank();
	}
}
