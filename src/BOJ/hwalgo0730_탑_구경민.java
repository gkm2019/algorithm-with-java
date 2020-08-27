package BOJ;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class hwalgo0730_탑_구경민 {

	static int n,top=-1;
	static int[] stack, arr, ret;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		stack = new int[n];
		arr = new int[n];
		ret = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		int prev;
		
		for(int i=0;i<n;i++) {
			while(top!=-1) {
				if(arr[i]>arr[stack[top]]) {
					top--;
				}else {
					break;
				}
			}//end while
			
			if(top==-1) {//stack이 비어있다면 앞에 더 높은것이 없다는 뜻
				prev=0;
			}else {//나보다 앞에 높은 건물이 있다면?
				prev=stack[top]+1;
			}
			
			ret[i]=prev;
			stack[++top]=i; //다음을 비교를 위해 현재 index stack에 push
		}
		
		for(int i=0;i<n;i++) {
			System.out.print(ret[i]+" ");
		}
	}

}
