package programmers;

import java.util.*;

public class 프린터 {

    static class Pair implements Comparable<Pair> {
        int x, y;
        Pair(int x, int y) {
            this.x=x;
            this.y=y;
        }

        //오름차순
        @Override
        public int compareTo(Pair o) {
            return Integer.compare(this.x, o.x);
        }

        //내림차순
        /*
        @Override
        public int compareTo(Pair o) {
            return Integer.compare(o.x, this.x);
        }
         */
    }

    public static void main(String[] args) {

        //int[] priorities = {2,1,3,2};
        //int location = 2;
        int[] priorities = {1,1,9,1,1,1};
        int location = 0;

        int answer;
        answer = solution(priorities, location);

        System.out.println(answer);
    }

    public static int solution(int[] priorities, int location) {
        int answer = 0;

        //queue 채우기
        Queue<Pair> queue = new LinkedList<>();
        for(int i=0; i<priorities.length; i++) {
            queue.add(new Pair(priorities[i], i));
        }

        //요소 정렬
        //array int to Integer
        Integer[] tmp = Arrays.stream(priorities).boxed().toArray(Integer[]::new);
        Arrays.sort(tmp, Collections.reverseOrder());
        int maxIndex = 0;

        //3 2 2 1
        while(!queue.isEmpty()) {
            Pair p = queue.poll();
            if(p.x == tmp[maxIndex]) { //출력한다.
                answer++;
                if(p.y == location) { //원했던 요소가 출력된다.
                    break;
                }

                maxIndex++;
            } else { //출력하지 않고 다시 push
                queue.add(p);
            }
        }

        return answer;
    }

}
