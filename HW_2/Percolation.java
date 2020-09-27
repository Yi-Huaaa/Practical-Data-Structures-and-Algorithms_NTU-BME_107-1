import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
    //n-matrix
    private int n;
    //store the points
    private boolean[] matrix;
    private WeightedQuickUnionUF con_1 ;
    private int OpenSites;
    private boolean perco = false;
    private int p2p;

    private int index(int row, int col) {
        if (col <= 0 || col > n || row <= 0 || row > n ) {
            throw new IllegalArgumentException("check coordinates");
        }
        return (row - 1) * n + col;
    }

    //todo 1
    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        //先確認n是不是合理
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        p2p = n*n;
        matrix = new boolean[p2p + 1];
        con_1 = new WeightedQuickUnionUF(p2p + 1);
    }

    //todo 2
    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        //Throw a java.lang.IllegalArgumentException if any argument to open(),
        // isOpen(), or isFull() is outside its prescribed range.
        if (col <= 0 || col > n || row <= 0 || row > n ) {
            throw new IllegalArgumentException("check coordinates");
        }
        if (isOpen(row, col)) {
            return;
        }
        matrix[index(row, col)] = true;
        // 將上面牽起來
        if (row == 1) {
            matrix[0] = true;
            con_1.union(index(row, col), 0);
        }
        //backwash issue.
        //上
        if (row > 1 && isOpen(row - 1, col)) {
            con_1.union(index(row, col), index(row - 1, col));
        }
        //下
        if (row < n && isOpen(row + 1, col)) {
            con_1.union(index(row, col), index(row + 1, col));
        }
        //左
        if (col > 1 && isOpen(row, col - 1)) {
            con_1.union(index(row, col), index(row, col - 1));
        }
        //右
        if (col < n && isOpen(row, col + 1)) {
            con_1.union(index(row, col), index(row, col + 1));
        }
        OpenSites++;
    }



    //todo 3
    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        //Throw a java.lang.IllegalArgumentException if any argument to open(),
        // isOpen(), or isFull() is outside its prescribed range.
        if (col <= 0 || col > n || row <= 0 || row > n ) {
            throw new IllegalArgumentException();
        }
        return matrix [index(row, col)];
    }

    //todo 4 okok
    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        if (col <= 0 || col > n || row <= 0 || row > n ) {
            throw new IllegalArgumentException();
        }
        return con_1.connected(index(row,col), 0);
    }


    //todo 5 okok
    // number of open sites
    public int numberOfOpenSites() {
        return OpenSites;
    }


    //todo 6
    //does the system percolate?
    //只檢查最後一列
    public boolean percolates() {
        if(perco == false)
            for(int i = 1; i <= n; i++)
                if(con_1.connected(index(n, i), 0))
                {
                    perco = true;
                    break;
                }
        return perco;
    }
    // for test
    public static void main(String[] args){
         // int n = 1;
         // Percolation p = new Percolation(n);
         // while (!p.percolates()) {
         //     int i = StdRandom.uniform(1, n + 1);
         //     int j = StdRandom.uniform(1, n + 1);
         //     if (!p.isOpen(i, j)) {
         //         p.open(i, j);
         //         System.out.println("open (" + i + "," + j + "), isFull::" + p.isFull(i, j));
         //     }
         //
         //     //System.out.println();
         // }
    }
}