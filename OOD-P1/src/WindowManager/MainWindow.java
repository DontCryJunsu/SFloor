package WindowManager;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GamePlayManager.Game;
import SFDesignManager.Design.*;

import java.awt.*;

public class MainWindow extends JFrame{
	public Container contentPane = getContentPane();
	public mainPanel mainPanel = new mainPanel(this);
	public GamePanel gamePanel = new GamePanel(this);
	public RankPanel rankPanel = new RankPanel(this);
	public ConfigPanel configPanel = new ConfigPanel(this);
	Game MyGame = new Game(this);

	public MainWindow(){
		setTitle("Slippery Floor");
		setSize(1215, 630);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setLayout(null);
		setLocationRelativeTo(null);
		//mainPanel.setbutton(ToGameBtn, ToRankBtn, ToConfigBtn, ExitBtn);
	
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("Slippery_floor.png");
		setIconImage(img);

		add(mainPanel);
		setVisible(true);
	}

	public void changePanel(Component c){
		contentPane.removeAll();
		contentPane.add(c);
		contentPane.revalidate();
		contentPane.repaint();
	}





}
