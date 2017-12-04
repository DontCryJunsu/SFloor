package GamePlayManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
/**Ŭ���� ����
 * <br>
 * ���� ���� �߿� ���¸� ǥ���� �г�
 * <br>
 * �⺻���� �����̳� ���� ȹ�� ���ε��� ���
 * @author �ڻ��
 * @see javax.swing.JPanel
 */
public class StatusPanel extends JPanel{
	/**�ؽ�Ʈ�� ����� �ؽ�Ʈ��*/
	private JLabel textLabel = new JLabel("����â");
	/**����â�� ũ��� ��ġ�� ����
	 * @author �ڻ��
	 * */
	StatusPanel(){
		setSize(1200, 120);
		setLocation(0,600);
		setBackground(Color.DARK_GRAY);
		//setOpaque(false);
		initText();
	}
	/**�⺻������ ������ ����â ����
	 * @author �ڻ��
	 * */
	public void initText() {
		textLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		textLabel.setForeground(Color.white);
		textLabel.setText("���� : ���� �޴� = ESC, �Ͻ� ���� = P, ��ȣ �ۿ� = �����̽���");
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
