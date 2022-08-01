package programmers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 전화번호목록 {
    public static void main(String[] args) {

        String[] phone_book = {"12","3","459","469", "59","6","9"};

        boolean ans = Solution.solution(phone_book);

        System.out.println(ans);
    }

    class Solution {
        public static boolean solution(String[] phone_book) {

            Arrays.sort(phone_book);
            for(int i=0; i<phone_book.length -1; i++) {
                if(phone_book[i+1].startsWith(phone_book[i])) return false;
            }
            return true;
        }
    }
}
