package SFDesignManager;


import java.applet.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.JButton;



public class Sound {
	// public String backMusic = "sounds\\BackgroundMusic_main.wav";
	
	AudioClip clip = null; // ����� Ŭ�� ��ü
	
	public Sound(){};
	// sound("�������ϸ�")���� ���� ��ü(Instance) ����. ���ϴ� ���� soundInstance.play() ���� �� ���� �� ���� ���.
	public Sound(String a){
		// ������ ���ͳ� ��θ��� �����Ѵ�.
		URL audioURL = getClass().getResource(a);
		// ������ �ε��Ͽ� ���ְ����� �������� �����.
		clip = Applet.newAudioClip(audioURL);
		
	}
	public void play(){
		clip.play();
	}
	public void play(String a){
		// ������ ���ͳ� ��θ��� �����Ѵ�.
		URL audioURL = getClass().getResource(a);
		// ������ �ε��Ͽ� ���ְ����� �������� �����.
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
		// ������ ���ͳ� ��θ��� �����Ѵ�.
		URL audioURL = getClass().getResource(a);
		// ������ �ε��Ͽ� ���ְ����� �������� �����.
		clip = Applet.newAudioClip(audioURL);		
		clip.loop();
	}
	

}