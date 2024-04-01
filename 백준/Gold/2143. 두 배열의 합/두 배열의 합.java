import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T, n, m;
    static int[] A;
    static int[] B;
    static List<Integer> subA = new ArrayList<>();
    static List<Integer> subB = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        A = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        m = Integer.parseInt(br.readLine());
        B = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
        findSubArrays();
        Collections.sort(subA);
        Collections.sort(subB);
        System.out.println(twoPointer(subA.size(), subB.size()));

    }

    static Long twoPointer(int pointA, int pointB) {
        long cnt = 0;
        int start = 0;
        int end = pointB - 1;
        while (start < pointA && end >= 0) {
            int sum = subA.get(start) + subB.get(end);
            if (sum > T) {
                end--;
            } else if (sum < T) {
                start++;
            }else if (sum==T){
                int a = subA.get(start);
                int b = subB.get(end);
                long cntA = 0;
                long cntB = 0;
                while (start<pointA&&subA.get(start)==a){
                    cntA++;
                    start++;
                }
                while (end>=0&&subB.get(end)==b){
                    cntB++;
                    end--;
                }
                cnt +=cntA*cntB;
            }
        }
        return cnt;
    }
    static void findSubArrays() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum = 0;
            for (int j = i; j < n; j++) {
                sum += A[j];
                subA.add(sum);
            }

        }
        for (int i = 0; i < m; i++) {
            sum = 0;
            for (int j = i; j < m; j++) {
                sum += B[j];
                subB.add(sum);
            }

        }
    }
}