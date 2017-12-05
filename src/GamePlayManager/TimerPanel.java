package GamePlayManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**클래스 설명
 * <br>
 * 게임실행 시간을 출력할 패널
 * <br>
 * 게임이 종료되면 이 클래스의 시간 값을 이용하여 랭크와 연동
 * @author 박상우, 홍예진
 * @see javax.swing.JPanel
 */
public class TimerPanel extends JPanel implements Runnable{
	/**시간을 출력할 텍스트라벨, 타이머 담당*/
	private JLabel timerLabel = new JLabel();
	/**증가될 시간 값*/
	private int count = 0;
	/**타이머 이미지를 읽어들일 이미지아이콘 객체*/
	ImageIcon time[] = new ImageIcon[7];
	/**타이머 패널의 배경이미지를 받을 이미지 객체*/
	Image img_p;
	/**타이머 이미지를 받을 이미지 객체*/
	Image img[] = new Image[7];
	/**타이머 패널 초기화
	 * @author 박상우, 홍예진
	 * */
	public TimerPanel(){
		setSize(200, 720);
		setLocation(1200, 0);
		setLayout(null);
		setBackground(Color.DARK_GRAY);
		initLabel();
	}
	/**필요한 라벨을 초기화하고 패널에 추가
	 * @author 박상우
	 */
	public void initLabel() {
		timerLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC | Font.BOLD, 20));
		timerLabel.setForeground(Color.WHITE);
		timerLabel.setLocation(20,120);
		timerLabel.setSize(200,120);
		timerLabel.setText("시간 : ");
		add(timerLabel);
	}
	/**스톱워치(타이머) 이미지 셋팅
	 * @author 홍예진
	 * */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		ImageIcon Timeback = new ImageIcon("images/Time_background.png"); 
		img_p = Timeback.getImage();
		g.drawImage(img_p, 0, 0, this);
		setCountImage();
		g.drawImage(img[0], 130, 200, this);
		g.drawImage(img[1], 115, 200, this);
		g.drawImage(img[6], 100, 200, this);
		g.drawImage(img[2], 85, 200, this);
		g.drawImage(img[3], 70, 200, this);
		g.drawImage(img[6], 55, 200, this);
		g.drawImage(img[4], 40, 200, this);
		g.drawImage(img[5], 25, 200, this);
	}
	/**Runnable run() 메소드 구현
	 * <br>
	 * 쓰레드 인터럽트 발생시 리턴
	 * @author 박상우
	 * */
	public void run() {
		while(!Thread.currentThread().isInterrupted()){
			repaint();
			count++;
			try{
				Thread.sleep(10);
			}catch(InterruptedException e){return;}
		}
	}
	/**시간을 출력하는 count값을 받아 한자리수마다 그림으로 저장하는 메소드
	 * @author 홍예진
	 * */
	private void setCountImage(){
		int decimal[] = new int[7];
		if(count ==0){
			decimal[0] = decimal[1] =decimal[2] = decimal[3] = decimal[4] = decimal[5] = decimal[6] = 0;	}
		else{
		decimal[0] = count%10; // decimal[0]에 1의 자릿수 저장 - 0.01초
		decimal[1] = count/10 - (count/100)*10; // decimal[1]에 10의 자릿수 저장 - 0.1초
		decimal[2] = count/100 - (count/1000)*10; // decimal[2]에 100의 자릿수 저장 - 1초
		decimal[3] = (count%6000)/1000; // decimal[3]에 - 10초-> 10초 단위 자르기
		decimal[4] = count/6000-(count/60000)*10; // decimal[4]에 -> 1분단위 자르기
		decimal[5] =(count%360000)/60000; // decimal[5]에 -> 10분단위 자르기
		decimal[6] = 10; // 콜론 담당
		}
		// 1000=1초, 60,000 = 60초. 100,000 = 100초 = 1분 40초
		for(int i=0; i<7; i++){
			time[i] = new ImageIcon("images/time_"+decimal[i]+".png");
			img[i] = time[i].getImage();
		}
	}
	/**게임 중 메뉴로 돌아가거나 게임이 끝났을 때 카운트 값을 리셋하는 메소드
	 * @author 박상우
	 * */
	public void resetCount(){
		count = 0;
	}
	public int getCount(){
		return count;
	}
}