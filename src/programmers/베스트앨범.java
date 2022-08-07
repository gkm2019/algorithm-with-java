package programmers;

import java.util.*;

public class 베스트앨범 {
    public static void main(String[] args) {

        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};

        int[] ans = Solution.solution(genres, plays);

    }

    public static class AlbumDTO {
        int total;
        List<Integer> indexList;

        public AlbumDTO(int total, List<Integer> indexList) {
            this.total=total;
            this.indexList=indexList;
        }
    }

    class Solution {
        public static int[] solution(String[] genres, int[] plays) {
            List<Integer> answer = new ArrayList<>();
            Map<String, AlbumDTO> map = new HashMap<>();

            //map 가공
            for(int i=0; i<genres.length; i++) {
                int total = plays[i];
                String key = genres[i];

                if(map.containsKey(key)) {
                    AlbumDTO dto = map.get(key);
                    dto.total += total;
                    dto.indexList.add(i);
                    map.put(key, dto);
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    map.put(key, new AlbumDTO(total, list));
                }
            }

            //total: 장르 정렬
            List<Map.Entry<String, AlbumDTO>> result = new LinkedList<>(map.entrySet());
            Collections.sort(result, new Comparator<Map.Entry<String, AlbumDTO>>() {
                @Override
                public int compare(Map.Entry<String, AlbumDTO> o1, Map.Entry<String, AlbumDTO> o2) {
                    if(o1.getValue().total <= o2.getValue().total) return 1;
                    else return -1;
                }
            });

            //장르 내의 재생 노래 정렬 answer 채우기
            for(Map.Entry<String, AlbumDTO> entry : result) {
                int maxIndex = 0;
                //개별 플레이 횟수 정렬
                Collections.sort(entry.getValue().indexList, new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        if(plays[o1] < plays[o2]) return 1;
                        return -1;
                    }
                });

                answer.add(entry.getValue().indexList.get(0));
                answer.add(entry.getValue().indexList.get(1));
            }

            return answer.stream().mapToInt(i->i).toArray();
        }
    }
}
