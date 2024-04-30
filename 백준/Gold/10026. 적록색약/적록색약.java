import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int N;
    static char[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        visited = new boolean[N][N];
        int cnt = 0;
        for (int i=0;i<N;i++){
            for (int j=0;j<N;j++){
                if (visited[i][j]) continue;
                bfs(i,j);
                cnt++;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(cnt).append(" ");
        for (int i=0;i<N;i++){
            for (int j=0;j<N;j++){
                if (map[i][j] == 'R') map[i][j] = 'G';
            }
        }
        visited = new boolean[N][N];
        cnt = 0;
        for (int i=0;i<N;i++){
            for (int j=0;j<N;j++){
                if (visited[i][j]) continue;
                bfs(i,j);
                cnt++;
            }
        }
        sb.append(cnt);
        System.out.println(sb.toString());
    }

    static void bfs(int x,int y) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y));
        while (!q.isEmpty()) {
            Point point = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny]) continue;
                if (map[point.x][point.y] != map[nx][ny]) continue;
                q.offer(new Point(nx,ny));
                visited[nx][ny] = true;
            }

        }
    }
}