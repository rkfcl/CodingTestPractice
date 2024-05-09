import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int T, h, w;
    static char[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            map = new char[h + 2][w + 2];
            int[][] p1, p2, shang;
            int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
            for (int i = 1; i <= h; i++) {
                String str = br.readLine();
                for (int j = 1; j <= w; j++) {
                    char c = str.charAt(j - 1);
                    if (c == '$') {
                        if (x1 == 0) {
                            x1 = i;
                            y1 = j;
                        } else {
                            x2 = i;
                            y2 = j;
                        }
                    }
                    map[i][j] = c;
                }
            }
            p1 = bfs(x1, y1);
            p2 = bfs(x2, y2);
            shang = bfs(0, 0);
            int minDoor = Integer.MAX_VALUE;
            for (int i = 0; i < p1.length; i++) {
                for (int j = 0; j < p1[i].length; j++) {
                    if (map[i][j] == '*') continue;
                    if (p1[i][j] == -1 && p2[i][j] == -1 && shang[i][j] == -1) continue;
                    int min = p1[i][j] + p2[i][j] + shang[i][j];
                    if (map[i][j] == '#') min -= 2;
                    minDoor = Math.min(minDoor, min);
                }
            }
            sb.append(minDoor).append("\n");
            T--;
        }
        System.out.println(sb);
    }

    static int[][] bfs(int x, int y) {
        PriorityQueue<FindWay> q = new PriorityQueue<>();
        q.offer(new FindWay(x, y, 0));
        int[][] door = new int[h + 2][w + 2];
        for (int[] i : door) {
            Arrays.fill(i, -1);
        }
        door[x][y] = 0;
        while (!q.isEmpty()) {
            FindWay findWay = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = findWay.x + dx[i];
                int ny = findWay.y + dy[i];
                if (nx < 0 || nx >= h + 2 || ny < 0 || ny >= w + 2) continue;
                if (door[nx][ny] != -1 || map[nx][ny] == '*') continue;
                door[nx][ny] = map[nx][ny] == '#' ? findWay.door + 1 : findWay.door;
                q.offer(new FindWay(nx, ny, map[nx][ny] == '#' ? findWay.door + 1 : findWay.door));

            }
        }
        return door;
    }

    static class FindWay implements Comparable<FindWay> {
        int x, y, door;

        public FindWay(int x, int y, int door) {
            this.x = x;
            this.y = y;
            this.door = door;
        }
        @Override
        public int compareTo(FindWay o){
            return Integer.compare(this.door,o.door);
        }
    }
}