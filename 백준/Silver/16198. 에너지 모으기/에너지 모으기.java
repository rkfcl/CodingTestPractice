import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main {
    static int N,Max = Integer.MIN_VALUE;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        dfs(list,0);
        System.out.println(Max);
    }

    static void dfs(List<Integer> list,int sum) {
        if (list.size()==2){
            Max = Math.max(Max,sum);
            return;
        }
        for (int i=1;i< list.size()-1;i++){
            int w1 = list.get(i-1);
            int w2 = list.get(i+1);
            int remove = list.remove(i);
            dfs(list,sum+ (w1*w2));
            list.add(i,remove);
        }
    }
}