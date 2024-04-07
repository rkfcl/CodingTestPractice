import java.util.*;
import java.awt.*;
class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        Map<Integer,int[]> edgeCnt = new HashMap<>();
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];

            if (!edgeCnt.containsKey(a)) {
                edgeCnt.put(a, new int[2]);
            }
            if (!edgeCnt.containsKey(b)) {
                edgeCnt.put(b, new int[2]);
            }
            edgeCnt.get(a)[0]++;
            edgeCnt.get(b)[1]++;
        }
        for(int i=1;i<=edgeCnt.size();i++){
            if(edgeCnt.containsKey(i)){ // test35 오류 해결 정점의 숫자는 오름차순이 아닌 서로 다른 수
                if(edgeCnt.get(i)[0]>=2 && edgeCnt.get(i)[1] == 0){ //시작정점
                    answer[0] = i;
                }else if(edgeCnt.get(i)[0]==0 && edgeCnt.get(i)[1] > 0){ // 막대 그래프
                    answer[2] += 1;
                }else if(edgeCnt.get(i)[0]>=2 && edgeCnt.get(i)[1] >= 2){ //8자 그래프
                    answer[3] += 1;
                }
            }
        }
        answer[1] = edgeCnt.get(answer[0])[0] - (answer[2] + answer[3]); //나머지 그래프
        return answer;
    }
}