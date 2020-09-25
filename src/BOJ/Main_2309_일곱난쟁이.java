package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2309_일곱난쟁이 {

	static int[] tall;
	public static void main(String[] args) throws IOException {
		tall = new int[9];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int sum=0;
		for(int i=0;i<9;i++) {
			tall[i]=Integer.parseInt(br.readLine());
			sum+=tall[i];
		}
		sum -=100;

		Arrays.sort(tall);
		int l=0,r=8;
		while(l<r) {
			int tmp = tall[l]+tall[r];
			if(tmp==sum) {
				tall[l]=tall[r]=0;
				break;
			}
			else if(tmp<sum)l++;
			else r--;
		}
		
		for(int i=0;i<9;i++) {
			if(tall[i]==0)continue;
			System.out.println(tall[i]);
		}
	}

}
