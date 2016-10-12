package edu.technopolis.homework;

/**
 * Matrix multiplication home task.
 * <br/>
 * Matrix dimension and elements are passed as CLI arguments.
 */
public class MatrixMultiplication {

    private int[][] A;
    private int[][] B;
    final private int n;
    final private int m;
    final private int x;
    final private int y;

    MatrixMultiplication(String[] args){
        n = Integer.parseInt(args[0]);
        m = Integer.parseInt(args[1]);
        x = Integer.parseInt(args[2]);
        y = Integer.parseInt(args[3]);
        String temp = check(args);
        if (!temp.equals("ok")){
            System.out.println(temp);
            System.exit(0);
        }
        int q = 4;
        A = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++, q++)
                A[i][j] = Integer.parseInt(args[q]);
        B = new int[x][y];
        for (int i = 0; i < x; i++)
            for (int j = 0; j < y; j++, q++)
                B[i][j] = Integer.parseInt(args[q]);
    }

    private String check(String[] args){
        if (args.length < (n*m + x*y))
            return "Not enough parameters";
        if (x < 0 || y < 0 || n < 0 || m < 0)
            return "Some parameters are negative";
        if (m != x)
            return "Sizes of the matrices are not equal";
        return "ok";
    }

    /*
    *  Use the transposed matrix to get the maximum benefit from the cache
    *  Get a access elements of the transposed matrix by rows
    *  Transposition and multiplication are combined into a single computational cycle
    *  Transposition wont be carried out for the whole of the matrix but only in the columns
    */
    private int[][] multiplication() {
        int[][] result = new int[n][y];
        try{
            int thatColumn[] = new int[x];
            for (int j = 0; j < y; j++) {
                for (int k = 0; k < m; k++)
                    thatColumn[k] = B[k][j];
                for (int i = 0; i < n; i++) {
                    int thisRow[] = A[i];
                    int sum = 0;
                    for (int k = 0; k < m; k++)
                        sum += thisRow[k] * thatColumn[k];
                    result[i][j] = sum;
                }
            }
        }catch (IndexOutOfBoundsException exception){}
        return result;
    }

    private void print(int[][] temp, int n, int m){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                System.out.print(temp[i][j] + " ");
            System.out.println();
        }
    }

    public static void main(String... args) {
        if (args.length < 4){
            System.out.println("Not enough parameters");
            return;
        }
        MatrixMultiplication matMul = new MatrixMultiplication(args);
        matMul.print(matMul.multiplication(),matMul.n,matMul.y);
    }
}
