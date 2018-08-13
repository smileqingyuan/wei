package win.smile;

/**
 * 斐波那契数列
 * F0=0，F1=1，Fn=Fn-1+Fn-2（n>=2，n∈N*），
 * 用文字来说，就是斐波那契数列由 0 和 1 开始，之后的斐波那契数列系数就由之前的两数相加。
 */
public class Fibonacci {

    public static void main(String[] args) {

        int n = 100;
        System.out.println(arrFibonacci(n));
        System.out.println(recursionFibonacci(n));

    }


    /**
     * 递归方式实现 - 不建议 --效率很慢，很占内存，没有具体算数字，但是n=100时已经很慢很慢了
     *
     * @param n n表示第几个数
     * @return
     */
    public static int recursionFibonacci(int n) {

        if (n < 0) {
            return -1;
        } else if (n == 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        } else {
            return recursionFibonacci(n - 1) + recursionFibonacci(n - 2);
        }

    }

    /**
     * 数组形式
     * @param n
     * @return
     */
    public static long arrFibonacci(int n) {

        if (n < 0) {
            return -1;
        } else if (n == 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        } else {
            /*斐波那契数列的n从0开始*/
            long fibArr[] = new long[n + 1];
            fibArr[0] = 0;
            fibArr[1] = 1;
            for (int i = 2; i <= n; i++) {
                fibArr[i] = fibArr[i - 1] + fibArr[i - 2];
            }
            return fibArr[fibArr.length - 1];
        }
    }


}


