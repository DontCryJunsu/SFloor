package GamePlayManager;

/**6스테이지씬 맵 타일
 * @author 박준수
 * */
public class Scene6 extends Scene{
	int Cx = 2*TileSize;
	int Cy = 3*TileSize;
	int Cs = 3;
	Scene6(){
		init();
		map();
	}
	/**맵툴(오브젝트, 열쇠들을 그려주는곳 map배열-1(투명벽),2(파랑벽),3(파랑벽하단),4(문),5(침대),6(캐비넷)*/
	public void map() {
		safe[4][3] = 0;
		safe[5][3] = 2;
		safe[4][4] = 6;
		safe[5][4] = 8;

		safe[4][6] = 0;
		safe[5][6] = 2;
		safe[4][7] = 6;
		safe[5][7] = 8;

		safe[11][5] = 0;
		safe[12][5] = 2;
		safe[11][6] = 6;
		safe[12][6] = 8;

		safe[12][8] = 0;
		safe[13][8] = 2;
		safe[12][9] = 6;
		safe[13][9] = 8;
		for(int i=0;i<24;i++)
			map[i][0] = 2;
		for(int i=0;i<24;i++)
			map[i][1] = 3;
		for(int i=0;i<24;i++)
			map[i][11] = 2;
		for(int i=0;i<12;i++)
			map[0][i] = 2;
		for(int i=0;i<12;i++)
			map[22][i] = 2;
		for(int i=0;i<12;i++)
			map[23][i] = 2;
		map[6][1] = 0;
		map[7][1] = 0;
		map[8][1] = 0;
		map[9][1] = 0;
		map[6][1] = 0;
		map[13][1] = 0;
		map[14][1] = 0;
		map[16][1] = 0;
		map[17][1] = 0;
		map[18][1] = 0;
		map[19][1] = 0;
		map[6][0] = 3;
		map[7][0] = 3;
		map[8][0] = 3;
		map[9][0] = 3;
		map[13][0] = 3;
		map[14][0] = 3;
		map[16][0] = 3;
		map[17][0] = 3;
		map[18][0] = 3;
		map[19][0] = 3;
		map[1][1] = 2;
		map[1][2] = 3;
		map[1][4] = 11;
		map[0][3] = 10;
		map[1][10] = 2;
		map[23][8] = 9;
		map[22][8] = 0;
		map[4][7] = 11;
		map[3][6] = 7;
		map[3][7] = 7;

		map[4][9] = 7;

		map[6][10] = 11;

		map[8][9] = 7;

		map[12][6] = 11;
		map[12][7] = 7;
		map[13][8] = 11;

		map[15][9] = 7;

		map[17][8] = 7;
		map[18][7] = 7;
		map[18][8] = 7;
		map[18][9] = 7;
		map[19][9] = 7;

		map[20][3] = 7;

		map[5][3] = 11;
	}
}