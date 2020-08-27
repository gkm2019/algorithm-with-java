package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_7793_오나의여신님 {

	static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };
	static int T, N, M;
	static char[][] map;
	static boolean[][] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			map=new char[N][M];
			visit=new boolean[N][M];
			
			for(int i=0;i<N;i++) {
				String str = br.readLine();
				for(int j=0;j<M;j++) {
					map[i][j]=str.charAt(j);
				}
			}//end input map
			
			
			System.out.println("#"+tc+" ");
		}//end Test Case
	}

}
