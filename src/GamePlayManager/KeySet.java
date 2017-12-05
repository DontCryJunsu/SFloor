package GamePlayManager;

import java.awt.event.KeyEvent;
import java.io.*;
/**클래스 설명
 * <br>
 * 외부 파일로부터 키 코드 값을 불러들여 개인 키 설정을 할 수 있게하는 클래스
 * @author 박상우
 */
public class KeySet {
	/**외부 파일로부터 불러들일 키 코드 값*/
	private int up, down, left, right;
	/**지정된 텍스트 파일 경로에서 사용할 키 코드 값을 불러온다
	 * @author 박상우
	 */
	public KeySet(){
		String [] line = new String[4];
		try{
			File config = new File("configs/configs.txt");
			BufferedReader reader = new BufferedReader(new FileReader(config));

			int index = 0;
			while((line[index] = reader.readLine()) != null){
				index++;
			}
			reader.close();

		}catch(Exception e){}
		up = Integer.parseInt(line[0]);
		down = Integer.parseInt(line[1]);
		left = Integer.parseInt(line[2]);
		right = Integer.parseInt(line[3]);
	}
	public int getUp() {
		return up;
	}
	public int getDown() {
		return down;
	}
	public int getLeft() {
		return left;
	}
	public int getRight() {
		return right;
	}
	public void setUp(int keyCode) {
		up = keyCode;
	}
	public void setDown(int keyCode) {
		down = keyCode;
	}
	public void setLeft(int keyCode) {
		left = keyCode;
	}
	public void setRight(int keyCode) {
		right = keyCode;
	}
}