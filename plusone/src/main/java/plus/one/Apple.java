package plus.one;

import java.util.PriorityQueue;

/**
 *
 * @author weifw
 * @date 2019-12-22-20:51
 */
public class Apple {

   public static String staticField = "静态变量";

   public String field = "普通变量";

   static {
        System.out.println(staticField);
       System.out.println("静态代码块");
       String a = "b"+"c";
   }

   {
       System.out.println(field);
       System.out.println("非静态代码块");
   }

    public Apple() {
        System.out.println("构造器代码块");
    }

    public static void main(String[] args) {
        System.out.println("父类中的main方法");
        new Fruit();
    }
}
