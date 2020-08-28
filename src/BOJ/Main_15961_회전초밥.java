package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15961_회전초밥 {

	static int N, d, k, c, cnt;
	static int[] input, visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken()); //벨트 수
		d=Integer.parseInt(st.nextToken()); //초밥 가짓수
		k=Integer.parseInt(st.nextToken()); //연속 접시
		c=Integer.parseInt(st.nextToken()); //쿠폰 번호
		input=new int[N];
		for(int i=0;i<N;i++)
			input[i]=Integer.parseInt(br.readLine());
		
		System.out.println(solution());
	}

	static int solution() {
		int ans=Integer.MIN_VALUE;
		visit = new int[d+1];
		int l=0, r=l+k-1;
		
		for(int i=l;i<=r;i++) { //초기 k구간 설정해주기
			if(visit[input[i]]==0)cnt++;
			visit[input[i]]++;
		}
		if(cnt==k && visit[c]==0) return k+1; //이미 최대치임
		ans=Math.max(ans, cnt); //아직 할인 빼고  cnt담겨있음..
		
		for(int i=1;i<N;i++) {
			l=i-1; r=(i+k-1)%N;
			visit[input[l]]--;
			if(visit[input[l]]==0)cnt--; //하나밖에 없던 숫자 버리는거니까 cnt-1
			visit[input[r]]++;
			if(visit[input[r]]==1)cnt++; //새로운 숫자 생긴거니까 cnt+1
			
			ans=(visit[c]==0)?Math.max(ans, cnt+1):Math.max(ans,cnt);//할인 초밥 가짓수 치냐 안치냐
		}
		return ans;
	}
}
