package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.LinkedBlockingDeque;

public class Main_5022_연결 {

	static int N, M;
	static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };
	static int[][] visit;
	static Pair[] pos;
	static Pair[][] dis;

	static class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visit = new int[M + 1][N + 1];
		pos = new Pair[4];
		dis = new Pair[111][111];

		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			pos[i] = new Pair(x, y);
		}

		int ans1 = -1, ans2=-1, tmp = 0;
		boolean flag=false;
		
		// A 연결 후 B 연결
		init();
		bfs(pos[0], pos[1]);
		ans1 = checkRoute(pos[1]); //A최단경로 연결해놓기 값이 무조건 나옴
		flag = bfs(pos[2], pos[3]);
		if(flag) { //두개 다 연결 가능하면 최단 경로로 저장
			ans1 += checkRoute(pos[3]);
		} else ans1=-1; //전선 이을 수 없음
		
		// B 연결 후 A 연결
		init();
		bfs(pos[2], pos[3]);
		ans2 = checkRoute(pos[3]); //B최단경로 연결해놓기 값이 무조건 나옴
		flag = bfs(pos[0], pos[1]);
		if(flag) {
			ans2 += checkRoute(pos[1]);
		} else ans2=-1;
		
		if (ans1 == -1 && ans2 == -1)
			System.out.println("IMPOSSIBLE");
		else {
			if (ans1 == -1)
				System.out.println(ans2);
			else if (ans2 == -1)
				System.out.println(ans1);
			else if (ans1 != -1 && ans2 != -1) {
				ans1 = Math.min(ans1, ans2);
				System.out.println(ans1);
			}
		}
	}
	
	public static void print() {
		System.out.println("======");
		for(int i=0;i<=M;i++) {
			for(int j=0;j<=N;j++) {
				System.out.print(visit[i][j]+" ");
			}
			System.out.println();
		}
	}
	public static int checkRoute(Pair end) {
		int cnt=0, x = end.x, y=end.y;
		
		init();
		while(true) {
			if(x==-1 && y==-1) break;
			visit[x][y]=1;
			int nx = dis[x][y].x;
			int ny = dis[x][y].y;
			x=nx; y=ny;
			cnt++;
		}
		
		return cnt-1;
	}
	public static void init() {
		for (int i = 0; i <= M; i++)
			Arrays.fill(visit[i], 0);

		for (int i = 0; i < 4; i++) {
			if (visit[pos[i].x][pos[i].y] == -1) {
				System.out.println("IMPOSSIBLE");
				System.exit(0);
			}
			visit[pos[i].x][pos[i].y] = -1;
		}
	}

	public static boolean bfs(Pair now, Pair end) {
		visit[end.x][end.y] = 0; // 도착지는 방문 가능해야하니까 0으로 바꿔주기
		Queue<Pair> q = new LinkedList<Pair>();
		dis[now.x][now.y] = new Pair(-1,-1);
		q.add(now);

		while (!q.isEmpty()) {
			Pair front = q.poll();

			for (int d = 0; d < 4; d++) {
				int nx = front.x + dx[d];
				int ny = front.y + dy[d];
				if (!Range(nx, ny) || visit[nx][ny] != 0) continue;
				//반대편에 도착했다면
				visit[nx][ny] = 1;
				dis[nx][ny] = front;
				if (nx == end.x && ny == end.y) {
					return true;
				}
				q.add(new Pair(nx, ny));
			}
		}
		return false;
	}

	public static boolean Range(int x, int y) {
		if (0 <= x && 0 <= y && x <= M && y <= N)
			return true;
		return false;
	}
}
