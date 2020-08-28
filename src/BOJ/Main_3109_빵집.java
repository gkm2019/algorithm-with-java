package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_3109_빵집 {

	static int R,C,ans;
	static int[] dx= {-1,0,1};
	static char[][] map;
	static boolean flag;
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		
		map=new char[R][C];
		for(int i=0;i<R;i++) {
			String str = br.readLine();
			for(int j=0;j<C;j++) {
				map[i][j]=str.charAt(j);
			}
		}//end input
		
		for(int x=0;x<R;x++) {
			flag=false;
			dfs(x,0); //시작!
		}
		System.out.println(ans);
	}

	static void dfs(int x, int y) {
		if(y==C-1) {
			ans++;
			flag=true;
			return;
		}
		
		for(int i=0;i<3;i++) {
			int nx=x+dx[i];
			int ny=y+1;
			
			if(!Range(nx,ny)||map[nx][ny]!='.')continue;
			map[nx][ny]='-';
			dfs(nx,ny);
			if(flag)return; //갔다왔는데 이미 true면 종료시키기
		}
	}
	
	static boolean Range(int x, int y) {
		if(0<=x && 0<=y && x<R && y<C) return true;
		return false;
	}
}
