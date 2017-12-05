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
/**Ŭ���� ����
 * <br>
 * �ܺ� ���Ϸκ��� ���� �ҷ��鿩 ������ �����ִ� ��ũ �г�
 * @author �ڻ��
 * @see javax.swing.JPanel
 */
public class RankPanel extends JPanel{
	/**���� ������ �ν��Ͻ�*/
	MainWindow window;
	/**"�ڷΰ���"��ư*/
	JButton back;
	/**"��ũ �ʱ�ȭ"��ư*/
	JButton reset;
	/**���� ������ �����ϴ� RankInfo�迭*/
	private RankInfo rank[] = new RankInfo[10];
	/**������ ǥ���� �� �迭*/
	JLabel rankScoreLabel[] = new JLabel[10];
	/**�̸��� ǥ���� �� �迭*/
	JLabel rankNameLabel[] = new JLabel[10];
	/**"�ð�"�ؽ�Ʈ��*/
	JLabel score = new JLabel("  �ð�  ");
	/**"�̸�"�ؽ�Ʈ��*/
	JLabel name = new JLabel("  �̸�  ");
	/**�ؽ�Ʈ�� �г�*/
	JPanel pnl = new JPanel();
	/**���� �󺧰� �̸� ���� ������ �г�*/
	JPanel mergedPanel[] = new JPanel[10];
	/**���� �󺧿� ����� ��Ʈ*/
	Font scoreFont = new Font(Font.SANS_SERIF, Font.ITALIC, 20);
	/**�̸� �󺧿� ����� ��Ʈ*/
	Font nameFont = new Font(Font.SANS_SERIF, Font.BOLD, 20);
	/**��ܿ� ��ġ�� �г�*/
	JPanel topPanel = new JPanel();
	/**�߾ӿ� ��ġ�� �г�*/
	JPanel centerPanel = new JPanel();
	/**��ư�� ������ ������ ���� ������ �ν��Ͻ� ����*/
	Design design = new Design();
	/**��ư�� ���� ������ ���� ���� �ν��Ͻ� ����*/
	Sound effect = new Sound("sounds/bt_cursorOn.wav");
	/**��ũ ���̾�α� �ν��Ͻ�*/
	public GetNameDialog gnd;
	/**��ũ �г� �ʱ�ȭ
	 * @author �ڻ��
	 * @param window �ν��Ͻ��� ����� ����������
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
			/**��ũ �ʱ�ȭ ��ư�� ������ �� ����Ǵ� �޼ҵ�
			 * <br>
			 * ��ũ ���� �ؽ�Ʈ ���Ϸκ��� ���� �ҷ��鿩 ��ũ �ؽ�Ʈ���Ͽ� ������ �� ��ũ ���� ���� �ٽ� ����
			 * @author �ڻ��
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
	/**���� �гο��� ��ũ �г��� �ҷ��� �� ��ũ�� �ֽ�ȭ �ϱ����� �޼ҵ�
	 * @author �ڻ��
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
	/**Ŭ���� ����
	 * <br>
	 * ������ ������ ��ũ �Է��� ������ �� �ҷ��� ���̾�α�
	 * @author �ڻ��
	 * @see javax.swing.JDialog
	 */
	public class GetNameDialog extends JDialog{
		/**��ܿ� ��ġ�� �г�*/
		private JPanel topPanel = new JPanel();
		/**�߾ӿ� ��ġ�� �г�*/
		private JPanel centerPanel = new JPanel();
		/**�ϴܿ� ��ġ�� �г�*/
		private JPanel bottomPanel = new JPanel();
		/**�ؽ�Ʈ ��*/
		private JLabel lbl = new JLabel("�̸��� �Է��ϼ���");
		/**�ؽ�Ʈ �ʵ�*/
		private JTextField txt = new JTextField("ȫ�浿");
		/**Ȯ�� ��ư*/
		private JButton btn;
		/**�� ��ũ �Է¿� ����� ��ũ ����*/
		private RankInfo getRank = new RankInfo();
		/**��ư�� ������ ������ ���� ������ �ν��Ͻ� ����*/
		private Design design = new Design();
		/**��ư�� ���� ������ ���� ���� �ν��Ͻ� ����*/
		private Sound effect = new Sound("sounds/bt_cursorOn.wav");
		/**�̸��� �Է��� ���̾�α� �ʱ�ȭ
		 * @param c ��� ��ġ ������ �����̳�
		 */
		public GetNameDialog(Container c){
			setTitle("�̸� �Է�");
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
						txt.setText("ȫ�浿");
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
	/**������ ǥ���� �� �����ϱ� ���� �޼ҵ�
	 * @author �ڻ��
	 * @param k ���Ŀ� ����� ���� ���� �迭
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
	/**���� �Է��� �� ������� ���� ������ �����ϴ� �޼ҵ�
	 * @param src ������� ��ũ ����
	 * @param dst ������� ��ũ ������ ������ ����
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