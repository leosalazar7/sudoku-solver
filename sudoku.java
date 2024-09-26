// Sudoku Solver (Backtracking)

import java.util.*;

public class sudoku {
    public static int grid[];
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long start = System.currentTimeMillis();

        int loop = scanner.nextInt();
        for (int k = 0; k < loop; k++) {

            // storing the board
            grid = new int[81];
            for (int i = 0; i < 81; i++)
                grid[i] = scanner.nextInt();

            boolean flag = checkBoard(grid);
            if (flag) perm(grid, 0);

            // Printing results
            System.out.println("Test Case: "+(k+1));
            if (flag) {
                for (int i = 0; i < 81; i++) {
                    if (i%9 == 0) System.out.println();
                    System.out.printf(grid[i]+" ");
                }
                System.out.println("\n");
            } else {
                System.out.println("\nNo solution possible.\n");
            }
        }
        long end = System.currentTimeMillis();
        System.out.println((end-start)+" ms");

        scanner.close();
    }

    public static boolean perm(int[] grid, int cur) {
        if (cur == 81) return true;
        if (grid[cur] != 0) return perm(grid, cur+1);

        for (int i = 0; i < 9; i++) {
            grid[cur] = i+1;
            if (!possible(grid, cur)) continue;
            if (!perm(grid, cur+1)) continue;
            else return true;
        }
        grid[cur] = 0;
        return false;
    }

    public static boolean possible(int[] grid, int cur) {
        return checkRow(grid, cur) & checkCol(grid, cur) & checkBox(grid, cur);
    }

    public static boolean checkRow(int[] grid, int cur) {
        boolean used[] = new boolean[9];
        int start = (cur/9)*9;
        for (int i = 0; i < 9; i++) {
            if (grid[start+i] == 0) continue;
            if (used[grid[start+i]-1]) return false;
            used[grid[start+i]-1] = true;
        }
        return true;
    }

    public static boolean checkCol(int[] grid, int cur) {
        boolean used[] = new boolean[9];
        int start = cur%9;
        for (int i = 0; i < 9; i++) {
            if (grid[start+(i*9)] == 0) continue;
            if (used[grid[start+(i*9)]-1]) return false;
            used[grid[start+(i*9)]-1] = true;
        }
        return true;
    }

    public static boolean checkBox(int[] grid, int cur) {
        int col = ((cur/9)/3);
        int row = ((cur%9)/3)*3;
        int start = 27*col+row;
        boolean used[] = new boolean[9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[start+(i*9)+j] == 0) continue;
                if (used[grid[start+(i*9)+j]-1]) return false;
                used[grid[start+(i*9)+j]-1] = true;
            }
        }
        return true;
    }

    public static void printBoard(int[] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                System.out.printf("%d ", grid[i*9+j]);
            System.out.println();
        }
        System.out.println();
    }

    public static boolean checkBoard(int[] grid) {
        boolean flag = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                flag &= checkRow(grid, (i*3+j)*9) & checkCol(grid, (i*3+j)) & checkBox(grid, (i*27+j*3));
            }
            if (!flag) return false;
        }
        return flag;
    }
}