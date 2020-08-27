package BOJ;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main_개구리점프 {

	static int N, Q;
	static int[] p, rank;
	static data[] input;

	static class data implements Comparable<data> {
		int idx, x1, x2, y;

		public data(int idx, int x1, int x2, int y) {
			this.idx = idx;
			this.x1 = x1;
			this.x2 = x2;
			this.y = y;
		}

		@Override
		public int compareTo(data o) {
			if (this.x1 > o.x1) return 1;
			if (this.x1 == o.x1) 
				if (this.y > o.y) return 1;
			return -1;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		input = new data[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			input[i] = new data(i+1, x1, x2, y);
		}

		Arrays.sort(input);
		make();
		solution(); // disjoint set 하면서 연산

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			if (find(s) == find(e))
				System.out.println(1);
			else
				System.out.println(0);
		}
	}

	static void solution() {
//		for (int i = 0, j=1; i < N && j<N ;) {
//			if(input[j].x1<=input[i].x2) {
//				union(input[i].idx, input[j].idx);
//				j++;
//			}else i++;
//		}//end for
		
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (input[j].x1 <= input[i].x2) 
					union(input[i].idx, input[j].idx);
				else
					break;
			}
		}
		
	}//end solution();

	static void make() {
		p = new int[N + 1];
		rank = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			p[i] = i;
			rank[i] = 0;
		}
	}// end make()
	
	static int find(int x) {
		if(p[x]==x)return x;
		return p[x]=find(p[x]);
	}//end find()
	
	static void union(int x, int y) {
		x=find(x);
		y=find(y);
		if(x==y)return;
		
		if(rank[x]>rank[y])p[y]=x;
		else {
			p[x]=y;
			if(rank[x]==rank[y])
				rank[y]++; //y랭크에 x 추가됨.. y랭크 상승!
		}
	}//end union()
}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
