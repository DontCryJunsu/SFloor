package WindowManager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**Ŭ���� ����
 * <br>
 * ���� �÷��̸� ����ϴ� �г�
 * @author �ڻ��, ȫ����
 * @see javax.swing.JPanel
 */

// Ʃ�丮���� ����ϴ� �г�
public class GamePanel extends JPanel implements ActionListener{
	/** �г� ��ȯ �޼ҵ带 ����ϱ� ���� MainWindow �ν��Ͻ� */
	MainWindow window;
	ImageIcon rollover_sk, pressedl_sk, normal_sk = new ImageIcon("images/BT_SK1.png");
	ImageIcon rollover_bk, pressedl_bk, normal_bk = new ImageIcon("images/BT_BK1.png");
	/** ���� �������� �Ѿ������ ��ư*/
	JButton skip = new JButton(normal_sk);
	/** ���� �޴��� ���ư������� ��ư*/
	JButton back = new JButton(normal_bk);
	JLabel gp = new JLabel("�����г�-Ʃ�丮��");
	JPanel pan_South = new JPanel();
	/**���� �г� �ʱ�ȭ
	 * @author �ڻ��, ȫ����
	 * @param window �ν��Ͻ��� ����� ����������
	 * */
	GamePanel(MainWindow window){
		this.window = window;
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		add(gp,BorderLayout.NORTH);
		add(new Cartoon(),BorderLayout.CENTER);
		setSouth();
		setMain();
	}
	/**
	 * @author ȫ����
	 */
	public void setSouth(){
		setButton();
		pan_South.setBackground(Color.BLACK);
		pan_South.add(skip);
		pan_South.add(back);
		add(pan_South,BorderLayout.SOUTH);
	}
	/**
	 * @author ȫ����
	 */
	public void setButton(){
		pressedl_sk = new ImageIcon("images/BT_SK2.png");
		rollover_sk = new ImageIcon("images/BT_SK2.png");		
		skip.setPressedIcon(pressedl_sk);
		skip.setRolloverIcon(rollover_sk);
		skip.setOpaque(false);
		skip.setBorder(null);
		skip.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				window.effect.play("bt_cursorOn.wav");
			}
			public void mousePressed(MouseEvent e){
				window.effect.play("bt_selected.wav");
			}
		});

		rollover_bk = new ImageIcon("images/BT_BK2.png");
		pressedl_bk = new ImageIcon("images/BT_BK2.png");
		back.setPressedIcon(pressedl_bk);
		back.setRolloverIcon(rollover_bk);
		back.setOpaque(false);
		back.setBorder(null);
		back.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				window.effect.play("bt_cursorOn.wav");
			}
			public void mousePressed(MouseEvent e){
				window.effect.play("bt_selected.wav");
			}
		});

	}
	/**
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
	 * ���� �г��� �ҷ��� �� ���� ������� Ÿ�̸� �����尡 ����
	 * @author �ڻ��, ȫ����
	 */
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == skip){
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
			window.sound.stop();
			window.sound.loop("BackgroundMusic_main.wav");
			window.changePanel(window.mainPanel);}
	}
	/**
	 * 
	 * @author ȫ����
	 *
	 */
	class Cartoon extends JPanel {
		ImageIcon toonIcon[] = new ImageIcon[8];
		Image img;
		int i = 0;
		// JPanel toon[] = new JPanel[8];
		// ���� ��ȭ�� ���������� ���-�� 10��
		Cartoon(){
			setToonPanel();
			setBackground(Color.BLACK);
			addMouseListener(new MouseListener() {
				public void mouseClicked (MouseEvent e) {
					if(i<8){
						img = toonIcon[i].getImage();
						repaint();
					}
					else if(i == 8){
						i = 0;
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
			toonIcon[0] = new ImageIcon("images/J1.png");
			toonIcon[1] = new ImageIcon("images/J2.png");
			toonIcon[2] = new ImageIcon("images/J1.png");
			toonIcon[3] = new ImageIcon("images/J2.png");
			toonIcon[4] = new ImageIcon("images/J3.png");
			toonIcon[5] = new ImageIcon("images/J4.png");
			toonIcon[6] = new ImageIcon("images/J3.png");
			toonIcon[7] = new ImageIcon("images/J4.png");
			img = toonIcon[0].getImage();
		}
		public void paintComponent(Graphics g){
			i++;
			super.paintComponent(g);
			g.drawImage(img, 200, 70, this);
		}
	}
}