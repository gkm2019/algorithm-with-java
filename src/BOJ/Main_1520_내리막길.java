package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1520_내리막길 {

	static int M, N;
	static int[][] map,dis;
	static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		dis = new int[M][N];

		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				dis[i][j]=-1;
			}
		}//end input
		
		int ans = dfs(0,0);
		System.out.println(ans);
	}

	static int dfs(int x, int y) {
		if(dis[x][y]!=-1)return dis[x][y];
		if(x==M-1 && y==N-1)return 1; //도착했으니까 counting + 1
		
		//방문안했었으면 방문시키자
		dis[x][y]=0;
		for(int i=0;i<4;i++) {
			int nx=x+dx[i];
			int ny=y+dy[i];
			if(!Range(nx,ny))continue;
			if(map[x][y]<=map[nx][ny])continue;
			dis[x][y]+=dfs(nx,ny);
		}
		return dis[x][y];
	}
	static boolean Range(int x, int y) {
		if (0 <= x && 0 <= y && x < M && y < N)
			return true;
		return false;
	}

}
