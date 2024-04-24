package programmers;

import java.util.*;
import java.util.stream.Collectors;

public class 베스트앨범 {
    public static void main(String[] args) {

        String s = "1100";
        int n = s.length();

//        int n = 3;
//        int[] arr = {1, 1, 0, 0};
//        int[] output = new int[n];
//        boolean[] visited = new boolean[n];

        boolean[] visited = new boolean[n];
        char[] arr = new char[n];
        char[] output = new char[n];

        String countOne = "";
        String countZero = "";

        //make result
        for(int i = 0; i < s.length(); i++) {
            char now = s.charAt(i);
            arr[i] = now;

            if('1' == now) {
                countOne += "1";
            } else if('0' == now) {
                countZero += "0";
            }
        }

        String resultB = countOne + countZero;
//        permutation(arr, result, visited, 0, s.length(), s.length(), resultB);

        String answer = "";
        perm(arr, output, visited, 0, n, n, resultB, answer);
        System.out.println();
    }

    // 사전순으로 순열 구하기
    // 사용 예시: perm(arr, output, visited, 0, n, 3);
    static void perm(char[] arr, char[] output, boolean[] visited, int depth, int n, int r, String resultB, String answer) {
        String out = new String(output);
        if(answer.equals(out)) return;

        if (depth == r) {
            print(output, r);
            String b = calculation(arr, output);
            if(resultB.equals(b)) {
                answer = new String(output);

                System.out.println("answer(output) : " + answer);
                System.out.println("b :" + b);
                return;
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i] != true) {
                visited[i] = true;
                output[depth] = arr[i];
                perm(arr, output, visited, depth + 1, n, r, resultB, answer);
                visited[i] = false;
            }
        }
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

    // 배열 출력
    static void print(char[] arr, int r) {
        for (int i = 0; i < r; i++)
            System.out.print(arr[i] + " ");
        System.out.println();

    }
}
