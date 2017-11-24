package GamePlayManager;

import javax.swing.*;
import WindowManager.MainWindow;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.image.*;

public class Game extends JPanel implements KeyListener, Runnable{ /**������ ĳ���Ϳ� ������Ʈ�� �׸��� Ŭ����*/
	MainWindow window;
	MenuFrame menu;
	int f_width ;   /**������ ũ�� */
	int f_height ;

	int x, y;  /**ĳ���� ������ */
	boolean KeyUp = false;      
	boolean KeyDown = false;
	boolean KeyLeft = false;
	boolean KeyRight = false;
	boolean KeySpace = false;
	int ing; // ������ ����
	int player_Status = 1; // �����¿� ����
	
	int KeyS = 0; /**���� ������*/           
	              
	Scene A = new Scene(); /**���ѷα� �� Ŭ���� ����*/

	Thread th;
	Toolkit tk = Toolkit.getDefaultToolkit();

	Image[] Player_img; /**�̹��� ����*/

	Image BWall_img;
	Image DWall_img;
	Image Door_img;
	Image BackGround_img;
	Image Bed_img;
	Image Cab_img;
	Image Key_img;
	Image buffImage;
	Graphics buffg;

	public Game(MainWindow window){
		this.window = window;
		init();      
		start();
		setSize(f_width, f_height);
		Dimension screen = tk.getScreenSize();
	}

	public void init(){ /**������ ���ÿ� �ѹ� ȣ��Ǵ� init�޼ҵ� (�ʱⰪ�� ���)*/
		x = 6*50;
		y = 10*50;  

		f_width = 1200;
		f_height = 600;  
		Player_img = new Image[8]; /**�÷��̾��� ���¿� ���� �ٸ� �̹����� ����*/
		for(int i = 0 ; i < Player_img.length ; ++i){
			Player_img[i] = 
					new ImageIcon("P_" + i + ".png").getImage();
		} 
		BWall_img = new ImageIcon("BWall.png").getImage();  /**������Ʈ �̹��� ����*/
		DWall_img = new ImageIcon("DWall.png").getImage();
		Door_img = new ImageIcon("Door.png").getImage();
		Bed_img = new ImageIcon("Bed.png").getImage();
		Cab_img = new ImageIcon("Cab.png").getImage();
		Key_img = new ImageIcon("Key.png").getImage();
		BackGround_img = new ImageIcon("background.png").getImage();
	}

	public void start(){   /**������ �޼ҵ�start*/
		addKeyListener(this);

		th = new Thread(this); 
		th.start(); 
	}

	public void run(){    /**������ �޼ҵ�run*/
		try{ 
			while(true){
				KeyProcess(); 
				repaint(); 
				Thread.sleep(45); //������ �ӵ�
			}
		}catch (Exception e){}
	}

	public void paint(Graphics g){ /**�̹����� �׷��ִ� paint�޼ҵ�*/
		buffImage = createImage(f_width, f_height); 
		buffg = buffImage.getGraphics();
		update(g);
	}
	public void update(Graphics g){ /**�ֱ� ������ ȣ��Ǵ� update�޼ҵ�*/
		Draw_Background();
		Draw_Player();
		Draw_Wall();
		KeyEat();
		g.drawImage(buffImage, 0, 0, this); 
	}

