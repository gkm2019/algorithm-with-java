package BOJ;

import java.io.*;
import java.util.*;

public class Main_2961_구경민 {

	static int cnt;
	static int N;
	static Material[] arr;
	static boolean[] select; //arr의 정보를 꺼내올 index 저장
	static int ansS, ansB,ans=1000000000;
	static class Material{
		int s, b;
		Material(int s, int b){
			this.s=s;
			this.b=b;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		arr=new Material[N];
		select=new boolean[N];
		
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int s=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			arr[i]=new Material(s,b);
		}
		permutation(0);
		System.out.println(ans);
		System.out.println("!!!!!"+cnt);
	}
	
	static void permutation(int depth) {
		cnt++;
		if(depth>=N) {
			ansS=1; ansB=0;
			boolean flag=false;
			for(int i=0;i<N;i++) {
				if(!select[i])continue;
				flag=true;
				ansS*=arr[i].s;
				ansB+=arr[i].b;
			}
			
			
			for(int i=0;i<N;i++) {
				if(!select[i])continue;
				System.out.print(i+" ");
			}
			System.out.println();
			
			if(!flag)return;//공집합
			int tmp = Math.abs(ansS-ansB);
			ans=Math.min(ans, tmp);		
			
			return;
		}//end bc
		
		select[depth]=true;//선택
		permutation(depth+1);
		
		select[depth]=false;//선택 안함
		permutation(depth+1);
	}
}
