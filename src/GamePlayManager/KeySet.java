package GamePlayManager;
import java.awt.event.KeyEvent;
import java.io.*;
public class KeySet {
	int myUp, myDown, myLeft, myRight;
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
		myUp = Integer.parseInt(line[0]);
		myDown = Integer.parseInt(line[1]);
		myLeft = Integer.parseInt(line[2]);
		myRight = Integer.parseInt(line[3]);
	}
	/*public static void main(String [] args){
		System.out.println(KeyEvent.VK_UP);
		System.out.println(KeyEvent.VK_DOWN);
		System.out.println(KeyEvent.VK_LEFT);
		System.out.println(KeyEvent.VK_RIGHT);
		KeySet key = new KeySet();
		System.out.println(key.myUp);
	}*/
}
