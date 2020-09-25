package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2294_동전2 {
	static int n,k;
	static int[] coin, dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		n=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		
		coin = new int[n];
		dp = new int[k+1];
		for(int i=0;i<n;i++) {
			coin[i]=Integer.parseInt(br.readLine());
		}
		Arrays.sort(coin);
		int min;
		for(int i=1;i<=k;i++) {
			min=Integer.MAX_VALUE;
			for(int j=0;j<n;j++) {
				if(coin[j]>i)break;
				if(coin[j]==i)min=1;
				if(coin[j]<i && dp[i-coin[j]]!=0 && min>dp[i-coin[j]]+1) min = dp[i-coin[j]]+1;
			}
			if(min==Integer.MAX_VALUE)continue;
			dp[i]=min;
		}
		
		int ans=dp[k];
		if(ans==0)ans=-1;
		System.out.println(ans);
	}

}
