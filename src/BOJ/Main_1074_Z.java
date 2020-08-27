package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1074_Z {

	static int N, r, c, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		int size = (int)Math.pow(2, N);
		div(0, 0, size);
	}

	static void div(int x, int y, int size) {
		if (x == r && y == c) {
			System.out.println(ans);
			return;
		}
		if (size == 1) {
			ans++;
			return;
		}
		div(x, y, size / 2);
		div(x, y + size / 2, size / 2);
		div(x + size / 2, y, size / 2);
		div(x + size / 2, y + size / 2, size / 2);
	}
}
