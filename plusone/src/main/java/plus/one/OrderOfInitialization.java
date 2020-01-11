package plus.one;

/**
 *
 * @author weifw
 * @date 2019-12-22-18:21
 */
public class OrderOfInitialization {

    static {
        System.out.println("静态代码块");
    }

    static String staticField = "静态字段";

    {
        System.out.println("代码块");
    }

    public static void main(String[] args) {
        System.out.println("main方法");
        System.out.println(staticField);
    }

}
