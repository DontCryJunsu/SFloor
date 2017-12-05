package WindowManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import SFDesignManager.Design;
import SFDesignManager.EndingPanel;
import SFDesignManager.Sound;
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
	JButton back;
	/**"랭크 초기화"버튼*/
	JButton reset;
	/**순위 정보를 포함하는 RankInfo배열*/
	private RankInfo rank[] = new RankInfo[10];
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
	/**버튼에 디자인 설정을 위한 디자인 인스턴스 생성*/
	Design design = new Design();
	/**버튼에 사운드 설정을 위한 사운드 인스턴스 생성*/
	Sound effect = new Sound("sounds/bt_cursorOn.wav");
	/**랭크 다이얼로그 인스턴스*/
	public GetNameDialog gnd;
	/**랭크 패널 초기화
	 * @author 박상우
	 * @param window 인스턴스로 사용할 메인윈도우
	 * */
	RankPanel(MainWindow window){
		this.window = window;
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		pnl.setBackground(Color.BLACK);
		topPanel.setBackground(Color.BLACK);
		centerPanel.setBackground(Color.BLACK);
		gnd = new GetNameDialog(window.contentPane);
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
		sort(getRank());
		score.setFont(scoreFont);
		name.setFont(nameFont);
		score.setForeground(Color.WHITE);
		name.setForeground(Color.WHITE);
		pnl.add(score);
		pnl.add(name);
		centerPanel.add(pnl);
		for(int i = 0; i < 10; i ++){
			rankScoreLabel[i] = new JLabel(Integer.toString(rank[i].score));
			rankScoreLabel[i].setFont(scoreFont);
			rankScoreLabel[i].setForeground(Color.WHITE);
			rankNameLabel[i] = new JLabel(rank[i].name);
			rankNameLabel[i].setFont(nameFont);
			rankNameLabel[i].setForeground(Color.WHITE);
			mergedPanel[i] = new JPanel();

			mergedPanel[i].add(rankScoreLabel[i]);
			mergedPanel[i].add(rankNameLabel[i]);
			mergedPanel[i].setBackground(Color.BLACK);
			centerPanel.add(mergedPanel[i]);
		}
		back = design.setbutton(back,7);
		reset = design.setbutton(reset,10);
		effect.setbutton(back);
		effect.setbutton(reset);
		
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				window.changePanel(window.mainPanel);
			}
		});
		reset.addActionListener(new ActionListener() {
			/**랭크 초기화 버튼을 눌렀을 때 실행되는 메소드
			 * <br>
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
	/**클래스 설명
	 * <br>
	 * 게임이 끝나고 랭크 입력이 가능할 때 불러올 다이얼로그
	 * @author 박상우
	 * @see javax.swing.JDialog
	 */
	public class GetNameDialog extends JDialog{
		/**상단에 배치할 패널*/
		private JPanel topPanel = new JPanel();
		/**중앙에 배치할 패널*/
		private JPanel centerPanel = new JPanel();
		/**하단에 배치할 패널*/
		private JPanel bottomPanel = new JPanel();
		/**텍스트 라벨*/
		private JLabel lbl = new JLabel("이름을 입력하세요");
		/**텍스트 필드*/
		private JTextField txt = new JTextField("홍길동");
		/**확인 버튼*/
		private JButton btn;
		/**새 랭크 입력에 사용할 랭크 정보*/
		private RankInfo getRank = new RankInfo();
		/**버튼에 디자인 설정을 위한 디자인 인스턴스 생성*/
		private Design design = new Design();
		/**버튼에 사운드 설정을 위한 사운드 인스턴스 생성*/
		private Sound effect = new Sound("sounds/bt_cursorOn.wav");
		/**이름을 입력할 다이얼로그 초기화
		 * @param c 상대 위치 설정할 컨테이너
		 */
		public GetNameDialog(Container c){
			setTitle("이름 입력");
			setSize(400, 200);
			setLayout(new BorderLayout());
			setLocationRelativeTo(c);
			setAlwaysOnTop(true);
			setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			btn = design.setbutton(btn,14);
			effect.setbutton(btn);
			btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					try{
						getRank.score = window.mgp.getTimerPanel().getCount();
						getRank.name = txt.getText();
						copy(getRank, getRank()[9]);
						dispose();
						txt.setText("홍길동");
						sort(getRank());
						BufferedWriter writer = new BufferedWriter(new FileWriter("rank/rank.txt"));
						for(int i = 0; i < 10; i++) {
							writer.write(Integer.toString(getRank()[i].score));
							writer.newLine();
							writer.write(getRank()[i].name);
							writer.newLine();
						}
						writer.close();
					}catch(Exception E1){}
					window.mgp.getTimerPanel().resetCount();
					window.mgp.resetGame();	
					window.changePanel(new EndingPanel(window));
					window.Bgm.stop();
					window.Bgm.loop("sounds/BackgroundMusic_ending.wav", Clip.LOOP_CONTINUOUSLY);
					//window.changePanel(window.mainPanel);
					//window.Bgm.stop();
					//window.Bgm.loop("sounds/BackgroundMusic_main.wav", Clip.LOOP_CONTINUOUSLY);
				}
			});
			topPanel.add(lbl);
			centerPanel.add(txt);
			bottomPanel.add(btn);
			add(topPanel, BorderLayout.NORTH);
			add(centerPanel, BorderLayout.CENTER);
			add(bottomPanel, BorderLayout.SOUTH);
		}
	}
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
	/**순위 입력할 때 사용자의 순위 정보를 복사하는 메소드
	 * @param src 사용자의 랭크 정보
	 * @param dst 사용자의 랭크 정보를 저장할 공간
	 */
	public static void copy(RankInfo src, RankInfo dst){
		dst.score = src.score;
		dst.name = src.name;
	}
	public RankInfo[] getRank() {
		return rank;
	}
	public void setRank(RankInfo rank[]) {
		this.rank = rank;
	}
}