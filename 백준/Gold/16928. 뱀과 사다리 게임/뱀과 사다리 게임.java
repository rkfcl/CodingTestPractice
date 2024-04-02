import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static Map<Integer, Integer> map = new HashMap<>();
    static boolean[] visited = new boolean[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N+M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map.put(a, b);
        }
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(1, 0));
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            if (point.x == 100) {
                return point.y;
            }
            for (int i = 1; i <= 6; i++) {
                int dx = point.x + i;
                int cnt = point.y;
                if (dx > 100 || visited[dx]) continue;
                if (map.containsKey(dx)) {
                    queue.offer(new Point(map.get(dx), cnt + 1));
                    visited[map.get(dx)] = true;
                    visited[dx] = true;
                } else {
                    queue.offer(new Point(dx, cnt + 1));
                    visited[dx] = true;
                }
            }
        }
        return 0;
    }
}