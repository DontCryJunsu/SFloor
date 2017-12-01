package GamePlayManager;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import GamePlayManager.MergedGamePanel.MenuDialog;

public class GamePlaying extends JPanel implements KeyListener, Runnable{ /**������ ĳ���Ϳ� ������Ʈ�� �׸��� Ŭ����*/
	MergedGamePanel mgp;
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
	int pauseCounter = 0;

	int KeyS = 0; /**���� ������*/           
	int stage = 1;              
	Scene2 C = new Scene2(); /**���ѷα� �� Ŭ���� ����*/
	Scene1 B = new Scene1(); /**���ѷα� �� Ŭ���� ����*/
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

	public GamePlaying(MergedGamePanel mgp){
		this.mgp = mgp;
		init();      
		start();
		setSize(f_width, f_height);
	}

	public void init(){ /**������ ���ÿ� �ѹ� ȣ��Ǵ� init�޼ҵ� (�ʱⰪ�� ���)*/
		x = 6*50;
		y = 10*50;  

		f_width = 1200;
		f_height = 600;  
		Player_img = new Image[8]; /**�÷��̾��� ���¿� ���� �ٸ� �̹����� ����*/
		for(int i = 0 ; i < Player_img.length ; ++i){
			Player_img[i] = 
					new ImageIcon("images/P_" + i + ".png").getImage();
		} 
		BWall_img = new ImageIcon("images/BWall.png").getImage();  /**������Ʈ �̹��� ����*/
		DWall_img = new ImageIcon("images/DWall.png").getImage();
		Door_img = new ImageIcon("images/Door.png").getImage();
		Bed_img = new ImageIcon("images/Bed.png").getImage();
		Cab_img = new ImageIcon("images/Cab.png").getImage();
		Key_img = new ImageIcon("images/Key.png").getImage();
		BackGround_img = new ImageIcon("images/background.png").getImage();
	}

	public void start(){   /**������ �޼ҵ�start*/
		addKeyListener(this);

		th = new Thread(this); 
		if(!th.isInterrupted())
			try{
				th.start();
			}catch(Exception k){
				return;
			}
	}

	public void run(){    /**������ �޼ҵ�run*/
		try{ 
			while(!Thread.currentThread().isInterrupted()){
				KeyProcess(); 
				repaint(); 
				KeySpace = false;
				Thread.sleep(45); //������ �ӵ�
				nextScene();
				prevScene();
			}
		}catch (Exception e){}
	}
	public void nextScene() {
		if(stage==1&&ing==0&&KeyS==1&&x==21*50&&y==1*50&&KeySpace==true) {	/**�������� �Ѿ���� �ϴ� ��*/
			Scene1 B= new Scene1();
			keyReset(); 
			player_Status = B.Cs;
			x = B.Cx;
			y = B.Cy;
			stage += 1;
			mgp.getStatusPanel().resetText();
		}else if(stage==1&&ing==0&&KeyS==0&&x==21*50&&y==1*50&&KeySpace==true)	
			mgp.getStatusPanel().printNotEnoughKey();
		if(stage==2&&x==20*50&&y==10*50) {	
			Scene2 C= new Scene2();
			keyReset(); 
			player_Status = C.Cs;
			x = C.Cx;
			y = C.Cy;
			stage += 1;
			mgp.getStatusPanel().resetText();

		}
	}
	public void prevScene() {
		if(stage==2&&x==2*50&&y==10*50) {	
			A.init();
			A.map();
			keyReset(); 
			player_Status = 1;
			x = 21*50;
			y = 2*50;
			stage -= 1;
			mgp.getStatusPanel().resetText();
		}
		if(stage==3&&x==2*50&&y==1*50&&KeySpace==true) {	
			B.init();
			B.map();
			keyReset(); 
			player_Status = 0;
			x = 20*50;
			y = 9*50;
			stage -= 1;
			mgp.getStatusPanel().resetText();
		}
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
					mgp.getStatusPanel().printGetKey();
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

		if(e.getKeyCode()==KeyEvent.VK_SPACE) {	/**�������� �Ѿ���� �ϴ� ��*/
			KeySpace = true;

		}
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE){ /**�޴������� ȣ��*/
			mgp.menu.setVisible(true);
		}
		if(e.getKeyCode()==KeyEvent.VK_P && pauseCounter%2 == 0){
			th.interrupt();
			mgp.getTimer().interrupt();
			th = new Thread(this);
			mgp.setTimer(new Thread(mgp.getTimerPanel()));
			mgp.getStatusPanel().printPause();
			pauseCounter++;
		}else if(e.getKeyCode()==KeyEvent.VK_P && pauseCounter%2 == 1) {
			th.start();
			mgp.getTimer().start();
			mgp.getStatusPanel().resetText();
			pauseCounter = 0;
		}
	}
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
}