package GamePlayManager;

public class Scene2 extends Scene{
	int Cx = 2*TileSize;
	int Cy = 2*TileSize;
	int Cs = 1;
	Scene2(){/**����(������Ʈ, ������� �׷��ִ°� map�迭-1(����),2(�Ķ���),3(�Ķ����ϴ�),4(��),5(ħ��),6(ĳ���)*/
	      init();
	      map();
	}
	public void map() {
		for(int i=0;i<24;i++)
			map[i][0] = 2;
		for(int i=0;i<23;i++)
			map[i][1] = 3;
		for(int i=0;i<24;i++)
			map[i][11] = 2;
		for(int i=0;i<12;i++)
			map[0][i] = 2;
		for(int i=0;i<12;i++)
			map[23][i] = 2;
		for(int i =0;i<12;i++)
			map[i+9][10] = 2;
		for(int i = 0;i<9;i++)
			map[22][i+2] = 2;
		for(int i = 0;i<8;i++)
			map[9][i+2] = 2;
		map[8][10] = 1; //1*1Ÿ�Ϸα�ü�ؾ���
		map[2][1] = 0;
		map[2][0] = 4;
		map[2][3] = 2;
		map[1][8] = 2;
		map[3][7] = 2;
		map[4][5] = 2;
		map[5][9] = 2;
		map[9][6] = 0;
		map[22][6] = 0;
		map[9][1] = 2;
		map[9][5] = 3;
		map[22][1] = 2;
		map[22][5] = 2;
		map[21][10] = 2;
	}
}
