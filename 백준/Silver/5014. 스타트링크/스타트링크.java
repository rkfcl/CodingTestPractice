import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int F, S, G, U, D;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        visited = new boolean[1000001];
        bfs();
    }
    static void bfs(){
        int[] move = {U,-D};
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(S,0));
        while (!q.isEmpty()){
            Point point = q.poll();
            int floor = point.x;
            if (floor == G){
                System.out.println(point.y);
                return;
            }
            for (int i=0;i<2;i++){
                int nFloor = floor + move[i];
                if (nFloor < 1 || nFloor > F) continue;
                if (visited[nFloor]) continue;
                q.offer(new Point(nFloor, point.y + 1));
                visited[nFloor] = true;
            }
        }
        System.out.println("use the stairs");
    }
}