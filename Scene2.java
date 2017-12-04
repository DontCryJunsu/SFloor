package GamePlayManager;

/**2스테이지씬 맵 타일
 * @author 박준수
 * */
public class Scene2 extends Scene{
	int Cx = 2*TileSize; // 원래라면 2 / 2 로
	int Cy = 2*TileSize;
	int Cs = 1;
	Scene2(){/**맵툴(오브젝트, 열쇠들을 그려주는곳 map배열-1(투명벽),2(파랑벽),3(파랑벽하단),4(문),5(침대),6(캐비넷)*/
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
		
		map[8][10] = 7; //1*1타일로교체해야함
		map[2][1] = 0;
		map[2][0] = 4;
		map[2][3] = 7;
		map[1][8] = 11;
		map[3][7] = 7;
		map[4][5] = 7;
		map[5][9] = 7;
		
		map[9][6] = 0;
		map[22][6] = 0;
		map[23][6] = 9;
		map[9][1] = 2;
		map[9][5] = 3;
		map[22][1] = 2;
		map[22][5] = 2;
		map[21][10] = 2;
		map[13][10] = 0;
		map[13][11] = 8;
		map[10][9] = 2;
		map[20][4] = 2;
		map[12][4] = 2;
		map[12][3] = 2;
		map[13][3] = 2;
		map[14][3] = 2;
		map[15][3] = 2;
		map[16][3] = 2;
		map[17][3] = 2;
		map[18][3] = 2;
		map[19][3] = 2;
		map[20][3] = 2;

		map[20][5] = 3;
		map[12][5] = 3;
		map[13][4] = 3;
		map[14][4] = 3;
		map[15][4] = 3;
		map[16][4] = 3;
		map[17][4] = 3;
		map[18][4] = 3;
		map[19][4] = 3;
		
		map[15][7] = 2;
		map[15][8] = 3;
		map[16][7] = 2;
		map[16][8] = 3;
		map[19][7] = 2;
		map[19][8] = 3;
		map[20][7] = 2;
		map[20][8] = 3;
		map[22][5] = 3;
		
	}
}
