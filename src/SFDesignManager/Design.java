package SFDesignManager;

import javax.sound.sampled.Clip;
import javax.swing.*;

import WindowManager.MainWindow;


import java.awt.*;
import java.awt.event.*;
/**Ŭ���� ����
 * <br>
 * �����ο� ����� �޼ҵ带 �����ϴ� Ŭ����
 * @author ȫ����
 */
public class Design{
	/**��ư ũ��, �̹��� ���� �� ����ȭ �޼ҵ�
	 * ��ư ������ ���� ������ ������ �Ű������� �޴´�
	 * @author ȫ����
	 * @param bt �̹����� �������� ��ư
	 * @param i ������ �̹����� ����, �����ϱ����� ������ ����.
	 * @return bt �̹����� �������� ��ư
	 */
	public JButton setbutton(JButton bt, int i){
		ImageIcon normal, selected;
		/* ��ư �̹��� ���� switch��
		 * i 0~3:�����г�, 6~7:�����г�, 8~9:��ũ�г�, 10~12:ȯ�漳���г�, �� ���� �ټ� �������� ����
		 */
		switch(i)
		{ 
		case 0 :// Game Start ��ư �̹���
			normal = new ImageIcon("images/Buttons/BT_GS1.png");
			selected = new ImageIcon("images/Buttons/BT_GS2.png");
			bt= new JButton(normal);
			bt.setLocation(700, 270);
			bt.setSize(new Dimension(212,22));
			break;
		case 1 : // Ranking ��ư �̹���
			normal = new ImageIcon("images/Buttons/BT_RK1.png");
			selected = new ImageIcon("images/Buttons/BT_RK2.png");
			bt= new JButton(normal);
			bt.setLocation(700, 330);
			bt.setSize(new Dimension(normal.getIconWidth(),normal.getIconHeight()));
			break;
		case 2 : // Setting ��ư �̹���
			normal = new ImageIcon("images/Buttons/BT_ST1.png");
			selected = new ImageIcon("images/Buttons/BT_ST2.png");
			bt= new JButton(normal);
			bt.setLocation(700, 390);
			bt.setSize(new Dimension(normal.getIconWidth(),normal.getIconHeight()));
			break;
		case 3 : // Exit ��ư �̹���
			normal = new ImageIcon("images/Buttons/BT_ET1.png");
			selected = new ImageIcon("images/Buttons/BT_ET2.png");
			bt= new JButton(normal);
			bt.setLocation(700, 450);
			bt.setSize(new Dimension(normal.getIconWidth(),normal.getIconHeight()));
			break;
		case 4 : // yes ��ư �̹���
			normal = new ImageIcon("images/Buttons/BT_yes1.png");
			selected = new ImageIcon("images/Buttons/BT_yes2.png");
			break;
		case 5 : // no ��ư �̹���
			normal = new ImageIcon("images/Buttons/BT_no1.png");
			selected = new ImageIcon("images/Buttons/BT_no2.png");
			break;
		case 6: // Skip ��ư �̹���
			normal = new ImageIcon("images/Buttons/BT_SK1.png");
			selected = new ImageIcon("images/Buttons/BT_SK2.png");
			break;
		case 7 : // Back��ư �̹���
			normal = new ImageIcon("images/Buttons/BT_BK1.png");
			selected = new ImageIcon("images/Buttons/BT_BK2.png");
			break;
		case  8 : // Key Setting��ư �̹���
			normal = new ImageIcon("images/Buttons/BT_KS1.png");
			selected = new ImageIcon("images/Buttons/BT_KS2.png");
			break;
		case  9 : // Set��ư �̹��� 
			normal = new ImageIcon("images/Buttons/BT_S1.png");
			selected = new ImageIcon("images/Buttons/BT_S2.png");
			break;
		case  10 : // Reset��ư �̹��� 
			normal = new ImageIcon("images/Buttons/BT_RE1.png");
			selected = new ImageIcon("images/Buttons/BT_RE2.png");
			break;
		case  11 :// MainMenu ��ư �̹���
			normal = new ImageIcon("images/Buttons/MainMenu1.png");
			selected = new ImageIcon("images/Buttons/MainMenu2.png");
			break;
		case  12 : // Key Setting (small) ��ư �̹��� 
			normal = new ImageIcon("images/Buttons/BT_KS_s1.png");
			selected = new ImageIcon("images/Buttons/BT_KS_s2.png");
			break;
		case  13 : // Set (small) ��ư �̹���
			normal = new ImageIcon("images/Buttons/BT_S_s1.png");
			selected = new ImageIcon("images/Buttons/BT_S_s2.png");
			break;
		default  : //  ok (small) ��ư �̹���
			normal = new ImageIcon("images/Buttons/BT_ok1.png");
			selected = new ImageIcon("images/Buttons/BT_ok2.png");
			break;
		}
		if(3< i) bt= new JButton(normal);
		bt.setPressedIcon(selected);
		bt.setRolloverIcon(selected);
		bt.setBackground(Color.white); 
		bt.setOpaque(false);
		bt.setBorder(null);
		return bt;
	}
}