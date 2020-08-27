package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_16637_괄호추가하기 {

	static int n, ans=Integer.MIN_VALUE;
	static char[] input, opSelect;
	static int[] numSelect;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		input = new char[n];
		opSelect = new char[n/2];
		numSelect = new int[n/2+1];
		
		String str = br.readLine();
		for(int i=0;i<n;i++) {
			input[i]=str.charAt(i);
		}//end input
		
		dfs(0,0,0);
		System.out.println(ans);
	}

	static void dfs(int opCnt, int numCnt, int idx) {
		if (idx >= n) {
			int tmp = numSelect[0];
			for (int i = 0; i < opCnt; i++) {
				tmp = calc(tmp, numSelect[i + 1], opSelect[i]);
			}
			ans = Math.max(ans, tmp);
			return;
		}

		if (checkOP(input[idx])) {// 연산자일 경우 그냥 넣기
			opSelect[opCnt] = input[idx];
			dfs(opCnt + 1, numCnt, idx + 1);
		} else {
			// 괄호 O : 미리 계산해서 넣기
			if (idx + 2 < n) {
				numSelect[numCnt] = calc(input[idx]-'0', input[idx + 2]-'0', input[idx + 1]);
				dfs(opCnt, numCnt + 1, idx + 3);
			}
			// 괄호 X
			numSelect[numCnt] = input[idx]-'0';
			dfs(opCnt, numCnt + 1, idx + 1);
		}
	}
	
	static int calc(int x, int y, char op) {
		if(op=='+') return x+y;
		if(op=='-') return x-y;
		return x*y;
	}
	
	static boolean checkOP(char c) {
		if(c=='+'||c=='-'||c=='*')return true;
		return false;
	}
}
