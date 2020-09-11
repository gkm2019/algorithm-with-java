package swexpert;
import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;
import java.io.*;

public class Solution_1251_하나로 {

	static double ans; //최소 비용을 출력한다.
	static int T, N;
	static double[] X,Y; //x,y좌표들
	static double E; //세율 
	static boolean[] select; //트리에 선택된 노드들을 담는다.
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine());
			X=new double[N];
			Y=new double[N];
			select=new boolean[N];
			StringTokenizer stX = new StringTokenizer(br.readLine()," ");
			StringTokenizer stY = new StringTokenizer(br.readLine()," ");
			for(int i=0;i<N;i++) {
				X[i]=Integer.parseInt(stX.nextToken());
				Y[i]=Integer.parseInt(stY.nextToken());
			}
			
			E = Double.parseDouble(br.readLine());
			ans = 0;
			
			Prim();
			System.out.println("#"+tc+" "+(long)ans);
		}//end TestCase
	}
	
	static class Data implements Comparable<Data>{
		int v; double w;
		public Data(int v, double w) {
			this.v=v;
			this.w=w;
		}
		@Override
		public int compareTo(Data o) {
			if(this.w>o.w)return 1;
			return -1;
		}
	}
	static void Prim() {
		PriorityQueue<Data> pq = new PriorityQueue<>();
		select[0]=true; //시작노드 0부터
		int cnt = 0, curr = 0; //curr: 시작노드 0, cnt: 간선의 갯수
		while(cnt<N-1) {
			//현재 curr과 인접한 모든 간선들을 우선순위 큐에 넣는다.
			for(int i=0;i<N;i++) {
				if(i==curr || select[i])continue;
				double d = dist(X[curr], Y[curr], X[i], Y[i]);
				pq.add(new Data(i,d));
			}
			
			while(!pq.isEmpty()) {
				Data now = pq.poll();
				if(select[now.v])continue;
				select[now.v]=true;
				ans+=now.w;
				cnt++;
				curr=now.v;
				break;
			}
			
		}//end while
		
		ans = ans*E;
		ans = Math.round(ans);
	}
	
	static double dist(double x1, double y1, double x2, double y2) {
		double x = (x1-x2)*(x1-x2);
		double y = (y1-y2)*(y1-y2);
		return x+y;
	}
}
