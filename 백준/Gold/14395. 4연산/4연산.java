import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static long S, T;
    static Set<Long> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = Long.parseLong(st.nextToken());
        T = Long.parseLong(st.nextToken());
        if (S == T) {
            System.out.println(0);
        } else {
            System.out.println(bfs());
        }
    }

    static String bfs() {
        Queue<Cal> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        q.offer(new Cal(S, sb));
        while (!q.isEmpty()) {
            Cal cal = q.poll();
            long s = cal.s;
            sb = cal.sb;
            if (s == T) {
                return sb.toString();
            }
            for (int i = 0; i < 4; i++) {
                long ns = 0;
                StringBuilder nsb = new StringBuilder();
                nsb.append(sb);
                switch (i) {
                    case 0:
                        ns = s * s;
                        if (!set.contains(ns)){
                            nsb.append('*');
                            set.add(ns);
                            q.offer(new Cal(ns, nsb));
                        }
                        break;
                    case 1:
                        ns = s + s;
                        if (!set.contains(ns)){
                            nsb.append('+');
                            set.add(ns);
                            q.offer(new Cal(ns, nsb));
                        }
                        break;
                    case 2:
                        ns = s - s;
                        if (!set.contains(ns)){
                            nsb.append('-');
                            set.add(ns);
                            q.offer(new Cal(ns, nsb));
                        }
                        break;
                    case 3:
                        if (s != 0) {
                            ns = s / s;
                            if (!set.contains(ns)){
                                nsb.append('/');
                                set.add(ns);
                                q.offer(new Cal(ns, nsb));
                            }
                        }
                        break;
                }
            }
        }
        return "-1";
    }

    static class Cal {
        long s;
        StringBuilder sb;

        public Cal(long s, StringBuilder sb) {
            this.s = s;
            this.sb = sb;
        }
    }
}