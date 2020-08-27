package BOJ;
import java.util.*;
import java.io.*;

public class Main_1931_회의실배정 {

	static int N;
	static class Pair implements Comparable<Pair>{
		int start, end;
		Pair(int start, int end){
			this.start=start;
			this.end=end;
		}
		@Override
		public int compareTo(Pair o) {			
			if(this.end>o.end)return 1;
			if(this.end==o.end) {
				if(this.start>o.start)return 1;
				return -1;
			}
			return -1;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int start=Integer.parseInt(st.nextToken());
			int end=Integer.parseInt(st.nextToken());
			pq.add(new Pair(start,end));
		}//end for input
		
		int cnt=1;
		Pair front = pq.poll();
		while(!pq.isEmpty()) {
			Pair next = pq.poll();
			
			if(front.end<=next.start) {
				cnt++;
				front=next;
			}
		}
		
		System.out.println(cnt);
	}//end main

}
