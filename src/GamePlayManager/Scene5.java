package GamePlayManager;

/**5스테이지씬 맵 타일
 * @author 박준수
 * */
public class Scene5 extends Scene{
	int Cx = 2*TileSize;
	int Cy = 2*TileSize;
	int Cs = 1;
	Scene5(){
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
			map[23][i] = 2;
		for(int i=0;i<12;i++)
			map[22][i] = 2;
		safe[20][8] = 2;
		map[2][1] = 0;
		map[22][8] = 3;
		map[22][9] = 0;
		map[23][9] = 9;
		map[2][0] = 4;
		map[1][7] = 7;
		map[2][7] = 7;
		map[3][9] = 12;
		map[3][10] = -1;
		map[6][2] = 2;
		map[6][3] = 3;
		map[8][7] = 6;
		map[10][7] = -1;
		map[10][8] = -1;
		map[9][8] = 3;
		map[21][2] = 2;
		map[21][3] = 3;
		map[20][8] = 11;
		map[6][1] = 2;
		map[21][1] = 2;
		map[8][8] = -1;
		safe[2][3] = 0;
		safe[3][3] = 1;
		safe[4][3] = 2;
		safe[2][4] = 3;
		safe[3][4] = 4;
		safe[4][4] = 5;
		safe[2][5] = 6;
		safe[3][5] = 7;
		safe[4][5] = 8;
		
		safe[11][7] = 2;
		safe[11][8] = 8;
		
		safe[19][8] = 0;
		safe[19][9] = 6;
		safe[20][9] = 8;
	}
}