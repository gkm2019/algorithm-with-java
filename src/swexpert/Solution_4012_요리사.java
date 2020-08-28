package swexpert;

import java.io.*;
import java.util.*;

public class Solution_4012_요리사 {

	static int T, N, ans;
	static int[] select;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			N=Integer.parseInt(br.readLine());
			map=new int[N][N];
			select=new int[N/2];
			ans=Integer.MAX_VALUE;
			
			for(int i=0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine()," ");
				for(int j=0;j<N;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}//end input
			combination(0,0);
			System.out.println("#"+tc+" "+ans);
		}//end TestCase
	}
	
	static void combination(int cnt, int idx) {
		if(cnt==N/2) {
			ans=Math.min(ans, calc());
			return;
		}
		
		for(int i=idx;i<N;i++) {
			select[cnt]=i;
			combination(cnt+1,i+1);
		}
	}
	
	static int calc() {
		boolean[] visit = new boolean[N];
		int[] B = new int[N/2];
		for(int i=0;i<N/2;i++) {
			visit[select[i]]=true;
		}
		int idx=0;
		for(int i=0;i<N;i++) {
			if(visit[i])continue;
			B[idx++]=i;
		}
		int a=0, b=0;
		for(int i=0;i<N/2;i++) {
			for(int j=0;j<N/2;j++) {
				if(i==j)continue;
				a+=map[select[i]][select[j]];
				b+=map[B[i]][B[j]];
			}
		}//end for
		return Math.abs(a-b);
	}
}
