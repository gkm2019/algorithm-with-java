package programmers;

import java.sql.Array;
import java.util.*;

public class N문제1 {
    public static void main(String[] args) {

        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(4);
        a.add(3);
        List<Integer> rotate = new ArrayList<>();
        rotate.add(1);
        rotate.add(3);

        List<Integer> ans = Solution.solution(a, rotate);

        for(int i=0;i<ans.size();i++)
            System.out.println(ans.get(i));
    }

    class Solution {
        public static List<Integer> solution(List<Integer> a, List<Integer> rotate) {
            // Write your code here
            List<Integer> ans = new ArrayList<>();
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();

            //map 가공
            int size = a.size();
            for(int i=0; i<size; i++) {
                map.put(a.get(i), i);
            }

            //find max
            Collections.sort(a);
            int max = a.get(size -1);
            int maxIndex = map.get(max);

            //roate
            for(int i=0; i<rotate.size(); i++) {
                int rotValue = rotate.get(i)%size;
                int newIndex = maxIndex - rotValue;
                if(newIndex < 0)
                    newIndex = size + newIndex;
                ans.add(newIndex);
            }

            return ans;
        }
    }
}
