package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3307_최장증가부분수열 {

	static int T, N;
	static int[] input, lis;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			input = new int[N];
			lis = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			} // input

			int max = 0;
			for (int i = 0; i < N; i++) {
				lis[i] = 1; // 일단 자기 자신 한줄 1로 초기화

				for (int j = 0; j < i; j++) { // 0부터 자신(i) 직전까지 비교한다.
					// 앞에있는 수가 자신보다 작고, 거기에 +1 한 값이 자신의 수열보다 크면 갱신
					if (input[j] < input[i] && lis[i] < lis[j] + 1) {
						lis[i] = lis[j] + 1;
					}
				}

				if (lis[i] > max) max = lis[i]; // 최대값 갱신 해준다.
			}
			System.out.println("#" + tc + " " + max);
		} // end testCase
	}

}
