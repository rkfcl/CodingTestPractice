import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int K, W, H;
    static int[][] board;
    static int[] dx = {1, -1, 0, 0, 2, 2, -2, -2, 1, -1, 1, -1};
    static int[] dy = {0, 0, 1, -1, 1, -1, 1, -1, 2, 2, -2, -2};
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        visited = new boolean[H][W][K+1];
        board = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Horse> q = new LinkedList<>();
        q.offer(new Horse(0, 0, K, 0));
        visited[0][0][K] = true;
        
        while (!q.isEmpty()) {
            Horse h = q.poll();
            if (h.x == H - 1 && h.y == W - 1) return h.cnt;

            for (int i = 0; i < (h.k > 0 ? 12 : 4); i++) {
                int nx = h.x + dx[i];
                int ny = h.y + dy[i];
                int nk = (i >= 4) ? h.k - 1 : h.k;

                if (check(nx, ny) && !visited[nx][ny][nk]) {
                    visited[nx][ny][nk] = true;
                    q.offer(new Horse(nx, ny, nk, h.cnt + 1));
                }
            }
        }
        return -1;
    }

    static boolean check(int x, int y) {
        return x >= 0 && y >= 0 && x < H && y < W && board[x][y] == 0;
    }

    static class Horse {
        int x, y, k, cnt;

        public Horse(int x, int y, int k, int cnt) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.cnt = cnt;
        }
    }
}
