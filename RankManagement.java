import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.io.*;

public class RankManagement extends JFrame{
	Container c = getContentPane();
	RankInfo rank[] = new RankInfo[10];
	RankInfo getRank = new RankInfo();
	JLabel rankLabel[] = new JLabel[10];
	JButton add = new JButton("add");
	JLabel lbl = new JLabel("랭크 추가 가능여부가 출력됩니다.");
	RankManagement(){
		try {
			BufferedReader reader = new BufferedReader(new FileReader("test.txt"));
			for(int i = 0; i < 10; i++){
				rank[i] = new RankInfo();
				rank[i].score = Integer.parseInt(reader.readLine());
				rank[i].name = reader.readLine();
			}
			reader.close();
		} catch (Exception e) {}
		sort(rank);
		setSize(300,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		for(int i = 0; i < 10; i ++){
			rankLabel[i] = new JLabel(rank[i].score + " " + rank[i].name);
			add(rankLabel[i]);
		}
		add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				add.setEnabled(false);
				new ScoreFrame();
			}
		});
		add(add);
		add(lbl);
		setVisible(true);
	}
	class ScoreFrame extends JFrame{
		JTextField txt = new JTextField("점수를 입력하세요.");
		ScoreFrame(){
			setSize(400, 100);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLocationRelativeTo(c);
			txt.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					try{
						getRank.score = Integer.parseInt(txt.getText());
						new NameFrame();
						dispose();
					}catch(NumberFormatException NFE){
						lbl.setText("점수의 형식이 틀렸습니다. 정수로 입력하세요");
						dispose();
						new ScoreFrame();
					}
				}
			});
			add(txt);
			setVisible(true);
		}
	}
	class NameFrame extends JFrame{
		JTextField txt = new JTextField("이름을 입력하세요.");
		NameFrame(){
			setSize(400, 100);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLocationRelativeTo(c);
			txt.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					getRank.name = txt.getText();
					dispose();
					if(getRank.score >= rank[9].score) {
						lbl.setText("랭크에 넣을수 없는 점수입니다.");
						new ScoreFrame();
					}
					else{
						copy(getRank, rank[9]);
						sort(rank);
						for(int i = 0; i < 10; i++){
							rankLabel[i].setText(rank[i].score + " " + rank[i].name);
						}
						add.setEnabled(true);
						lbl.setText("랭크 추가 가능여부가 출력됩니다.");
						try{
							BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt"));
							for(int i = 0; i < 10; i++){
								writer.write(Integer.toString(rank[i].score));
								writer.newLine();
								writer.write(rank[i].name);
								writer.newLine();
							}
							writer.close();
						}catch(Exception ex){}
					}
				}
			});
			add(txt);
			setVisible(true);
		}
	}
	public static void sort(RankInfo []k){
		for(int i = 0; i < k.length; i++){
			int min_idx = i;
			for(int j = i + 1; j < k.length; j++){
				if(k[min_idx].score > k[j].score) min_idx = j;
			}
			if(min_idx != i){
				RankInfo tmp = k[i];
				k[i] = k[min_idx];
				k[min_idx] = tmp;
			}
		}
	}
	public static void copy(RankInfo src, RankInfo dst){
		dst.score = src.score;
		dst.name = src.name;
	}
	public static void main(String [] args){
		new RankManagement();
	}
}
class RankInfo{
	int score;
	String name;
}
