package programmers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TMP2 {
    public static void main(String[] args) {

        Map<Integer, Integer> map = new HashMap<>();

        map.put(1, 2);
        map.put(3, 3);

        String s = "1100";
        int n = s.length();

        String countOne = "";
        String countZero = "";

        //make result
        for(int i = 0; i < s.length(); i++) {
            char now = s.charAt(i);

            if('1' == now) {
                countOne += "1";
            } else if('0' == now) {
                countZero += "0";
            }
        }

        String resultB = countOne + countZero;


        String before = s;
        nextPermutation(s);
        String after = s;
    }


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

    public static String calculation(char[] arr, char[] result) {
        String b = "";

        for(int i = 1; i <= result.length; i++) {
            b += result[i-1];
            StringBuffer sb = new StringBuffer(b);
            String reverseB = sb.reverse().toString();

            b = reverseB;
        }

        return b;
    }
}
