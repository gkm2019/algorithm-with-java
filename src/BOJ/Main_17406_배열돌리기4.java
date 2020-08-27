package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17406_배열돌리기4 {

	static int N, M, K, r, c, s,ans=Integer.MAX_VALUE;
	static int[][] map,originMap;
	static int[] dx = { 0, 1, 0, -1 }, dy = { 1, 0, -1, 0 };
	static boolean[] flag;//순열 체크
	static int[] select; //순열 결과 담기
	static struct[] oper;
	
	static class struct {
		int r, c, s;

		public struct(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		originMap = new int[N + 1][M + 1];
		flag=new boolean[K];
		select=new int[K];
		oper=new struct[K];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= M; j++) {
				originMap[i][j] = Integer.parseInt(st.nextToken());
			}
		} // end for map

		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			oper[i]=new struct(r,c,s);
		}
		permutation(0);
		System.out.println(ans);
	}// end main()
	static void permutation(int cnt) {
		if(cnt==K) {
			map=new int[N+1][M+1];
			for(int i=1;i<=N;i++)
				for(int j=1;j<=M;j++)
					map[i][j]=originMap[i][j];
			
			for(int i=0;i<K;i++) { //연산 순서가 담겨있다!
				int idx=select[i];
				r=oper[idx].r;
				c=oper[idx].c;
				s=oper[idx].s;
				rotate();
			}
			ans=Math.min(ans, Avalue());
			return;
		}
		for(int i=0;i<K;i++) {
			if(flag[i])continue;
			flag[i]=true;
			select[cnt]=i;
			permutation(cnt+1);
			flag[i]=false;
		}
	}
	
	static int Avalue() {// A의 최솟값 구하기!
		int ret = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			int tmp = 0;
			for (int j = 1; j <= M; j++) {
				tmp += map[i][j];
			}
			ret = Math.min(ret, tmp);
		}
		return ret;
	}

	static void rotate() {
		int x=r-s, y=c-s, d=0, i=0; //i는 껍데기 안으로 들어가기 위한 변수
		int tmp = map[x][y];
		
		while(true) {
			int nx=x+dx[d];
			int ny=y+dy[d];
			
			if(!(r-s+i<=nx && c-s+i<=ny && nx<=r+s-i && ny<=c+s-i)) {
				if(d==3) { //한바퀴 다돌기 끝났으면 껍데기 한칸 들어가기
					i++;
					x=r-s+i; y=c-s+i;
					tmp=map[x][y];
					if(x==r && y==c)break;
				}
				d=(d+1)%4;
				nx=x+dx[d];
				ny=y+dy[d];
			}//end if
			
			int nextTmp = map[nx][ny];
			map[nx][ny]=tmp;
			tmp = nextTmp;
			
			x=nx; y=ny;
			
		}//end while
	}//end rotate()
}
