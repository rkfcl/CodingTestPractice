import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] S;
    static boolean[] visited;
    static boolean[] arr = new boolean[2000000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = new int[N];
        visited = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < S.length; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }
        for (int i=1;i<=S.length;i++){
            dfs(visited,0,i,0);
        }
        for (int i=1;i<arr.length;i++){
            if (!arr[i]){
                System.out.println(i);
                System.exit(0);
            }
        }
    }
    static void dfs(boolean[] visited, int n,int r,int start){
        if (r==0){
            arr[n] = true;
            return;
        }
        for (int i=start;i<S.length;i++){
            if (!visited[i]){
                visited[i] = true;
                dfs(visited,n+S[i],r-1,i+1);
                visited[i] = false;
            }
        }
    }
}