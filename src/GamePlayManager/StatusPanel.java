package GamePlayManager;

import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
/**클래스 설명
 * <br>
 * 게임 실행 중에 상태를 표시할 패널
 * 기본적인 도움말이나 열쇠 획득 여부등을 출력
 * @author 박상우
 * @see javax.swing.JPanel
 */
public class StatusPanel extends JPanel{
	/**텍스트를 출력할 텍스트라벨*/
	private JLabel textLabel = new JLabel("상태창");
	/**상태창의 크기와 위치를 설정
	 * @author 박상우
	 * */
	StatusPanel(){
		setSize(1200, 120);
		setLocation(0,600);
		initText();
	}
	/**기본적으로 보여질 상태창 설정
	 * @author 박상우
	 * */
	public void initText() {
		textLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		textLabel.setText("도움말 : 서브 메뉴 = ESC, 일시 정지 = P, 상호 작용 = 스페이스바");
		add(textLabel);
	}
	/**열쇠 획득시 상태창 설정
	 * @author 박상우
	 * */
	public void printGetKey(){
		textLabel.setText("열쇠를 획득하였습니다");
	}
	/**열쇠 조건 불충족시 상태창 설정
	 * @author 박상우
	 * */
	public void printNotEnoughKey(){
		textLabel.setText("열쇠가 부족합니다");
	}
	/**일시 정지시 상태창 설정
	 * @author 박상우
	 * */
	public void printPause(){
		textLabel.setText("일시 정지되었습니다 계속하려면 P키를 입력하세요");
	}
}
