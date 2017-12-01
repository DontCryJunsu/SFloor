package WindowManager;

import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import GamePlayManager.MergedGamePanel;
import SFDesignManager.Design.MainPanel;

public class MainWindow extends JFrame{
	public Container contentPane = getContentPane();
	public MainPanel mainPanel = new MainPanel(this);
	public GamePanel gamePanel = new GamePanel(this);
	public RankPanel rankPanel = new RankPanel(this);
	public ConfigPanel configPanel = new ConfigPanel(this);
	public MergedGamePanel mgp = new MergedGamePanel(this);
	//Game MyGame = new Game(this);

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
	}

	public void changePanel(Component c){
		contentPane.removeAll();
		contentPane.add(c);
		contentPane.revalidate();
		contentPane.repaint();
	}





}
