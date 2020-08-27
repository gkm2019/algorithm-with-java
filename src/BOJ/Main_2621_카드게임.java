package BOJ;

import java.util.*;
import java.io.*;

public class Main_2621_카드게임 {
	static int[] number;
	static HashSet<Character> colorSet = new HashSet<>();
	static ArrayList<Integer> numArr = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		number = new int[10];
		
		for (int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			char c = st.nextToken().charAt(0);
			int num = Integer.parseInt(st.nextToken());
			if(number[num]==0)
				numArr.add(num);
			number[num]++;
			colorSet.add(c);
		}
		
		Collections.sort(numArr);
		
		if(colorSet.size()==1) {//색 다 똑같음
			int last=numArr.size()-1;
			if(sequenceCheck()) {
				System.out.println(numArr.get(last)+900);
				return;
			}
			else {
				System.out.println(numArr.get(last)+600);
				return;
			}
		}
		
		if(numArr.size()==5 && sequenceCheck()) {//연속 수
			System.out.println(numArr.get(4)+500);
			return;
		}
		
		if(numArr.size()==2) {
			for(int i=0;i<numArr.size();i++) {
				int idx=numArr.get(i);
				int other=numArr.get((i+1)%numArr.size());
				
				if(number[idx]==4) { //4개 같은 숫자
					System.out.println(idx+800);
					return;
				}
				else if(number[idx]==3) { //3개 , 2개 같은 숫자
					System.out.println(idx*10 + other +700);
					return;
				}
			}
		}
		
		if(numArr.size()==3) {
			for(int i=0;i<numArr.size();i++) {
				int idx=numArr.get(i);
				if(number[idx]==3) {//3개, 1개, 1개
					System.out.println(idx+400);
					return;
				}
				if(number[idx]==2) {//2개,2개,1개
					//System.out.println(numArr.get(i+1)*10 + idx + 300);
					for(int j=i+1;j<numArr.size();j++) {
						int other = numArr.get(j);
						if(number[other]==2) {
							System.out.println(other*10+idx+300);
							return;
						}
					}
				}
			}
		}
		
		if(numArr.size()==4) { //2개, 1개, 1개, 1개
			for(int i=0;i<numArr.size();i++) {
				int idx=numArr.get(i);
				if(number[idx]==2) {
					System.out.println(idx+200);
					return;
				}
			}
		}
		int last=numArr.size()-1;
		System.out.println(numArr.get(last)+100);
	}

	static boolean sequenceCheck() {
		if(numArr.size()==5) {
			for(int i=1;i<5;i++) {
				if(numArr.get(i-1)+1!=numArr.get(i))
					return false;
			}
		}
		return true;
	}
}
