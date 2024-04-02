import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = {-2,-2,0,0,2,2};
    static int[] dy = {-1,1,-2,2,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        visited = new boolean[N][N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        System.out.println(bfs(r,c,x,y));
    }
    static int bfs(int r,int c,int x,int y){
        Queue<Dot> q = new LinkedList<>();
        q.offer(new Dot(r,c,0));
        int cnt = -1;
        while (!q.isEmpty()){
            Dot point = q.poll();
            for (int i=0;i<6;i++){
                int nr = point.x+dx[i];
                int nc = point.y+dy[i];
                if (nr==x&&nc==y) return point.cnt+1;
                if (check(nr,nc) && !visited[nr][nc]){
                    q.offer(new Dot(nr,nc, point.cnt+1));
                    visited[nr][nc] = true;
                }
            }
        }
        return cnt;
    }
    static boolean check(int x,int y){
        return x>=0 && y>=0 && x < N && y < N;
    }
    static class Dot{
        int x,y,cnt;

        public Dot(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getCnt() {
            return cnt;
        }

        public void setCnt(int cnt) {
            this.cnt = cnt;
        }
    }
}