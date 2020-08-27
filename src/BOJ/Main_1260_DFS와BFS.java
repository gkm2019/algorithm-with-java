package BOJ;

import java.util.*;
import java.io.*;

public class Main_1260_DFS와BFS {

	static int n, m, v;
	static int map[][];
	static boolean visit[];

	public static void dfs(int v) {
		System.out.print(v + " ");
		visit[v] = true;
		for (int i = 1; i <= n; i++) {
			if (map[v][i] == 1 && visit[i] == false) {// 연결 된 간선이 나타난다면
				dfs(i);
			}
		}
		return;
	}

	public static void bfs(int v) {
		Queue<Integer> q = new LinkedList<>();
		System.out.print(v + " ");
		visit[v] = true;
		q.offer(v);

		while (!q.isEmpty()) {
			int newNode = q.poll();
			for (int i = 1; i <= n; i++) {
				if (map[newNode][i] == 1 && visit[i] == false) { // newNode와 연결된 노드들은 다 넣는다.
					System.out.print(i + " ");
					visit[i] = true;
					q.offer(i);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());

		map = new int[n + 1][n + 1];
		visit = new boolean[n + 1];
		int s, e;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			map[s][e] = 1;
			map[e][s] = 1;
		} // end for input

		dfs(v);
		System.out.println();
		Arrays.fill(visit, false); // bfs를 위해 방문 배열 다시 초기화
		bfs(v);
	}
}