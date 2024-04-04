import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, result = 0;
    static int[][] map;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][2];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }
        System.out.println(bfs() ? result : -1);
    }

    static boolean bfs() {
        Queue<status> q = new LinkedList<>();
        q.offer(new status(0, 0, 1, 0));
        while (!q.isEmpty()) {
            status status = q.poll();
            int x = status.x;
            int y = status.y;
            if (x==N-1&&y==M-1){
                result = status.cnt;
                return true;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (status.wall == 0 && !visited[nx][ny][0]){
                        if (map[nx][ny] == 1) {
                            visited[nx][ny][1] = true;
                            q.offer(new status(nx,ny, status.cnt+1,1));
                        }else if (map[nx][ny] ==0){
                            visited[nx][ny][0] = true;
                            q.offer(new status(nx,ny, status.cnt+1, status.wall));
                        }
                    }else{
                        if (map[nx][ny] ==0 && !visited[nx][ny][1]){
                            visited[nx][ny][1] = true;
                            q.offer(new status(nx,ny, status.cnt+1, status.wall));
                        }
                    }

                }
            }
        }
        return false;
    }
    static class status {
        int x, y, cnt, wall;

        public status(int x, int y, int cnt, int wall) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.wall = wall;
        }

        public status(int x, int y, int wall) {
            this.x = x;
            this.y = y;
            this.wall = wall;
        }
    }
}