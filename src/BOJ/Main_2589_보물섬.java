package BOJ;
import java.io.*;
import java.util.*;


public class Main_2589_보물섬 {

	static int N, M;
	static char[][] map;
	static boolean[][] visit;
	static int dx[] = { -1, 1, 0, 0 }, dy[] = { 0, 0, -1, 1 };

	static class Data {
		int x, y, cnt;

		public Data(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	static int bfs(int x, int y) {
		visit = new boolean[N][M];
		Queue<Data> q = new LinkedList<Data>();
		q.add(new Data(x, y, 0));
		visit[x][y]=true;
		int depth = 0;
		while (!q.isEmpty()) {
			Data front = q.poll();
			if (front.cnt > depth)
				depth = front.cnt; // 최고 깊이 갱신해주자

			for (int i = 0; i < 4; i++) {
				int nx = front.x + dx[i];
				int ny = front.y + dy[i];
				// 범위가 넘어가거나, 이미 방문했거나, 바다일경우 pass
				if (!Range(nx, ny) || visit[nx][ny] || map[nx][ny] == 'W')
					continue;
				visit[nx][ny] = true;
				q.add(new Data(nx, ny, front.cnt + 1));
			}
		}

		return depth;
	}

	static boolean Range(int x, int y) {
		if (0 <= x && 0 <= y && x < N && y < M)
			return true;
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		// logic
		int ans = Integer.MIN_VALUE; // 최종 결과 값은 최단 거리 이동 결과 중 "가장 깊은 depth"
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'W')
					continue; // 바다일 경우 pass
				// 깊이 0으로 초기화
				// dfs탐색 후 depth는 최대 깊이로 바뀌어 있을 것이다.
				int depth = bfs(i, j);
				ans = Math.max(ans, depth); //가장 깊었던 depth로 갈아끼워주자
			}
		}
        
        System.out.println(ans);
	}
} 
