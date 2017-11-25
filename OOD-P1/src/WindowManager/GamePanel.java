package WindowManager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import SFDesignManager.Design.mainPanel;
/**
 * <br>
 * ���� �÷��̸� ����ϴ� �г�
 * @author �ڻ��
 * @see java.swing.JPanel
 */

// Ʃ�丮���� ����ϴ� �г�
class GamePanel extends JPanel implements ActionListener{
	/** �г� ��ȯ �޼ҵ带 ����ϱ� ���� MainWindow �ν��Ͻ� */
	MainWindow window;
	/** ���� �������� �Ѿ������ ��ư*/
	JButton start;
	/** ���� �޴��� ���ư������� ��ư*/
	JButton back;
	ImageIcon normal;
	JLabel gp = new JLabel("�����г�-Ʃ�丮��");

	// gp.setForeground(Color.WHITE);
	/** ������ �Ű������� MainWindow ��ü�� �޾� Ŭ�������� window�� ����*/
	GamePanel(MainWindow window){
		this.window = window;
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		add(gp,BorderLayout.NORTH);

		setbutton();
		add(start,BorderLayout.SOUTH);
		//add(back,BorderLayout.SOUTH);

		add(new Cartoon(),BorderLayout.CENTER);
		setmain();
	}
	public void setbutton(){
		normal = new ImageIcon("BT_SK1.png");
		//rollover = new ImageIcon("BT_SK2.png");
		//pressedl = new ImageIcon("BT_SK3.png");
		start = new JButton(normal);

		normal = new ImageIcon("BT_BK1.png");
		//rollover = new ImageIcon("BT_SK2.png");
		//pressedl = new ImageIcon("BT_SK3.png");
		back = new JButton(normal);


	}
	public void setmain( ){
		//start.setLocation(700, 550);
		start.setSize(new Dimension(180,35));
		start.setBackground(Color.white); //btn�� ������ �����ϰ� ������� �ƹ� ���̳� �־���.
		start.setOpaque(false); //btn�� ���� �����ϰ� ����.


		//back.setLocation(900, 550);
		back.setSize(new Dimension(180 ,35));
		back.setBackground(Color.white); //btn�� ������ �����ϰ� ������� �ƹ� ���̳� �־���.
		back.setOpaque(false); //btn�� ���� �����ϰ� ����.

		start.addActionListener(this);
		back.addActionListener(this);

	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == start){
			window.changePanel(window.MyGame);
			window.MyGame.requestFocus();
		}
		if(e.getSource() == back) window.changePanel(window.mainPanel);
	}

	class Cartoon extends JPanel {
		ImageIcon toonIcon[] = new ImageIcon[8];
		Image img;
		int i =0;

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
					else if(i==8){
					
						window.changePanel(window.MyGame);
						window.MyGame.requestFocus();
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
			toonIcon[0] = new ImageIcon("J1.png");
			toonIcon[1] = new ImageIcon("J2.png");
			toonIcon[2] = new ImageIcon("J1.png");
			toonIcon[3] = new ImageIcon("J2.png");
			toonIcon[4] = new ImageIcon("J3.png");
			toonIcon[5] = new ImageIcon("J4.png");
			toonIcon[6] = new ImageIcon("J3.png");
			toonIcon[7] = new ImageIcon("J4.png");
			img = toonIcon[0].getImage();

		}

		public void paintComponent(Graphics g){
			i++;
			super.paintComponent(g);
			g.drawImage(img, 200, 70, this);

		}


	}
}