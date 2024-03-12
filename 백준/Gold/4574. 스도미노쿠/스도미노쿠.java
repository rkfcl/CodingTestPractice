
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, cnt = 1;
    static boolean flag;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            board = new int[9][9];
            visited = new boolean[10][10];
            if (N == 0) break;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int U = Integer.parseInt(st.nextToken());
                String LU = st.nextToken();
                int V = Integer.parseInt(st.nextToken());
                String LV = st.nextToken();
                setBoard(LU.charAt(0) - 65, LU.charAt(1) - 49, U);
                setBoard(LV.charAt(0) - 65, LV.charAt(1) - 49, V);
                visited[U][V] = true;
                visited[V][U] = true;
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= 9; i++) {
                String numDot = st.nextToken();
                setBoard(numDot.charAt(0) - 65, numDot.charAt(1) - 49, i);
            }
            flag = false;
            dfs(0, 0);
        }
        System.out.println(sb);
    }

    static void dfs(int row, int col) {
        if (col == 9) {
            dfs(row + 1, 0);
            return;
        }
        if (row == 9) {
            if (flag) return;
            flag = true;
            printBoard();
            return;
        }
        if (board[row][col] == 0) {
            for (int num = 1; num <= 9; num++) {
                if (check(row, col, num)) {
                    board[row][col] = num;
                    for (int i = 0; i < 2; i++) {
                        int nRow = row + dx[i];
                        int nCol = col + dy[i];
                        if (nRow >= 0 && nRow < 9 && nCol >= 0 && nCol < 9) {
                            if (board[nRow][nCol] == 0) {
                                for (int nNum = 1; nNum <= 9; nNum++) {
                                    if (check(nRow, nCol, nNum)) {
                                        if (!visited[num][nNum]) {
                                            board[nRow][nCol] = nNum;
                                            visited[num][nNum] = true;
                                            visited[nNum][num] = true;
                                            dfs(row, col + 1);
                                            board[nRow][nCol] = 0;
                                            visited[num][nNum] = false;
                                            visited[nNum][num] = false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    board[row][col] = 0;
                }
            }
            return;
        }
        dfs(row, col + 1);
    }

    static boolean check(int row, int col, int n) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == n || board[row][i] == n) return false;
        }
        int x = (row / 3) * 3;
        int y = (col / 3) * 3;
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                if (board[i][j] == n) return false;
            }
        }
        return true;
    }

    private static void printBoard() {
        sb.append("Puzzle ").append(cnt).append("\n");
        cnt++;
        for (int[] i : board) {
            for (int j : i) {
                sb.append(j);
            }
            sb.append("\n");
        }
    }

    static void setBoard(int x, int y, int num) {
        board[x][y] = num;
    }
}

