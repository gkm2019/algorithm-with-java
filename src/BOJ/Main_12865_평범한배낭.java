package BOJ;

import java.util.*;

import javax.swing.InputMap;

import java.io.*;

public class Main_12865_평범한배낭 {

	static int N, K, ans;
	static int dp[][]; //배열 값은 가치이다. dp[idx][w]=v
	static class Pair{
		int w, v;
		
		Pair(int w, int v) {
			this.w = w;
			this.v = v;
			}
	}

	static Pair[] arr;
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new Pair[N];
		dp = new int[N][100001];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			arr[i] = new Pair(w, v);
		} // end for input
		
		ans=knapsack(0,0);
		System.out.println(ans);
	}// end main

	static int knapsack(int idx, int w) {
		if(idx==N)return 0;
		if(dp[idx][w]>0)return dp[idx][w];
		
		int w1=0,w2=0;
		//선택..
		if(w+arr[idx].w<=K) {
			w1=knapsack(idx+1, w+arr[idx].w)+arr[idx].v;
		}
		//선택x..
		w2=knapsack(idx+1, w);
		return dp[idx][w]=Math.max(w1, w2);
	}// end knapsack
}// end class
