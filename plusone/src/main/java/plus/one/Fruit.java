package plus.one;

/**
 *
 * @author weifw
 * @date 2019-12-22-20:51
 */
public class Fruit extends Apple{

    static String subStaticField ="子类静态变量";

    static {
        System.out.println("子类静态代码块");
    }

    {
        System.out.println("子类代码块");
    }

    public Fruit() {
        System.out.println("子类构造器");
    }
}
