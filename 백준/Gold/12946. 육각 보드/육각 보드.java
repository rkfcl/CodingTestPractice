
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[][] board;
    static int[][] col;
    static boolean[][] visited;
    static int result = 0;
    static final int[] dx = {-1, -1, 0, 0, 1, 1};
    static final int[] dy = {0, 1, -1, 1, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        col = new int[N][N];
        visited = new boolean[N][N];
        //배열 채우기
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = str.charAt(j);
                col[i][j] = -1;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 'X' && col[i][j] == -1){
                    dfs(i,j,0);
                }
            }
        }
        System.out.println(result);
    }

    public static void dfs(int x, int y, int color) {
        col[x][y] = color;
        result = Math.max(result,1);
        for (int i = 0; i < 6; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (check(nx, ny) && board[nx][ny] == 'X') {
                if (col[nx][ny] == -1){
                    dfs(nx,ny,1-color);
                }
                result = Math.max(result,2);
                if (col[nx][ny] == color){
                    result = Math.max(result,3);
                }
            }
        }
    }

    static boolean check(int x, int y) { //좌표 범위 확인
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
