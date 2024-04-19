import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static char[][] map = new char[8][8];
    static boolean[][] visited = new boolean[8][8];
    static int dx[] = {1, -1, 0, 0, 1, -1, -1, 1, 0};
    static int dy[] = {0, 0, 1, -1, 1, -1, 1, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 8; i++) {
            String str = br.readLine();
            for (int j = 0; j < 8; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(7, 0));
        while (!q.isEmpty()) {
            visited = new boolean[8][8];
            int size = q.size();
            for (int j=0;j<size;j++) {
                Point point = q.poll();
                int x = point.x;
                int y = point.y;
                if (x == 0 && y == 7) {
                    return 1;
                }
                if (map[x][y] == '#') {
                    continue;
                }
                for (int i = 0; i < 9; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (check(nx, ny) && !visited[nx][ny] && map[nx][ny] != '#') {
                        visited[nx][ny] = true;
                        q.offer(new Point(nx, ny));
                    }
                }
            }
            dropWall();
        }
        return 0;
    }

    static void dropWall() {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                if (map[i][j] == '#') {
                    map[i][j] = '.';
                    if (i + 1 < 8) {
                        map[i + 1][j] = '#';
                    }
                }
            }
        }
    }

    static boolean check(int x, int y) {
        return x >= 0 && y >= 0 && x < 8 && y < 8;
    }
}