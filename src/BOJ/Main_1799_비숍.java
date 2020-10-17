package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1799_비숍 {

	static int N;
	static int[] dx = { -1, -1, 1, 1 }, dy = { -1, 1, -1, 1 }, ans;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		ans = new int[2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // end input
		ans[0] = Integer.MIN_VALUE;
		ans[1] = Integer.MIN_VALUE;
		
		dfs(0, 0, 0);
		dfs(0, 0, 1);
		System.out.println(ans[0]+ans[1]);
	}

	public static void print() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("===");
		System.out.println();
	}

	public static void dfs(int depth, int cnt, int color) { // 가능 : 1, 불가능 : 0, 비숍 : 2
		if (depth >= N * N) {
			ans[color] = Math.max(ans[color], cnt);
			return;
		}
		boolean flag=false;
		int x = depth / N, y = depth % N;
		if(color==0 && (x%2)==(y%2)) flag=true;
		if(color==1 && (x%2)!=(y%2)) flag=true;
		
		if (!flag) {
			if (y == 0) {
				y++;
				depth++;
			} else {
				y--;
				depth--;
			}
		}

		if (map[x][y]==1 && isOk(x, y)) { // 비숍 놓기 
			map[x][y] = 2;
			dfs(depth + 2, cnt + 1, color);
			map[x][y] = 1;
		}
		dfs(depth + 2, cnt, color);
	}

	public static boolean isOk(int x, int y) {
		for (int k = 1; k <= N; k++) {
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d] * k;
				int ny = y + dy[d] * k;
				if (!Range(nx, ny)) continue;
				if (map[nx][ny] == 2) return false;
			}
		}
		return true;
	}

	public static boolean Range(int x, int y) {
		if (0 <= x && 0 <= y && x < N && y < N) return true;
		return false;
	}
}
