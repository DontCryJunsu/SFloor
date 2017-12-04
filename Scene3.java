package GamePlayManager;

/**3스테이지씬 맵 타일
 * @author 박준수
 * */
public class Scene3 extends Scene{
	int Cx = 2*TileSize;
	int Cy = 6*TileSize;
	int Cs = 4;
	Scene3(){/**맵툴(오브젝트, 열쇠들을 그려주는곳 map배열-1(투명벽),2(파랑벽),3(파랑벽하단),4(문),5(침대),6(캐비넷)*/
	      init();
	      map();
	}
	public void map() {
		for(int i=0;i<24;i++)
			map[i][0] = 2;
		for(int i=0;i<24;i++)
			map[i][1] = 3;
		for(int i=0;i<24;i++)
			map[i][11] = 2;
		for(int i=0;i<12;i++)
			map[0][i] = 2;
		for(int i=0;i<12;i++)
			map[1][i] = 2;
		for(int i=0;i<12;i++)
			map[23][i] = 2;
		for(int i=0;i<12;i++)
			map[22][i] = 2;
		map[1][6] = 0;
		map[22][6] = 0;
		map[23][6] = 9;
		map[0][6] = 10;
		map[5][7] = 11;
		map[6][3] = 2;
		map[6][4] = 3;
		map[6][9] = 7;
		map[7][3] = 2;
		map[7][4] = 3;
		map[7][9] = 7;
		map[1][5] = 3;
		map[22][5] = 3;
		map[20][1] = 2;
		map[20][2] = 3;
		map[16][4] = 2;
		map[16][5] = 3;
		map[17][4] = 2;
		map[17][5] = 3;
		map[18][4] = 2;
		map[18][5] = 3;
		map[18][8] = 2;
		map[18][9] = 3;
		map[19][8] = 2;
		map[19][9] = 3;
		
	}
}
