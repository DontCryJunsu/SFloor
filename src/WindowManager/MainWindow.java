package WindowManager;

import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;

import javax.sound.sampled.Clip;
import javax.swing.JFrame;

import GamePlayManager.MergedGamePanel;
import SFDesignManager.Sound;
/**Ŭ���� ����
 * <br>
 * ������ ������Ʈ���� ������ ���� ������ Ŭ����
 * <br>
 * �� �гε��� �ν��Ͻ��� ����
 * @author �ڻ��, ȫ����
 * @see javax.swing.JFrame
 */
public class MainWindow extends JFrame{
	/**����Ʈ���� �˾Ƴ��� ���� �ν��Ͻ�*/
	public Container contentPane = getContentPane();
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
	/**���� Ŭ���� �ν��Ͻ�*/
	public Sound Bgm = new Sound("sounds/BackgroundMusic_main.wav");
	public Sound effect = new Sound("sounds/bt_cursorOn.wav");
		/**���� ������ Ŭ���� �ʱ�ȭ
	 * @author �ڻ��, ȫ����
	 * */
	public MainWindow(){
		setTitle("Slippery Floor");
		setSize(1400, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		// ���α׷��� �������� �����Ѵ�.
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("Slippery_floor.png");
		setIconImage(img);

		add(mainPanel);
		setVisible(true);
		Bgm.loop(Clip.LOOP_CONTINUOUSLY); // ������ �ݺ� ����Ѵ�.
	}
	/**ȭ�� ��ȯ�� ���� �޼ҵ�
	 * <br>
	 * ������ �ҷ��� �г��� �Ű������� �޴´�
	 * @author �ڻ��
	 * @param c ������ �ҷ��� ������Ʈ
	 */
	public void changePanel(Component c){	
		contentPane.removeAll();
		contentPane.add(c);
		contentPane.revalidate();
		contentPane.repaint();
	}
}