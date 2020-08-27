package swexpert;

import java.util.*;
import java.io.*;

public class Solution_5643_키순서 {

	static int T, N, M, cnt, ansCnt;
	static ArrayList<Integer>[] down, up;
	static boolean[] visit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
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
			}

			for (int i = 1; i <= N; i++) { // 1번부터 n번까지 다 탐색해본다.
				Arrays.fill(visit, false);
				cnt = 1; // 자기 자신 부터 카운팅
				dfs(up, i);
				dfs(down, i);
				if (cnt == N)
					ansCnt++;
			}

			System.out.println("#" + tc + " " + ansCnt);
		} // end for test_case
	}

	static void dfs(ArrayList<Integer>[] arr, int v) {
		visit[v] = true;
		for (int m : arr[v]) {
			if (visit[m])
				continue;
			cnt++;
			dfs(arr, m);
		}
	}
}
