package GamePlayManager;

import java.awt.event.KeyEvent;
import java.io.*;
/**Ŭ���� ����
 * <br>
 * �ܺ� ���Ϸκ��� Ű �ڵ� ���� �ҷ��鿩 ���� Ű ������ �� �� �ְ��ϴ� Ŭ����
 * @author �ڻ��
 */
public class KeySet {
	/**�ܺ� ���Ϸκ��� �ҷ����� Ű �ڵ� ��*/
	private int up, down, left, right;
	/**������ �ؽ�Ʈ ���� ��ο��� ����� Ű �ڵ� ���� �ҷ��´�
	 * @author �ڻ��
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