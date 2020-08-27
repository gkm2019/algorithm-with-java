package swexpert;

import java.util.Scanner;

public class Solution_1954_달팽이 {
	static int[] di = { 0, 1, 0, -1 };// 0:right 1:down 2:left 3:up
	static int[] dj = { 1, 0, -1, 0 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();

		for (int tc = 1; tc <= TC; tc++) {
			int N = sc.nextInt();
			int[][] arr = new int[N][N];

			int dir = 0; // 0:right 1:down 2:left 3:up
			int cnt = 1; // N*N까지 증가

			int nowi = 0, nowj = 0;
			while (true) {
				if (cnt > (N * N))
					break;

				arr[nowi][nowj] = cnt++; // 현재 서있는 칸에 1,2,3,4,.. 순차적으로 기록

				int nexti = nowi + di[dir]; // 현재 방향으로 직진할 경우 다음 칸 좌표
				int nextj = nowj + dj[dir];

				if (nexti >= N || nexti < 0 || nextj >= N || nextj < 0 || arr[nexti][nextj] > 0) {
					// 방향을 틀어야 되는 경우
					dir = (dir + 1) % 4;
					nowi = nowi + di[dir];
					nowj = nowj + dj[dir];
				} else {
					// 그냥 직진하면 되는 경우
					nowi = nexti;
					nowj = nextj;
				}
			}
			System.out.println("#" + tc);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(arr[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
}
