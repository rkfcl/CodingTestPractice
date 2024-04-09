import java.util.*;

class Solution {
    static List<Integer> selDice = new ArrayList<>();
    static List<Integer> arrA;
    static List<Integer> arrB;
    static int[][] copyDice;
    static int maxWin = Integer.MIN_VALUE;
    static int n;
    static int[] answer;

    public static int[] solution(int[][] dice) {
        n = dice.length; //주사위 개수
        copyDice = dice;
        answer = new int[n/2];
        selectDice(0, 0);

        return answer;
    }

    static void selectDice(int idx, int selected) { //주사위 고르기
        if (idx == n / 2) {
            int win = calWin(); //승률 계산
            if (win > maxWin) {
                maxWin = win;
                for (int i=0;i<selDice.size();i++){
                    answer[i] = selDice.get(i) + 1;
                }
            }
            return;
        }
        for (int i = selected; i < n; i++) {
            selDice.add(i);
            selectDice(idx + 1, i + 1);
            selDice.remove(selDice.size() - 1);
        }
    }

    static int calWin() {
        int cnt = 0;
        makeArrAB(); // 선택한 조합에 대한 모든 경우의 수 저장
        Collections.sort(arrB);
        for (int i = 0; i < arrA.size(); i++) {
            int num = arrA.get(i);
            int left = 0, right = arrB.size() - 1;
            int idx = -1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (arrB.get(mid) < num) {
                    left = mid + 1;
                    idx = Math.max(idx, mid);
                } else {
                    right = mid - 1;
                }
            }
            if (idx != -1) {
                cnt += idx + 1;
            }
        }

        return cnt;
    }

    public static void makeArr(int depth, int[][] dice, int sum, List<Integer> arr) {
        if (depth == n / 2) {
            arr.add(sum);
            return;
        }
        for (int i = 0; i < 6; i++) {
            makeArr(depth + 1, dice, sum + dice[depth][i], arr);
        }
    }

    public static void makeArrAB() {
        arrA = new ArrayList<>();
        arrB = new ArrayList<>();
        int[][] diceA = new int[n / 2][6];
        int[][] diceB = new int[n / 2][6];
        int a = 0, b = 0;
        for (int i = 0; i < n; i++) {
            if (selDice.contains(i)) {
                diceA[a] = copyDice[i];
                a++;
            } else {
                diceB[b] = copyDice[i];
                b++;
            }
        }
        makeArr(0, diceA, 0, arrA);
        makeArr(0, diceB, 0, arrB);
    }
}