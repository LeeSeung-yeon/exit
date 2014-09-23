package com.example.uiexample2;

import android.widget.Toast;

public class MapPath {
	public Map r;
	public boolean[][] wall;

	public int a;
	public int[] xx;
	public int[] yy;

	int ST;
	int EN;
	int X, Y, K;

	int h, g; 

	int[][] complete;

	public int counter;

	public MapPath(){

		r = new Map();
		wall = r.newWall();
		counter=0;

		int ST = 8000;
		int EN = 0;
		int X = 20, Y = 48, K=8;

		int[] x = { 1, 2, 3, 4, 1, 2, 3, 4}; 
		int[] y = { 0, 0, 0, 0,47,47,47,47}; 

		int[][][] outs = new int[K][][];

		for(int k=0;k<K;k++){
			int[][] map = new int[X][Y]; 

			for(int a=0; a<X; a++){
				for(int b=0; b<Y; b++){
					if(wall[a][b]==true) map[a][b] = X*Y;
					else map[a][b]=-1;
				}
			}
			map[x[k]][y[k]]=EN;
			map[h][g]=ST;

			for(int step=0; step<X*Y; step++)
			{             
				for(int i=0; i<X; i++)
				{
					for(int j=0; j<Y; j++)
					{
						if( map[i][j] == step)
						{      
							if(i+1<X&&map[i+1][j]==-1)                         
								map[i+1][j]= step+1;
							if(i-1>=0&&map[i-1][j]==-1)
								map[i-1][j]= step+1;
							if(j+1<Y&&map[i][j+1]==-1)
								map[i][j+1]= step+1;
							if(j-1>=0&&map[i][j-1]==-1)
								map[i][j-1]= step+1;
						}
					}
				}
			}
			outs[k] = map;
		}

		complete = new int[X][Y];
		for(int n=0;n<X;n++)
			for(int m=0;m<Y;m++){
				complete[n][m]=outs[0][n][m];
			}



		for(int k=1;k<K;k++){
			for(int n=0;n<X;n++)
				for(int m=0;m<Y;m++){
					if(complete[n][m]
							>outs[k][n][m])
						complete[n][m]=outs[k][n][m];
				}
		}

		xx = new int[X*Y];
		yy = new int[X*Y];

		a = 0;
	}
	public void ShortestPath(int ax,int ay){
		counter++;
		if (counter%20000 == 0) {

			int h = ax, g =ay; 

			while (complete[h][g]!=EN)
			{
				int tmp = complete[h][g];
				if((h+1<X)&&(complete[h+1][g] < tmp)&&complete[h+1][g]!=X*Y) {   
					h=h+1;
					a++;

					xx[a]=h;
					yy[a]=g;
				}
				else if((h-1>=0)&&(complete[h-1][g] < tmp)&&complete[h-1][g]!=X*Y){
					h=h-1;
					a++;

					xx[a]=h;
					yy[a]=g;
				}
				else if((g+1<Y)&&(complete[h][g+1] < tmp)&&complete[h][g+1]!=X*Y){
					g=g+1;
					a++;

					xx[a]=h;
					yy[a]=g;
				}
				else if(g-1>=0&&complete[h][g-1]!=X*Y){
					g=g-1;
					a++;

					xx[a]=h;
					yy[a]=g;
				}
			}
		}
	}
}
