import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,result;
    static boolean[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        board = new boolean[N][N];
        dfs(0);
        System.out.println(result);
    }

    static void dfs(int row) {
        if (row == N) {
            result += 1;
            return;
        }
        for (int col = 0; col < N; col++) {
            if (check(row, col)) {
                board[row][col] = true;
                dfs(row + 1);
                board[row][col] = false;
            }
        }
    }
    static boolean check(int x, int y) {
        // 열 체크
        for (int i = 0; i < x; i++) {
            if (board[i][y]) {
                return false;
            }
        }

        // 오른쪽 대각선 체크
        for (int i = x, j = y; i >= 0 && j < N; i--, j++) {
            if (board[i][j]) {
                return false;
            }
        }

        // 왼쪽 대각선 체크
        for (int i = x, j = y; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j]) {
                return false;
            }
        }
        return true;
    }
}