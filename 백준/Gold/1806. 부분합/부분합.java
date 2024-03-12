
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, S;
    static int[] arr;
    static int result = Integer.MAX_VALUE;

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
        sumOfParts();
        System.out.println(result == Integer.MAX_VALUE ? 0 : result);
    }

    static void sumOfParts() {
        int start = 0, end = 0;
        int sum = 0;
        while (start < N) {
            if (sum < S && end < N) {
                sum += arr[end++];
            } else if (sum >= S) {
                result = Math.min(result, end - start);
                sum -= arr[start++];
            } else {
                break;
            }
        }
    }
}
