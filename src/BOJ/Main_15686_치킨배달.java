package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.crypto.Cipher;

public class Main_15686_치킨배달 {

	static int n, m, ans = Integer.MAX_VALUE;
	static int[] select;
	// static Pair[] house, chicken;
	static ArrayList<Pair> house, chicken;

	static class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		select = new int[m];
		house = new ArrayList<Pair>();
		chicken = new ArrayList<Pair>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if (tmp==1) { // 집
					house.add(new Pair(i, j));
				} else if (tmp==2) { // 치킨 집
					chicken.add(new Pair(i, j));
				}
			}
		} // end input
		permutation(0,0);
		System.out.println(ans);
	}// end main()

	static void permutation(int cnt, int idx) {// 조합으로 치킨 집 개수 정하기..
		if (cnt == m) {
			// logic 수행
			//1. 집들과 치킨집의 거리 하나씩 다 비교하기..
			//집 1개 - m개의 치킨집 비교 (그 중 제일 작은 거 total에 저장..
			//이렇게 모든 집 total구해서 ans와 최소값 비교하기
			int tot=0, houseDis=0;
			for(int i=0;i<house.size();i++) {
				Pair h = house.get(i); //집의 좌표
				houseDis=Integer.MAX_VALUE;
				for(int j=0;j<m;j++) {
					Pair c = chicken.get(select[j]); //치킨의 좌표
					int d = dis(h.x, h.y, c.x, c.y); //집과 모든 치킨집의 거리를 비교한다..
					houseDis = Math.min(houseDis, d); //그 중 가장 가까운 치킨집과의 거리가 "치킨 거리"
				}
				//System.out.println("치킨거리? "+houseDis);
				tot+=houseDis; //최소의 치킨 거리르 도시의 치킨거리에 저장한다.
			}
			ans=Math.min(ans, tot);
			return;
		}
		for (int i = idx; i < chicken.size(); i++) {
			select[cnt]=i;
			permutation(cnt+1, i+1);
		}
	}

	static int dis(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}
