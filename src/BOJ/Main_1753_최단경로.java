package BOJ;

import java.io.*;
import java.util.*;

public class Main_1753_최단경로 {

	static int V, E, K;
	static int[] dist;
	static ArrayList<Pair>[] matrix;

	static class Pair implements Comparable<Pair>{
		int v, w; // 현재 정점과 그때 까지의 total 가중치 합

		public Pair(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Pair o) {
			return this.w-o.w;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		dist = new int[V + 1];
		matrix  = new ArrayList[V+1];
		
		for(int i=1;i<=V;i++) 
			matrix[i]=new ArrayList<>();
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			matrix[u].add(new Pair(v,w));
		} // end input

		dijkstra(K);
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= V; i++) {
			if (dist[i] == Integer.MAX_VALUE)
				System.out.println("INF");
			else
				System.out.println(dist[i]);
		}
	}

	static void dijkstra(int start) {
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		dist[start] = 0;
		pq.add(new Pair(start, 0));// 현재 노드, 토탈 가중치를 저장한다.

		while (!pq.isEmpty()) {
			Pair curr = pq.poll();
			if(curr.w>dist[curr.v])continue;
			
			for(Pair p: matrix[curr.v]) {
				if(dist[p.v]>dist[curr.v]+p.w) {
					dist[p.v]=dist[curr.v]+p.w;
					pq.add(new Pair(p.v, dist[p.v]));
				}
			}
			
		}//end while

	}

}
