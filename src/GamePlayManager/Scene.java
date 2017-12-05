package GamePlayManager;

/**프롤로그 스테이지씬 맵 타일
 * @author 박준수
 * */
public class Scene {
	public static final int TileSize = 50;
	int Cx =6*TileSize;
	int Cy =12*TileSize;
	int Cs = 1;
	static  int map[][] = new int[24][12];
	static  int key[][] = new int[24][12]; 
	static 	int safe[][] = new int[24][12];
	/**key배열을 1로 설정 1(열쇠)*/
	Scene() {
		init(); 
		map();    
		key[3][2] = 1; 
	}
	/**맵전체를 0으로 초기화*/
	public void init() { 
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
		for(int i=0;i<24;i++) {   
			for(int j =0;j<12;j++){
				safe[i][j] = -1;
			}
		}
	}
	/**맵툴(오브젝트를 그려주는곳 map배열-1(투명벽),2(파랑벽),3(파랑벽하단),4(문),5(침대),6(캐비넷)*/
	public void map() {
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
		map[7][9] = -1;
		map[7][10] = -1;
		map[8][10] = -1;
		map[22][10] = 11;
		map[8][1] = 6;
		map[10][2] = -1;
		map[9][2] = -1;
		map[8][9] = -1;
		safe[6][9] = 0;
		safe[6][10] = 6;
	}
}