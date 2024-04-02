import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] list;
    static int N;
    static boolean[] visited;
    static int[] arr;
    static Queue<Integer> queue = new LinkedList<>();
    static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N+1];
        arr = new int[N];
        list = new ArrayList[N+1];
        for (int i=1;i<=N;i++) list[i] = new ArrayList<>();
        StringTokenizer st;
        for (int i=1;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list[x].add(y);
            list[y].add(x);
        }
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        if (arr[0] != 1) {
            System.out.println(0);
            return;
        }
        queue.offer(1);
        visited[1] = true;
        System.out.println(bfs());
    }

    public static int bfs() {
        int idx = 1;
        while (!queue.isEmpty()) {
            set.clear();
            int cur = queue.poll();
            for (int next : list[cur]){
                if (!visited[next]) {
                    set.add(next);
                    visited[next] = true;
                }
            }
            int size = set.size();
            for (int i = idx ; i<idx+size;i++){
                if (set.contains(arr[i])) queue.offer(arr[i]);
                else return 0;
            }
            idx += size;
        }
        return 1;
    }
}