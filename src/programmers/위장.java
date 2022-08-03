package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 위장 {

    public static void main(String[] args) {

        String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};

        int ans = Solution.solution(clothes);

        System.out.println(ans);
    }

    class Solution {
        public static int solution(String[][] clothes) {
            int answer = 1;
            Map<String, Integer> map = new HashMap<>();
            for(String[] strs : clothes) {
                String key = strs[1];

                if(map.containsKey(key)) {
                    int value = map.get(key);
                    map.put(key, value + 1);
                } else {
                    map.put(key, 1);
                }
            }

            for(Map.Entry<String, Integer> entry : map.entrySet()) {
                int tmp = entry.getValue() + 1;
                answer *= tmp;
            }
            return answer -1;
        }
    }
}
