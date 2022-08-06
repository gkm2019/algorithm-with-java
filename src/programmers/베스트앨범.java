package programmers;

import java.util.*;

public class 베스트앨범 {
    public static void main(String[] args) {

        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};

        int[] ans = Solution.solution(genres, plays);

        System.out.println(ans.length);
    }

    static class AlbumDTO {
        int index;
        String genre;
        int play;
        int total;

        public AlbumDTO(int index, String genre, int play, int total) {
            this.index=index;
            this.genre=genre;
            this.play=play;
            this.total=total;
        }
    }
    class Solution {
        public static int[] solution(String[] genres, int[] plays) {
            int[] answer = {};
            return answer;
        }
    }
}
