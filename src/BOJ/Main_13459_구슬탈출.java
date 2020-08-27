package BOJ;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.io.*;

public class Main_13459_구슬탈출 {

	static int N, M;
	static char[][] map;
	static int ans;
	static int[] dx= {0,0,-1,1}, dy= {-1,1,0,0};
	static Struct start;
	static Pair hole, R, B;
	static boolean[][][][] visit;

	static class Struct {
		int rx, ry, bx, by;

		Struct(int rx, int ry, int bx, int by) {
			this.rx=rx;
			this.ry=ry;
			this.bx=bx;
			this.by=by;
		}
	}
	
	static class Pair{
		int x,y;
		Pair(int x, int y){
			this.x=x;
			this.y=y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visit = new boolean[N][M][N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'O') {
					hole.x=i;
					hole.y=j;
				} else if (map[i][j] == 'R') {
					R.x = i;
					R.y = j;
				} else if (map[i][j] == 'B') {
					B.x = i;
					B.y = j;
				}
			}
		} // end input
		ans = 1;

		start.rx=R.x; start.ry=R.y; start.bx=B.x; start.by=B.y;
		Move();
	}//end main
	
	static void Move() {
		Queue<Struct> q = new LinkedList<>();
		visit[start.rx][start.ry][start.bx][start.by]=true;
		q.add(new Struct(start.rx, start.ry, start.bx, start.by));
		
		while(!q.isEmpty()) {
			Struct front=q.poll();
			if(front.bx==hole.x && front.by==hole.y)continue;
			if(front.rx==hole.x && front.ry==hole.y) {
				System.out.println(1);
				return;
			}
			
			for(int d=0;d<4;d++) {
				int nrx=front.rx+dx[d];
				int nry=front.ry+dy[d];
				int nbx=front.bx+dx[d];
				int nby=front.by+dy[d];
				
				if(!Range(nrx,nry,nbx,nby)||visit[nrx][nry][nbx][nby])continue;
				//if([nrx][nry][nbx][nby]=='#')continue;
				
			}
		}
		System.out.println(0);
	}//end Move
	
	static boolean Range(int rx, int ry, int bx, int by) {
		if(0<=rx && 0<=ry && 0<=bx && 0<=by && rx<N && ry<M && bx<N && by<M)
			return true;
		return false;
	}
}
