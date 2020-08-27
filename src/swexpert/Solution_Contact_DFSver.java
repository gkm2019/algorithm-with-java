package swexpert;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import swexpert.Solustion_Contact.Pair;

public class Solution_Contact_DFSver {

	static int SIZE = 101;
	static ArrayList<Integer>[] map;
	static int dis[];
	static int N, start, ansCnt, ansNum;

	static class Pair {
		int idx, cnt;

		public Pair(int idx, int cnt) {
			this.idx = idx;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int t = 1; t <= 10; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());

			map = new ArrayList[SIZE];
			for (int i = 1; i < SIZE; i++) { // map 정점들 초기화
				map[i] = new ArrayList<Integer>();
			} // end for init map

			dis = new int[SIZE];
			Arrays.fill(dis, -1);
			ansNum = -1;
			ansCnt = -1;

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				map[from].add(to); // map에 인접 정점 저장
			} // end input=====

			dfs(start, 1);
			
			for(int i=0;i<SIZE;i++) {
				if(ansCnt<dis[i]) {
					ansCnt=dis[i];
					ansNum=i;
				}else if(ansCnt==dis[i]) {
					ansNum=i;
				}
			}
			System.out.println("#" + t + " " + ansNum);
		} // end for test_case;
	}

	static void dfs(int v, int cnt) {
		dis[v]=cnt;
		for (int m : map[v]) {
			if(dis[m]==-1 || dis[m]>cnt) {
				dfs(m,cnt+1);
			}
		}
	}
}
