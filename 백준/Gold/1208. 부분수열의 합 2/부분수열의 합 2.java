import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, S;
    static List<Integer> Llist = new ArrayList<>();
    static List<Integer> Rlist = new ArrayList<>();
    static int[] arr;
    static long result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        //배열 두수열로 나눠서 계산
        getSumSubsequence(0, N / 2, 0, Llist);
        getSumSubsequence(N / 2, N, 0, Rlist);
        Collections.sort(Llist);
        Collections.sort(Rlist);
        getCnt();
        System.out.println(S == 0 ? result-1 : result); //정수 값 0 일경우 두 리스틑 공집합 0 + 0 생겨서 결과값 -1
    }

    static void getSumSubsequence(int idx, int end, int sum, List<Integer> list) {
        if (idx == end) {
            list.add(sum);
            return;
        }
        getSumSubsequence(idx + 1, end, sum + arr[idx], list);
        getSumSubsequence(idx + 1, end, sum, list);
    }

    //투 포인터
    static void getCnt() {
        int lIdx = 0;
        int rIdx = Rlist.size() - 1;
        int sum;
        while (lIdx < Llist.size() && rIdx >= 0) {
            int lNum =Llist.get(lIdx);
            int rNum = Rlist.get(rIdx);
            sum = lNum + rNum;
            if (sum == S) { //lNum, rNum 리스트 같은 값 개수 구해서 경우의 수 구하기
                long lCnt = 0; //부분 집합의 개수가 int 범위를 초과 할 수 있기 때문에 long으로 선언
                long rCnt = 0;
                while (lIdx< Llist.size()&&Llist.get(lIdx)==lNum){
                    lIdx++;
                    lCnt++;
                }
                while (rIdx>=0&&Rlist.get(rIdx)==rNum){
                    rIdx--;
                    rCnt++;
                }
                result += lCnt * rCnt;

            }else if (sum>S){
                rIdx--;
            }else if (sum<S){
                lIdx++;
            }
        }
    }
}