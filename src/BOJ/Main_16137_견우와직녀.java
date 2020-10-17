package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16137_견우와직녀 {

	static int N, M;
	static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };
	static int[][] map;
	static int[][][] dis;

	static class Struct {
		int x, y, k;

		public Struct(int x, int y, int k) {
			this.x = x;
			this.y = y;
			this.k = k;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		dis = new int[N][N][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // end input

		// init distance to INF
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < 2; k++) {
					dis[i][j][k] = Integer.MAX_VALUE;
				}
			}
		}

		int ans = bfs();
		System.out.println(ans);
	}

	public static int bfs() {
		int cnt = 0;
		Queue<Struct> q = new LinkedList<Struct>();
		dis[0][0][0] = 0;
		q.add(new Struct(0, 0, 0));

		while (!q.isEmpty()) {
			Struct now = q.poll();
			int nowTime = dis[now.x][now.y][now.k];

			if (now.x == N - 1 && now.y == N - 1)
				return nowTime;

			for (int d = 0; d < 4; d++) {
				int nx = now.x + dx[d];
				int ny = now.y + dy[d];

				if (!Range(nx, ny))
					continue;
				if (map[nx][ny] == 1
						|| (map[nx][ny] >= 2 && (nowTime + 1) % map[nx][ny] == 0 && map[now.x][now.y] == 1)) {
					// 땅이거나, 오작교 밟을 수 있다면(직전에 땅을 밟았어야함 : 두번 연속 오작교 X)
					if (dis[nx][ny][now.k] > nowTime + 1) {
						dis[nx][ny][now.k] = nowTime + 1;
						q.add(new Struct(nx, ny, now.k));
					}
				} else if (map[nx][ny] == 0 && now.k == 0 && isOkBridge(nx, ny) && (nowTime + 1) % M == 0
						&& map[now.x][now.y] == 1) {
					// 오작교 직접 설치하기
					// 절벽인데, 오작교 남아있고, 다리 놓을 수 있고, 주기도 맞고, 직전에 땅이었어야함(두번 연속 오작교 X)
					if (dis[nx][ny][1] > nowTime + 1) {
						dis[nx][ny][1] = nowTime + 1;
						q.add(new Struct(nx, ny, 1));
					}
				} else if (map[now.x][now.y] == 1) { // 아무곳도 갈 수 없는 경우 땅(1)에서 시간 때우기
					dis[now.x][now.y][now.k] = nowTime + 1;
					q.add(new Struct(now.x, now.y, now.k));
				}

			}
		}
		return cnt;
	}

	public static boolean isOkBridge(int x, int y) {
		// 교차 지점인지 확인한다.
		int uX = x + dx[0], uY = y + dy[0];
		int dX = x + dx[1], dY = y + dy[1];
		int lX = x + dx[2], lY = y + dy[2];
		int rX = x + dx[3], rY = y + dy[3];

		if (Range(lX, lY) && map[lX][lY] == 0) {
			if (Range(uX, uY) && map[uX][uY] == 0)
				return false;
			if (Range(dX, dY) && map[dX][dY] == 0)
				return false;
		}
		if (Range(rX, rY) && map[rX][rY] == 0) {
			if (Range(uX, uY) && map[uX][uY] == 0)
				return false;
			if (Range(dX, dY) && map[dX][dY] == 0)
				return false;
		}
		return true;
	}

	public static boolean Range(int x, int y) {
		if (0 <= x && 0 <= y && x < N && y < N)
			return true;
		return false;
	}

}
