package SFDesignManager;

import javax.swing.*;

import WindowManager.MainWindow;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Design {

	public static class  MainPanel extends JPanel  implements ActionListener{
		MainWindow window;
		JButton ToGameBtn;
		JButton ToRankBtn;
		JButton ToConfigBtn;
		JButton ExitBtn;
		ExitDialog exit;

		ImageIcon mainIcon = new ImageIcon("images/Slippery_floor.png");
		ImageIcon normal;
		ImageIcon rollover;
		ImageIcon pressedl;
		Image img = mainIcon.getImage();

		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(img, 200, 70, this);

		}
		public MainPanel(MainWindow window){
			this.window = window;
			setbutton();
			setmain();
			setBackground(Color.BLACK);
			setLayout(null);
		}
		public void setmain( ){

			ToGameBtn.setLocation(650, 120);
			ToGameBtn.setSize(new Dimension(normal.getIconWidth() ,normal.getIconHeight()));
			ToGameBtn.setBackground(Color.white); //btn의 배경색을 투명하게 만드려고 아무 색이나 넣었음.
			ToGameBtn.setOpaque(false); //btn의 배경색 투명하게 만듬.
			add(ToGameBtn);

			ToRankBtn.setLocation(650, 220);
			ToRankBtn.setSize(new Dimension(normal.getIconWidth() ,normal.getIconHeight()));
			ToRankBtn.setBackground(Color.white); 
			add(ToRankBtn);

			ToConfigBtn.setLocation(650, 320);
			ToConfigBtn.setSize(new Dimension(normal.getIconWidth() ,normal.getIconHeight()));
			ToConfigBtn.setBackground(Color.white); 
			add(ToConfigBtn);

			ExitBtn.setLocation(650, 420);
			ExitBtn.setSize(new Dimension(normal.getIconWidth() ,normal.getIconHeight()));
			ExitBtn.setBackground(Color.white); 
			add(ExitBtn);


			ToGameBtn.addActionListener(this);
			ToRankBtn.addActionListener(this);
			ToConfigBtn.addActionListener(this);
			ExitBtn.addActionListener(this);
		}
		public void setbutton(){

			normal = new ImageIcon("images/BT_GS1.png");
			rollover = new ImageIcon("images/BT_GS2.png");
			pressedl = new ImageIcon("images/BT_GS3.png");

			ToGameBtn= new JButton(normal);
			ToGameBtn.setPressedIcon(pressedl);
			ToGameBtn.setRolloverIcon(rollover);

			normal = new ImageIcon("images/BT_RK1.png");
			rollover = new ImageIcon("images/BT_RK2.png");
			pressedl = new ImageIcon("images/BT_RK3.png");

			ToRankBtn= new JButton(normal);
			ToRankBtn.setPressedIcon(pressedl);
			ToRankBtn.setRolloverIcon(rollover);

			normal = new ImageIcon("images/BT_ST1.png");
			rollover = new ImageIcon("images/BT_ST2.png");
			pressedl = new ImageIcon("images/BT_ST3.png");

			ToConfigBtn= new JButton(normal);
			ToConfigBtn.setPressedIcon(pressedl);
			ToConfigBtn.setRolloverIcon(rollover);

			normal = new ImageIcon("images/BT_ET1.png");
			rollover = new ImageIcon("images/BT_ET2.png");
			pressedl = new ImageIcon("images/BT_ET3.png");

			ExitBtn= new JButton(normal);
			ExitBtn.setPressedIcon(pressedl);
			ExitBtn.setRolloverIcon(rollover);

		}
		public void actionPerformed(ActionEvent e){
			if((JButton)e.getSource() == ToGameBtn) window.changePanel(window.gamePanel);
			if((JButton)e.getSource() == ToRankBtn) window.changePanel(window.rankPanel);
			if((JButton)e.getSource() == ToConfigBtn) window.changePanel(window.configPanel);
			if((JButton)e.getSource() == ExitBtn) {
				exit = new ExitDialog(window.contentPane);
				exit.setVisible(true);
			}
		}

	}
	static class ExitDialog extends JDialog implements ActionListener{
		JPanel labelPanel = new JPanel();
		JPanel btnPanel = new JPanel();
		JLabel label = new JLabel("정말로 종료하시겠습니까?");
		JButton yes = new JButton("예");
		JButton no = new JButton("아니오");
		ExitDialog(Container c){
			setTitle("게임 종료");
			setSize(300,200);
			setLayout(new BorderLayout());
			setLocationRelativeTo(c);
			labelPanel.add(label);
			yes.addActionListener(this);
			no.addActionListener(this);
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