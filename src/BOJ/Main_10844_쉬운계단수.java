package BOJ;

import java.util.Scanner;

public class Main_10844_쉬운계단수 {

	static int N;
	static int ans, sub = 1000000000;
	static int[][] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		dp = new int[N + 1][10];
		for (int i = 1; i <10; i++) dp[1][i] = 1;
		
		for(int i=2;i<=N;i++) {
			dp[i][0]=dp[i-1][1];
			dp[i][9]=dp[i-1][8];
			for(int j=1;j<9;j++) {
				dp[i][j]=(dp[i-1][j-1]+dp[i-1][j+1])%sub;
			}
		}
		
		for(int i=0;i<10;i++)
			ans=(ans+dp[N][i])%sub;
		
		System.out.println(ans%sub);
	}

}
