package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_다음순열_permutation버전 {

	static int n;
	static int[] input;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		input = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i=0;i<n;i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}//end input
		
		String before = Arrays.toString(input);
		nextPermutation(input);
		String after = Arrays.toString(input);
		
		if(before.equals(after)) {
			System.out.println(-1);
			return;
		}
		for(int i: input)
			System.out.print(i+" ");
		System.out.println();
		
	}//end main()
	
	static boolean nextPermutation(int[] arr) {
		/* 1. 꼭대기 i찾기
		 * 2. i-1 < j값 찾기
		 * 3. i-1, j swap 하기
		 * 4. i부터~뒤까지 swap하면서 정렬하기...
		 * */
		
		int i=n-1;
		while(i>0 && arr[i-1]>=arr[i])--i;
		if(i==0)return false; //더이상 큰 꼭대기 값 없으니까 종료
		
		int j=n-1;
		while(arr[i-1]>=arr[j])--j;
		
		swap(arr, i-1, j);
		
		int k=n-1;
		while(i<k) {
			swap(arr, i++, k--); //뒷부분 정렬...
		}
		
		return true;
	}//end nextPermutation()
	
	static void swap(int[] arr, int x, int y ) {
		int tmp = arr[x];
		arr[x]=arr[y];
		arr[y]=tmp;
	}

}
