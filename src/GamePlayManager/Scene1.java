package GamePlayManager;

public class Scene1 extends Scene{
	int Cx = 9*TileSize;
	int Cy = 9*TileSize;
	Scene1(){
	      init();
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
	}
}
