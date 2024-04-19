import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int N, M, K;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][K + 1];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Dot> q = new LinkedList<>();
        q.offer(new Dot(0, 0, K, 1));
        visited[0][0][K] = true;
        while (!q.isEmpty()) {
            Dot dot = q.poll();
            int x = dot.x;
            int y = dot.y;
            if (x == N - 1 && y == M - 1) {
                return dot.cnt;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int k = dot.k;
                int cnt = dot.cnt;
                if (check(nx, ny) && !visited[nx][ny][k]) {
                    if (map[nx][ny] == 0) {
                        visited[nx][ny][k] = true;
                        q.offer(new Dot(nx, ny, k, cnt + 1));
                    } else if (map[nx][ny] == 1 && k > 0 && !visited[nx][ny][k-1]) {
                        visited[nx][ny][k - 1] = true;
                        q.offer(new Dot(nx, ny, k - 1, cnt + 1));
                    }
                }
            }
        }
        return -1;
    }

    static boolean check(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }

    static class Dot {
        int x;
        int y;
        int k;
        int cnt;

        public Dot(int x, int y, int k, int cnt) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.cnt = cnt;
        }
    }
}