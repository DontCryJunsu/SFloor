package SFDesignManager;


import java.applet.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.JButton;



public class Sound {
	// public String backMusic = "sounds\\BackgroundMusic_main.wav";
	
	AudioClip clip = null; // 오디오 클립 객체
	
	public Sound(){};
	// sound("음악파일명")으로 사운드 객체(Instance) 생성. 원하는 곳에 soundInstance.play() 부착 및 시행 시 사운드 출력.
	public Sound(String a){
		// 파일의 인터넷 경로명을 결정한다.
		URL audioURL = getClass().getResource(a);
		// 파일을 로드하여 연주가능한 형식으로 만든다.
		clip = Applet.newAudioClip(audioURL);
		
	}
	public void play(){
		clip.play();
	}
	public void play(String a){
		// 파일의 인터넷 경로명을 결정한다.
		URL audioURL = getClass().getResource(a);
		// 파일을 로드하여 연주가능한 형식으로 만든다.
		clip = Applet.newAudioClip(audioURL);		
		clip.play();
	}
	public void stop(){
		clip.stop();
	}
	public void loop(){
		clip.loop();
	}
	public void loop(String a){
		// 파일의 인터넷 경로명을 결정한다.
		URL audioURL = getClass().getResource(a);
		// 파일을 로드하여 연주가능한 형식으로 만든다.
		clip = Applet.newAudioClip(audioURL);		
		clip.loop();
	}
	

}