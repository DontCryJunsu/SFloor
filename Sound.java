package SoundManager;

import java.io.File;
import javax.sound.sampled.*;

import WindowManager.MainWindow; 

 
public class Sound {
private static final int EXTERNAL_BUFFER_SIZE = 128000;  //버퍼 SIZE 지정
File Close_Door = new File("Close-Door.wav");
File Click = new File("Click.wav");


 public Sound (){
	 
} 

  public void play(File sound){
    try {
        // File 값 받기

      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(sound);
      // 받아온 File 지정된 오디오 입력 스트림을 변환해, 나타난 인코딩의 오디오 입력 스트림을 취득 


      AudioFormat audioFormat = audioInputStream.getFormat();

      // 바이트수는 아니고 샘플 프레임수로 나타내지는, 스트림의 길이를 취득

 

      DataLine.Info info = new DataLine.Info(SourceDataLine.class,audioFormat);

      // 단일의 오디오 형식을 포함한 지정한 정보로부터 데이터 라인의 정보 오브젝트를 구축


      SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);

      // 지정된 Line.Info 오브젝트의 기술에 일치하는 라인을 취득


      line.open(audioFormat);

     // 지정된 형식과 지정된 버퍼 사이즈로 라인을 열어, 라인이 필요한 system resource를 획득해 조작 가능


      line.start();

    // 라인에서의 데이터 입출력을 가능

 

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

