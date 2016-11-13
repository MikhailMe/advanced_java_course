/**
 * Matrix multiplication home task.
 * <br/>
 * Matrix dimension and elements are passed as CLI arguments.
 */
public class MatrixMultiplication {

    private long[][] A;
    private long[][] B;
    private int n;
    private int m;
    private int x;
    private int y;

    MatrixMultiplication(String[] args) {
        try {
            n = Integer.parseInt(args[0]);
            m = Integer.parseInt(args[1]);
            x = Integer.parseInt(args[2]);
            y = Integer.parseInt(args[3]);
        } catch (NumberFormatException e) {
            System.out.println("Error number format!");
            System.exit(-1);
        }
        String temp = check(args);
        if (!temp.equals("ok")) {
            System.out.println(temp);
            System.exit(0);
        }
        int q = 4;
        A = new long[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++, q++)
                try {
                    A[i][j] = Long.parseLong(args[q]);
                } catch (NumberFormatException e) {
                    help();
                    System.out.println("Your mistake: Error number format!");
                    System.exit(-1);
                }
        B = new long[x][y];
        for (int i = 0; i < x; i++)
            for (int j = 0; j < y; j++, q++)
                try {
                    B[i][j] = Long.parseLong(args[q]);
                } catch (NumberFormatException e) {
                    help();
                    System.out.println("Your mistake: Error number format!");
                    System.exit(-1);
                }
    }

    private String check(String[] args) {
        if (args.length - 4 < (n * m + x * y)) {
            help();
            return "Your mistake: Not enough parameters";
        }
        if (x <= 0 || y <= 0 || n <= 0 || m <= 0) {
            help();
            return "Your mistake: Some parameters are negative or 0";
        }
        if (m != x) {
            help();
            return "Your mistake: Sizes of the matrices are not equal";
        }
        return "ok";
    }

    /*
    *  Use the transposed matrix to get the maximum benefit from the cache
    *  Get a access elements of the transposed matrix by rows
    *  Transposition and multiplication are combined into a single computational cycle
    *  Transposition wont be carried out for the whole of the matrix but only in the columns
    */
    private long[][] multiplication() {
        long[][] result = new long[n][y];
        try {
            long thatColumn[] = new long[x];
            for (int j = 0; j < y; j++) {
                for (int k = 0; k < m; k++)
                    thatColumn[k] = B[k][j];
                for (int i = 0; i < n; i++) {
                    long thisRow[] = A[i];
                    int sum = 0;
                    for (int k = 0; k < m; k++)
                        sum += thisRow[k] * thatColumn[k];
                    result[i][j] = sum;
                }
            }
        } catch (IndexOutOfBoundsException exception) {
        }
        return result;
    }

    private void print(long[][] temp, int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                System.out.print(temp[i][j] + " ");
            System.out.println();
        }
    }

    private static void help(){
        System.out.println("Welcome to HELP of the matrix multiply");
        System.out.println("Your input must have only digits");
        System.out.println("This is example of the input : N M X Y A_1_1 ... A_N_M B_1_1 ... B_X_Y");
        System.out.println("N и M - dimension first matrix A");
        System.out.println("A_1_1 ... A_N_M - elements of the matrix A");
        System.out.println("X и Y - dimension second matrix B");
        System.out.println("B_1_1 ... B_X_Y - elements of the matrix B");
        System.out.println("So, if you understand all rules then you might try again!");
    }

    public static void main(String... args) {
        if (args.length < 4) {
            System.out.println("Your mistake: Not enough parameters");
            help();
            return;
        }
        MatrixMultiplication matMul = new MatrixMultiplication(args);
        matMul.print(matMul.multiplication(), matMul.n, matMul.y);
    }
}