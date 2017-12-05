package SFDesignManager;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.event.*;
import java.lang.Thread;

import WindowManager.MainWindow;
/**Ŭ���� ����
 * <br>
 * ���� ������ ����ϴ� �г�
 * @author ȫ����
 */
public class EndingPanel extends JPanel implements Runnable {
	MainWindow window;
	Sound type = new Sound("sounds/Typing.wav");
	ImageIcon logo;
	ImageIcon typing = new ImageIcon("images/ending/SF_typing.png");
	ImageIcon clickTonext = new ImageIcon("images/ending/click anywhere.png");

	int location_x = 30, location_y=200, count = 0, i =40, sleeptime; // count : ���̵���, Ÿ���� ȿ�� ����1, i : Ÿ���� ȿ�� ����2 sleeptime : ���̵��ΰ� �����̵� ȿ�� ���� ����
	Thread th;

	public EndingPanel(MainWindow window){
		this.window = window;
		setLayout(null);
		setBackground(Color.BLACK);
		setVisible(true);
		// setLocationRelativeTo(window.contentPane);
		start();
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				th.interrupt();
			}
		});

	}
	// private void setLocationRelativeTo(Container contentPane) {}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Image img;
		// 21���� ���̵��� ȿ���� �ִ� �̹��� ���. 

		if(count<22){

			sleeptime = 40;
			logo = new ImageIcon("images/ending/SF_"+count+".png"); 
			img = logo.getImage();
			g.drawImage(img, location_x, location_y , this);
			count++;
		}

		// 0.5�ʵ��� �ΰ� ���
		else if(count==22){
			sleeptime = 500;
			img = logo.getImage();
			g.drawImage(img, location_x, location_y , this);
			count++; // ���� Ÿ���� ȿ���� ���� ���� ���� ����
		}
		// ���� �����̵� ȿ��-�ΰ� ����
		else if(location_y<270){
			sleeptime = 10;
			img = logo.getImage();
			g.drawImage(img, location_x, location_y , this);
			location_y++;
		}
		// �ΰ� ������ �����ִ� ����, Ű ������ ���.
		else if(location_y==270) {
			img = logo.getImage();
			g.drawImage(img, location_x, location_y , this);
			// ���� ��������
			if(count%2 == 1 && count < 28){
				img = typing.getImage();
				g.drawImage(img, location_x, location_y-180 , this);
				sleeptime = 400;
				if(count==27) type.play();// �� ���� ����
			}
			else if(count >= 28 && count < 70){

				sleeptime = 100;
				typing = new ImageIcon("images/ending/SF_typing"+(count-27)+".png");
				img = typing.getImage();
				g.drawImage(img, location_x, location_y-200 , this);
			}
			else if( count > 69 ) {
				sleeptime = 400;
				i = (i==41)?40:41;
				typing = new ImageIcon("images/ending/SF_typing"+i+".png");
				img = typing.getImage();
				g.drawImage(img, location_x, location_y-200 , this);
				img = clickTonext.getImage();
				g.drawImage(img, location_x+270, location_y+150 , this);

			}
			count++;
		}
	}

	/**Runnable �޼ҵ� run() �������̵�*/
	public void run(){    
		try{ 
			while(true){
				repaint();
				Thread.sleep(sleeptime); //������ �ӵ�
			}
		}catch (InterruptedException e){
			window.changePanel(window.mainPanel);
			window.Bgm.stop();
			window.Bgm.loop("sounds/BackgroundMusic_main.wav", Clip.LOOP_CONTINUOUSLY);
			count = 0;
			return;
		}
	}
	/**������ ��ü �ʱ�ȭ �� ����*/
	public void start(){
		th = new Thread(this); 
		th.start(); 
	}
}