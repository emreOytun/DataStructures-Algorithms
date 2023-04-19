/*
 * Consider two integer arrays A and B as follows:
 * 𝐴=[𝑎0, 𝑎1,…,𝑎𝑛−1] 
 * 𝐵=[𝑏0, 𝑏1,…,𝑏𝑚−1]
 * where 𝑛, 𝑚 ∈ 𝑍+.
 * Design a linear time algorithm to find the minimum value of 𝑎𝑖⋅𝑏𝑗 where 0≤𝑖<𝑛 and 0≤𝑗<𝑚 . 
 * 
 * 
 */

class FindMinimumProduct {

    int[] a;
    int[] b;

    public FindMinimumProduct(int[] a, int[] b) {
        this.a = a;
        this.b = b;
    }

    public int execute() {
        
        int min_a = a[0];
        int max_a = a[0];

        int min_b = b[0];
        int max_b = b[0];

        for (int i = 0; i < a.length; ++i) {
            if (a[i] < min_a) min_a = a[i];
            else if (a[i] > max_a) max_a = a[i]; 
        }

        
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < min_b) min_b = b[i];
            else if (b[i] > max_b) max_b = b[i]; 
        }

        int res1 = Math.min(min_a*min_b, min_a*max_b);
        int res2 = Math.min(max_a*min_b, max_a*max_b);
        return Math.min(res1, res2);
    }

    public static void main(String[] args) {
        int[] a = new int[] {-7, -5, -4};
        int[] b = new int[] {-10, 4, 5, 1, -2, 0};
        FindMinimumProduct findMinimumProduct = new FindMinimumProduct(a, b);
        System.out.println(findMinimumProduct.execute());
    }

}