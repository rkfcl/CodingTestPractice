import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[][] ex = {{0, 1, 2, 1}, {0, 1, 2, 1}, {0, 0, 0, 1}, {0, 0, 0, -1}};
    static int[][] ey = {{0, 0, 0, 1}, {0, 0, 0, -1}, {0, 1, 2, 1}, {0, 1, 2, 1}};
    static int N, M, result;

    static int[][] arr;
    static boolean[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        board = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] = true;
                dfs(i, j, arr[i][j], 1);
                check_exshape(i, j);
                board[i][j] = false;
            }
        }
        System.out.println(result);
    }

    public static void dfs(int x, int y, int sum_value, int lenght) {
        if (lenght >= 4) {
            result = Math.max(result, sum_value);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            //좌표 범위 초과 검사
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            //방문하지 않은 점
            if (!board[nx][ny]) {
                //방문 체크
                board[nx][ny] = true;
                dfs(nx, ny, sum_value + arr[nx][ny], lenght + 1);
                //나올때 체크 해제
                board[nx][ny] = false;
            }

        }
    }

    public static void check_exshape(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int sum = 0;
            boolean canMove = true;
            for (int j = 0; j < 4; j++) {
                int nx = x + ex[i][j];
                int ny = y + ey[i][j];
                if (nx<0||nx>=N||ny<0||ny>=M){
                    canMove = false;
                    break;
                }
                else {
                    sum += arr[nx][ny];
                }
            }
            if (canMove){
                result = Math.max(result,sum);
            }
        }
    }
}