package BOJ;

import java.util.*;
import java.io.*;

public class Main_4963_섬의갯수 {
	static int w, h, ans;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = { 0, 0, -1, 1, -1, -1, 1, 1 }, dy = { -1, 1, 0, 0, -1, 1, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if (w == 0 && h == 0)
				return;
			map = new int[h][w];
			visit = new boolean[h][w];
			ans = 0;
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // end input

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] == 0 || visit[i][j])
						continue;
					ans++;
					bfs(i, j);
				}
			}
			System.out.println(ans);
		}
	}

	static boolean Range(int x, int y) {
		if(0<=x && 0<=y && x<h && y<w)
			return true;
		return false;
	}
	static void bfs(int x, int y) {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(x, y));

		while (!q.isEmpty()) {
			Pair front = q.poll();

			for (int d = 0; d < 8; d++) {
				int nx=front.x+dx[d];
				int ny=front.y+dy[d];
				
				if(!Range(nx,ny)||visit[nx][ny]||map[nx][ny]==0)continue;
				visit[nx][ny]=true;
				q.add(new Pair(nx,ny));
			}
		}
	}

	static class Pair {
		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
