package swexpert;

import java.util.*;
import java.io.*;

public class Solution_1222_계산기1 {

	static int len, numtop = -1;
	static int numstack[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= 10; tc++) {// test_case
			len = Integer.parseInt(br.readLine());
			numtop = -1;
			numstack = new int[len]; // 피연산자 담을 스택

			String str = br.readLine();
			for (int i = 0; i < len; i++) {//+만 있는 후위 연산자로 만들기
				char data = str.charAt(i);
				if ('0' <= data && data <= '9') {// 숫자일경우 push
					numstack[++numtop] = data - '0';
				} else {// 연산자 일 경우 내용물 2개 pop해서 연산!
					int x=str.charAt(i+1)-'0';
					int y=numstack[numtop--];
					numstack[++numtop]=y+x;
					i++;
				}
			}//후위 연산자 반복문 종료

			System.out.println("#" + tc + " " + numstack[numtop]);
		} // end for test_case
	}

}

