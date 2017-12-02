package SFDesignManager;

import javax.swing.*;

import WindowManager.MainWindow;


import java.awt.*;
import java.awt.event.*;
public class Design {

	public static class  MainPanel extends JPanel  implements ActionListener{
		MainWindow window;
		JButton ToGameBtn;
		JButton ToRankBtn;
		JButton ToConfigBtn;
		JButton ExitBtn;
		ExitDialog exit;

		ImageIcon mainIcon = new ImageIcon("images/Slippery_floor.png");
		ImageIcon GameNameIcon = new ImageIcon("images/SF_MAIN.png");
		ImageIcon normal;
		ImageIcon rollover;
		ImageIcon pressedl;

		public void paintComponent(Graphics g){
			super.paintComponent(g);
			Image img = mainIcon.getImage();
			g.drawImage(img, 200, 70, this);
			img = GameNameIcon.getImage();
			g.drawImage(img, 550, 50, this);

		}
		public MainPanel(MainWindow window){
			this.window = window;
			setbutton();
			setmain();
			setBackground(Color.BLACK);
			setLayout(null);
		}
		public void setmain( ){

			ToGameBtn.setLocation(700, 270);
			ToGameBtn.setSize(new Dimension(212,22));
			ToGameBtn.setBackground(Color.white); 
			ToGameBtn.setOpaque(false);
			ToGameBtn.setBorder(null);
			add(ToGameBtn);

			ToRankBtn.setLocation(700, 330);
			ToRankBtn.setSize(new Dimension(212,22));
			ToRankBtn.setBackground(Color.white);
			ToRankBtn.setOpaque(false);
			ToRankBtn.setBorder(null);
			add(ToRankBtn);

			ToConfigBtn.setLocation(700, 390);
			ToConfigBtn.setSize(new Dimension(212,22));
			ToConfigBtn.setBackground(Color.white);
			ToConfigBtn.setOpaque(false);
			ToConfigBtn.setBorder(null);
			add(ToConfigBtn);

			ExitBtn.setLocation(700, 450);
			ExitBtn.setSize(new Dimension(212,22));
			ExitBtn.setBackground(Color.white);
			ExitBtn.setOpaque(false);
			ExitBtn.setBorder(null);
			add(ExitBtn);

			ToGameBtn.addActionListener(this);
			ToRankBtn.addActionListener(this);
			ToConfigBtn.addActionListener(this);
			ExitBtn.addActionListener(this);

		}
		public void setbutton(){

			normal = new ImageIcon("images/BT_GS1.png");
			rollover = new ImageIcon("images/BT_GS2.png");
			pressedl = new ImageIcon("images/BT_GS2.png");
			ToGameBtn= new JButton(normal);
			ToGameBtn.setPressedIcon(pressedl);
			ToGameBtn.setRolloverIcon(rollover);
			ToGameBtn.addMouseListener(new MouseAdapter(){
				public void mouseEntered(MouseEvent e){
					window.sound.play("bt_cursorOn.wav");
				}
				public void mousePressed(MouseEvent e){
					window.sound.play("bt_selected.wav");
				}
			});
			
			normal = new ImageIcon("images/BT_RK1.png");
			rollover = new ImageIcon("images/BT_RK2.png");
			pressedl = new ImageIcon("images/BT_RK2.png");
			ToRankBtn= new JButton(normal);
			ToRankBtn.setPressedIcon(pressedl);
			ToRankBtn.setRolloverIcon(rollover);
			ToRankBtn.addMouseListener(new MouseAdapter(){
				public void mouseEntered(MouseEvent e){
					window.effect.play("bt_cursorOn.wav");
				}
				public void mousePressed(MouseEvent e){
					window.effect.play("bt_selected.wav");
				}
			});
			
			normal = new ImageIcon("images/BT_ST1.png");
			rollover = new ImageIcon("images/BT_ST2.png");
			pressedl = new ImageIcon("images/BT_ST2.png");
			ToConfigBtn= new JButton(normal);
			ToConfigBtn.setPressedIcon(pressedl);
			ToConfigBtn.setRolloverIcon(rollover);
			ToConfigBtn.addMouseListener(new MouseAdapter(){
				public void mouseEntered(MouseEvent e){
					window.effect.play("bt_cursorOn.wav");
				}
				public void mousePressed(MouseEvent e){
					window.effect.play("bt_selected.wav");
				}
			});
			
			normal = new ImageIcon("images/BT_ET1.png");
			rollover = new ImageIcon("images/BT_ET2.png");
			pressedl = new ImageIcon("images/BT_ET2.png");
			ExitBtn= new JButton(normal);
			ExitBtn.setPressedIcon(pressedl);
			ExitBtn.setRolloverIcon(rollover);
			ExitBtn.addMouseListener(new MouseAdapter(){
				public void mouseEntered(MouseEvent e){
					window.effect.play("bt_cursorOn.wav");
				}
				public void mousePressed(MouseEvent e){
					window.effect.play("bt_selected.wav");
				}
			});
			
		}
		
		public void actionPerformed(ActionEvent e){
			if((JButton)e.getSource() == ToGameBtn){
				window.sound.stop();
				System.out.println("?");
				window.sound.loop("BackgroundMusic_tutorial.wav");
				window.changePanel(window.gamePanel);
			}
			if((JButton)e.getSource() == ToRankBtn) {
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