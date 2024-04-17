import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int N, M;
    static int[][] map, group;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Map<Integer, Integer> board = new HashMap<>();
    static Set<Integer> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        group = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }
        int gId = 2;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    board.put(gId, bfs(i, j, gId));
                    gId++;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    set = new HashSet<>();
                    int cnt = 1;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (check(nx, ny) && map[nx][ny] != 1) {
                            set.add(map[nx][ny]);
                        }
                    }
                    for (int num : set){
                        cnt +=board.get(num);
                    }
                    sb.append(cnt%10);
                } else sb.append(0);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int bfs(int x, int y, int gId) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y));
        map[x][y] = gId;
        int cnt = 1;
        while (!q.isEmpty()) {
            Point point = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];
                if (check(nx, ny) && map[nx][ny] == 0) {
                    q.offer(new Point(nx, ny));
                    map[nx][ny] = gId;
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static boolean check(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
}