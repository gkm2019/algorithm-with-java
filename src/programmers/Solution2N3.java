package programmers;

import java.util.HashMap;
import java.util.Map;

public class Solution2N3 {
    public static void main(String[] args) {

        int[] levels = {1,1,2,2};

        int ans = Solution.solution(levels);

        System.out.println(ans);
    }

    class Solution {
        public static int solution(int[] tasks) {
            int answer = 0;

            Map<Integer, Integer> map = new HashMap<>();
            for(int task : tasks){
                Integer total = 1;
                if(map.containsKey(task)) {
                    total += map.get(task);
                }

                map.put(task, total);
            }

            for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if(entry.getValue() < 2) return -1;

                int mock = entry.getValue()/3;
                if(entry.getValue() % 3 != 0) mock ++;

                answer += mock;
            }

            return answer;
        }
    }
}