	public void Draw_Background(){   /**���׸��� �޼ҵ�*/
		buffg.drawImage(BackGround_img, 0, 0, this);
	}
	public void Draw_Wall() {  /**������Ʈ �׸��� �޼ҵ�*/
		for(int i=0;i<24;i++)   {
			for(int j =0;j<12;j++){
				if(A.map[i][j]==2)
					buffg.drawImage(BWall_img,i*50,j*50,this);
			}
		}
		for(int i=0;i<24;i++)   {
			for(int j =0;j<12;j++){
				if(A.map[i][j]==3)
					buffg.drawImage(DWall_img,i*50,j*50,this);
			}
		}
		for(int i=0;i<24;i++)   {
			for(int j =0;j<12;j++){
				if(A.map[i][j]==4)
					buffg.drawImage(Door_img,i*50,j*50,this);
			}
		}
		for(int i=0;i<24;i++)   {
			for(int j =0;j<12;j++){
				if(A.map[i][j]==5)
					buffg.drawImage(Bed_img,i*50,j*50,this);
			}
		}
		for(int i=0;i<24;i++)   {
			for(int j =0;j<12;j++){
				if(A.map[i][j]==6)
					buffg.drawImage(Cab_img,i*50,j*50,this);
			}
		}

		for(int i=0;i<24;i++) {  
			for(int j =0;j<12;j++){
				if(A.key[i][j]==1)
					buffg.drawImage(Key_img,i*50,j*50,this);
			}
		}
	}
	public void Draw_Player(){ /**�÷��̾� �׸��� �޼ҵ�*/
		switch (player_Status){ 
		case 0 :
			if(ing == 0){  
				buffg.drawImage(Player_img[0], x, y, this);

			}else { buffg.drawImage(Player_img[4], x, y, this); }
			break;

		case 1 :
			if(ing== 0){ 
				buffg.drawImage(Player_img[1], x, y, this);
			}else { buffg.drawImage(Player_img[5], x, y, this); }         
			break;

		case 2 :
			if(ing== 0){ 
				buffg.drawImage(Player_img[2], x, y, this);
			}else { buffg.drawImage(Player_img[6], x, y, this); }         
			break;

		case 3:
			if(ing== 0){ 
				buffg.drawImage(Player_img[3], x, y, this);
			}else { buffg.drawImage(Player_img[7], x, y, this); }         
			break;
		}
	}
	public void KeyProcess(){ /**�޴� Ű���� ���� �̵��� �浹����*/
		if(KeyUp == true&& A.map[x/50][y/50-1]==0) {
			y -= 50;
			KeyDown = false;
			KeyLeft = false;
			KeyRight = false;
			player_Status = 0;
		}
		else if(KeyDown == true&& A.map[x/50][y/50+1]==0) {
			y += 50;
			KeyUp = false;
			KeyLeft = false;
			KeyRight = false;
			player_Status = 1;
		}
		else if(KeyLeft == true&& A.map[x/50-1][y/50]==0) {
			x -= 50;
			KeyUp = false;
			KeyDown = false;
			KeyRight = false;
			player_Status = 2;
		}
		else if(KeyRight == true  && A.map[x/50+1][y/50]==0) {
			x += 50;
			KeyUp = false;
			KeyDown = false;
			KeyLeft = false;
			player_Status = 3;
		}
		else{
			ing = 0;
		}
	}
	public void KeyEat() { /**���� ȹ��� �ߵ��Ǵ� �޼ҵ�*/
		for(int i =0;i<24;i++){
			for(int j = 0; j<12;j++){
				if(x == 3*50 && y==2*50&& A.key[i][j] ==1){
					A.key[i][j] = 0;
					KeyS++;
				}
			}
		}
	}
	public void keyReset(){ /**���������� �Ѿ�� ��� Ű���� �ʱ�ȭ*/
		KeyUp = false;
		KeyDown = false;
		KeyLeft = false;
		KeyRight = false;
	}
	public void keyPressed(KeyEvent e){ /**keyListener�� Ű�Է°��� �޾Ƽ� keyProcess�� ��������*/
		if(e.getKeyCode()==KeyEvent.VK_UP && ing ==0){
			KeyUp = true;
			ing = 1;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN && ing ==0){
			KeyDown = true;
			ing = 1;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT && ing ==0){
			KeyLeft = true;
			ing = 1;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT && ing ==0){
			KeyRight = true;
			ing = 1;
		}

		if(ing==0&&KeyS==1&&x==21*50&&y==2*50&&e.getKeyCode()==KeyEvent.VK_SPACE) {	/**�������� �Ѿ���� �ϴ� ��*/
			Scene1 B= new Scene1();
			keyReset(); 
			player_Status = 1;
			x = B.Cx;
			y = B.Cy;
		}
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE){ /**�޴������� ȣ��*/
			menu = new MenuFrame();
			menu.setLocationRelativeTo(window.contentPane);
		}
	}
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	
	class AskFrame extends JFrame implements ActionListener{
		JPanel labelPanel = new JPanel();
		JPanel btnPanel = new JPanel();
		JLabel label = new JLabel("�� �޴��� ���ư��ðڽ��ϱ�?");
		JButton yes = new JButton("��");
		JButton no = new JButton("�ƴϿ�");
		AskFrame(){
			setTitle("�� �޴�");
			setSize(300,200);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLayout(new BorderLayout());
			labelPanel.add(label);
			yes.addActionListener(this);
			no.addActionListener(this);
			btnPanel.add(yes);
			btnPanel.add(no);
			add(labelPanel, BorderLayout.NORTH);
			add(btnPanel, BorderLayout.CENTER);
			setVisible(true);
		}
		public void actionPerformed(ActionEvent e){
			if((JButton)e.getSource() == yes){ 
				window.changePanel(window.mainPanel);
				dispose();
				menu.dispose();
			}
			else dispose();
		}

	}class MenuFrame extends JFrame implements ActionListener{
		JButton ToMain = new JButton("�� �޴�");
		MenuFrame(){
			setTitle("�޴�");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setSize(300, 400);
			setLayout(new FlowLayout());
			ToMain.addActionListener(this);
			add(ToMain);
			setVisible(true);
		}
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == ToMain){
				AskFrame ask = new AskFrame();
				ask.setLocationRelativeTo(getContentPane());
			}
		}
	}
}