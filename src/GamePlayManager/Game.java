package GamePlayManager;

import javax.swing.*;
import WindowManager.MainWindow;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.image.*;
/**Class Description of Game
 * <br>
 * 게임의 캐릭터와 오브젝트를 그리는 클래스
 * @author 박준수
 *
 */
public class Game extends JPanel implements KeyListener, Runnable{ 
	/**패널 전환 메소드를 사용하기 위한 MainWindow 인스턴스*/
	MainWindow window;
	/**게임 중 서브 메뉴를 불러오기 위한 인스턴스*/
	MenuFrame menu;
	KeySet key = new KeySet();
	/**프레임의 가로 크기 */
	int f_width ;   
	/**프레임의 세로 크기*/
	int f_height ;
	/**캐릭터 설정값 */
	int x, y;  
	boolean KeyUp = false;      
	boolean KeyDown = false;
	boolean KeyLeft = false;
	boolean KeyRight = false;
	boolean KeySpace = false;
	int ing; // 움직임 감지
	int player_Status = 1; // 상하좌우 감지
	/**열쇠 보유량*/    
	int KeyS = 0;        
	/**프롤로그 씬 클래스 생성*/
	Scene A = new Scene(); 

	Thread th;
	Toolkit tk = Toolkit.getDefaultToolkit();
	/**캐릭터의 이미지 배열 생성*/
	Image[] Player_img; 

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
	/**생성과 동시에 초기 값을 담당하는 한번 호출되는 init메소드*/
	public void init(){ 
		x = 6*50;
		y = 10*50;  

		f_width = 1200;
		f_height = 600;  
		/**플레이어의 상태에 따른 다른 이미지를 설정*/
		Player_img = new Image[8]; 
		for(int i = 0 ; i < Player_img.length ; ++i){
			Player_img[i] = 
					new ImageIcon("images/P_" + i + ".png").getImage();
		} 
		BWall_img = new ImageIcon("images/BWall.png").getImage();
		DWall_img = new ImageIcon("images/DWall.png").getImage();
		Door_img = new ImageIcon("images/Door.png").getImage();
		Bed_img = new ImageIcon("images/Bed.png").getImage();
		Cab_img = new ImageIcon("images/Cab.png").getImage();
		Key_img = new ImageIcon("images/Key.png").getImage();
		BackGround_img = new ImageIcon("images/background.png").getImage();
	}
	/**쓰레드 메소드start*/
	public void start(){   
		addKeyListener(this);

		th = new Thread(this); 
		th.start(); 
	}
	/**쓰레드 메소드run*/
	public void run(){    
		try{ 
			while(true){
				KeyProcess(); 
				repaint(); 
				Thread.sleep(45); //쓰레드 속도
			}
		}catch (Exception e){}
	}
	/**이미지를 그려주는 paint메소드
	 * @param g Graphics
	 * */
	public void paint(Graphics g){ 
		buffImage = createImage(f_width, f_height); 
		buffg = buffImage.getGraphics();
		update(g);
	}
	/**주기 적으로 호출되는 update메소드
	 * @param g Graphics
	 * */
	public void update(Graphics g){ 
		Draw_Background();
		Draw_Player();
		Draw_Wall();
		KeyEat();
		g.drawImage(buffImage, 0, 0, this); 
	}
	/**배경그리기 메소드*/
	public void Draw_Background(){   
		buffg.drawImage(BackGround_img, 0, 0, this);
	}
	/**오브젝트 그리기 메소드*/
	public void Draw_Wall() {  
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
	/**플레이어 그리기 메소드*/
	public void Draw_Player(){ 
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
	/**받는 키값에 따른 이동과 충돌구현*/
	public void KeyProcess(){ 
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
	/**열쇠 획득시 발동되는 메소드*/
	public void KeyEat() { 
		for(int i =0;i<24;i++){
			for(int j = 0; j<12;j++){
				if(x == 3*50 && y==2*50&& A.key[i][j] ==1){
					A.key[i][j] = 0;
					KeyS++;
				}
			}
		}
	}
	/**스테이지를 넘어갈시 모든 키값을 초기화*/
	public void keyReset(){ 
		KeyUp = false;
		KeyDown = false;
		KeyLeft = false;
		KeyRight = false;
	}
	/**keyListener의 키입력값을 받아서 keyProcess를 설정해줌*/
	public void keyPressed(KeyEvent e){ 
		if(e.getKeyCode()==key.myUp && ing ==0){
			KeyUp = true;
			ing = 1;
		}
		if(e.getKeyCode()==key.myDown && ing ==0){
			KeyDown = true;
			ing = 1;
		}
		if(e.getKeyCode()==key.myLeft && ing ==0){
			KeyLeft = true;
			ing = 1;
		}
		if(e.getKeyCode()==key.myRight && ing ==0){
			KeyRight = true;
			ing = 1;
		}

		if(ing==0&&KeyS==1&&x==21*50&&y==2*50&&e.getKeyCode()==KeyEvent.VK_SPACE) {	//스테이지 넘어가도록 하는 곳
			Scene1 B= new Scene1();
			keyReset(); 
			player_Status = 1;
			x = B.Cx;
			y = B.Cy;
		}
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE){ //메뉴프레임 호출
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

	}
	class MenuFrame extends JFrame implements ActionListener{
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