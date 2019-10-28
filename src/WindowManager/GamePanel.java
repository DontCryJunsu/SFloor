package WindowManager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
/**Class Description of GamePanel
 * <br>
 * 게임 플레이를 담당하는 패널
 * @author 박상우
 * @see javax.swing.JPanel
 */
class GamePanel extends JPanel implements ActionListener{
	/** 패널 변환 메소드를 사용하기 위한 MainWindow 인스턴스 */
	MainWindow window;
	/** 게임 시작으로 넘어가기위한 버튼*/
	JButton start = new JButton("게임 시작");
	/** 메인 메뉴로 돌아가기위한 버튼*/
	JButton back = new JButton("뒤로가기");
	/** 생성자 매개변수로 MainWindow 객체를 받아 클래스내의 window에 저장
	 * @param window 게임 패널의 인스턴스로 사용할 MainWindow 객체*/
	GamePanel(MainWindow window){
		this.window = window;
		add(new JLabel("게임 패널"));
		start.addActionListener(this);
		back.addActionListener(this);
		add(start);
		add(back);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == start){
			window.changePanel(window.MyGame);
			window.MyGame.requestFocus();
		}
		if(e.getSource() == back) window.changePanel(window.mainPanel);
	}

}