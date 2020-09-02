package swexpert;

import java.io.*;
import java.util.*;

public class Solution_1767_프로세서연결하기 {

	// 코어 갯수는 많아야 하고, 전선의 길이 합은 최소 / coreTot는 현재 코어의 총 갯수임
	static int T, N, coreTot, ansCore, ansD;
	static int[][] map;
	static Pair[] Core;
	static int[] dx = { -1, 0, 1, 0 }, dy = { 0, 1, 0, -1 }; // 위부터 시계방향

	static class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); // 1<=N<=12
			map = new int[N][N];
			Core = new Pair[13]; // 코어의 총 갯수는 최대 12개 까지 가능하다.
			coreTot = 0;
			ansCore = Integer.MIN_VALUE; // 총 연결된 코어 갯수
			ansD = Integer.MAX_VALUE; // 전선 길이 총 합

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						Core[coreTot++] = new Pair(i,j);
					}
				}
			} // end input

			dfs(0, 0, 0);// 현재 코어의 idx, 현재 연결된 코어, 지금까지의 전선 총 합

			System.out.println("#" + tc + " " + ansD);
		} // end testCase
	}// end main()

	static void dfs(int coreidx, int nowCore, int nowD) {
		if (coreidx == coreTot) { // 마지막 코어까지 탐색이 끝났다면?
			if (ansCore < nowCore) {
				ansCore = nowCore;
				ansD = nowD;
			} else if (ansCore == nowCore)
				ansD = Math.min(ansD, nowD);

			return;
		}
		int x = Core[coreidx].x, y = Core[coreidx].y;
		if (Range(x, y)) // 이미 가장자리에 있는 코어라면?
			dfs(coreidx + 1, nowCore + 1, nowD);
		else {
			for (int d = 0; d < 4; d++) {
				int tmpD = Check(x, y, d);
				if (tmpD != 0) {// 연결 가능하다면?
					Connect(x, y, d, 2);// 연결은 2
					dfs(coreidx + 1, nowCore + 1, nowD + tmpD);
					Connect(x, y, d, 0);//다시 원래대로 돌리기
				}else
					dfs(coreidx+1, nowCore, nowD);
			}
		}
	}// end dfs()

	static void Connect(int x, int y, int d, int val) {
		int nx=x, ny=y;
		while(!Range(nx,ny)) {
			nx+=dx[d];
			ny+=dy[d];
			map[nx][ny]=val;
		}
	}

	static int Check(int x, int y, int d) {
		int nx = x, ny = y, cnt = 0;
		while (!Range(nx, ny)) {
			nx += dx[d];
			ny += dy[d];
			if (map[nx][ny] != 0)
				return 0;
			cnt++;
		}
		return cnt;
	}

	static boolean Range(int x, int y) {
		if (x == 0 || y == 0 || x == N - 1 || y == N - 1)
			return true;
		return false;
	}

}
