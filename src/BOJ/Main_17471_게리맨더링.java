package BOJ;

import java.io.*;
import java.util.*;

public class Main_17471_게리맨더링 {

	static int N, ans=Integer.MAX_VALUE; // 구역의 수이다. 1~N번까지, 정답: 2지역 인구수 차이의 최소값을 담아야한다.
	static ArrayList<Integer>[] input; //구역별 연결 상태 저장
	//peopole: 각 구역의 인구 수 저장
	//select: 하나의 구역 정할 때 사용할 그릇(부분집합 담을 그릇)
	//Area: 1구역/2구역 나눠서 구분지을 배열이다. A구역은 1, B구역이면 0로 표기
	static int[] people, select, Area;
	static boolean[] visit;//구역간의 연결이 온전한지 확인하기 위한 배열
	static int total;//전체 구역의 인구 합
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		people = new int[N+1];
		input = new ArrayList[N+1];
		select = new int[N/2]; //최대 n/2명 만 담아도된다.
		
		for(int i=1;i<=N;i++)
			input[i]=new ArrayList<Integer>();
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i=1;i<=N;i++) {
			people[i]=Integer.parseInt(st.nextToken());
			total+=people[i];
		}
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int E = Integer.parseInt(st.nextToken());
			
			for(int e=0;e<E;e++) {
				int nextNode = Integer.parseInt(st.nextToken()); //i노드를 ed에 연결한다.
				input[i].add(nextNode);
			}
		}//end input
		
		//조합은 1명부터~N/2만큼만 돌려도 된다.
		for(int i=1;i<=N/2;i++)
			combination(0,1,i); 
		if(ans==Integer.MAX_VALUE)
			ans=-1;
		System.out.println(ans);
	}

	static void combination(int cnt, int idx, int size) {
		if(cnt==size) {
			solution(size);
			return;
		}
		for(int i=idx;i<=N;i++) {
			select[cnt]=i;
			combination(cnt+1, i+1, size);
		}
	}

	static void solution(int size) {
		//select기준으로 구역을 2개로 나누고
		//구역끼리 잘 연결되어있는지 확인하고
		//잘 연결 되어있다면 각 구역의 인구 수 차이를 구하고
		//최소 값을 구한다.
		
		//구역 2개로 나누기 A:1, B:0
		Area = new int[N+1];
		for(int i=0;i<size;i++) {
			Area[select[i]]=1; //a구역 표기 (b는 자동으로 표기됨)
		}
		
		//각 구역 시작 위치 뽑아내기
		int Astart = select[0];
		int Bstart=0;
		for(int i=1;i<=N;i++) {
			if(Area[i]==1)continue;
			Bstart=i;
			break;
		}
		
		visit = new boolean[N+1];
		dfs(Astart, 1);
		dfs(Bstart, 0);
		
		boolean flag = true; //온전히 구역끼리 연결되었는가?
		for(int i=1;i<=N;i++) {
			if(visit[i])continue;
			flag=false;
			break;
		}
		
		if(!flag)return;
		//온전히 연결 되었으니까 인구 수 비교해보자
		int totalA =0, totalB=0;
		for(int i=1;i<=N;i++) {
			if(Area[i]==0)continue;
			totalA+=people[i];
		}
		totalB=total-totalA;
		int tmp = Math.abs(totalA-totalB);
		ans=Math.min(ans, tmp);
	}
	
	
	static void dfs(int v, int val) {
		visit[v]=true;
		for(Integer x: input[v]) {
			if(Area[x]==val && !visit[x]) {
				dfs(x, val);
			}
		}
	}
}
