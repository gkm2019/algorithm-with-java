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
            Map<String, Integer> sortTotalMap = new HashMap<>();
            List<AlbumDTO> albumDTOs = new ArrayList<>();
            //장르별 total 재생 횟수
            for(int i=0; i<genres.length; i++) {
                String key = genres[i];
                int value = plays[i];
                if(sortTotalMap.containsKey(key)) {
                    value += sortTotalMap.get(key);
                }

                sortTotalMap.put(key, value);
                AlbumDTO dto = new AlbumDTO(i, key, plays[i], 0);
                albumDTOs.add(dto);
            }

            //total count setting
            for(AlbumDTO dto : albumDTOs) {
                int total = sortTotalMap.get(dto.genre);
                dto.total = total;
            }

            //장르 total counting
            //TreeMap<String, List<AlbumDTO>> result = albumDTOs.stream()
            return answer;
        }
    }
}
