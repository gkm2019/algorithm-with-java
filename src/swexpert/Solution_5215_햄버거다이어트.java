package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5215_햄버거다이어트 {

	static int T, N, L, kcal, val;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			dp = new int[L+1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				val = Integer.parseInt(st.nextToken());
				kcal = Integer.parseInt(st.nextToken());
				
				for(int k=L;k>=kcal;k--) {
					//해당 재료 선택하는게 더 가치가 높으면 갱신!
					if(dp[k] < val + dp[k-kcal]) dp[k]=val + dp[k-kcal];
				}
			}
			

			System.out.println("#" + tc + " "+dp[L]);
		} // end testCase
	}

}
