import java.io.*;
import java.util.*;

public class Main {
    static int N, idx = 1;
    static ArrayList<Integer>[] list;
    static Set<Integer> set = new HashSet<>();
    static boolean[] visited;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        arr = new int[N + 1];
        StringTokenizer st;
        for (int i = 1; i <= N; i++) list[i] = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[v].add(w);
            list[w].add(v);
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dfs(1);
        System.out.println(1);

    }

    static void dfs(int cur) {
        visited[cur] = true;
        set.clear();
        for (int i : list[cur]) {
            if (!visited[i]) {
                set.add(i);
            }
        }
        if (set.size() == 0) return;
        if (set.contains(arr[idx])) {
            dfs(arr[idx++]);
        } else {
            System.out.println(0);
            System.exit(0);
        }

    }
}