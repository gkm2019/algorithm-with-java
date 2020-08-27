package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17070_파이프옮기기 {

	static int n, d, ans;
	static int[] dx = { 0, 1, 1 }, dy = { 1, 1, 0 }; // 가로,대각,아래
	static int[][] map;
	// static boolean[][] visit;

	static public class struct {
		int x, y, d;

		public struct(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		map = new int[n][n];
		// visit = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // end input

		// solution
		solution();
		System.out.println(ans);
	}

	static void solution() {
		Queue<struct> q = new LinkedList<struct>();
		q.add(new struct(0, 1, 0));
		// visit[0][1] = true;

		while (!q.isEmpty()) {
			struct front = q.poll();
			System.out.println(front.x+", "+front.y+" 방향:"+front.d);
			if (front.x == n - 1 && front.y == n - 1) {
				ans++;
				continue;
			}

			int nx, ny;
			for (int i = 0; i < 4; i++) {
				if (i == 3) { // 대각 선 이동 가능함!
					q.add(new struct(front.x + dx[1], front.y + dx[1], 1));
					break;
				}
				nx = front.x + dx[i];
				ny = front.y + dy[i];
				if (!Range(nx, ny) || map[nx][ny] != 0)
					break;
			}

			if (front.x == 1) { // 대각선은 3가지 가능해~ 2개 더 검사

				// 가로 가능?(0)
				nx = front.x + dx[0];
				ny = front.y + dy[0];
				if (Range(nx, ny) && map[nx][ny] == 0)
					q.add(new struct(nx, ny, 0));
				// 아래 가능?(2)
				nx = front.x + dx[2];
				ny = front.y + dy[2];
				if (Range(nx, ny) && map[nx][ny] == 0)
					q.add(new struct(nx, ny, 2));

			} else {// 나머지는 그냥 같은 방향 검사
				nx = front.x + dx[front.d];
				ny = front.y + dy[front.d];
				if (Range(nx, ny) && map[nx][ny] == 0)
					q.add(new struct(nx, ny, front.d));
			}

		} // end while
	}

	static boolean Range(int x, int y) {
		if (0 <= x && 0 <= y && x < n && y < n)
			return true;
		return false;
	}
}
