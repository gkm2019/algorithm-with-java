package BOJ;

import java.util.*;
import java.io.*;

public class Main_2458_키순서 {
	//bfs 버전
	static int T, N, M, cnt, ansCnt;
	static ArrayList<Integer>[] down, up;
	static boolean[] visit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// ArrayList map init
		ansCnt = 0;
		up = new ArrayList[N + 1];
		down = new ArrayList[N + 1];
		visit = new boolean[N + 1];
		for (int i = 0; i <= N; i++) {
			up[i] = new ArrayList<Integer>();
			down[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			up[a].add(b);
			down[b].add(a);
		}//end input
		
		

		System.out.println(ansCnt);
	}
}
