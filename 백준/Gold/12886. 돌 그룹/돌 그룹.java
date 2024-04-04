import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] visited = new boolean[1501][1501];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        System.out.println(bfs(A, B, C) ? 1 : 0);
    }

    static boolean bfs(int A, int B, int C) {
        if ((A+B+C)%3 !=0) return false;
        Queue<Stone> q = new LinkedList<>();
        q.offer(new Stone(A, B, C));

        while (!q.isEmpty()) {
            Stone stone = q.poll();
            int a = stone.a;
            int b = stone.b;
            int c = stone.c;
            if (a == b && b == c) {
                return true;
            }
            if (a != b) {
                int na = a > b ? a - b : a + a;
                int nb = a > b ? b + b : b - a;
                if (!visited[na][nb]) {
                    visited[na][nb] = true;
                    q.offer(new Stone(na, nb, c));
                }
            }
            if (a != c) {
                int na = a > c ? a - c : a + a;
                int nc = a > c ? c + c : c - a;
                if (!visited[na][nc]) {
                    visited[na][nc] = true;
                    q.offer(new Stone(na, b, nc));
                }
            }
            if (b != c) {
                int nb = b > c ? b - c : b + b;
                int nc = b > c ? c + c : c - b;
                if (!visited[nb][nc]) {
                    visited[nb][nc] = true;
                    q.offer(new Stone(a, nb, nc));
                }
            }
        }
        return false;
    }

    static class Stone {
        int a, b, c;

        public Stone(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
}