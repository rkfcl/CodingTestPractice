import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb;
    static int[] arr;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            if (k==0) break;
            arr = new int[k];
            visited = new boolean[k];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            dfs(0,6);
            sb.append("\n");
        }
        System.out.println(sb);
    }
    public static void dfs(int start, int r){
        if (r==0){
            for (int i=0;i<arr.length;i++){
                if (visited[i]){
                    sb.append(arr[i]).append(" ");
                }
            }
            sb.append("\n");
        }
        for (int i=start;i<arr.length;i++){
            if (!visited[i]){
                visited[i] = true;
                dfs(i+1,r-1);
                visited[i] = false;
            }
        }
    }
}