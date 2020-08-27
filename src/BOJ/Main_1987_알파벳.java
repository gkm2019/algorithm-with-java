package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1987_알파벳 {

	static int R, C, ans = Integer.MIN_VALUE;
	static char[][] map;
	static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };
	static boolean[] alpha;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		alpha = new boolean[26];
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		} // end input

		alpha[map[0][0] - 'A'] = true;
		dfs(0, 0, 1);
		System.out.println(ans);
	}

	static void dfs(int x, int y, int cnt) {
		if (cnt > R * C) return;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (!Range(nx, ny) || alpha[map[nx][ny] - 'A']) continue;
			alpha[map[nx][ny] - 'A'] = true;
			dfs(nx, ny, cnt + 1);
			alpha[map[nx][ny] - 'A'] = false;
		}
		ans = Math.max(ans, cnt);
	}

	static boolean Range(int x, int y) {
		if (0 <= x && 0 <= y && x < R && y < C)
			return true;
		return false;
	}
}
