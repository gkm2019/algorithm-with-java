package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_7699_수지의수지맞은여행 {

	static int T, R, C, ans;
	static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };
	static char[][] map;
	static boolean[] alpha;
	// static boolean[][] visit;

//	static class Pair {
//		int x, y, cnt;
//
//		public Pair(int x, int y, int cnt) {
//			this.x = x;
//			this.y = y;
//			this.cnt = cnt;
//		}
//	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			map = new char[R][C];
			alpha = new boolean[26]; // 명물 방문 체크!
			ans = 0;
			for (int i = 0; i < R; i++) {
				String str = br.readLine();
				for (int j = 0; j < C; j++) {
					map[i][j] = str.charAt(j);
				}
			} // end input

			// logic
			alpha[map[0][0] - 'A'] = true;
			dfs(0, 0, 1);
			System.out.println("#" + tc + " " + ans);
		} // end testCase
	}// end main()

	static void dfs(int x, int y, int cnt) {
		if (cnt > R * C)
			return; // 범위 나감
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (!Range(nx, ny) || alpha[map[nx][ny] - 'A'])continue;
			alpha[map[nx][ny] - 'A'] = true;
			dfs(nx, ny, cnt + 1);
			alpha[map[nx][ny] - 'A'] = false;
		}
		
		ans=Math.max(cnt, ans);
	}

	static boolean Range(int x, int y) {
		if (0 <= x && 0 <= y && x < R && y < C)return true;
		return false;
	}
}
