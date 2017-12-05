package GamePlayManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**Ŭ���� ����
 * <br>
 * ���ӽ��� �ð��� ����� �г�
 * <br>
 * ������ ����Ǹ� �� Ŭ������ �ð� ���� �̿��Ͽ� ��ũ�� ����
 * @author �ڻ��, ȫ����
 * @see javax.swing.JPanel
 */
public class TimerPanel extends JPanel implements Runnable{
	/**�ð��� ����� �ؽ�Ʈ��, Ÿ�̸� ���*/
	private JLabel timerLabel = new JLabel();
	/**������ �ð� ��*/
	private int count = 0;
	/**Ÿ�̸� �̹����� �о���� �̹��������� ��ü*/
	ImageIcon time[] = new ImageIcon[7];
	/**Ÿ�̸� �г��� ����̹����� ���� �̹��� ��ü*/
	Image img_p;
	/**Ÿ�̸� �̹����� ���� �̹��� ��ü*/
	Image img[] = new Image[7];
	/**Ÿ�̸� �г� �ʱ�ȭ
	 * @author �ڻ��, ȫ����
	 * */
	public TimerPanel(){
		setSize(200, 720);
		setLocation(1200, 0);
		setLayout(null);
		setBackground(Color.DARK_GRAY);
		initLabel();
	}
	/**�ʿ��� ���� �ʱ�ȭ�ϰ� �гο� �߰�
	 * @author �ڻ��
	 */
	public void initLabel() {
		timerLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC | Font.BOLD, 20));
		timerLabel.setForeground(Color.WHITE);
		timerLabel.setLocation(20,120);
		timerLabel.setSize(200,120);
		timerLabel.setText("�ð� : ");
		add(timerLabel);
	}
	/**�����ġ(Ÿ�̸�) �̹��� ����
	 * @author ȫ����
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
	/**Runnable run() �޼ҵ� ����
	 * <br>
	 * ������ ���ͷ�Ʈ �߻��� ����
	 * @author �ڻ��
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
	/**�ð��� ����ϴ� count���� �޾� ���ڸ������� �׸����� �����ϴ� �޼ҵ�
	 * @author ȫ����
	 * */
	private void setCountImage(){
		int decimal[] = new int[7];
		if(count ==0){
			decimal[0] = decimal[1] =decimal[2] = decimal[3] = decimal[4] = decimal[5] = decimal[6] = 0;	}
		else{
		decimal[0] = count%10; // decimal[0]�� 1�� �ڸ��� ���� - 0.01��
		decimal[1] = count/10 - (count/100)*10; // decimal[1]�� 10�� �ڸ��� ���� - 0.1��
		decimal[2] = count/100 - (count/1000)*10; // decimal[2]�� 100�� �ڸ��� ���� - 1��
		decimal[3] = (count%6000)/1000; // decimal[3]�� - 10��-> 10�� ���� �ڸ���
		decimal[4] = count/6000-(count/60000)*10; // decimal[4]�� -> 1�д��� �ڸ���
		decimal[5] =(count%360000)/60000; // decimal[5]�� -> 10�д��� �ڸ���
		decimal[6] = 10; // �ݷ� ���
		}
		// 1000=1��, 60,000 = 60��. 100,000 = 100�� = 1�� 40��
		for(int i=0; i<7; i++){
			time[i] = new ImageIcon("images/time_"+decimal[i]+".png");
			img[i] = time[i].getImage();
		}
	}
	/**���� �� �޴��� ���ư��ų� ������ ������ �� ī��Ʈ ���� �����ϴ� �޼ҵ�
	 * @author �ڻ��
	 * */
	public void resetCount(){
		count = 0;
	}
	public int getCount(){
		return count;
	}
}