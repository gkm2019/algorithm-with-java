package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15649_nm1 {

	static int N,M;
	static boolean flag[];
	static int[] select;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		flag=new boolean[N+1];
		select=new int[M];
		permutation(0);
	}

	static void permutation(int cnt) {
		if(cnt==M) {
			for(int i=0;i<M;i++)
				System.out.print(select[i]+" ");
			System.out.println();
			return;
		}
		
		for(int i=1;i<=N;i++) {
			if(flag[i])continue;
			flag[i]=true;
			select[cnt]=i;
			permutation(cnt+1);
			flag[i]=false;
		}
	}
}
