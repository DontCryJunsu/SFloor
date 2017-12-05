package GamePlayManager;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import SFDesignManager.EndingPanel;
/**Ŭ���� ����
 * <br>
 * ������ ��ü���� �帧�� ����ϴ� �г�
 * @author �ڻ��, ���ؼ�
 * @see javax.swing.JPanel
 */
public class GamePlaying extends JPanel implements KeyListener, Runnable{
	/**���� �г��� ������ �̿��ϱ� ���� �ν��Ͻ�*/
	MergedGamePanel mgp;
	/**6�������� �� Ŭ���� ����*/
	Scene6 G = new Scene6(); 
	/**5�������� �� Ŭ���� ����*/
	Scene5 F = new Scene5(); 
	/**4�������� �� Ŭ���� ����*/
	Scene4 E = new Scene4();
	/**3�������� �� Ŭ���� ����*/
	Scene3 D = new Scene3();
	/**2�������� �� Ŭ���� ����*/
	Scene2 C = new Scene2();
	/**1�������� �� Ŭ���� ����*/
	Scene1 B = new Scene1();
	/**���ѷα� �� Ŭ���� ����*/
	Scene A = new Scene();
	/**������ ������ ����ϴ� ������*/
	private Thread gamePlayingThread;
	/**�÷��̾� �̹���  �迭*/
	private Image[] Player_img;
	/**������ �� �� �̹���*/
	private Image BWall_img;
	/**�տ��� �� �� �̹���*/
	private Image DWall_img;
	/**�� �̹���*/
	private Image Door_img;
	/**��� �̹���*/
	private Image BackGround_img;
	/**ħ�� �̹���*/
	private Image Bed_img;
	/**������ �̹���*/
	private Image Cab_img;
	/**���� �̹���*/
	private Image Key_img;
	/**�� �̹���*/
	private Image Flower_img;
	/**å�� �̹���*/
	private Image Desk_img;
	/**���� �̹���*/
	private Image ICE_img;
	/**������ �� �̹���*/
	private Image DoorUp_img;
	/**���ʹ��� �� �̹���*/
	private Image DoorLeft_img;
	/**������ ���� �� �̹���*/
	private Image DoorRight_img;
	/**���̺� �̹���*/
	private Image Table_img;
	/**�������� �̹���*/
	private Image[] safe_img;
	private Image buffImage;
	Graphics buffg;
	/**������������ �ƴ��� �˻�*/
	boolean safe = false;
	/**������ ���� ũ�� */
	int f_width ;  
	/**������ ���� ũ��*/
	int f_height ;
	/**ĳ���� ������ */
	int x, y;  
	boolean KeyUp = false;      
	boolean KeyDown = false;
	boolean KeyLeft = false;
	boolean KeyRight = false;
	boolean KeySpace = false;
	/**ĳ������ ������ ����*/
	private int ing; // ������ ����
	/**ĳ������ �����¿� ����*/
	int player_Status = 1; // �����¿� ����
	/**�Ͻ� ���� ���θ� �˱� ���� ī����*/
	private int pauseCounter = 0;
	/**���� ������*/ 
	int KeyS = 0;           
	int stage = 1;              
	/**���� �÷��� �г� �ʱ�ȭ
	 * @author �ڻ��, ���ؼ�
	 * @param mgp �ν��Ͻ��� ����� ���� �г�
	 * */
	public GamePlaying(MergedGamePanel mgp){
		this.mgp = mgp;
		init();      
		start();
		setSize(f_width, f_height);
	}
	/**�ʱⰪ�� ����ϴ� ������ ���ÿ� �ѹ� ȣ��Ǵ� �޼ҵ�
	 * �÷��̾��� ���¿� ���� �ٸ� �̹����� ����
	 * ������Ʈ �̹��� ����
	 * @author ���ؼ�
	 */
	public void init(){ 
		x = 6*50;
		y = 10*50;  

		f_width = 1200;
		f_height = 600;  
		Player_img = new Image[8]; 
		safe_img = new Image[9];
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
		Flower_img = new ImageIcon("images/flower.png").getImage();
		Desk_img = new ImageIcon("images/desk.png").getImage();
		ICE_img = new ImageIcon("images/ICE.png").getImage();
		Table_img = new ImageIcon("images/Table.png").getImage();
		DoorLeft_img = new ImageIcon("images/DoorLeft.png").getImage();
		DoorRight_img = new ImageIcon("images/DoorRight.png").getImage();
		for(int i = 0 ; i < safe_img.length ; ++i){
			safe_img[i] = 
					new ImageIcon("images/S" + i + ".png").getImage();
		} 
		DoorUp_img = new ImageIcon("images/DoorUp.png").getImage();
		BackGround_img = new ImageIcon("images/background.png").getImage();
	}
	/**Ű �����ʸ� �߰��ϰ� ���� �÷��� ������ ����
	 * @author �ڻ��, ���ؼ�
	 * */
	public void start(){   
		addKeyListener(this);
		gamePlayingThread = new Thread(this); 
	}
	/**������ �޼ҵ� run() �������̵�
	 * @author ���ؼ�*/
	public void run(){    
		try{ 
			while(!Thread.currentThread().isInterrupted()){
				KeyProcess(); 
				repaint(); 
				KeySpace = false;
				Thread.sleep(45); //������ �ӵ�
				nextScene();
				prevScene();
			}
		}catch (InterruptedException e){return;}
	}
	/**���ǿ� �����ϸ� ���������� �Ѿ�� �޼ҵ�
	 * @author �ڻ��, ���ؼ�
	 * */
	public void nextScene() {
		if(stage==1&&ing==0&&KeyS==1&&x==21*50&&y==1*50&&KeySpace==true) {
			B.init();
			B.map();
			keyReset(); 
			player_Status = B.Cs;
			x = B.Cx;
			y = B.Cy;
			stage += 1;
			mgp.getStatusPanel().initText();
			mgp.window.effect.loop("sounds/Close-Door.wav",0);
		}else if(stage==1&&ing==0&&KeyS==0&&x==21*50&&y==1*50&&KeySpace==true)	{
			mgp.getStatusPanel().printNotEnoughKey();
			mgp.window.effect.play("sounds/noKey.wav");
		}
		if(stage==2&&x==20*50&&y==10*50) {	
			C.init();
			C.map();
			keyReset(); 
			player_Status = C.Cs;
			x = C.Cx;
			y = C.Cy;
			stage += 1;
			mgp.getStatusPanel().initText();
			mgp.window.effect.loop("sounds/Close-Door.wav",0);
		}
		if(stage==3&&x==22*50&&y==6*50) {	
			D.init();
			D.map();
			//keyReset(); 
			player_Status = D.Cs;
			x = D.Cx;
			y = D.Cy;
			stage += 1;
			if(KeyS<=2)
				D.key[21][2] = 1;
			mgp.getStatusPanel().initText();
			mgp.window.effect.loop("sounds/Close-Door.wav",0);
		}
		if(stage==4&&x==22*50&&y==6*50) {	
			E.init();
			E.map();
			//keyReset(); 
			player_Status = E.Cs;
			x = E.Cx;
			y = E.Cy;
			stage += 1;
			if(KeyS==1)
				E.key[20][9] = 1;
			mgp.getStatusPanel().initText();
			mgp.window.effect.loop("sounds/Close-Door.wav",0);
		}
		if(stage==3&&x==13*50&&y==10*50&&KeyS==3&&KeySpace==true) {	
			F.init();
			F.map();
			//keyReset(); 
			player_Status = F.Cs;
			x = F.Cx;
			y = F.Cy;
			stage = 6;
			mgp.getStatusPanel().initText();
			mgp.window.effect.loop("sounds/Close-Door.wav",0);
		}
		else if(stage==3&&x==13*50&&y==10*50&&KeyS<3&&KeySpace==true)	{
			mgp.getStatusPanel().printNotEnoughKey();
			mgp.window.effect.play("sounds/noKey.wav");
		}
		if(stage==6&&x==22*50&&y==9*50&&KeyS==3) {	
			G.init();
			G.map();
			keyReset(); 
			player_Status = G.Cs;
			x = G.Cx;
			y = G.Cy;
			stage += 1;
			G.key[16][6] = 1;
			mgp.getStatusPanel().initText();
			mgp.window.effect.loop("sounds/Close-Door.wav",0);
		}
		if(stage==7&&x==22*50&&y==8*50&&KeyS==4&&KeySpace==true) {	
			// ���Ⱑ ������ �������� �����κ���!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			mgp.window.effect.loop("sounds/Close-Door.wav",0);

			if(mgp.window.rankPanel.getRank()[9].score > mgp.getTimerPanel().getCount()) {
				mgp.window.rankPanel.gnd.setVisible(true);
				mgp.getTimer().interrupt();
				mgp.getGamePlaying().getGamePlayingThread().interrupt();
				mgp.getStatusPanel().initText();
				mgp.setTimer(new Thread(mgp.getTimerPanel()));
				mgp.getGamePlaying().setGamePlayingThread(new Thread(mgp.getGamePlaying()));
				mgp.getGamePlaying().resetPauseCounter();
			}
			else {
				mgp.getTimer().interrupt();
				mgp.getGamePlaying().getGamePlayingThread().interrupt();
				mgp.getStatusPanel().initText();
				mgp.setTimer(new Thread(mgp.getTimerPanel()));
				mgp.getGamePlaying().setGamePlayingThread(new Thread(mgp.getGamePlaying()));
				mgp.getGamePlaying().resetPauseCounter();
				mgp.getTimerPanel().resetCount();
				mgp.resetGame();	
				mgp.window.changePanel(new EndingPanel(mgp.window));
				mgp.window.Bgm.stop();
				mgp.window.Bgm.loop("sounds/BackgroundMusic_ending.wav", Clip.LOOP_CONTINUOUSLY);
			}
		}
		else if(stage==7&&x==22*50&&y==8*50&&KeyS<4&&KeySpace==true)	{
			mgp.getStatusPanel().printNotEnoughKey();
			mgp.window.effect.play("sounds/noKey.wav");
		}
	}
	/**���ǿ� �����ϸ� ���� ���������� ���ư��� �޼ҵ�
	 * @author �ڻ��, ���ؼ�
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
			mgp.window.effect.loop("sounds/Close-Door.wav",0);
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
			mgp.window.effect.loop("sounds/Close-Door.wav",0);
		}
		if(stage==4&&x==1*50&&y==6*50) {	
			C.init();
			C.map();
			keyReset(); 
			player_Status = 2;
			x = 21*50;
			y = 6*50;
			stage -= 1;
			mgp.getStatusPanel().initText();
			mgp.window.effect.loop("sounds/Close-Door.wav",0);
		}
		if(stage==5&&x==1*50&&y==6*50&&(KeyS==2||KeyS==3)) {	
			D.init();
			D.map();
			keyReset(); 
			player_Status = 2;
			x = 21*50;
			y = 6*50;
			stage -= 1;
			if(KeyS<=2)
				D.key[21][2] = 1;
			mgp.getStatusPanel().initText();
			mgp.window.effect.loop("sounds/Close-Door.wav",0);
		}
		else if(stage==5&&x==1*50&&y==6*50&&KeyS<2&&KeySpace==true)	{
			mgp.getStatusPanel().printNotEnoughKey();
			mgp.window.effect.play("sounds/noKey.wav");
		}
		if(stage==6&&x==2*50&&y==1*50&&KeyS==3) {	
			C.init();
			C.map();
			keyReset(); 
			player_Status = 0;
			x = 13*50;
			y = 9*50;
			stage = 3;
			mgp.getStatusPanel().initText();
			mgp.window.effect.loop("sounds/Close-Door.wav",0);
		}
		if(stage==7&&x==1*50&&y==3*50&&KeyS==3) {	
			F.init();
			F.map();
			keyReset(); 
			player_Status = 2;
			x = 21*50;
			y = 9*50;
			stage -= 1;
			mgp.getStatusPanel().initText();
			mgp.window.effect.loop("sounds/Close-Door.wav",0);
		}
	}
	/**�̹����� �׷��ִ� �޼ҵ�
	 * @author ���ؼ�
	 * */
	public void paint(Graphics g){ 
		buffImage = createImage(f_width, f_height); 
		buffg = buffImage.getGraphics();
		update(g);
	}
	/**�ֱ������� ȣ��Ǵ� �޼ҵ�
	 * @author ���ؼ�
	 * */
	public void update(Graphics g){ 
		Drawing_Background();
		Drawing_Wall();
		Drawing_Player();
		safeZone();
		KeyEating();
		g.drawImage(buffImage, 0, 0, this); 
	}
	/**���׸��� �޼ҵ�
	 * @author ���ؼ�
	 * */
	public void Drawing_Background(){   
		buffg.drawImage(BackGround_img, 0, 0, this);
	}
	/**������Ʈ �׸��� �޼ҵ�
	 * @author ���ؼ�
	 * */
	public void Drawing_Wall() {
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
				for(int k=0;k<9;k++)
					if(A.safe[i][j]==k)
						buffg.drawImage(safe_img[k],i*50,j*50,this);
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
		for(int i=0;i<24;i++)   {
			for(int j =0;j<12;j++){
				if(A.map[i][j]==7)
					buffg.drawImage(ICE_img,i*50,j*50,this);
			}
		}
		for(int i=0;i<24;i++)   {
			for(int j =0;j<12;j++){
				if(A.map[i][j]==8)
					buffg.drawImage(DoorUp_img,i*50,j*50,this);
			}
		}
		for(int i=0;i<24;i++)   {
			for(int j =0;j<12;j++){
				if(A.map[i][j]==9)
					buffg.drawImage(DoorLeft_img,i*50,j*50,this);
			}
		}
		for(int i=0;i<24;i++)   {
			for(int j =0;j<12;j++){
				if(A.map[i][j]==10)
					buffg.drawImage(DoorRight_img,i*50,j*50,this);
			}
		}
		for(int i=0;i<24;i++)   {
			for(int j =0;j<12;j++){
				if(A.map[i][j]==11)
					buffg.drawImage(Table_img,i*50,j*50,this);
			}
		}
		for(int i=0;i<24;i++)   {
			for(int j =0;j<12;j++){
				if(A.map[i][j]==12)
					buffg.drawImage(Flower_img,i*50,j*50,this);
			}
		}
		for(int i=0;i<24;i++)   {
			for(int j =0;j<12;j++){
				if(A.map[i][j]==13)
					buffg.drawImage(Desk_img,i*50,j*50,this);
			}
		}
	}
	/**�÷��̾� �׸��� �޼ҵ�
	 * @author ���ؼ�
	 * */
	public void Drawing_Player(){ 
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
	/**�޴� Ű���� ���� �̵��� �浹����
	 * @author ���ؼ�
	 * */
	public void KeyProcess(){ /**�޴� Ű���� ���� �̵��� �浹����*/
		if(safe==false) {
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
		else if(safe==true) {
			if(KeyUp == true&& A.map[x/50][y/50-1]==0) {
				if(A.safe[x/50][y/50-1]!=-1) {
					y -= 50;
					keyReset();
					player_Status = 0;
				}
				else
				{
					player_Status = 0;
					y -= 50;
					KeyUp = true;
					ing = 1;
				}	
			}
			if(KeyDown == true&& A.map[x/50][y/50+1]==0) {
				if(A.safe[x/50][y/50+1]!=-1) {
					y += 50;
					keyReset();
					player_Status = 1;
				}
				else
				{
					player_Status = 1;
					y += 50;
					KeyDown = true;
					ing = 1;
				}	
			}
			if(KeyLeft == true&& A.map[x/50-1][y/50]==0) {
				if(A.safe[x/50-1][y/50]!=-1) {
					x -= 50;
					keyReset();
					player_Status = 2;
				}
				else
				{
					player_Status = 2;
					x -= 50;
					KeyLeft = true;
					ing = 1;
				}	
			}
			if(KeyRight == true&& A.map[x/50+1][y/50]==0) {
				if(A.safe[x/50+1][y/50]!=-1) {
					x += 50;
					keyReset();
					player_Status = 3;
				}
				else
				{
					player_Status = 3;
					x += 50;
					KeyRight = true;
					ing = 1;
				}	
			}
		}
	}
	/**���� ȹ��� �ߵ��Ǵ� �޼ҵ�
	 * @author ���ؼ�
	 * */
	public void KeyEating() {
		for(int i =0;i<24;i++){
			for(int j = 0; j<12;j++){
				if(stage==1&&x == 3*50 && y==2*50&& A.key[i][j] ==1){
					A.key[i][j] = 0;
					KeyS++;
					mgp.getStatusPanel().printGetKey();
					mgp.window.effect.play("sounds/getKey.wav");
				}
				if(stage==5&&x == 20*50 && y==9*50&& A.key[i][j] ==1){
					A.key[i][j] = 0;
					KeyS++;
					mgp.getStatusPanel().printGetKey();
					mgp.window.effect.play("sounds/getKey.wav");
				}
				if(stage==4&&x == 21*50 && y==2*50&& A.key[i][j] ==1){
					A.key[i][j] = 0;
					KeyS++;
					mgp.getStatusPanel().printGetKey();
					mgp.window.effect.play("sounds/getKey.wav");
				}
				if(stage==7&&x == 16*50 && y==6*50&& A.key[i][j] ==1){
					A.key[i][j] = 0;
					KeyS++;
					mgp.getStatusPanel().printGetKey();
					mgp.window.effect.play("sounds/getKey.wav");
				}
			}
		}
	}
	/**�������� ����� �ߵ��Ǵ� �޼ҵ�
	 * @author ���ؼ�
	 * */
	public void safeZone() {
		for(int i =0;i<24;i++){
			for(int j = 0; j<12;j++){
				if(A.safe[i][j]!=-1) {
					if(stage==1&&x == 6*50 && y==9*50){
						stoping();
					}
					else if(stage==1&&x == 6*50 && y==10*50){
						stoping();
					}
					else if(stage==1&&x == 6*50 && y==10*50){
						stoping();
					}
					else if(stage==6&&x == 2*50 && y==3*50){
						stoping();
					}
					else if(stage==6&&x == 3*50 && y==3*50){
						stoping();
					}
					else if(stage==6&&x == 4*50 && y==3*50){
						stoping();
					}
					else if(stage==6&&x == 2*50 && y==4*50){
						stoping();
					}
					else if(stage==6&&x == 3*50 && y==4*50){
						stoping();
					}
					else if(stage==6&&x == 4*50 && y==4*50){
						stoping();
					}
					else if(stage==6&&x == 2*50 && y==5*50){
						stoping();
					}
					else if(stage==6&&x == 3*50 && y==5*50){
						stoping();
					}
					else if(stage==6&&x == 4*50 && y==5*50){
						stoping();
					}
					else if(stage==6&&x == 11*50 && y==7*50){
						stoping();
					}
					else if(stage==6&&x == 11*50 && y==8*50){
						stoping();
					}
					else if(stage==6&&x == 19*50 && y==8*50){
						stoping();
					}
					else if(stage==6&&x == 19*50 && y==9*50){
						stoping();
					}
					else if(stage==7&&x == 4*50 && y==3*50){
						stoping();
					}
					else if(stage==7&&x == 5*50 && y==3*50){
						stoping();
					}
					else if(stage==7&&x == 4*50 && y==4*50){
						stoping();
					}
					else if(stage==7&&x == 5*50 && y==4*50){
						stoping();
					}
					else if(stage==7&&x == 4*50 && y==6*50){
						stoping();
					}
					else if(stage==7&&x == 5*50 && y==6*50){
						stoping();
					}
					else if(stage==7&&x == 4*50 && y==7*50){
						stoping();
					}
					else if(stage==7&&x == 5*50 && y==7*50){
						stoping();
					}
					else if(stage==7&&x == 11*50 && y==5*50){
						stoping();
					}
					else if(stage==7&&x == 12*50 && y==5*50){
						stoping();
					}
					else if(stage==7&&x == 11*50 && y==6*50){
						stoping();
					}
					else if(stage==7&&x == 12*50 && y==8*50){
						stoping();
					}
					else if(stage==7&&x == 12*50 && y==9*50){
						stoping();
					}
					else if(stage==7&&x == 13*50 && y==9*50){
						stoping();
					}
					else 
						safe = false;
				}
			}
		}
	}
	/**�÷��̾ ���ڸ��� ���������ִ� �޼ҵ�
	 * @author ���ؼ�
	 * */
	public void stoping() {
		safe=true;
		keyReset();
		ing=0;
	}
	/**��� Ű ���� �ʱ�ȭ�ϴ� �޼ҵ�
	 * @author ���ؼ�
	 * */
	public void keyReset(){

		KeyUp = false;
		KeyDown = false;
		KeyLeft = false;
		KeyRight = false;
	}
	/**keyListener�� Ű �Է°��� �޾Ƽ� keyProcess�� �������ִ� �޼ҵ�
	 * @author �ڻ��, ���ؼ�
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

		if(e.getKeyCode()==KeyEvent.VK_SPACE) {	
			KeySpace = true;

		}
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE){ 
			mgp.menu.setVisible(true);
			pauseGame();
			pauseCounter = 1;
		}
		if(e.getKeyCode()==KeyEvent.VK_P && pauseCounter%2 == 0){
			pauseGame();
		}
		else if(e.getKeyCode()==KeyEvent.VK_P && pauseCounter%2 == 1) {
			resumeGame();
		}
	}
	/**�Ͻ� ���� �޼ҵ�
	 * @author �ڻ��
	 * */
	public void pauseGame() {
		gamePlayingThread.interrupt();
		mgp.getTimer().interrupt();
		mgp.getStatusPanel().printPause();
		pauseCounter++;
		mgp.window.effect.loop("sounds/pause.wav",0);
	}
	/**���� �簳 �޼ҵ�
	 * @author �ڻ��
	 */
	public void resumeGame() {
		gamePlayingThread = new Thread(this);
		mgp.setTimer(new Thread(mgp.getTimerPanel()));
		gamePlayingThread.start();
		mgp.getTimer().start();
		mgp.getStatusPanel().initText();
		pauseCounter = 0;
		mgp.window.effect.loop("sounds/resume.wav",0);
	}
	public Thread getGamePlayingThread() {
		return gamePlayingThread;
	}
	public void setGamePlayingThread(Thread th) {
		this.gamePlayingThread = th;
	}
	/**pauseCounter�� 0���� �����ϴ� �޼ҵ�
	 * @author �ڻ��
	 * */
	public void resetPauseCounter() {
		pauseCounter = 0;
	}
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
}