package SoundManager;

import java.io.File;
import javax.sound.sampled.*;

import WindowManager.MainWindow; 

 
public class Sound {
private static final int EXTERNAL_BUFFER_SIZE = 128000;  //���� SIZE ����
File Close_Door = new File("Close-Door.wav");
File Click = new File("Click.wav");


 public Sound (){
	 
} 

  public void play(File sound){
    try {
        // File �� �ޱ�

      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(sound);
      // �޾ƿ� File ������ ����� �Է� ��Ʈ���� ��ȯ��, ��Ÿ�� ���ڵ��� ����� �Է� ��Ʈ���� ��� 


      AudioFormat audioFormat = audioInputStream.getFormat();

      // ����Ʈ���� �ƴϰ� ���� �����Ӽ��� ��Ÿ������, ��Ʈ���� ���̸� ���

 

      DataLine.Info info = new DataLine.Info(SourceDataLine.class,audioFormat);

      // ������ ����� ������ ������ ������ �����κ��� ������ ������ ���� ������Ʈ�� ����


      SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);

      // ������ Line.Info ������Ʈ�� ����� ��ġ�ϴ� ������ ���


      line.open(audioFormat);

     // ������ ���İ� ������ ���� ������� ������ ����, ������ �ʿ��� system resource�� ȹ���� ���� ����


      line.start();

    // ���ο����� ������ ������� ����

 

      int nBytesRead = 0;
      byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
      while (nBytesRead != -1) {
        nBytesRead = audioInputStream.read(abData, 0, abData.length);
        if (nBytesRead >= 0) {
          int nBytesWritten = line.write(abData, 0, nBytesRead);
        }
      }
 
    }

 

 catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }


  }
  public static void main(String [] args){
		new Sound();
	}
}

