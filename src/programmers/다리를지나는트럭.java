package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class 다리를지나는트럭 {
    public static void main(String[] args) {

        int bridge_length = 2;
        int weight = 10;
        int[] truck_weight = {7, 4, 5, 6};

        int ans = Solution.solution(bridge_length, weight, truck_weight);
        System.out.println(ans);
    }

    class Solution {
        public static int solution(int bridge_length, int weight, int[] truck_weights) {
            int answer = 0;
            Queue<Integer> q = new LinkedList<>();

            int time = 0;
            int now_weight = 0;
            for (int i = 0; i < truck_weights.length; i++) {
                int truck = truck_weights[i];

                if (q.size() == bridge_length) {
                    int tmp = q.poll();
                    now_weight -= tmp;
                }

                if (truck + now_weight <= weight) {
                    q.add(truck);
                    now_weight += truck;
                    time++;
                } else if (truck + now_weight > weight) {
                    while (q.size() != bridge_length) {
                        q.add(0);
                        time++;
                    }
                    i--;
                }
            } //end for

            return time + bridge_length;
        }
    }
}