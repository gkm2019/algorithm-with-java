package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11559_puyo {

	static int N, M, cnt;
	static char[][] map;
	static boolean[][] visit;
	static int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = 12;
		M = 6;
		map = new char[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		int ans = 0;
		while (true) {
			visit = new boolean[N][M];
			boolean flag = false;
			for (int x = N - 1; x >= 0; x--) {
				for (int y = 0; y < M; y++) {
					if (visit[x][y] || map[x][y] == '.')
						continue;
					cnt = 1;
					dfs(x, y, map[x][y], map[x][y]); // 탐색
					if (cnt < 4)
						continue;
					flag = true;
					dfs(x, y, '.', map[x][y]); // 삭제
				}
			}
			if (!flag)
				break;
			// 4개 이상이면 제거하기
			for (int x = N - 1; x >= 0; x--) {
				for (int y = 0; y < M; y++) {
					if (map[x][y] == '.')
						continue;
					moveDown(x, y);
				}
			}
			ans++;
		} // end while()

		System.out.println(ans);
	}

	static void moveDown(int x, int y) {
		char val = map[x][y];
		if (x + 1 >= N)
			return;
		map[x][y] = '.';
		int nx = x + 1;
		while (nx < N && map[nx][y] == '.') {
			nx++;
		}
		map[nx - 1][y] = val;
	}

	// val=='.'이면 삭제버전
	// val!='.'이면 탐색 버전
	static void dfs(int x, int y, char val, char c) {
		visit[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (!Range(nx, ny) || map[nx][ny] != c) continue;
			if (val != '.' && visit[nx][ny]) continue; // 탐색버전인데 이미 방문했으면 out

			map[nx][ny] = val;
			cnt++;
			dfs(nx, ny, val, c);
		}
	}

	static boolean Range(int x, int y) {
		if (0 <= x && 0 <= y && x < N && y < M)
			return true;
		return false;
	}
}
