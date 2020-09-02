package BOJ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_18513_샘터 {

	static int N, K;
	static int[] input, house; // N개의 치킨집을 저장한다, house배열은 K개의 집위치를 저장할 때 사용되는 배열이다.
	static HashSet<Integer> set;// 집을 짓기 위한 방문 체크 목적으로 사용한다.
	static Queue<Pair> q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		input = new int[N]; // N개의 치킨집이 저장된다.
		house = new int[K]; // K개의 집위치를 담는다.
		set = new HashSet<Integer>();
		q = new LinkedList<Pair>();
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
			set.add(input[i]); // 치킨 좌표를 미리 넣어놓자 >> 왜? 나중에 치킨 자리에 집을 지으면 안되니까!
			q.add(new Pair(input[i]-1, 1));
			q.add(new Pair(input[i]+1, 1));
		} // end input

		System.out.println(solution());
	}

	static class Pair {
		int x, dis;

		public Pair(int x, int dis) {
			this.x = x;
			this.dis = dis;
		}
	}

	static long solution() {
		long result=0;
		while(K>0) {
			Pair front = q.poll();
			if(!Range(front.x) || set.contains(front.x))continue;
			set.add(front.x);
			result+=front.dis;
			K--;
			q.add(new Pair(front.x-1, front.dis+1));
			q.add(new Pair(front.x+1, front.dis+1));
		}

		return result;
	}

	static boolean Range(int x) { // 범위 체크를 위한 함수
		if (-100000000 <= x && x <= 100000000)
			return true;
		return false;
	}

}