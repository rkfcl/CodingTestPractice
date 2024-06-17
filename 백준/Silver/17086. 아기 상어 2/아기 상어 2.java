
import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {0, 1, -1, 1, -1, 0, 1, -1};
    static int[] dy = {1, 1, 1, 0, 0, -1, -1, -1};

    static int N, M;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        Queue<Point> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                board[i][j] = num;
                if (num == 1) {
                    q.offer(new Point(i, j));
                }
            }
        }

        int answer = bfs(q);
        System.out.println(answer - 1);
    }

    static int bfs(Queue<Point> q) {
        boolean[][] visited = new boolean[N][M];
        int maxDist = 0;

        while (!q.isEmpty()) {
            Point p = q.poll();
            int dist = board[p.x][p.y];
            maxDist = Math.max(maxDist, dist);

            for (int i = 0; i < 8; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (check(nx, ny) && !visited[nx][ny] && board[nx][ny] != 1) {
                    int nd = dist + 1;
                    if (board[nx][ny] == 0 || nd < board[nx][ny]) {
                        board[nx][ny] = nd;
                        visited[nx][ny] = true;
                        q.offer(new Point(nx, ny));
                    }
                }
            }
        }

        return maxDist;
    }

    static boolean check(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
}
