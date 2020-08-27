package swexpert;
import java.util.*;
import java.io.*;

public class Solution_7733_치즈도둑 {

	static int N,T,ans,cnt;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx= {-1,1,0,0}, dy= {0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			N=Integer.parseInt(br.readLine());
			map=new int[N][N];
			for(int i=0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine()," ");
				for(int j=0;j<N;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}//end for input
			
			ans=1;//0일 일때 한 덩어리			
			for(int day=1;day<=100;day++) {
				visit = new boolean[N][N];
				cnt=0;//영역 갯수 counting
				
				for(int i=0;i<N;i++) {
					for(int j=0;j<N;j++) {
						if(map[i][j]<=day || visit[i][j])continue;
						cnt++;
						dfs(i,j,day);
					}
				}
				ans=Math.max(ans, cnt);
			}//end for loop
			
			System.out.println("#"+tc+" "+ans);
		}//end for test_case
	}//end main
	
	static boolean Range(int x, int y) {
		if(0<=x && 0<=y && x<N && y<N)
			return true;
		return false;
	}
	
	static void dfs(int x, int y, int day) {
		visit[x][y]=true;
		for(int d=0;d<4;d++) {
			int nx=x+dx[d];
			int ny=y+dy[d];
			
			if(!Range(nx,ny) || visit[nx][ny] || map[nx][ny]<=day)continue;
			dfs(nx,ny,day);
		}
	}
}
