package GamePlayManager;

public class Scene1 extends Scene{
	int Cx = 2*TileSize;
	int Cy = 9*TileSize;
	int Cs = 0;
	Scene1(){/**����(������Ʈ, ������� �׷��ִ°� map�迭-1(����),2(�Ķ���),3(�Ķ����ϴ�),4(��),5(ħ��),6(ĳ���)*/
	      init();
	      map();
	}
	public void map()
	{
		for(int i=0;i<24;i++)
			map[i][0] = 3;
		for(int i=0;i<24;i++)
			map[i][10] = 2;
		for(int i=0;i<24;i++)
			map[i][11] = 2;
		for(int i=0;i<12;i++)
			map[0][i] = 2;
		for(int i=0;i<12;i++)
			map[23][i] = 2;
		
		map[2][10] = 0;
		map[20][10] = 0;
		map[1][9] = 2;
		map[2][2] = 2;
		map[3][2] = 2;
		map[3][3] = 2;
		map[11][2] = 2;
		map[10][5] = 2;
		map[11][8] = 2;
		map[18][4] = 2;
		map[18][8] = 2;
		map[19][6] = 2; //1*1Ÿ�Ϸα�ü�ؾ���
		map[21][3] = 2; //1*1Ÿ�Ϸα�ü�ؾ���
	}
}
