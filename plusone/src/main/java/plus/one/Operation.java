package plus.one;

/**
 *
 * @author weifw
 * @date 2019-12-22-10:40
 */
public class Operation {

    private static int a  =1;
    static int c;

    public static void main(String[] args) {
        System.out.println(a);
        a++;
         addA(a);
        System.out.println(a);
        System.out.println("int "+c);
    }


    public  static int addA(int a){
        a++;
        a++;
        return a;
    }

}
