import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int N;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int[] cur = null;
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                if (num == 9) {
                    cur = new int[]{i, j};
                    map[i][j] = 0;
                }
            }
        }
        int size = 2;
        int eat = 0;
        int move = 0;
        while (true) {
            PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) ->
                    o1[2] != o2[2] ? Integer.compare(o1[2], o2[2]) : (o1[0] != o2[0] ? Integer.compare(o1[0], o2[0]) : Integer.compare(o1[1], o2[1]))
            ); // 거리가 다르면 거리 비교 거리가 같으면 가장 위에 있는 순 오름차순 이것도 같다면 맨 왼쪽부터 오름차순 정렬
            boolean[][] visit = new boolean[N][N];
            q.add(new int[]{cur[0], cur[1], 0});
            visit[cur[0]][cur[1]] = true;
            boolean ck = false; //상머 먹이 체크
            while (!q.isEmpty()) {
                cur = q.poll();
                if (map[cur[0]][cur[1]] != 0 && map[cur[0]][cur[1]] < size) {
                    map[cur[0]][cur[1]] = 0; //물고기 먹기
                    eat++;
                    move += cur[2];
                    ck = true;
                    break;
                }
                for (int i = 0; i < 4; i++) {
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];
                    if (ny < 0 || nx < 0 || nx >= N || ny >= N || visit[nx][ny] || map[nx][ny] > size) {
                        continue;
                    }
                    q.add(new int[]{nx, ny, cur[2] + 1});
                    visit[nx][ny] = true;
                }
            }
            if (!ck) break;
            if (size == eat) {
                size++;
                eat = 0;
            }
        }
        System.out.println(move);
    }
}