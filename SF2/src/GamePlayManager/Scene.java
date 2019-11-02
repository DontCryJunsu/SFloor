package GamePlayManager;

public class Scene { /**ù��° �������� ��*/
	public static final int TileSize = 50;
	int Cx =6*TileSize;
	int Cy =12*TileSize;
	int Cs = 1;
	static  int map[][] = new int[24][12];
	static  int key[][] = new int[24][12]; 
	Scene() {
		init(); 
		map();    
		key[3][2] = 1; /**key�迭 1(����)*/
	}
	public void init() { /**����ü�� 0���� �ʱ�ȭ*/
		for(int i=0;i<24;i++) {

			for(int j =0;j<12;j++){
				map[i][j] = 0;
			}
		}
		for(int i=0;i<24;i++) {   
			for(int j =0;j<12;j++){
				key[i][j] = 0;
			}
		}
	}
	public void map() {
		/**����(������Ʈ, ������� �׷��ִ°� map�迭-1(����),2(�Ķ���),3(�Ķ����ϴ�),4(��),5(ħ��),6(ĳ���)*/
		map[8][2] = -1;
		map[7][8] = 5;
		for(int i=0;i<24;i++)
			map[i][0] = 2;
		for(int i=0;i<24;i++)
			map[i][1] = 3;
		for(int i=0;i<24;i++)
			map[i][11] = 2;
		for(int i=0;i<12;i++)
			map[0][i] = 2;
		for(int i=0;i<12;i++)
			map[23][i] = 2;
		map[21][0] = 4;
		map[21][1] = 0;
		map[7][10] = -1;
		map[8][10] = -1;
		map[22][10] = 2;
		map[8][1] = 6;
		map[10][2] = -1;
		map[9][2] = -1;
		map[8][9] = -1;
		
	}
}
