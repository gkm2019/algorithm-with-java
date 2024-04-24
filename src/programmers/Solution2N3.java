package programmers;

import java.util.HashMap;
import java.util.Map;

public class Solution2N3 {
    public static void main(String[] args) {

        Map<Integer, Integer> uniqueMap = new HashMap<Integer, Integer>();
        Map<Integer, Integer> arryToMap = new HashMap<Integer, Integer>();

        int[] A = new int[5];

        for (int index = 0; index < A.length; index++) {

            if (arryToMap.containsKey(A[index])) { //값이 있다면? unique에서 삭제
                uniqueMap.remove(A[index]);
            } else { //값이 없다면? 일단 unique에 넣기
                uniqueMap.put(A[index], index);
            }


            uniqueMap.put(A[index], index);
        }


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
