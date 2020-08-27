package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_벽부수고이동 {

	static int N,M,ans=-1;
	static int[][] map;
	static int[][][] visit;
	static int[] dx= {0,0,-1,1}, dy= {-1,1,0,0};
	static class Struct{
		int x, y, cnt;
		public Struct(int x, int y, int cnt) {
			this.x=x;
			this.y=y;
			this.cnt=cnt;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		map=new int[N][M];
		visit=new int[N][M][2]; //0은 안부숨 1은 부숨
		
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j]=str.charAt(j)-'0';
			}
		}//end input
		
		bfs();
	}//end main()
	
	static void bfs() {
		Queue<Struct> q = new LinkedList<>();
		visit[0][0][0]=1;
		q.add(new Struct(0, 0, 0));//시작부터 counting~
		
		while(!q.isEmpty()) {
			Struct front = q.poll();
			if(front.x==N-1 && front.y==M-1) {
				ans=visit[front.x][front.y][front.cnt];
				break;
			}
			
			for(int i=0;i<4;i++) {
				int nx=front.x+dx[i];
				int ny=front.y+dy[i];
				if(!Range(nx,ny))continue;
				if(map[nx][ny]==0 && visit[nx][ny][front.cnt]==0) {
					visit[nx][ny][front.cnt]=visit[front.x][front.y][front.cnt]+1;//전 경로에 +1
					q.add(new Struct(nx, ny, front.cnt));
				}
				else if(map[nx][ny]==1 && visit[nx][ny][front.cnt]==0 && front.cnt==0) {//벽 이미 1개 부쉈으면 못들어가!
					visit[nx][ny][1]=visit[front.x][front.y][front.cnt]+1; //전 경로에 +1 벽 뚫고서 들어갔음
					q.add(new Struct(nx, ny, 1));//1개 부쉈다.
				}
			}
		}
		
		System.out.println(ans);
	}

	static boolean Range(int x, int y) {
		if(0<=x && 0<=y && x<N && y<M)return true;
		return false;
	}
}
