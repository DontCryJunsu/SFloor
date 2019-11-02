package GamePlayManager;

public class Scene { /**첫번째 스테이지 씬*/
	public static final int TileSize = 50;
	int Cx =6*TileSize;
	int Cy =12*TileSize;
	int Cs = 1;
	static  int map[][] = new int[24][12];
	static  int key[][] = new int[24][12]; 
	Scene() {
		init(); 
		map();    
		key[3][2] = 1; /**key배열 1(열쇠)*/
	}
	public void init() { /**맵전체를 0으로 초기화*/
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
		/**맵툴(오브젝트, 열쇠들을 그려주는곳 map배열-1(투명벽),2(파랑벽),3(파랑벽하단),4(문),5(침대),6(캐비넷)*/
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
