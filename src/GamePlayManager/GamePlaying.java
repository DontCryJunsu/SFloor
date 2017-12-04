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
/**클래스 설명
 * <br>
 * 게임의 전체적인 흐름을 담당하는 패널
 * @author 박상우, 박준수
 * @see javax.swing.JPanel
 */
public class GamePlaying extends JPanel implements KeyListener, Runnable{
	/**통합 패널의 정보를 이용하기 위한 인스턴스*/
	MergedGamePanel mgp;
	/**프롤로그 씬 클래스 생성*/
	Scene2 C = new Scene2(); 
	/**프롤로그 씬 클래스 생성*/
	Scene1 B = new Scene1();
	/**프롤로그 씬 클래스 생성*/
	Scene A = new Scene(); 
	/**게임의 실행을 담당하는 쓰레드*/
	private Thread gamePlayingThread;

	/**플레이어 이미지  배열*/
	Image[] Player_img;
	/**위에서 본 벽 이미지*/
	Image BWall_img;
	/**앞에서 본 벽 이미지*/
	Image DWall_img;
	/**문 이미지*/
	Image Door_img;
	/**배경 이미지*/
	Image BackGround_img;
	/**침대 이미지*/
	Image Bed_img;
	/**서랍장 이미지*/
	Image Cab_img;
	/**열쇠 이미지*/
	Image Key_img;
	Image buffImage;
	Graphics buffg;
	/**프레임 가로 크기 */
	int f_width ;  
	/**프레임 세로 크기*/
	int f_height ;
	/**캐릭터 설정값 */
	int x, y;  
	boolean KeyUp = false;      
	boolean KeyDown = false;
	boolean KeyLeft = false;
	boolean KeyRight = false;
	boolean KeySpace = false;
	/**캐릭터의 움직임 감지*/
	int ing; // 움직임 감지
	/**캐릭터의 상하좌우 감지*/
	int player_Status = 1; // 상하좌우 감지
	/**일시 정지 여부를 알기 위한 카운터*/
	private int pauseCounter = 0;
	/**열쇠 보유량*/ 
	int KeyS = 0;           
	int stage = 1;              
	/**게임 플레잉 패널 초기화
	 * @author 박상우, 박준수
	 * @param mgp 인스턴스로 사용할 통합 패널
	 * */
	public GamePlaying(MergedGamePanel mgp){
		this.mgp = mgp;
		init();      
		start();
		setSize(f_width, f_height);
	}
	/**초기값을 담당하는 생성과 동시에 한번 호출되는 메소드
	 * 플레이어의 상태에 따른 다른 이미지를 설정
	 * 오브젝트 이미지 설정
	 * @author 박준수
	 */
	public void init(){ 
		x = 6*50;
		y = 10*50;  

		f_width = 1200;
		f_height = 600;  

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
	/**키 리스너를 추가하고 게임 플레잉 쓰레드 생성
	 * @author 박상우, 박준수
	 * */
	public void start(){   
		addKeyListener(this);
		gamePlayingThread = new Thread(this); 
	}
	/**쓰레드 메소드 run() 오버라이드
	 * @author 박준수*/
	public void run(){    
		try{ 
			while(!Thread.currentThread().isInterrupted()){
				KeyProcess(); 
				repaint(); 
				KeySpace = false;
				Thread.sleep(45); //쓰레드 속도
				nextScene();
				prevScene();
			}
		}catch (InterruptedException e){return;}
	}
	/**조건에 부합하면 스테이지를 넘어가는 메소드
	 * @author 박상우, 박준수
	 * */
	public void nextScene() {
		if(stage==1&&ing==0&&KeyS==1&&x==21*50&&y==1*50&&KeySpace==true) {	
			Scene1 B= new Scene1();
			keyReset(); 
			player_Status = B.Cs;
			x = B.Cx;
			y = B.Cy;
			stage += 1;
			mgp.getStatusPanel().initText();
		}else if(stage==1&&ing==0&&KeyS==0&&x==21*50&&y==1*50&&KeySpace==true)	
			mgp.getStatusPanel().printNotEnoughKey();
		if(stage==2&&x==20*50&&y==10*50) {	
			Scene2 C= new Scene2();
			keyReset(); 
			player_Status = C.Cs;
			x = C.Cx;
			y = C.Cy;
			stage += 1;
			mgp.getStatusPanel().initText();

		}
	}
	/**조건에 부합하면 이전 스테이지로 돌아가는 메소드
	 * @author 박상우, 박준수
	 * */
	public void prevScene() {
		if(stage==2&&x==2*50&&y==10*50) {	
			A.init();
			A.map();
			keyReset(); 
			player_Status = 1;
			x = 21*50;
			y = 2*50;
			stage -= 1;
			mgp.getStatusPanel().initText();
		}
		if(stage==3&&x==2*50&&y==1*50&&KeySpace==true) {	
			B.init();
			B.map();
			keyReset(); 
			player_Status = 0;
			x = 20*50;
			y = 9*50;
			stage -= 1;
			mgp.getStatusPanel().initText();
		}
	}
	/**이미지를 그려주는 메소드
	 * @author 박준수
	 * */
	public void paint(Graphics g){ 
		buffImage = createImage(f_width, f_height); 
		buffg = buffImage.getGraphics();
		update(g);
	}
	/**주기적으로 호출되는 메소드
	 * @author 박준수
	 * */
	public void update(Graphics g){ 
		Draw_Background();
		Draw_Player();
		Draw_Wall();
		KeyEat();
		g.drawImage(buffImage, 0, 0, this); 
	}
	/**배경그리기 메소드
	 * @author 박준수
	 * */
	public void Draw_Background(){   
		buffg.drawImage(BackGround_img, 0, 0, this);
	}
	/**오브젝트 그리기 메소드
	 * @author 박준수
	 * */
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
	/**플레이어 그리기 메소드
	 * @author 박준수
	 * */
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
	/**받는 키값에 따른 이동과 충돌구현
	 * @author 박준수
	 * */
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
	/**열쇠 획득시 발동되는 메소드
	 * @author 박준수
	 * */
	public void KeyEat() { 
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
	/**모든 키 값을 초기화하는 메소드
	 * @author 박준수
	 * */
	public void keyReset(){ 
		KeyUp = false;
		KeyDown = false;
		KeyLeft = false;
		KeyRight = false;
	}
	/**keyListener의 키 입력값을 받아서 keyProcess를 설정해주는 메소드
	 * @author 박상우, 박준수
	 * */
	public void keyPressed(KeyEvent e){ 
		if(e.getKeyCode()== mgp.getKeySet().getUp() && ing ==0){
			KeyUp = true;
			ing = 1;
		}
		if(e.getKeyCode()== mgp.getKeySet().getDown() && ing ==0){
			KeyDown = true;
			ing = 1;
		}
		if(e.getKeyCode()== mgp.getKeySet().getLeft() && ing ==0){
			KeyLeft = true;
			ing = 1;
		}
		if(e.getKeyCode()== mgp.getKeySet().getRight() && ing ==0){
			KeyRight = true;
			ing = 1;
		}

		if(e.getKeyCode()==KeyEvent.VK_SPACE) {	/**스테이지 넘어가도록 하는 곳*/
			KeySpace = true;

		}
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE){ /**메뉴프레임 호출*/
			mgp.menu.setVisible(true);
			pauseGame();
		}
		if(e.getKeyCode()==KeyEvent.VK_P && pauseCounter%2 == 0){
			pauseGame();
		}
		else if(e.getKeyCode()==KeyEvent.VK_P && pauseCounter%2 == 1) {
			resumeGame();
		}
	}
	/**일시 정지 메소드
	 * @author 박상우
	 * */
	public void pauseGame() {
		gamePlayingThread.interrupt();
		mgp.getTimer().interrupt();
		mgp.getStatusPanel().printPause();
		pauseCounter++;		
	}
	/**게임 재개 메소드
	 * @author 박상우
	 */
	public void resumeGame() {
		gamePlayingThread = new Thread(this);
		mgp.setTimer(new Thread(mgp.getTimerPanel()));
		gamePlayingThread.start();
		mgp.getTimer().start();
		mgp.getStatusPanel().initText();
		pauseCounter = 0;		
	}
	public Thread getGamePlayingThread() {
		return gamePlayingThread;
	}
	public void setGamePlayingThread(Thread th) {
		this.gamePlayingThread = th;
	}
	/**pauseCounter를 0으로 세팅하는 메소드
	 * @author 박상우
	 * */
	public void resetPauseCounter() {
		pauseCounter = 0;
	}
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
}