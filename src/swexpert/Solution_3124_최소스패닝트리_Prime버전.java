package swexpert;

import java.io.*;
import java.util.*;

public class Solution_3124_최소스패닝트리_Prime버전 {

	static int T, V, E;

	static class Data implements Comparable<Data> {
		int v, w;

		public Data(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Data o) {
			return this.w - o.w;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			PriorityQueue<Data> pq = new PriorityQueue<Data>();
			ArrayList<Data>[] input = new ArrayList[V + 1];
			boolean[] select = new boolean[V + 1]; // 트리에 포함되어있는지 아닌지 체크!
			// int[] dist = new int[V+1];
			long ans = 0; // 총 간선의 수를 저장한다.
			int cnt = 0;
			// Arrays.fill(dist, Integer.MAX_VALUE);
			for (int i = 1; i <= V; i++)
				input[i] = new ArrayList<Data>();

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				input[a].add(new Data(b, c));
				input[b].add(new Data(a, c));
			}

			select[1] = true;// 트리에 포함시킨다.
			// dist[1]=0; //프림은 어떤점에서 시작해도 항상 같은 트리가 생성된다. 임의로 1이라고 지정

			// 1. 일단 시작노드 1에 인접한 모든 간선들을 우선순위 큐에 넣어본다
			for (Data d : input[1]) {
				pq.add(d); // 시작점 1과 연결된 간선들이 저장된다.
			}
			// 2. 이제 pq에 저장 되었으니 반복을 시작한다. 가장 가중치 작은 간선을 탐색하고 트리에 추가해나간다.
			while (!pq.isEmpty()) {
				Data curr = pq.poll();

				if (select[curr.v]) continue; // 이미 해당 정점이 트리에 포함되어있다면 그냥 pass
				select[curr.v] = true; // 트리에 추가해준다!
				ans += curr.w; // 가중치 누적시키기
				cnt++; 
				if(cnt==V-1)break; //cnt가 v-1이 되면 종료시키자 <스패닝 트리가 완성되었음>

				for (Data d : input[curr.v]) { // 현재 curr노드와 연결 성사된 노드들의 간선을 또 넣어
					// 지금까지 형성된 트리들의 모든 간선들이
					if (select[d.v])continue;
					pq.add(d);
				}
			}//end while pq

			System.out.println("#" + tc + " " + ans);
		} // end TestCase
	}

}
