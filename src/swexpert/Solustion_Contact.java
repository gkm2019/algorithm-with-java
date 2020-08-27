package swexpert;

import java.util.*;
import java.io.*;

public class Solustion_Contact {
//ArrayList 버전의 BFS
	static int SIZE=101;
	static ArrayList<Integer>[] map;
	static boolean visit[];
	static int N, start, cnt, ans;

	static class Pair {
		int idx, cnt;

		public Pair(int idx, int cnt) {
			this.idx = idx;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int t = 1; t <= 10; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			
			map = new ArrayList[SIZE];
			for(int i=1;i<SIZE;i++) { //map 정점들 초기화
				map[i] = new ArrayList<Integer>();
			}//end for init map
			
			visit = new boolean[101];
			ans = -1; cnt = -1;
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				map[from].add(to); //map에 인접 정점 저장
			} // end input

			
			Queue<Pair> q = new LinkedList<>();
			visit[start] = true;
			q.offer(new Pair(start, 0));

			
			while (!q.isEmpty()) {
				Pair p = q.poll();
				System.out.print("["+p.idx+"/"+p.cnt+"]->");
				if(cnt<p.cnt) {
					cnt=p.cnt;
					ans=p.idx;
				}else if(cnt==p.cnt) {
					if(ans<p.idx)
						ans=p.idx;
				}
				
				for(int m:map[p.idx]) { //p.idx와 연결된 것만 돈다.
					if(!visit[m]) {
						visit[m]=true;
						q.offer(new Pair(m,p.cnt+1));
					}
				}
			}
			System.out.println("#" + t + " " + ans);
			System.out.println("=======");
		} // end for test_case;
	}

}
