package programmers;

public class Solution1 {

    public static void main(String[] args) {

        String s = "12223";

        int ans = Solution.solution(s);

        System.out.println(ans);
    }

    class Solution {
        public static int solution(String s) {
            int answer = -1;
            for(int i=0; i<s.length()-2; i++) {
                if((s.charAt(i) == s.charAt(i+1)) && (s.charAt(i) == s.charAt(i+2))) {
                    int num = Character.getNumericValue(s.charAt(i));
                    if(num > answer) answer = num;

                    i+=2;
                }
            }

            if(answer == -1) return -1;
            if(answer == 0) return 0;
            String str = String.valueOf(answer);
            str = str + str + str;
            answer = Integer.parseInt(str);
            return answer;
        }
    }
}
