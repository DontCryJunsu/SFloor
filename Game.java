package GamePlayManager;

import javax.swing.*;
import WindowManager.MainWindow;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.image.*;

public class Game extends JPanel implements KeyListener, Runnable{ /**게임의 캐릭터와 오브젝트를 그리는 클래스*/
	MainWindow window;
	MenuFrame menu;
	int f_width ;   /**프레임 크기 */
	int f_height ;

	int x, y;  /**캐릭터 설정값 */
	boolean KeyUp = false;      
	boolean KeyDown = false;
	boolean KeyLeft = false;
	boolean KeyRight = false;
	boolean KeySpace = false;
	int ing; // 움직임 감지
	int player_Status = 1; // 상하좌우 감지
	
	int KeyS = 0; /**열쇠 보유량*/           
	              
	Scene A = new Scene(); /**프롤로그 씬 클래스 생성*/

	Thread th;
	Toolkit tk = Toolkit.getDefaultToolkit();

	Image[] Player_img; /**이미지 생성*/

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

	public void init(){ /**생성과 동시에 한번 호출되는 init메소드 (초기값을 담당)*/
		x = 6*50;
		y = 10*50;  

		f_width = 1200;
		f_height = 600;  
		Player_img = new Image[8]; /**플레이어의 상태에 따른 다른 이미지를 설정*/
		for(int i = 0 ; i < Player_img.length ; ++i){
			Player_img[i] = 
					new ImageIcon("P_" + i + ".png").getImage();
		} 
		BWall_img = new ImageIcon("BWall.png").getImage();  /**오브젝트 이미지 설정*/
		DWall_img = new ImageIcon("DWall.png").getImage();
		Door_img = new ImageIcon("Door.png").getImage();
		Bed_img = new ImageIcon("Bed.png").getImage();
		Cab_img = new ImageIcon("Cab.png").getImage();
		Key_img = new ImageIcon("Key.png").getImage();
		BackGround_img = new ImageIcon("background.png").getImage();
	}

	public void start(){   /**쓰레드 메소드start*/
		addKeyListener(this);

		th = new Thread(this); 
		th.start(); 
	}

	public void run(){    /**쓰레드 메소드run*/
		try{ 
			while(true){
				KeyProcess(); 
				repaint(); 
				Thread.sleep(45); //쓰레드 속도
			}
		}catch (Exception e){}
	}

	public void paint(Graphics g){ /**이미지를 그려주는 paint메소드*/
		buffImage = createImage(f_width, f_height); 
		buffg = buffImage.getGraphics();
		update(g);
	}
	public void update(Graphics g){ /**주기 적으로 호출되는 update메소드*/
		Draw_Background();
		Draw_Player();
		Draw_Wall();
		KeyEat();
		g.drawImage(buffImage, 0, 0, this); 
	}

	public void Draw_Background(){   /**배경그리기 메소드*/
		buffg.drawImage(BackGround_img, 0, 0, this);
	}
	public void Draw_Wall() {  /**오브젝트 그리기 메소드*/
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
	public void Draw_Player(){ /**플레이어 그리기 메소드*/
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
	public void KeyProcess(){ /**받는 키값에 따른 이동과 충돌구현*/
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
	public void KeyEat() { /**열쇠 획득시 발동되는 메소드*/
		for(int i =0;i<24;i++){
			for(int j = 0; j<12;j++){
				if(x == 3*50 && y==2*50&& A.key[i][j] ==1){
					A.key[i][j] = 0;
					KeyS++;
				}
			}
		}
	}
	public void keyReset(){ /**스테이지를 넘어갈시 모든 키값을 초기화*/
		KeyUp = false;
		KeyDown = false;
		KeyLeft = false;
		KeyRight = false;
	}
	public void keyPressed(KeyEvent e){ /**keyListener의 키입력값을 받아서 keyProcess를 설정해줌*/
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

		if(ing==0&&KeyS==1&&x==21*50&&y==2*50&&e.getKeyCode()==KeyEvent.VK_SPACE) {	/**스테이지 넘어가도록 하는 곳*/
			Scene1 B= new Scene1();
			keyReset(); 
			player_Status = 1;
			x = B.Cx;
			y = B.Cy;
		}
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE){ /**메뉴프레임 호출*/
			menu = new MenuFrame();
			menu.setLocationRelativeTo(window.contentPane);
		}
	}
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	
	class AskFrame extends JFrame implements ActionListener{
		JPanel labelPanel = new JPanel();
		JPanel btnPanel = new JPanel();
		JLabel label = new JLabel("주 메뉴로 돌아가시겠습니까?");
		JButton yes = new JButton("예");
		JButton no = new JButton("아니오");
		AskFrame(){
			setTitle("주 메뉴");
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
		JButton ToMain = new JButton("주 메뉴");
		MenuFrame(){
			setTitle("메뉴");
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