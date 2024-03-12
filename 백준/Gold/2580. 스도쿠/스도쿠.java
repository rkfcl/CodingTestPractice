import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] sudoku = new int[9][9];
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        sb = new StringBuilder();
        dfs(0);

    }

    static void dfs(int row) {
        for (int i = row; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku[i][j]==0){
                    for (int num = 1; num <= 9; num++) {
                        if (check(num,i,j)){
                            sudoku[i][j] = num;
                            dfs(i);
                            sudoku[i][j] = 0;
                        }
                    }
                    return;
                }
            }
        }
        for (int[] i:sudoku){
            for (int j: i){
                sb.append(j).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
        System.exit(0);
    }

    static boolean check(int num, int x, int y) {
        for (int i = 0; i < 9; i++) {
            if (sudoku[i][y] == num || sudoku[x][i] == num) return false;
        }
        int row = (x / 3) * 3;
        int col = (y / 3) * 3;
        for (int i=row;i<row+3;i++){
            for (int j=col;j<col+3;j++){
                if (sudoku[i][j]==num) return false;
            }
        }
        return true;
    }
}