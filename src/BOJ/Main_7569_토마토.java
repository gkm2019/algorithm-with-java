package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.LinkedBlockingDeque;

public class Main_7569_토마토 {

	static int M, N, H;
	static int[] dx = { 0, 0, 0, 0, 1, -1 }, dy = { 0, 0, -1, 1, 0, 0 }, dz = { 1, -1, 0, 0, 0, 0 };
	static int[][][] map;
	static Queue<Data> q;

	static class Data {
		int x, y, z, cnt;

		public Data(int x, int y, int z, int cnt) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[N][M][H];
		q = new LinkedList<>();

		for (int z = 0; z < H; z++) {
			for (int x = 0; x < N; x++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int y = 0; y < M; y++) {
					map[x][y][z] = Integer.parseInt(st.nextToken());
					if (map[x][y][z] == 1) {
						q.add(new Data(x, y, z, 0));
					}
				}
			}
		} // end input

		int ans = 0;
		while (!q.isEmpty()) {
			Data front = q.poll();
			ans = front.cnt;

			for (int i = 0; i < 6; i++) {
				int nx = front.x + dx[i];
				int ny = front.y + dy[i];
				int nz = front.z + dz[i];

				if (!Range(nx, ny, nz) || map[nx][ny][nz] != 0)
					continue;
				map[nx][ny][nz] = front.cnt + 1;
				q.add(new Data(nx, ny, nz, front.cnt + 1));
			}
		}

		boolean flag = check();
		if (!flag)
			ans = -1;
		System.out.println(ans);
	}// end main

	static boolean check() {
		for (int z = 0; z < H; z++) {
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < M; y++) {
					if (map[x][y][z] == 0)
						return false;
				}
			}
		}
		return true;
	}

	static boolean Range(int x, int y, int z) {
		if (0 <= x && 0 <= y && 0 <= z && x < N && y < M && z < H)
			return true;
		return false;
	}
}
