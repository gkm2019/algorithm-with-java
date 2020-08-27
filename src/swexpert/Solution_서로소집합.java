package swexpert;
import java.util.*;

import javax.sound.midi.SysexMessage;

import java.io.*;

public class Solution_서로소집합 {

	static int T,n,m,ans;
	static int[] root;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T=Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine()," ");
			n=Integer.parseInt(st.nextToken());
			m=Integer.parseInt(st.nextToken());
			root = new int[n+1];
			ans=0;
			
			StringBuilder ans = new StringBuilder();
			make();
			for(int i=0;i<m;i++) {
				st = new StringTokenizer(br.readLine()," ");
				int op = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				switch(op) {
				case 0:
					union(x,y);
					break;
				case 1:
					if(find(x)==find(y))
						ans.append("1");
					else
						ans.append("0");
					break;
				}
			}
			
			System.out.println("#"+t+" "+ans);
		}//end for test_case
	}

	static void make() {
		for(int i=0;i<n+1;i++)
			root[i]=i;
	}
	static int find(int x) {
		if(root[x]==x)return x;
		return root[x]=find(root[x]);
	}
	static void union(int x, int y) {
		x=find(x);
		y=find(y);
		
		if(x==y)return;//이미 같음
		root[y]=x;
		return;
	}
}
