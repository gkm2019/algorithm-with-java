package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1992_쿼드트리 {

	static int n;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < n; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		} // end input

		System.out.println(div(0, 0, n));
	}// end main()

	static String div(int x, int y, int size) {
		
		if (check(x, y, size))
			return Integer.toString(map[x][y]);

		String str1 = div(x, y, size / 2);
		String str2 = div(x, y + size / 2, size / 2);
		String str3 = div(x + size / 2, y, size / 2);
		String str4 = div(x + size / 2, y + size / 2, size / 2);

		StringBuilder ans = new StringBuilder();
		ans.append("(").append(str1).append(str2).append(str3).append(str4).append(")");
		return ans.toString();
	}

	static boolean check(int x, int y, int size) { // 한 판이 같은 숫자인지?
		int tmp = map[x][y];
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (map[i][j] != tmp)
					return false;
			}
		}
		return true;
	}// end check()
}
