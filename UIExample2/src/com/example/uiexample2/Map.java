package com.example.uiexample2;


public class Map {

	boolean[][] newWall(){

		boolean[][] wall = new boolean[50][150];
		int i, j;
		for(i = 0; i < 50; i++)
			for(j = 0; j < 150; j++)
				wall[i][j] = false;

		for(i = 0; i < 2; i++)
			for(j = 0; j < 150; j++)
				wall[i][j] = true;

		for(i = 12 ; i < 50; i++)
			wall[i][149] = true;

		wall[9][47] = true;
		wall[9][48] = true;

		for(j = 43; j < 49; j++)
			wall[10][j] = true;
		for(j = 68; j < 104; j++)
			wall[10][j] = true;

		for(j = 40; j < 44; j++)
			wall[11][j] = true;
		wall[11][48] = true;
		wall[11][49] = true;

		for(j = 37; j < 50; j++)
			wall[12][j] = true;

		for(j = 0; j < 13; j++)
			wall[13][j] = true;
		for(j = 17; j < 38; j++)
			wall[13][j] = true;
		wall[13][49] = true;

		for(j = 0; j < 4; j++)
			wall[14][j] = true;
		wall[14][17] = true;
		for(j = 29; j < 32; j++)
			wall[14][j] = true;
		wall[14][38] = true;

		for(j = 0; j < 4; j++)
			wall[15][j] = true;
		wall[15][17] = true;
		for(j = 29; j < 32; j++)
			wall[15][j] = true;
		wall[15][38] = true;
		wall[15][39] = true;
		for(j = 42; j < 46; j++)
			wall[15][j] = true;

		for(j = 0; j < 4; j++)
			wall[16][j] = true;
		wall[16][17] = true;
		for(j = 29; j < 32; j++)
			wall[16][j] = true;
		wall[16][38] = true;
		wall[16][39] = true;
		for(j = 42; j < 46; j++)
			wall[16][j] = true;

		for(i = 17; i < 50; i++)
			for(j = 0; j < 3; j++)
				wall[i][j] = true;

		for(i = 17; i < 50; i++)
			wall[i][17] = true;

		wall[17][38] = true;
		wall[17][39] = true;
		for(j = 42; j < 47; j++)
			wall[17][j] = true;

		wall[18][38] = true;
		wall[18][39] = true;
		for(j = 43; j < 47; j++)
			wall[18][j] = true;

		wall[19][38] = true;
		wall[19][39] = true;
		for(j = 43; j < 47; j++)
			wall[19][j] = true;

		wall[20][39] = true;
		wall[20][40] = true;
		for(j = 43; j < 48; j++)
			wall[20][j] = true;

		wall[21][39] = true;
		wall[21][40] = true;
		for(j = 44; j < 48; j++)
			wall[21][j] = true;

		wall[22][39] = true;
		wall[22][40] = true;

		wall[23][39] = true;
		wall[23][40] = true;

		wall[24][40] = true;

		wall[25][39] = true;
		wall[25][40] = true;

		wall[26][40] = true;
		wall[26][41] = true;
		wall[26][48] = true;

		wall[27][40] = true;
		wall[27][41] = true;
		for(j = 44; j < 48; j++)
			wall[27][j] = true;

		for(j = 40; j < 45; j++)
			wall[28][j] = true;

		wall[29][41] = true;

		for(i = 33; i < 48; i++)
			wall[i][41] = true;

		for(i = 36; i < 40; i++)
			for(j = 42; j < 50; j++)
				wall[i][j] = true;

		wall[46][3] = true;
		wall[46][4] = true;
		for(j = 29; j < 32; j++)
			wall[46][j] = true;

		wall[47][3] = true;
		wall[47][4] = true;
		for(j = 29; j < 32; j++)
			wall[46][j] = true;

		for(j = 39; j < 50; j++)
			wall[47][j] = true;


		for(j=68;j<100;j++){
			wall[9][j] = true;
		}
		
		for(i=9;i<27;i++){
			wall[i][68] = true;
			wall[i][81] = true;
			wall[i][82] = true;
			wall[i][83] = true;
			wall[i][94] = true;
		}
		for(i=13;i<21;i++){
			wall[i][74] = true;
			wall[i][75] = true;
			wall[i][76] = true;
			wall[i][88] = true;
			wall[i][89] = true;
			wall[i][90] = true;
			wall[i][99] = true;
		}
		for(j=50;j<60;j++){
			wall[13][j] = true;
		}for(j=65;j<69;j++){
			wall[13][j] = true;
		}
		for(i=13;i<19;i++){
			wall[i][50] = true;
		}
		for(i=18;i<22;i++){
			wall[i][51] = true;
		}
		for(j=54;j<57;j++){
			wall[14][j] = true;
			wall[15][j] = true;
		}
		for(i=15;i<27;i++){
			wall[i][66] = true;
			wall[i][67] = true;
		}
		
		for(j=72;j<90;j++){
			wall[26][j] = true;
		}
		
		for(j=50;j<57;j++){
			wall[36][j] = true;
			wall[37][j] = true;
			wall[38][j] = true;
			wall[39][j] = true;
			wall[47][j] = true;
		}
		for(j=54;j<58;j++){
			wall[40][j]=true;
			wall[46][j]=true;
		}
		for(i=33;i<48;i++){
			wall[i][68] = true;
		}
		for(i=36;i<44;i++){
			wall[i][76] = true;
			wall[i][77] = true;
			wall[i][78] = true;

			wall[i][88] = true;
			wall[i][89] = true;
			wall[i][90] = true;
			wall[i][99] = true;
		}
		for(j=80;j<83;j++){
			wall[46][j] = true;
			wall[47][j] = true;
		}
		for(j=100;j<105;j++){
			wall[9][j]=true;
		}
		for(i=9;i<13;i++){
			wall[i][104] = true;
		}
		for(i=13;i<21;i++){
			wall[i][100] = true;
			wall[i][101] = true;
		}
		
		
		
		

		for(i = 48; i < 50; i++)
			for(j = 0; j < 150; j++)
				wall[i][j] = true;


		for(i = 0; i < 50; i++){
			for(j = 0; j < 150; j++){
				String temp=" ";
				if(wall[i][j]==true) temp = "T";
				System.out.print(temp);
			}System.out.println();
		}
		
		return wall;
	}


}
