import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static boolean[][][][] visited;
    static char[][] board;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        visited = new boolean[N][M][N][M];
        boolean nCoin = false;
        int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = str.charAt(j);
                if (str.charAt(j) == 'o') {
                    if (!nCoin) {
                        x1 = i;
                        y1 = j;
                        nCoin = true;
                    } else {
                        x2 = i;
                        y2 = j;
                    }
                }
            }
        }
        System.out.println(bfs(x1, x2, y1, y2));
    }

    public static int bfs(int x1, int x2, int y1, int y2) {
        Queue<Coin> queue = new LinkedList<>();
        queue.offer(new Coin(x1, y1, x2, y2, 0));
        visited[x1][y1][x2][y2] = true;
        while (!queue.isEmpty()) {
            Coin coin = queue.poll();
            if (coin.cnt>=10) break;
            for (int i = 0; i < 4; i++) {
                int nx1 = coin.x1 + dx[i];
                int nx2 = coin.x2 + dx[i];
                int ny1 = coin.y1 + dy[i];
                int ny2 = coin.y2 + dy[i];
                if (checkWall(nx1, ny1)) {
                    nx1 = coin.x1;
                    ny1 = coin.y1;
                }
                if (checkWall(nx2, ny2)) {
                    nx2 = coin.x2;
                    ny2 = coin.y2;
                }
                int fallCoin = 0;
                if (!checkMove(nx1, ny1)) fallCoin++;
                if (!checkMove(nx2, ny2)) fallCoin++;
                if (fallCoin == 1) return coin.cnt+1;
                else if(fallCoin==0&&!visited[nx1][ny1][nx2][ny2]){
                    visited[nx1][ny1][nx2][ny2] = true;
                    queue.offer(new Coin(nx1,ny1,nx2,ny2,coin.cnt+1));
                }
            }
        }
        return -1;
    }

    static boolean checkWall(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M && board[x][y] == '#';
    }

    static boolean checkMove(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}

class Coin {
    int x1, y1, x2, y2, cnt;

    public Coin(int x1, int y1, int x2, int y2, int cnt) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.cnt = cnt;
    }
}