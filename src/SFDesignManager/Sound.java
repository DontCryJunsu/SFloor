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
/**클래스 설명
 * <br>
 * 소리를 이용하기 위한 클래스
 * @author 홍예진, 박상우
 *
 */
public class Sound {
	/**오디오 인풋 스트림 인스턴스*/
	AudioInputStream audioInputStream;
	/**클립 인스턴스*/
	Clip clip;
	/**볼륨 조절을 위한 컨트롤 인스턴스*/
	public FloatControl control;
	/**볼륨 값 유지를 위한 실수 멤버*/
	public float vol;
	/**기본 인스턴스 생성
	 * @author 홍예진
	 */
	public Sound(){};
	/**매개변수로 받은 오디오파일로 객체 생성
	 * @author 홍예진, 박상우
	 * @param path 재생할 오디오 파일 경로
	 */
	public Sound(String path){
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(path));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			vol = control.getValue(); // 객체 생성할 때 볼륨 값을 가져옴
		}catch(Exception e) {}

	}
	/**오디오 파일 재생
	 * @author 홍예진
	 */
	public void play(){
		clip.start();
	}
	/**매개변수로 받은 경로에 있는 오디오 파일 재생
	 * @author 홍예진, 박상우
	 * @param path 재생할 오디오 파일 경로
	 */
	public void play(String path){
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(path));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			control.setValue(vol); // 인스턴스에서 새로 파일을 불러서 할 때 볼륨 값을 이전의 값과 같게 해주기 위함
		}catch(Exception e) {}
		clip.start();
	}
	/**오디오 파일 정지
	 * @author 홍예진
	 */
	public void stop(){
		clip.stop();
	}
	/**매개 변수로 받은 수의 + 1만큼 반복 재생
	 * @author 홍예진, 박상우
	 * @param i 반복 재생할 횟수 - 1
	 */
	public void loop(int i){
		clip.loop(i);
	}
	/**매개 변수로 받은 경로의 오디오 파일을 매개 변수로 받은 수의 + 1만큼 반복 재생
	 * @author 홍예진, 박상우
	 * @param path 재생할 오디오 파일 경로
	 * @param i 반복 재생할 횟수 - 1
	 */
	public void loop(String path, int i){
		this.stop();
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(path));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			control.setValue(vol); // 인스턴스에서 새로 파일을 불러서 할 때 볼륨 값을 이전의 값과 같게 해주기 위함
		}catch(Exception e) {}
		clip.loop(i);
	}
	/**매개변수로 받은 버튼 컴포넌트에 사운드 부착
	 * @author 홍예진
	 * @param bt 사운드를 부착할 컴포넌트
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