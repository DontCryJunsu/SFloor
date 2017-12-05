package SFDesignManager;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JButton;
/**Ŭ���� ����
 * <br>
 * �Ҹ��� �̿��ϱ� ���� Ŭ����
 * @author ȫ����, �ڻ��
 *
 */
public class Sound {
	/**����� ��ǲ ��Ʈ�� �ν��Ͻ�*/
	AudioInputStream audioInputStream;
	/**Ŭ�� �ν��Ͻ�*/
	Clip clip;
	/**���� ������ ���� ��Ʈ�� �ν��Ͻ�*/
	public FloatControl control;
	/**���� �� ������ ���� �Ǽ� ���*/
	public float vol;
	/**�⺻ �ν��Ͻ� ����
	 * @author ȫ����
	 */
	public Sound(){};
	/**�Ű������� ���� ��������Ϸ� ��ü ����
	 * @author ȫ����, �ڻ��
	 * @param path ����� ����� ���� ���
	 */
	public Sound(String path){
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(path));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			vol = control.getValue(); // ��ü ������ �� ���� ���� ������
		}catch(Exception e) {}

	}
	/**����� ���� ���
	 * @author ȫ����
	 */
	public void play(){
		clip.start();
	}
	/**�Ű������� ���� ��ο� �ִ� ����� ���� ���
	 * @author ȫ����, �ڻ��
	 * @param path ����� ����� ���� ���
	 */
	public void play(String path){
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(path));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			control.setValue(vol); // �ν��Ͻ����� ���� ������ �ҷ��� �� �� ���� ���� ������ ���� ���� ���ֱ� ����
		}catch(Exception e) {}
		clip.start();
	}
	/**����� ���� ����
	 * @author ȫ����
	 */
	public void stop(){
		clip.stop();
	}
	/**�Ű� ������ ���� ���� + 1��ŭ �ݺ� ���
	 * @author ȫ����, �ڻ��
	 * @param i �ݺ� ����� Ƚ�� - 1
	 */
	public void loop(int i){
		clip.loop(i);
	}
	/**�Ű� ������ ���� ����� ����� ������ �Ű� ������ ���� ���� + 1��ŭ �ݺ� ���
	 * @author ȫ����, �ڻ��
	 * @param path ����� ����� ���� ���
	 * @param i �ݺ� ����� Ƚ�� - 1
	 */
	public void loop(String path, int i){
		this.stop();
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(path));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			control.setValue(vol); // �ν��Ͻ����� ���� ������ �ҷ��� �� �� ���� ���� ������ ���� ���� ���ֱ� ����
		}catch(Exception e) {}
		clip.loop(i);
	}
	/**�Ű������� ���� ��ư ������Ʈ�� ���� ����
	 * @author ȫ����
	 * @param bt ���带 ������ ������Ʈ
	 */
	public void setbutton(JButton bt){
		bt.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				loop("sounds/bt_cursorOn.wav", 0);
			}
			public void mousePressed(MouseEvent e){
				loop("sounds/bt_selected.wav", 0);			
			}
		});
	}
}