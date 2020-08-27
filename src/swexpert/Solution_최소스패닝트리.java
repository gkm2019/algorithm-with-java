package swexpert;
import java.util.*;
import java.io.*;

public class Solution_최소스패닝트리 {

	static int size=100001;
	static int T,V,E;
	static int[] root;
	static class Tuple implements Comparable<Tuple>{
		int a, b, c;
		Tuple(int a, int b, int c){
			this.a=a;
			this.b=b;
			this.c=c;
		}
		@Override
		public int compareTo(Tuple o) {
			if(this.c>o.c)return 1;
			if(this.c==o.c)return 0;
			return -1;
		}
	}
	//Disjoint Set
	static void make() {
		for(int i=1;i<=V;i++)
			root[i]=i;
	}
	
	static int find(int x) {
		if(x==root[x])return x;
		return root[x]=find(root[x]);
	}
	
	static void union(int x, int y) {
		int a=find(x);
		int b=find(y);
		if(a==b)return;
		root[b]=a;
	}
	//main
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T=Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine()," ");
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			root=new int[V+1];
			make();
			PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>();
			long ans=0L;
			int cnt=0; //간선 갯수 카운팅 v-1
			int a,b,c;
			for(int i=0;i<E;i++) {
				st=new StringTokenizer(br.readLine()," ");
				a=Integer.parseInt(st.nextToken());
				b=Integer.parseInt(st.nextToken());
				c=Integer.parseInt(st.nextToken());
				
				pq.add(new Tuple(a,b,c));
			}
			
			while(!pq.isEmpty()) {
				Tuple front=pq.poll();
				if(find(front.a)!=find(front.b)) {
					union(front.a, front.b);
					cnt++;
					ans+=front.c;
					if(cnt==V-1)
						break;
				}
			}
			pq.clear();
			
			System.out.println("#"+tc+" "+ans);
		}//end for test_case
	}

}
