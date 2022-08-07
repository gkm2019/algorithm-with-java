package programmers;

import java.util.*;

public class N문제2 {
    public static void main(String[] args) {

        List<List<Integer>> grid = new ArrayList<>();
        List<Integer> subGrid = new ArrayList<>();
        subGrid.add(1);
        subGrid.add(1);
        subGrid.add(1);
        subGrid.add(1);

        List<Integer> subGrid2 = new ArrayList<>();
        subGrid2.add(2);
        subGrid2.add(2);
        subGrid2.add(2);
        subGrid2.add(2);

        List<Integer> subGrid3 = new ArrayList<>();
        subGrid3.add(3);
        subGrid3.add(3);
        subGrid3.add(3);
        subGrid3.add(3);

        List<Integer> subGrid4 = new ArrayList<>();
        subGrid4.add(4);
        subGrid4.add(4);
        subGrid4.add(4);
        subGrid4.add(4);

        grid.add(subGrid);
        grid.add(subGrid2);
        grid.add(subGrid3);
        grid.add(subGrid4);

        int maxSum = 39;

        int ans = Solution.solution(grid, maxSum);
        System.out.println(ans);

    }

    class Solution {
        public static int solution(List<List<Integer>> grid, int maxSum) {
            int answer = 0; //return sub-grid
            int n = grid.size();
            int[][] dp = new int[n][n];
            int max = -1;

            for(int x=0; x<n; x++) {
                for(int y=0; y<n; y++) {
                    if(x==0 && y==0) {
                        dp[0][0] = grid.get(x).get(y);
                    } else if(x==0) {
                        dp[0][x] = dp[x-1][0] + grid.get(x).get(0);
                    } else {
                        dp[x][y] = dp[x-1][y] + dp[x][y-1] + grid.get(x).get(y) - dp[x-1][y-1];
                    }

                    if(grid.get(x).get(y)>max)
                        max = grid.get(x).get(y);
                }
            }

            int indexI = 0;
            int indexJ = n;

            while(indexI<indexJ){
                int newX = indexI + (indexJ-indexI+1)/2;
                int result = 0;

                for(int i=newX-1; i<n; i++) {
                    for(int j=newX-1; j<n; j++){
                        int sum = dp[i][j];

                        if(i>=newX) sum = sum - dp[i-newX][j];
                        if(j>=newX) sum = sum - dp[i][j-newX];
                        if(i>=newX && j>=newX) sum = sum + dp[i-newX][j-newX];
                        if(sum > result) result = sum;
                    }
                }

                if(maxSum >= result) indexI=newX;
                else indexJ=newX-1;
            }

            return indexJ;

            /*
            for(int size=1; size<=n; size++){
                int gridMax = -1;
                for(int x=0; x<=n-size; x++){
                    for(int y=0; y<=n-size; y++){
                        int sum = sumInGrid(grid, x, y, size);
                        if(sum>gridMax) gridMax = sum;
                    }
                }
                if(gridMax==maxSum){
                    answer = size;
                    break;
                } else if(gridMax > maxSum) {
                    answer = size-1;
                    break;
                }
            }

            if(answer == 1) return 0;
            return answer;
            */
        }

        public static int sumInGrid(List<List<Integer>> grid, int x, int y, int size) {
            int sum = 0;

            for(int i=x; i<x+size; i++){
                for(int j=y; j<y+size; j++){
                    sum+=grid.get(i).get(j);
                }
            }

            return sum;
        }
    }
}
