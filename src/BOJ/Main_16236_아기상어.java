package BOJ;

import java.util.*;
import java.io.*;

public class Main_16236_아기상어 {
	static class Data implements Comparable<Data> {
		int x, y, cnt; // cnt는 pq에 들어갈때는 거리(시간)처럼 사용되고, 상어현재 정보를 저장할때는 size로 사용된다!!

		public Data(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Data o) { // 거리 작고, x작고, y작은거 우선
			if (this.cnt > o.cnt) return 1;
			if (this.cnt == o.cnt) {
				if (this.x > o.x) return 1;
				if (this.x == o.x) {
					if (this.y > o.y) return 1;
				}
			}
			return -1;
		}
	}// end Data

	static Data Shark; // 상어 정보 저장
	static int ansTime, N, fishCnt;// 총 물고기 수 저장
	static int[][] map;
	static int[] dx= {-1,1,0,0}, dy= {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					Shark = new Data(i, j, 2);
					map[i][j] = 0;
				}
				if (map[i][j] != 0)
					fishCnt++; // 물고기 수 저장
			}
		}//end input
		
		solution();
	}

	static void solution() {
		int eatFishCnt=0; //현재까지 먹은 물고기 갯수(상어크기 늘릴 때 사용할거야)
		while(fishCnt!=0) {
			Queue<Data> q = new LinkedList<Data>(); //현재 상어의 이동 정보를 담을 큐! (bfs에 쓰임)
			PriorityQueue<Data> pq = new PriorityQueue<Data>(); //먹을 수 있는 물고기들만 담는다.
			boolean[][] visit = new boolean[N][N];
			q.add(new Data(Shark.x, Shark.y, 0)); //현재 상어의 좌표와 소요 시간을 담는다.
			int minTime = Integer.MAX_VALUE; //최소 시간을 저장할 변수이다.
			
			while(!q.isEmpty()) {
				Data curr = q.poll();
				if(curr.cnt>minTime)break; //현재 시간이 최소 시간보다 크면 더이상 돌 필요없음 (이미 최소시간의 상어를 확보했기 때문)
				
				if(map[curr.x][curr.y]!=0 && map[curr.x][curr.y]<Shark.cnt) { //상어가 커야지 먹을 수 있음
					minTime = curr.cnt;
					pq.add(new Data(curr.x, curr.y, minTime)); //물고기까지의 거리가 상어가 현재 움직인 최소 거리임
					continue;
				}
				
				for(int i=0;i<4;i++) {
					int nx = curr.x+dx[i];
					int ny = curr.y+dy[i];
					
					if(!Range(nx,ny) || map[nx][ny]>Shark.cnt || visit[nx][ny]) continue;
					visit[nx][ny]=true;
					q.add(new Data(nx,ny,curr.cnt+1));
				}
				
			}//end while q
			
			if(pq.size()==0) break; //먹을거 없으면 종료
			Data eat = pq.poll(); //먹을수 있는 물고기임
			eatFishCnt++;
			fishCnt--; //물고기 먹었으니까 총 갯수에서 하나 빼주자
			map[eat.x][eat.y]=0; //물고기 제거됨
			ansTime+=eat.cnt; //현재까지의 시간 + 물고기 먹으러간 거리(시간)
			if(eatFishCnt==Shark.cnt) {//먹은 물고기의 갯수랑 상어 크기랑 같으면?
				Shark.cnt++;
				eatFishCnt=0;
			}
			Shark.x=eat.x; Shark.y=eat.y;
		}
				
		System.out.println(ansTime);
	}
	
	static boolean Range(int x, int y) {
		if(0<=x && 0<=y && x<N && y<N)return true;
		return false;
	}
}
