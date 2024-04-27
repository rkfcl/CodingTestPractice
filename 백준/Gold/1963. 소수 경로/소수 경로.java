import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int T;
    static boolean[] prime = new boolean[10000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        makePrime();
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            Queue<Integer> q = new LinkedList<>();
            boolean[] visited = new boolean[10000];
            int[] cnt = new int[10000];
            q.offer(a);
            visited[a] = true;
            while (!q.isEmpty()) {
                int num = q.poll();
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j <= 9; j++) {
                        if (i == 0 && j == 0) {
                            continue;
                        }
                        int chNum = change(num, i, j);
                        if (!prime[chNum] && !visited[chNum]) {
                            q.offer(chNum);
                            visited[chNum] = true;
                            cnt[chNum] = cnt[num] + 1;
                        }
                    }
                }
            }
            System.out.println(cnt[b]);
        }
    }

    static int change(int num, int i, int j) {
        StringBuilder sb = new StringBuilder(String.valueOf(num));
        sb.setCharAt(i, (char) (j + '0'));
        return Integer.parseInt(sb.toString());

    }

    static void makePrime() {
        prime[0] = prime[1] = true;
        for (int i = 2; i <= Math.sqrt(10000); i++) {
            if (prime[i] == true) {
                continue;
            }
            for (int j = i * i; j < prime.length; j = j + i) {
                prime[j] = true;
            }
        }
    }
}