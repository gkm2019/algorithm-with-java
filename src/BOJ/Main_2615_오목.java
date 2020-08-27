package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2615_오목 {

	static int[][] map;
	static int N = 19, ans, X, Y;
	static int[] dx = { 0, 1, 1, 1 }, dy = { 1, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // end input

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0)
					continue;
				ans = check(i, j);
				if (ans == 0)
					continue;
				System.out.println(ans);
				System.out.println(X + " " + Y);
				return;
			}
		}

		System.out.println(0); // 승부 안남...
	}// end main()

	static int check(int x, int y) { // black: 1, white: 2, none: 0
		int color = map[x][y];

		for (int i = 0; i < 4; i++) { // 4가지 방향 탐색 -> 5칸 탐색
			int nx = x - dx[i];
			int ny = y - dy[i];
			if (Range(nx, ny) && map[nx][ny] == color)
				continue;

			int cnt = 0;
			for (int j = 0; j < 6; j++) {
				nx = x + dx[i] * j;
				ny = y + dy[i] * j;
				if (!Range(nx, ny) || map[nx][ny] != color)
					break;
				cnt++;
			}

			if (cnt == 5) {
				X = x + 1;
				Y = y + 1;
				if (i == 3) {
					X = x + dx[i] * 4 + 1;
					Y = y + dy[i] * 4 + 1;
				}
				return color;
			}
		} // end for (i):방향

		return 0;
	}

	static boolean Range(int x, int y) {
		if (0 <= x && 0 <= y && x < N && y < N)
			return true;
		return false;
	}
}
