import java.io.*;
import java.util.*;
class Solution {
    public int solution(String[] friends, String[] gifts) {
        int fl = friends.length;
        int gl = gifts.length;
        //주고받은 선물 리스트
        int[][] gel = new int[friends.length][gifts.length];
        //선물지수
        int[][] gp = new int[fl][2];
        
        int row=0,col=0;
        //선물 리스트 작성
        for(int i=0;i<gl;i++){
            String str = gifts[i];
            String[] strArray = str.split(" ");
            for(int j=0;j<fl;j++){
                if(friends[j].equals(strArray[0])){
                    row = j;
                }
                if(friends[j].equals(strArray[1])){
                    col = j;
                }
            }
            gel[row][col]++;
        }
        //선물 지수 작성
        for(int i=0;i<gel.length;i++){
            int a=0,b=0;
            for(int j=0;j<gel.length;j++){
                a+=gel[i][j];
                b+=gel[j][i];
            }
            gp[i][0] = a-b;
        }
        //받아야할 선물 개수
        for(int i=0;i<gel.length;i++){
            for(int j=0;j<gel.length;j++){  
                if(i!=j){
                    if(gel[i][j]-gel[j][i]>0){
                        gp[i][1]++;
                    }else if(gel[i][j]-gel[j][i]==0){
                        if(gp[i][0]-gp[j][0]>0){
                            gp[i][1]++;
                        }
                    }
                }
            }
        }
        int answer = 0;
        for(int i=0;i<gp.length;i++){
            answer=Math.max(gp[i][1],answer); 
        }
        return answer;
    }
}