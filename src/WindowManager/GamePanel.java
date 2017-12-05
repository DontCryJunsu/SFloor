package WindowManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SFDesignManager.Design;
import SFDesignManager.Sound;

/**Ŭ���� ����
 * <br>
 * ���� �÷��̸� ����ϴ� �г�
 * @author �ڻ��, ȫ����
 * @see javax.swing.JPanel
 */
public class GamePanel extends JPanel implements ActionListener{
	/** �г� ��ȯ �޼ҵ带 ����ϱ� ���� MainWindow �ν��Ͻ� */
	MainWindow window;
	/** ���� �������� �Ѿ������ ��ư*/
	JButton skip;
	/** ���� �޴��� ���ư������� ��ư*/
	JButton back;
	/**��ư�� ������ ������ ���� ������ �ν��Ͻ� ����*/
	Design design = new Design();
	/**��ư�� ���� ������ ���� ���� �ν��Ͻ� ����*/
	Sound effect = new Sound("sounds/bt_cursorOn.wav");
	/**�ؽ�Ʈ ��*/
	JLabel gp = new JLabel("�����г�-Ʃ�丮��<���丮 ��ȭ �� ���۹�>");
	/**�ϴܿ� ������ �г�*/
	JPanel pan_South = new JPanel();
	/**Ʃ�丮��� ���丮�� ����� �̹��� ��ü*/
	ImageIcon toonIcon[] = new ImageIcon[21];
	/**�̹��� �ν��Ͻ�*/
	Image img;
	/**�̹��� ��ȣ�� ����� ��*/
	int i = 0;
	/**���� �г� �ʱ�ȭ
	 * @author �ڻ��, ȫ����
	 * @param window �ν��Ͻ��� ����� ����������
	 * */
	GamePanel(MainWindow window){
		this.window = window;
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		gp.setForeground(Color.WHITE);
		add(gp,BorderLayout.NORTH);
		add(new Cartoon(),BorderLayout.CENTER);
		setSouth();
		setMain();
	}
	/**�ϴ� �г� �ʱ�ȭ
	 * @author ȫ����
	 */
	public void setSouth(){
		pan_South.setBackground(Color.BLACK);

		skip= design.setbutton(skip, 6);
		back = design.setbutton(back, 7);
		effect.setbutton(skip);
		effect.setbutton(back);
		pan_South.add(skip);
		pan_South.add(back);
		add(pan_South,BorderLayout.SOUTH);
	}
	/**�г��� ���� ��ư �׼Ǹ����� ����
	 * @author ȫ����
	 */
	public void setMain( ){
		skip.setBackground(Color.white);
		skip.setOpaque(false); 
		back.setBackground(Color.white); 
		back.setOpaque(false); 

		skip.addActionListener(this);
		back.addActionListener(this);
	}
	/**���� ��ư�� ������ �� ����� �׼Ǹ����� �޼ҵ� ����
	 * <br>
	 * ���� �г��� �ҷ��� �� ���� ������� Ÿ�̸� �����尡 ����
	 * @author �ڻ��, ȫ����
	 */
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == skip){
			i = 0;
			img = toonIcon[0].getImage();
			repaint();
			window.changePanel(window.mgp);
			window.mgp.getGamePlaying().requestFocus();
			try{
				window.mgp.getTimer().start();
				window.mgp.getGamePlaying().getGamePlayingThread().start();
			}catch(Exception k){
				return;
			}
		}
		if(e.getSource() == back){
			i = 0;
			img = toonIcon[0].getImage();
			repaint();
			window.Bgm.stop();
			window.Bgm.loop("sounds/BackgroundMusic_main.wav", Clip.LOOP_CONTINUOUSLY);
			window.changePanel(window.mainPanel);}
	}
	/**Ŭ���� ����
	 * <br>
	 * ���콺 Ŭ���� ���� �̹����� ������ �г�
	 * @author ȫ����
	 * @see javax.swing.JPanel
	 */
	class Cartoon extends JPanel {
		Cartoon(){
			setToonPanel();
			setBackground(Color.BLACK);
			addMouseListener(new MouseListener() {
				public void mouseClicked (MouseEvent e) {
					if(i<21){
						img = toonIcon[i].getImage();
						repaint();
					}
					else if(i == 21){
						i = 0;
						img = toonIcon[0].getImage();
						repaint();
						window.changePanel(window.mgp);
						window.mgp.getGamePlaying().requestFocus();
						try{
							window.mgp.getTimer().start();
							window.mgp.getGamePlaying().getGamePlayingThread().start();
						}catch(Exception k){
							return;
						}
					}
				}
				public void mouseEntered(MouseEvent arg0) {}
				public void mouseExited(MouseEvent arg0) {}
				public void mousePressed(MouseEvent arg0) {}
				public void mouseReleased(MouseEvent arg0) {}
			});
		}
		public void setToonPanel(){
			// �׽�Ʈ�� �ӽ� �׸�. �� ���� ��Ŭ���ϸ� ����ȭ������ �Ѿ.
			for(int k =0; k<21;k++){
				toonIcon[k] = new ImageIcon("images/cartoons/cartoon"+(k+1)+".png");
			}
			img = toonIcon[0].getImage();
		}
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			i++;
			g.drawImage(img, 0, 0, this);
		}
	}
}