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
	JButton back = new JButton("�ڷΰ���");
	/**"��ũ �ʱ�ȭ"��ư*/
	JButton reset = new JButton("��ũ �ʱ�ȭ");
	/**���� ������ �����ϴ� RankInfo�迭*/
	RankInfo rank[] = new RankInfo[10];
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
	/**��ũ �г� �ʱ�ȭ
	 * @author �ڻ��
	 * @param window �ν��Ͻ��� ����� ����������
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
			/**��ũ �ʱ�ȭ ��ư�� ������ �� ����Ǵ� �޼ҵ�
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
		topPanel.add(new JLabel("��ũ �г�"));
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
	//���߿� ��ũ �߰��� �� �κ�
	/*class GetNameDialog extends JDialog{
		JTextField txt = new JTextField("������ �Է��ϼ���.");
		GetNameDialog(){
			setSize(400, 100);
			txt.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					try{
						getRank.score = Integer.parseInt(txt.getText());
						if(getRank.score >= rank[9].score){
							lbl.setText("��ũ�� ������ ���� �����Դϴ�.");
							dispose();
							add.setEnabled(true);
						}
						else{
							copy(getRank, rank[9]);
							dispose();
						}
					}catch(NumberFormatException NFE){
						lbl.setText("������ ������ Ʋ�Ƚ��ϴ�. ������ �Է��ϼ���");
						dispose();
						new ScoreFrame();
					}
				}
			});
			add(txt);
			setVisible(true);
		}
	}*/
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
	//���� �߰��� �� �� �κ�
	public static void copy(RankInfo src, RankInfo dst){
		dst.score = src.score;
		dst.name = src.name;
	}
}
/**Ŭ���� ����
 * <br>
 * �ܼ��� �̸��� ������ ������ Ŭ����
 * @author �ڻ��
 */
class RankInfo{
	/**����*/
	int score;
	/**�̸�*/
	String name;
}