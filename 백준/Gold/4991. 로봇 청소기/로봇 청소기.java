
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;


public class Main {
    static int w, h;
    static char[][] room;
    static List<Point> list;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if (w == 0 && h == 0) break;
            room = new char[h][w];
            list = new ArrayList<>();
            for (int i = 0; i < h; i++) {
                String str = br.readLine();
                for (int j = 0; j < w; j++) {
                    room[i][j] = str.charAt(j);
                    if (str.charAt(j) == 'o') list.add(0,new Point(i, j));
                    else if (str.charAt(j) == '*') list.add(new Point(i, j));
                }
            }
            result = Integer.MAX_VALUE;
            getMinimumMoves();
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static void getMinimumMoves() {
        int dust_cnt = list.size();
        if (dust_cnt == 0) result = -1;
        int[][] distance = new int[dust_cnt][dust_cnt];
        for (int i = 0; i < dust_cnt; i++) {
            if (!Bfs(list.get(i), i, distance)) result = -1;
        }
        boolean[] visited = new boolean[dust_cnt];
        getPermutation(dust_cnt, distance,1,0,visited,0);
    }

    private static void getPermutation(int dust_cnt, int[][] distance,int depth,int sum,boolean[] visited,int src) {
        if (depth == dust_cnt){
            result = Math.min(result,sum);
            return;
        }
        for (int i = 1;i<dust_cnt;i++){
            if (!visited[i]){
                visited[i] = true;
                getPermutation(dust_cnt, distance, depth + 1, sum + distance[src][i], visited, i);
                visited[i] = false;
            }
        }
    }

    private static boolean Bfs(Point src, int index, int[][] distance) {
        boolean[][] visited = new boolean[h][w];
        Queue<Point> q = new LinkedList<>();
        q.offer(src);
        int[][] dist = new int[h][w];
        visited[src.x][src.y] = true;
        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < h && ny < w && !visited[nx][ny] && room[nx][ny] != 'x') {
                    visited[nx][ny] = true;
                    dist[nx][ny] = dist[p.x][p.y] + 1;
                    q.offer(new Point(nx, ny));
                }
            }
        }
        for (int i = 0; i < list.size(); i++) {
            Point dust = list.get(i);
            if (!visited[dust.x][dust.y]) return false;
            distance[index][i] = dist[dust.x][dust.y];
        }
        return true;
    }
}