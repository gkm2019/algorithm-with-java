package swexpert;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.io.*;

public class Solution_2382_미생물격리 {

	static int T, N, M, K;

	static class Data {
		int x, y, d, cnt;

		public Data(int x, int y, int d, int cnt) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.cnt = cnt;
		}
	}

	static int[] dx = { 0,-1, 1, 0, 0 }, dy = {0, 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			ConcurrentHashMap<Integer, Data> map = new ConcurrentHashMap<Integer, Data>();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				Data tmp = new Data(x, y, d, cnt);
				map.put(i, tmp);
			}

			for (int time = 0; time < M; time++) {
				int[][][] cell = new int[N][N][2];
				int idx = 0;//iterator
				Iterator<Integer> iter = map.keySet().iterator();
				while(iter.hasNext()) {
					idx = iter.next();
					
					Data now = map.get(idx);
					now.x+=dx[now.d];
					now.y+=dy[now.d];
					if (!Range(now.x, now.y)) {// 가장자리 가면 반으로 줄고 방향 바꿔줌
						now.cnt /= 2;
						if(now.cnt==0) {
							map.remove(idx);
							continue;
						}
						now.d = change(now.d);
						map.replace(idx, now);
						continue;
					} 
					
					int num = cell[now.x][now.y][0];
					int max = cell[now.x][now.y][1];
					if (max == 0) {// cell에 아무것도 없으면 그자리로 바로 이동
						cell[now.x][now.y][0] = idx;
						cell[now.x][now.y][1] = now.cnt;
					} 
					else {// 이미 cell에 무엇인가 존재한다면 비교하고 합치기(중복)
						if (max < now.cnt) {
							cell[now.x][now.y][1] = now.cnt;
							map.get(num).d = now.d;
						}
						map.get(num).cnt += now.cnt;
						map.remove(idx); // 삭제제하고
					}
				}//end while
					
			} // end for time
			int tot = 0;
			Iterator<Integer> iter = map.keySet().iterator();
			while(iter.hasNext()) {
				int idx=iter.next();
				tot+=map.get(idx).cnt;
				
			}
			System.out.println("#" + tc + " " + tot);
		} // end testCase
	}

	static boolean Range(int x, int y) {
		if (0 < x && 0 < y && x < N - 1 && y < N - 1)
			return true;
		return false;
	}

	static int change(int dir) {
		if (dir == 1)
			return 2;
		if (dir == 2)
			return 1;
		if (dir == 3)
			return 4;
		return 3;
	}
}
