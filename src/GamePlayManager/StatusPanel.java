package GamePlayManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SFDesignManager.Sound;
/**Ŭ���� ����
 * <br>
 * ���� ���� �߿� ���¸� ǥ���� �г�
 * <br>
 * �⺻���� �����̳� ���� ȹ�� ���ε��� ���
 * @author �ڻ��, ȫ����
 * @see javax.swing.JPanel
 */
public class StatusPanel extends JPanel{
	/**�ؽ�Ʈ�� ����� �ؽ�Ʈ��*/
	private JLabel textLabel = new JLabel("����â");
	//Sound effect = new Sound("sounds/Close-Door.wav");
	/**����â�� ũ��� ��ġ�� ����
	 * @author �ڻ��
	 * */
	StatusPanel(){
		setSize(1200, 120);
		setLocation(0,600);
		setLayout(null);
		initText();
	}
	/**paintComponent �޼ҵ� �������̵�
	 * ����â�� ��� �̹��� ����
	 * @author ȫ����
	 * @param g �׷���
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		ImageIcon status = new ImageIcon("images/Status_background2.png"); 
		Image img = status.getImage();
		g.drawImage(img, 0, 0, this);
	}
	/**�⺻������ ������ ����â ����
	 * @author �ڻ��, ȫ����
	 * */
	public void initText() {
		textLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		textLabel.setForeground(Color.white);
		textLabel.setText("���� : ���� �޴� = ESC, �Ͻ� ���� = P, ��ȣ �ۿ� = �����̽���");
		textLabel.setLocation(50,-20);
		textLabel.setSize(1200,120);
		add(textLabel);	
	}
	/**���� ȹ��� ����â ����
	 * @author �ڻ��
	 * */
	public void printGetKey(){
		textLabel.setText("���踦 ȹ���Ͽ����ϴ�");
	}
	/**���� ���� �������� ����â ����
	 * @author �ڻ��
	 * */
	public void printNotEnoughKey(){
		textLabel.setText("���谡 �����մϴ�");
	}
	/**�Ͻ� ������ ����â ����
	 * @author �ڻ��
	 * */
	public void printPause(){
		textLabel.setText("�Ͻ� �����Ǿ����ϴ� ����Ϸ��� PŰ�� �Է��ϼ���");
	}
}