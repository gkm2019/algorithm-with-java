package programmers;

import java.util.*;

public class 주식가격 {
    public static void main(String[] args) {

        int[] prices = {2, 3, 4, 2, 1};

        int[] ans = Solution.solution(prices);

        for(int i=0;i<ans.length;i++)
            System.out.println(ans[i]);
    }

    class Solution {
        public static int[] solution(int[] prices) {
            Stack<Integer> s = new Stack<>();
            int size = prices.length;
            int[] answer = new int[size];

            for(int i=0; i<size; i++) {
                while(!s.empty() && prices[i] < prices[s.peek()]) {
                    answer[s.peek()] = i - s.peek();
                    s.pop();
                }

                s.push(i);
            }

            while(!s.empty()) {
                answer[s.peek()] = size - 1 - s.peek();
                s.pop();
            }
            return answer;
        }
    }
}
