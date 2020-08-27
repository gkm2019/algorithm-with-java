package BOJ;

import java.util.*;
import java.io.*;

public class Main_1697_숨바꼭질 {

	static int N, K, ans;
	static int[] visit;
	static int size = 100001;

	static class Pair {
		int x, time;

		Pair(int x, int time) {
			this.x = x;
			this.time = time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visit = new int[size];
		Arrays.fill(visit, 999999);
		bfs();
	}

	static void bfs() {
		ans=-1;
		int cnt=0;
		Queue<Pair> q = new LinkedList<>();
		visit[N] = 0;
		q.add(new Pair(N, 0));
		while (!q.isEmpty()) {
			Pair front = q.poll();
			if(ans!=-1&&ans<front.time)break;
			if(front.x==K) {
				if(ans==-1) {
					ans=front.time;
					cnt++;
				}else if(ans==front.time){
					cnt++;
				}
			}

			for (int d = 0; d < 3; d++) {
				int nextPos = calc(front.x, d);
				if (!(0 <= nextPos && nextPos < size))continue;
				if(visit[nextPos]<front.time+1)continue;
				visit[nextPos] = front.time+1;
				q.add(new Pair(nextPos, front.time + 1));
			}

		} // end while
		
		System.out.println(ans);
		System.out.println(cnt);
	}// end bfs

	static int calc(int n, int d) {
		if (d == 0)
			return n - 1;
		if (d == 1)
			return n + 1;
		return n * 2;
	}
}