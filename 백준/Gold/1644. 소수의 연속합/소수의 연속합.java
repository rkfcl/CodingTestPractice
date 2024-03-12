import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static boolean[] prime;
    static int result=0;
    static List<Integer> primeList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        primeList = new ArrayList<>();
        makePrime(N);
        sumOfPrime();
        System.out.println(result);
    }

    public static void sumOfPrime(){
        int start = 0;
        int end = 0;
        int sum = 0;
        int size = primeList.size();
        while (start<size){
            if (sum<N&&end<size){
                sum+=primeList.get(end++);
            }else if (sum>N){
                sum-=primeList.get(start++);
            }else if (sum==N){
                sum-=primeList.get(start++);
                result++;
            }else break;
        }
    }
    public static void makePrime(int n){
        prime = new boolean[n+1];
        prime[0] = prime[1] = true;

        for (int i=2;i<=Math.sqrt(n);i++){
            if (prime[i]==true){
                continue;
            }
            for (int j = i*i;j< prime.length;j=j+i){
                prime[j] = true;
            }
        }
        for (int i=0;i< prime.length;i++){
            if (prime[i]== false){
                primeList.add(i);
            }
        }
    }
}