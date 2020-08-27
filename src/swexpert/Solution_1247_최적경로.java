package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1247_최적경로 {

	static int T, N, ans;
	static Pair company, house, customer[];
	static int[] select;
	static boolean[] flag;
	
	static class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			customer = new Pair[N];
			select = new int[N];
			flag = new boolean[N];
			ans=Integer.MAX_VALUE;
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			company = new Pair(x, y);
			
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			house = new Pair(x, y);
			
			for (int i = 0; i < N; i++) {
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());

				customer[i] = new Pair(x, y);
			}//end input
			
			permutation(0);
			
			System.out.println("#" + tc + " "+ans);
		}//end test case
	}//end main()

	static void permutation(int cnt) {
		if(cnt==N) {
			//logic
			//회사 -(모든 고객의 거리 다 계산)-집
			int tmp=dist(company, customer[select[0]]);
			
			for(int i=0;i<N-1;i++) {
				tmp+=dist(customer[select[i]], customer[select[i+1]]);
			}
			tmp += dist(customer[select[N-1]], house);
			ans=Math.min(ans, tmp);
			return;
		}
		
		for(int i=0;i<N;i++) {
			if(flag[i])continue;
			flag[i]=true;
			select[cnt]=i;
			permutation(cnt+1);
			flag[i]=false;
		}
	}
	
	static int dist(Pair o1, Pair o2) {
		return Math.abs(o1.x-o2.x)+Math.abs(o1.y-o2.y);
	}
}
