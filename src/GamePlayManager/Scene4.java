package GamePlayManager;

/**4스테이지씬 맵 타일
 * @author 박준수
 * */
public class Scene4 extends Scene{
	int Cx = 2*TileSize;
	int Cy = 6*TileSize;
	int Cs = 3;
	Scene4(){
	      init();
	      map();
	}
	/**맵툴(오브젝트, 열쇠들을 그려주는곳 map배열-1(투명벽),2(파랑벽),3(파랑벽하단),4(문),5(침대),6(캐비넷)*/
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
		for(int i=4;i<9;i++)
			map[4][i] = 2;
		map[0][6] = 10;
		map[1][6] = 0;
		map[1][5] = 3;
		map[2][5] = 11;
		map[5][6] = 3;
		map[4][9] = 3;
		map[5][5] = 2;
		map[5][4] = 2;
		map[6][4] = 7;
		map[6][8] = 7;
		map[18][6] = 7;
		map[7][10] = 7;
		map[8][7] = 11;
		map[9][7] = 2;
		map[9][8] = 2;
		map[9][9] = 3;
		map[11][8] = 7;
		map[12][5] = 7;
		map[13][10] = 7;
		map[15][9] = 7;
		map[16][9] = 7;
		map[16][5] = 2;
		map[17][5] = 2;
		map[16][6] = 3;
		map[17][6] = 3;
		map[18][10] = 7;
		map[21][7] = 7;
		map[22][4] = 11;
	}
}