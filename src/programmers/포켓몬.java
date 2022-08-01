package programmers;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class 포켓몬 {

    public static void main(String[] args) {

        int[] nums = {3,3,3,2,2,4};

        int ans = Solution.solution(nums);

        System.out.println(ans);
    }

    class Solution {
        public static int solution(int[] nums) {
            int answer = 0;
            Set<Integer> set = new HashSet<>();

            for(int i=0; i<nums.length; i++) {
                set.add(nums[i]);
            }

            answer = set.size() >= nums.length/2 ? nums.length/2 : set.size();
            return answer;
        }
    }
}
