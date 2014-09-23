package com.example.uiexample2;


public class Map {

	boolean[][] newWall(){

		boolean[][] wall = new boolean[20][48];
		int i, j;
		//벽정보 초기화 
		for(i = 0; i < 20; i++)
			for(j = 0; j < 48; j++)
				wall[i][j] = false;
		//벽정보 입력
		for(j=0;j<48;j++){
			wall[0][j] = true;
			wall[19][j] = true;
		}
		for(i=5;i<19;i++){
			wall[i][0] = true;
			wall[i][4] = true;
			wall[i][20] = true;
			wall[i][40] = true;
			wall[i][47] = true;
		}
		wall[11][20] = false;
		wall[12][20] = false;
		wall[10][40] = false;
		wall[11][40] = false;
		wall[12][40] = false;
		
		for(j=20;j<34;j++){
			wall[3][j] = true;
		}
		wall[3][13] = true;
		wall[3][14] = true;
		
		for(i=4;i<11;i++){
			wall[i][20] = true;
			wall[i][24] = true;
			wall[i][28] = true;
			wall[i][32] = true;
		}
		wall[4][11] = true;
		wall[4][12] = true;
		wall[4][14] = true;
		wall[4][33] = true;

		wall[5][1] =true;
		wall[5][2] =true;
		wall[5][5] =true;
		wall[5][6] =true;
		wall[5][7] =true;
		wall[5][8] =true;
		wall[5][9] =true;
		wall[5][10] =true;
		wall[5][15] =true;
		wall[5][16] =true;
		wall[5][17] =true;
		wall[5][19] =true;
		for(j=33;j<45;j++)
			wall[5][j] =true;

		wall[6][7] = true;
		wall[6][10] = true;
		wall[6][15] = true;
		
		wall[7][11] = true;
		wall[7][15] = true;
		
		wall[8][11] = true;
		wall[8][15] = true;
		
		wall[9][11] = true;
		wall[9][15] = true;
		
		wall[10][12] = true;
		wall[10][13] = true;
		wall[10][22] = true;
		wall[10][23] = true;
		wall[10][24] = true;
		wall[10][25] = true;
		wall[10][26] = true;
		wall[10][30] = true;
		wall[10][31] = true;
		
		for(i=13;i<20;i++)
			wall[i][11] = true;
		
		for(j=12;j<16;j++)
			wall[14][j] = true;
		
		wall[15][5] = true;
		wall[15][6] = true;
		wall[15][7]= true;
		wall[15][16] = true;
		
		return wall;
	}


}
