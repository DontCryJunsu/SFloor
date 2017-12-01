package GamePlayManager;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TimerPanel extends JPanel implements Runnable{

	private JLabel timerLabel = new JLabel();
	private int count = 0;
	public TimerPanel(){
		setSize(200, 50);
		setLocation(1200, 0);
		timerLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC | Font.BOLD, 20));
		add(timerLabel);
	}
	public void run() {
		while(!Thread.currentThread().isInterrupted()){
			timerLabel.setText("½Ã°£ : " + Integer.toString(count));
			count++;
			try{
				Thread.sleep(10);
			}catch(InterruptedException e){return;}
		}
	}
	public void resetCount(){
		count = 0;
	}
	public int getCount(){
		return count;
	}
}
