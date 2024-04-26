import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int W, H;
    static char[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[][][] visited;
    static Laser[] cLaser;
    static int result = Integer.MAX_VALUE;
    static PriorityQueue<Laser> q = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        visited = new int[H][W][4];
        cLaser = new Laser[2];
        for (int i = 0, idx = 0; i < H; i++) {
            String line = br.readLine();
            map[i] = line.toCharArray();
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 'C') cLaser[idx++] = new Laser(i, j, -5, -1);
            }
        }
        bfs(cLaser[0]);
        System.out.println(result);
    }

    static void bfs(Laser start) {
        Laser c = cLaser[1];
        q.add(start);
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }
        while (!q.isEmpty()) {
            Laser laser = q.poll();
            if (laser.x == c.x && laser.y == c.y) {
                result = Math.min(result, laser.mir);
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nx = laser.x + dx[i];
                int ny = laser.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= H || ny >= W) continue;

                if (map[nx][ny] == '*') continue;
                if (Math.abs(i - laser.dir) == 2) continue;
                int nMir = laser.dir == i ? laser.mir : laser.mir + 1;
                if (visited[nx][ny][i] > nMir){
                    visited[nx][ny][i] = nMir;
                    q.offer(new Laser(nx,ny,i,nMir));
                }
            }
        }
    }


    static class Laser implements Comparable<Laser> {
        int x, y, dir, mir;

        public Laser(int x, int y, int dir, int mir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.mir = mir;
        }
        @Override
        public int compareTo(Laser o){
            return this.mir - o.mir;
        }
    }
}