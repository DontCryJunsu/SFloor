package WindowManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**클래스 설명
 * <br>
 * 외부 파일로부터 값을 불러들여 순위를 보여주는 랭크 패널
 * @author 박상우
 * @see javax.swing.JPanel
 */
public class RankPanel extends JPanel{
	/**메인 윈도우 인스턴스*/
	MainWindow window;
	/**"뒤로가기"버튼*/
	JButton back = new JButton("뒤로가기");
	/**"랭크 초기화"버튼*/
	JButton reset = new JButton("랭크 초기화");
	/**순위 정보를 포함하는 RankInfo배열*/
	RankInfo rank[] = new RankInfo[10];
	/**점수를 표시할 라벨 배열*/
	JLabel rankScoreLabel[] = new JLabel[10];
	/**이름을 표시할 라벨 배열*/
	JLabel rankNameLabel[] = new JLabel[10];
	/**"시간"텍스트라벨*/
	JLabel score = new JLabel("  시간  ");
	/**"이름"텍스트라벨*/
	JLabel name = new JLabel("  이름  ");
	/**텍스트라벨 패널*/
	JPanel pnl = new JPanel();
	/**점수 라벨과 이름 라벨을 포함할 패널*/
	JPanel mergedPanel[] = new JPanel[10];
	/**점수 라벨에 사용할 폰트*/
	Font scoreFont = new Font(Font.SANS_SERIF, Font.ITALIC, 20);
	/**이름 라벨에 사용할 폰트*/
	Font nameFont = new Font(Font.SANS_SERIF, Font.BOLD, 20);
	/**상단에 배치될 패널*/
	JPanel topPanel = new JPanel();
	/**중앙에 배치될 패널*/
	JPanel centerPanel = new JPanel();
	/**랭크 패널 초기화
	 * @author 박상우
	 * @param window 인스턴스로 사용할 메인윈도우
	 * */
	RankPanel(MainWindow window){
		this.window = window;
		setLayout(new BorderLayout());
		centerPanel.setPreferredSize(new Dimension(300, 600));
		centerPanel.setLayout(new GridLayout(11, 1, 5, 5));
		try {
			BufferedReader reader = new BufferedReader(new FileReader("rank/rank.txt"));
			for(int i = 0; i < 10; i++){
				rank[i] = new RankInfo();
				rank[i].score = Integer.parseInt(reader.readLine());
				rank[i].name = reader.readLine();
			}
			reader.close();
		} catch (Exception e) {}
		sort(rank);
		score.setFont(scoreFont);
		name.setFont(nameFont);
		pnl.add(score);
		pnl.add(name);
		centerPanel.add(pnl);
		for(int i = 0; i < 10; i ++){
			rankScoreLabel[i] = new JLabel(Integer.toString(rank[i].score));
			rankScoreLabel[i].setFont(scoreFont);
			rankNameLabel[i] = new JLabel(rank[i].name);
			rankNameLabel[i].setFont(nameFont);
			mergedPanel[i] = new JPanel();

			mergedPanel[i].add(rankScoreLabel[i]);
			mergedPanel[i].add(rankNameLabel[i]);

			centerPanel.add(mergedPanel[i]);
		}
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				window.changePanel(window.mainPanel);
			}
		});
		reset.addActionListener(new ActionListener() {
			/**랭크 초기화 버튼을 눌렀을 때 실행되는 메소드
			 * 랭크 원본 텍스트 파일로부터 값을 불러들여 랭크 텍스트파일에 저장한 후 랭크 라벨의 값을 다시 설정
			 * @author 박상우
			 */
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedReader readerDefault = new BufferedReader(new FileReader("rank/rank_default.txt"));
					for(int i = 0; i < 10; i++){
						rank[i].score = Integer.parseInt(readerDefault.readLine());
						rank[i].name = readerDefault.readLine();
					}
					readerDefault.close();
					BufferedWriter writer = new BufferedWriter(new FileWriter("rank/rank.txt"));
					for(int i = 0; i < 10; i++) {
						writer.write(Integer.toString(rank[i].score));
						writer.newLine();
						writer.write(rank[i].name);
						writer.newLine();
					}
					writer.close();
					BufferedReader reader = new BufferedReader(new FileReader("rank/rank.txt"));
					for(int i = 0; i < 10; i++){
						rank[i].score = Integer.parseInt(reader.readLine());
						rank[i].name = reader.readLine();
					}
					reader.close();
				} catch (Exception e1) {}
				sort(rank);
				for(int i = 0; i < 10; i++) {
					rankScoreLabel[i].setText(Integer.toString(rank[i].score));
					rankNameLabel[i].setText(rank[i].name);
				}
			}
		});
		topPanel.add(new JLabel("랭크 패널"));
		topPanel.add(back);
		topPanel.add(reset);
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
	}
	/**메인 패널에서 랭크 패널을 불러올 때 랭크를 최신화 하기위한 메소드
	 * @author 박상우
	 */
	public void updateRank() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("rank/rank.txt"));
			for(int i = 0; i < 10; i++){
				rank[i].score = Integer.parseInt(reader.readLine());
				rank[i].name = reader.readLine();
			}
			reader.close();
		} catch (Exception e) {}
		sort(rank);
		for(int i = 0; i < 10; i ++){
			rankScoreLabel[i].setText(Integer.toString(rank[i].score));
			rankNameLabel[i].setText(rank[i].name);
		}
	}
	//나중에 랭크 추가에 쓸 부분
	/*class GetNameDialog extends JDialog{
		JTextField txt = new JTextField("점수를 입력하세요.");
		GetNameDialog(){
			setSize(400, 100);
			txt.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					try{
						getRank.score = Integer.parseInt(txt.getText());
						if(getRank.score >= rank[9].score){
							lbl.setText("랭크에 넣을수 없는 점수입니다.");
							dispose();
							add.setEnabled(true);
						}
						else{
							copy(getRank, rank[9]);
							dispose();
						}
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
	}*/
	/**순위를 표시할 때 정렬하기 위한 메소드
	 * @author 박상우
	 * @param k 정렬에 사용할 순위 정보 배열
	 */
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
	//순위 추가할 때 쓸 부분
	public static void copy(RankInfo src, RankInfo dst){
		dst.score = src.score;
		dst.name = src.name;
	}
}
/**클래스 설명
 * <br>
 * 단순히 이름과 점수를 가지는 클래스
 * @author 박상우
 */
class RankInfo{
	/**점수*/
	int score;
	/**이름*/
	String name;
}