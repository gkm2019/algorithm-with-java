package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_3234_준환이의양팔저울 {

	static int T, N, ans;
	static boolean[] flag;
	static int[] input, select;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			flag = new boolean[N];
			input = new int[N];
			select = new int[N];
			ans = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			} // end input
			permutation(0);
			System.out.println("#" + tc + " " + ans);
		} // end TestCase
	}

	static void permutation(int cnt) {
		if (cnt == N) {
			subSet(0,0,0);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (flag[i])continue;
			flag[i] = true;
			select[cnt] = input[i];
			permutation(cnt + 1);
			flag[i] = false;
		}
	}
	
	static void subSet(int cnt, int L, int R) {
		if(cnt>=N) {
			ans++;
			return;
		}
		if(L>=R+select[cnt]) subSet(cnt+1, L,R+select[cnt]);
		subSet(cnt+1,L+select[cnt],R);
	}
}
