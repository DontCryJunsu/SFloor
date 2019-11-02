package GamePlayManager;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusPanel extends JPanel{
	private JLabel textLabel = new JLabel("상태창");
	GamePlaying gamePlaying;
	StatusPanel(GamePlaying gamePlaying){
		this.gamePlaying = gamePlaying;
		setSize(1200, 120);
		setLocation(0,600);
		textLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
		add(textLabel);
	}
	public void printGetKey(){
		textLabel.setText("열쇠를 획득하였습니다");
	}
	public void printNotEnoughKey(){
		textLabel.setText("열쇠가 부족합니다");
	}
	public void resetText(){
		textLabel.setText("");
	}
	public void printPause(){
		textLabel.setText("일시 정지");
	}
}
