package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9205_맥주마시면서걸어가기 {
	static int T, N;
	static int[] X, Y;
	static int map[][];
	static boolean[] visit;
	static boolean flag;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine());
			X=new int[N+2];
			Y=new int[N+2];
			map=new int[N+2][N+2];
			visit=new boolean[N+2];
			flag=false;
			
			for(int i=0;i<=N+1;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine()," ");
				X[i]=Integer.parseInt(st.nextToken());
				Y[i]=Integer.parseInt(st.nextToken());
			}
			for(int i=0;i<=N+1;i++) {
				for(int j=0;j<=N+1;j++) {
					int d=Math.abs(X[i]-X[j])+Math.abs(Y[i]-Y[j]);
					map[i][j]=map[j][i]=d;
				}
			}
			
			dfs(0);
			if(flag)
				System.out.println("happy");
			else
				System.out.println("sad");
		}
	}
	
	static void dfs(int v) {
		visit[v]=true;
		if(v==N+1) {
			flag=true;
			return;
		}		
		for(int i=0;i<=N+1;i++) {
			if(visit[i] || map[v][i]==0 || map[v][i]>1000) continue;
			dfs(i);
		}
	}
}
