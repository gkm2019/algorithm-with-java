package BOJ;

import java.util.Scanner;

public class Main_1562_계단수 {

	static int N;
	static int ans, sub = 1000000000;
	static int[][][] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		dp = new int[N + 1][10][1 << 10];
		for (int i = 1; i < 10; i++)
			dp[1][i][(1 << i)] = 1;

		for (int i = 2; i <= N; i++) {
			for (int j = 0; j <= 9; j++) {
				for (int bit = 0; bit < (1 << 10); bit++) {
					if (j == 0)
						dp[i][j][bit | (1 << j)] += dp[i-1][j + 1][bit];
					else if (j == 9)
						dp[i][j][bit | (1 << j)] += dp[i-1][j - 1][bit];
					else
						dp[i][j][bit | (1 << j)] += (dp[i-1][j + 1][bit] + dp[i-1][j - 1][bit]) % sub;
					
					dp[i][j][bit | (1<<j)] %=sub;
				}
			}
		}

		for (int i = 0; i < 10; i++)
			ans = (ans + dp[N][i][(1 << 10) - 1]) % sub;

		System.out.println(ans);
	}

}
