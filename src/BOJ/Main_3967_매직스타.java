package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_3967_매직스타 {

	static char[][] map;
	static boolean[] visit;
	static int[] number;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int idx = 0;
		map = new char[5][9];
		visit = new boolean[13];
		number = new int[12];

		for (int i = 0; i < 5; i++) {
			String str = br.readLine();
			for (int j = 0; j < 9; j++) {
				map[i][j]=str.charAt(j);
				if (str.charAt(j) == '.')
					continue;
				else if (str.charAt(j) == 'x') {
					number[idx++] = 0; // 이곳에 숫자를 채워넣어야 함
				} else { // 알파벳일 경우
					map[i][j] = 'x';
					int tmp = str.charAt(j) - 'A' + 1;
					visit[tmp] = true;
					number[idx++] = tmp;
				}
			}
		} // end input

		dfs(0);
	}

	public static void dfs(int cnt) {
		if (cnt == 12 && check()) {
			print();
			System.exit(0); // 시스템 정상 종료
		}
		if(cnt==12) return;
		if (number[cnt] != 0) dfs(cnt + 1);

		else {
			for (int i = 1; i <= 12; i++) {
				if (visit[i]) continue;
				visit[i] = true;
				number[cnt] = i;
				dfs(cnt + 1);
				visit[i] = false;
				number[cnt]=0;
			}
		}
	}

	public static void print() {
		int idx = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 9; j++) {
				if (map[i][j] == '.')
					System.out.print(map[i][j]);
				else {
					char c = (char)('A' + number[idx++]-1);
					System.out.print(c);
				}

			}
			System.out.println();
		}
	}

	public static boolean check() {
		int tmp[] = new int[6];
		tmp[0] = number[0] + number[2] + number[5] + number[7];
		tmp[1] = number[0] + number[3] + number[6] + number[10];
		tmp[2] = number[1] + number[2] + number[3] + number[4];
		tmp[3] = number[7] + number[8] + number[9] + number[10];
		tmp[4] = number[1] + number[5] + number[8] + number[11];
		tmp[5] = number[4] + number[6] + number[9] + number[11];

		for (int i = 0; i < 6; i++) {
			if (tmp[i] != 26)
				return false;
		}

		return true;
	}

}
