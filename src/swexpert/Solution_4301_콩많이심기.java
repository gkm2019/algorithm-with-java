package swexpert;
import java.util.*;
import java.io.*;

public class Solution_4301_콩많이심기 {

	static int T,N,M;
	static boolean[][] map;
	static int[] dx= {0,0,-2,2}, dy= {2,-2,0,0};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			map = new boolean[M][N];
			int ans=0;			
			
			for(int i=0;i<M;i++) {
				for(int j=0;j<N;j++) {
					if(map[i][j])continue;
					boolean flag=true;
					
					for(int d=0;d<4;d++) {
						int nx=i+dx[d];
						int ny=j+dy[d];
						
						if(!Range(nx,ny))continue;
						if(map[nx][ny]) {
							flag=false;
							break;
						}
					}// 2거리 탐색 반복
					if(flag) {
						map[i][j]=true;
						ans++;
					}
				}
			}
			
			System.out.println("#"+tc+" "+ans);
		}//end for input
	}
	static boolean Range(int x, int y) {
		if(0<=x && 0<=y && x<M && y<N)return true;
		return false;
	}
}
