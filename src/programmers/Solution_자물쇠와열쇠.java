package programmers;

import java.io.*;
import java.util.*;

public class Solution_자물쇠와열쇠 {
	static int tmpKey[][], tmpLock[][]; // tmpKey는 key 회전 저장용, tmpLock은 확장형 복구를 위한 lock 저장
	static int m, n;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// input
		m = 3;
		n = 3;
		int key[][] = new int[m][m];
		int lock[][] = new int[n][n];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				key[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				lock[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// end input
		System.out.println(solution(key, lock));
	}

	static void print(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	static boolean solution(int[][] key, int[][] lock) {
		boolean ans = true;
		int size = m * 2 + (n - 2);
		tmpLock = new int[size][size]; // 확장 버전의 lock
		tmpKey = new int[m][m];

		// lock 확장
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				tmpLock[i + m - 1][j + m - 1] = lock[i][j];
			}
		}
		int[][] LockPad = new int[size][size];

		// LockPad에 확장 자물쇠 원본 저장
		for (int i = 0; i < size; i++) {
			System.arraycopy(tmpLock[i], 0, LockPad[i], 0, tmpLock[0].length);
		}

		/*
		 * 회전본의 key생성 회전 후의 key와 tmpLock XOR연산 시키기 열렸는지 하나하나 check하기 안열렸다면 tmpLock XOR
		 * 하기 전으로 복구 시키기
		 */
		// tmpKey=key;
		for (int i = 0; i < key.length; i++) {
			System.arraycopy(key[i], 0, tmpKey[i], 0, key[0].length);
		}

		for (int d = 0; d < 4; d++) {
			if (d > 0) {
				rotate();
			}
			for (int i = 0; i <= size - m; i++) {
				for (int j = 0; j <= size - m; j++) {
					for (int x = 0; x < m; x++) {
						for (int y = 0; y < m; y++) {
							tmpLock[i + x][j + y] ^= tmpKey[x][y]; // xor연산 하면서 맞추기
						}
					}

					if (Check())
						return true; // tmpLock 검사 함수
					// tmpLock=LockPad; //원상 복기
					for (int k = 0; k < LockPad.length; k++) {
						System.arraycopy(LockPad[k], 0, tmpLock[k], 0, LockPad[0].length);
					}
				}
			}
		}
		return false;
	}

	static boolean Check() {
		for (int i = m - 1; i < m + n - 1; i++) {
			for (int j = m - 1; j < m + n - 1; j++) {
				if (tmpLock[i][j] == 0)
					return false;
			}
		}
		return true;
	}

	static void rotate() {
		int key[][] = new int[m][m];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				key[j][m - 1 - i] = tmpKey[i][j];
			}
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				tmpKey[i][j] = key[i][j];
			}
		}
	}
}
