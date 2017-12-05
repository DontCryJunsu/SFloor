package SFDesignManager;

import javax.sound.sampled.Clip;
import javax.swing.*;

import WindowManager.MainWindow;


import java.awt.*;
import java.awt.event.*;
/**클래스 설명
 * <br>
 * 디자인에 사용할 메소드를 포함하는 클래스
 * @author 홍예진
 */
public class Design{
	/**버튼 크기, 이미지 지정 및 투명화 메소드
	 * 버튼 구분을 위한 정수형 변수를 매개변수로 받는다
	 * @author 홍예진
	 * @param bt 이미지를 지정받을 버튼
	 * @param i 지정할 이미지를 구분, 선택하기위한 정수형 변수.
	 * @return bt 이미지를 지정받은 버튼
	 */
	public JButton setbutton(JButton bt, int i){
		ImageIcon normal, selected;
		/* 버튼 이미지 지정 switch문
		 * i 0~3:메인패널, 6~7:게임패널, 8~9:랭크패널, 10~12:환경설정패널, 몇 개는 다소 공용으로 쓰임
		 */
		switch(i)
		{ 
		case 0 :// Game Start 버튼 이미지
			normal = new ImageIcon("images/Buttons/BT_GS1.png");
			selected = new ImageIcon("images/Buttons/BT_GS2.png");
			bt= new JButton(normal);
			bt.setLocation(700, 270);
			bt.setSize(new Dimension(212,22));
			break;
		case 1 : // Ranking 버튼 이미지
			normal = new ImageIcon("images/Buttons/BT_RK1.png");
			selected = new ImageIcon("images/Buttons/BT_RK2.png");
			bt= new JButton(normal);
			bt.setLocation(700, 330);
			bt.setSize(new Dimension(normal.getIconWidth(),normal.getIconHeight()));
			break;
		case 2 : // Setting 버튼 이미지
			normal = new ImageIcon("images/Buttons/BT_ST1.png");
			selected = new ImageIcon("images/Buttons/BT_ST2.png");
			bt= new JButton(normal);
			bt.setLocation(700, 390);
			bt.setSize(new Dimension(normal.getIconWidth(),normal.getIconHeight()));
			break;
		case 3 : // Exit 버튼 이미지
			normal = new ImageIcon("images/Buttons/BT_ET1.png");
			selected = new ImageIcon("images/Buttons/BT_ET2.png");
			bt= new JButton(normal);
			bt.setLocation(700, 450);
			bt.setSize(new Dimension(normal.getIconWidth(),normal.getIconHeight()));
			break;
		case 4 : // yes 버튼 이미지
			normal = new ImageIcon("images/Buttons/BT_yes1.png");
			selected = new ImageIcon("images/Buttons/BT_yes2.png");
			break;
		case 5 : // no 버튼 이미지
			normal = new ImageIcon("images/Buttons/BT_no1.png");
			selected = new ImageIcon("images/Buttons/BT_no2.png");
			break;
		case 6: // Skip 버튼 이미지
			normal = new ImageIcon("images/Buttons/BT_SK1.png");
			selected = new ImageIcon("images/Buttons/BT_SK2.png");
			break;
		case 7 : // Back버튼 이미지
			normal = new ImageIcon("images/Buttons/BT_BK1.png");
			selected = new ImageIcon("images/Buttons/BT_BK2.png");
			break;
		case  8 : // Key Setting버튼 이미지
			normal = new ImageIcon("images/Buttons/BT_KS1.png");
			selected = new ImageIcon("images/Buttons/BT_KS2.png");
			break;
		case  9 : // Set버튼 이미지 
			normal = new ImageIcon("images/Buttons/BT_S1.png");
			selected = new ImageIcon("images/Buttons/BT_S2.png");
			break;
		case  10 : // Reset버튼 이미지 
			normal = new ImageIcon("images/Buttons/BT_RE1.png");
			selected = new ImageIcon("images/Buttons/BT_RE2.png");
			break;
		case  11 :// MainMenu 버튼 이미지
			normal = new ImageIcon("images/Buttons/MainMenu1.png");
			selected = new ImageIcon("images/Buttons/MainMenu2.png");
			break;
		case  12 : // Key Setting (small) 버튼 이미지 
			normal = new ImageIcon("images/Buttons/BT_KS_s1.png");
			selected = new ImageIcon("images/Buttons/BT_KS_s2.png");
			break;
		case  13 : // Set (small) 버튼 이미지
			normal = new ImageIcon("images/Buttons/BT_S_s1.png");
			selected = new ImageIcon("images/Buttons/BT_S_s2.png");
			break;
		default  : //  ok (small) 버튼 이미지
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