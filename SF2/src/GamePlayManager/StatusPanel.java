package GamePlayManager;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusPanel extends JPanel{
	private JLabel textLabel = new JLabel("����â");
	GamePlaying gamePlaying;
	StatusPanel(GamePlaying gamePlaying){
		this.gamePlaying = gamePlaying;
		setSize(1200, 120);
		setLocation(0,600);
		textLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
		add(textLabel);
	}
	public void printGetKey(){
		textLabel.setText("���踦 ȹ���Ͽ����ϴ�");
	}
	public void printNotEnoughKey(){
		textLabel.setText("���谡 �����մϴ�");
	}
	public void resetText(){
		textLabel.setText("");
	}
	public void printPause(){
		textLabel.setText("�Ͻ� ����");
	}
}
