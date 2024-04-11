import java.util.*;
class Solution {
    static int n;
    public int solution(int coin, int[] cards) {
        n = cards.length;
        int answer = 0;
        List<Integer> deck = new ArrayList<>();
        List<Integer> drawCards = new ArrayList<>();
        for(int card : cards){
            deck.add(card);
        }
        List<Integer> userCards = new ArrayList<>();
        for(int i = 0;i<n/3;i++){
            userCards.add(deck.remove(0));
        }
        while(true){
            answer++;
            if(deck.isEmpty()) break;
            for(int i=0;i<2;i++){
                drawCards.add(deck.remove(0));
            }
            boolean flag = false;
            for(int i : userCards){ //기존 카드로 만들 수 있는지 확인
                if(userCards.contains((n+1)-i)){
                    userCards.remove(Integer.valueOf((n+1)-i));
                    userCards.remove(Integer.valueOf(i));
                    flag = true;
                    break;
                }
            }
            if(!flag){
                if(coin>0){
                    for(int i : userCards){ //뽑은 카드를 사용해서 만들수 있는지 확인
                        if(drawCards.contains((n+1)-i)){
                            userCards.remove(Integer.valueOf(i));
                            drawCards.remove(Integer.valueOf((n+1)-i));
                            coin--;
                            flag = true;
                            break;
                        }
                    }
                }
            }
            if(!flag){
                if(coin>1){
                    for(int i : drawCards){ //뽑은 카드로만 만들 수 있는 경우
                        if(drawCards.contains((n+1)-i)){
                            drawCards.remove(Integer.valueOf(i));
                            drawCards.remove(Integer.valueOf((n+1)-i));
                            coin -= 2;
                            flag = true;
                            break;
                        }
                    }
                }
            }
            if(!flag) break;
        }
        return answer;
    }
}