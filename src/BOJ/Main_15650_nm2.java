package BOJ;

import java.io.*;
import java.util.*;

public class Main_15650_nm2 {

	static int N, M;
	//static boolean[] flag;
	static int[] select,num;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		select = new int[M];
		num = new int[N];// 숫자 저장하는 곳
		//flag = new boolean[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(num);

		dfs(0,0);
		System.out.println(sb);
	}

	static void dfs(int depth, int idx) throws IOException {
		boolean[] visit=new boolean[10001];
		
		if (depth == M) {
			for (int i = 0; i < M; i++)
				sb.append(select[i] + " ");
			sb.append("\n");
			return;
		}

		for (int i = idx; i < N; i++) {
			if(visit[num[i]])continue;
			visit[num[i]]=true;
			select[depth]=num[i];
			dfs(depth+1, i);
		}
	}
}
