package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution_4613_러시아국기같은깃발 {

	static int T, N, M;
	static int[][] dp, color;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			dp = new int[N + 1][3];
			color = new int[N + 1][3];

			for (int i = 1; i <= N; i++) {
				String str = br.readLine();
				for (int j = 0; j < M; j++) {
					int colorIDX = Change(str.charAt(j));
					color[i][colorIDX]++;
				}
			}
			
			dp[1][0] = M - color[1][0]; // 1줄은 무조건 흰색(0)으로 바꿔줘야 한다.
			int min;
			for (int i = 2; i < N; i++) {
				dp[i][0] = dp[i - 1][0] + (M - color[i][0]); // 현재 줄이 흰색이려면 전의 줄도 흰색이어야 한다.
				min = Integer.MAX_VALUE;
				for (int c = 1; c <= 2; c++) {
					if (dp[i - 1][c - 1] != 0 && min > dp[i - 1][c - 1]) min = dp[i - 1][c - 1];
					if (dp[i - 1][c] != 0 && min > dp[i - 1][c]) min = dp[i - 1][c];
					if(min==Integer.MAX_VALUE)continue;
					dp[i][c] = min + M - color[i][c];
				}
			}

			dp[N][2]=Math.min(dp[N-1][1], dp[N-1][2])+M-color[N][2];
			System.out.println("#" + tc + " " + dp[N][2]);
		}
	}
	static int Change(char color) {
		if (color == 'W') return 0;
		if (color == 'B') return 1;
		return 2;
	}

}
