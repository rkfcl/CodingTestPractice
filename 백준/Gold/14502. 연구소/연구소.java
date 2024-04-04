import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] board;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int result=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0);
        System.out.println(result);
    }

    static void dfs(int cnt) {
        if (cnt == 3) {
            bfs();
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = 1;
                    dfs(cnt + 1);
                    board[i][j] = 0;
                }
            }
        }
    }

    static void bfs() {
        Queue<Point> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 2) {
                    q.offer(new Point(i, j));
                }
            }
        }
        int[][] testMap = new int[N][M];
        for (int i=0;i<N;i++){
            testMap[i] = board[i].clone();
        }
        while (!q.isEmpty()) {
            Point point = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (testMap[nx][ny] == 0){
                        testMap[nx][ny] = 2;
                        q.offer(new Point(nx,ny));
                    }
                }
            }
        }
        int cnt=0;
        for (int i=0;i<N;i++){
            for (int j=0;j<M;j++){
                if (testMap[i][j]==0){
                    cnt++;
                }
            }
        }
        result = Math.max(result,cnt);
    }
}